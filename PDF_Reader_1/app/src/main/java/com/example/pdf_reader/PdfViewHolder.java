package com.example.pdf_reader;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class PdfViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;
    public CardView container;

    public PdfViewHolder(@NonNull View itemView) {
        super(itemView);

        tvName = itemView.findViewById(R.id.textPdfName);
        container = itemView.findViewById(R.id.container);
    }
}
