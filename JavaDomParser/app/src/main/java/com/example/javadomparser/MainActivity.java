package com.example.javadomparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    TextView textview;
    Document doc = null;
    LinearLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.textView1);
    }

    public void onClick(View view) {
        GetXMLTask task = new GetXMLTask();
        task.execute("http://www.kma.go.kr/wid/queryDFS.jsp?gridx=61&gridy=125");
    }

    private class GetXMLTask extends AsyncTask<String, Void, Document> {

        @Override
        protected Document doInBackground(String... strings) {
            URL url;
            try {
                url = new URL(strings[0]);
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                doc = documentBuilder.parse(new InputSource(url.openStream()));
                doc.getDocumentElement().normalize();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return doc;
        }

        @Override
        protected void onPostExecute(Document document) {

            StringBuilder s = new StringBuilder();
            NodeList nodeList = doc.getElementsByTagName("data");

            for(int i = 0; i< nodeList.getLength(); i++) {
                s.append("").append(i).append(": 날씨 정보");
                Node node = nodeList.item(i);

                if(node != null) {
                    Element fstElmnt = (Element) node;
                    NodeList nameList = fstElmnt.getElementsByTagName("temp");
                    Element nameElement = (Element) nameList.item(0);
                    nameList = nameElement.getChildNodes();
                    s.append("온도 =").append(nameList.item(0).getNodeValue()).append(",");

                    NodeList webSiteList = fstElmnt.getElementsByTagName("wfKor");
                    s.append("날씨 =").append(webSiteList.item(0).getChildNodes().item(0).getNodeValue()).append("\n");
                }
            }
            textview.setText(s);
            super.onPostExecute(doc);
        }
    }
}