package com.nwo.prodigy.care4project;

import static com.nwo.prodigy.care4project.Constantes.*;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Login extends AppCompatActivity {
    EditText editUsername;
    EditText editPassword;
    String textUserName;
    String textPassword;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                editUsername = (EditText) findViewById(R.id.editLoginUsername);
                editPassword = (EditText) findViewById(R.id.editLoginPassword);
    }


    public void login(View view) {
         textUserName = editUsername.getText().toString();
         textPassword = editPassword.getText().toString();
        if(VerificateurEntreeText.isTextEmpty(textUserName,textPassword)){
            Toast.makeText(this,"Veuillez entre tous les champs S.V.P",Toast.LENGTH_LONG).show();
            return;
        }
    }

    public void creeCompte(View view) {
        Intent creeComteIntent = new Intent(this,CreeNouveauCompte.class);
        startActivity(creeComteIntent);
    }

    public void login(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        XMLDOMParser parser = new XMLDOMParser();
        ArrayList<NameValuePair> donnees = new ArrayList<NameValuePair>();
        donnees.add(new BasicNameValuePair(ACTION, ACTION_LOGIN));
        donnees.add(new BasicNameValuePair(NODE_USERNAME, ACTION_LOGIN));
        donnees.add(new BasicNameValuePair(NODE_PASSWORD, ACTION_LOGIN));
        try {
            InputStream is = null;
            String resultat = "";
            String chaineRetour = "";

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(STR_URL);
            httppost.setEntity(new UrlEncodedFormEntity(donnees));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"utf-8"),8);
                StringBuilder sb = new StringBuilder();
                String ligne;
                while ((ligne = reader.readLine()) != null) {
                    sb.append(ligne + "\n");
                }
                is.close();
                resultat=sb.toString();
            }catch(Exception e){
                Toast.makeText(getBaseContext(),
                        "Erreur de conversion de resultat ", Toast.LENGTH_LONG).show();
            }
            Document doc = parser.getDocument(resultat);
            NodeList nodeList = doc.getElementsByTagName(NODE_PROFIL);

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element e = (Element) nodeList.item(i);
               if(VerificateurEntreeText.isTextEmpty(parser.getValue(e,NODE_USERNAME))){
                   Toast.makeText(this,"Login ou username incorrect",Toast.LENGTH_LONG).show();
               }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
