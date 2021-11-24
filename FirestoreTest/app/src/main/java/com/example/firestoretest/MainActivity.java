package com.example.firestoretest;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    final static int TAKE_PICTURE = 1;
    private final int GALLERY_CODE = 2;
    ImageView photo;
    Button save;
    int num = 0;
    boolean usingCamera = false;
    Uri selectedImageUri;

//    ImageView imgView;
//    TextView textView;
//    private FirebaseStorage storage;
//    private Intent photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photo = (ImageView) findViewById(R.id.picture);
        save = (Button) findViewById(R.id.save);

//        final FirebaseFirestore db = FirebaseFirestore.getInstance();
//        storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference();
//
//        imgView = findViewById(R.id.imageView);
//        textView = findViewById(R.id.textView2);
//
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_photo, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(dialogView);

                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button cameraBtn = dialogView.findViewById(R.id.photoBtn);
                Button albumBtn = dialogView.findViewById(R.id.galleryBtn);

                cameraBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        usingCamera = true;
                        num ++;
                        takePicture();
                        alertDialog.dismiss();
                    }
                });

                albumBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        usingCamera = false;
                        num ++;
                        loadAlbum();
                        alertDialog.dismiss();
                    }
                });

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
            }
        });
//        Map<String, String> user = new HashMap<>();
//        user.put("contents", String.valueOf(editInfo.getText()));

//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String mainText = ((EditText)findViewById(R.id.editInfo)).getText().toString();
//                Input input = new Input(mainText,"fsd", "gsdff");
//
//                db.collection("posts")
//                        .add(input)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.d(TAG, "Error!", e);
//                            }
//                        });
//            }
//        });

//        Button writeBtn = findViewById(R.id.read);
//        writeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                db.collection("posts")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                                        Log.d(TAG, documentSnapshot.getId() + "=> " + documentSnapshot.getData());
//                                    }
//                                } else {
//                                    Log.w(TAG, "Error!", task.getException());
//                                }
//                            }
//                        });
//            }
//        });
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, TAKE_PICTURE);
    }

    private void loadAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(!usingCamera) {
            if(requestCode == GALLERY_CODE) {
                if(resultCode == RESULT_OK && data.getData() != null) {
                    selectedImageUri = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(data.getData());
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        photo.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            if(requestCode == TAKE_PICTURE) {
                if(resultCode ==RESULT_OK && data.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if(bitmap != null) {
                        Bundle extras = data.getExtras();

                        Bitmap imageBitmap = (Bitmap) extras.get("data");

                        photo.setImageBitmap(imageBitmap);

                        String imageSaveUri = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "사진 저장", "사진 저장 완료!");
                        selectedImageUri = Uri.parse(imageSaveUri);
                    }
                }
            }
        }

    }

    public void uploadImage() {
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        String fileName = num + ".png";
        StorageReference storageReference = firebaseStorage.getReference("posts/" + fileName);
        UploadTask uploadTask = storageReference.putFile(selectedImageUri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(MainActivity.this, "성공!!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "실패...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(usingCamera) {
//            if (requestCode == 0 && resultCode == RESULT_OK) {
//
//                Bundle extras = data.getExtras();
//
//                Bitmap imageBitmap = (Bitmap) extras.get("data");
//
//                imgView.setImageBitmap(imageBitmap);
//            }
//        }else {
//            if(requestCode == 0) {
//                if(resultCode == RESULT_OK) {
//                    try {
//                        photoUri = data;
//                        InputStream in = getContentResolver().openInputStream(data.getData());
//
//                        Bitmap img = BitmapFactory.decodeStream(in);
//                        in.close();
//
//                        imgView.setImageBitmap(img);
//                    }catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                } else if (resultCode == RESULT_CANCELED) {
//                    Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//
//    }
}