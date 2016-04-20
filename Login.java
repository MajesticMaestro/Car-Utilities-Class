package com.nwo.prodigy.care4project;

import static com.nwo.prodigy.care4project.Constantes.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class Login extends AppCompatActivity {
    EditText editUsername;
    EditText editPassword;
    String textUserName;
    String textPassword;
    ConnectionPHP connectionPHP;
    private SharedPreferences preferences;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_login);
                editUsername = (EditText) findViewById(R.id.editLoginUsername);
                editPassword = (EditText) findViewById(R.id.editLoginPassword);

                preferences = getPreferences(MODE_PRIVATE);

                connectionPHP = new ConnectionPHP();


    }


    public void login(View view) {
         textUserName = editUsername.getText().toString();
         textPassword = editPassword.getText().toString();
        if(VerificateurEntreeText.isTextEmpty(textUserName,textPassword)){
            Toast.makeText(this,"Veuillez entre tous les champs S.V.P",Toast.LENGTH_LONG).show();
            return;
        }
        loginAcount();
    }

    public void creeCompte(View view) {
        Intent creeComteIntent = new Intent(this,CreeNouveauCompte.class);
        startActivity(creeComteIntent);
    }

    public void loginAcount(){
        connectionPHP.setAction(ACTION_LOGIN);
        connectionPHP.addNomUtilisateur(editUsername);
        connectionPHP.addPassword(editPassword);
        connectionPHP.sendRequest(DEBUG_STR_URL);
        String data = connectionPHP.getResponse();


        Toast.makeText(this,"" + data,Toast.LENGTH_SHORT).show();
        if (!data.isEmpty()){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(NODE_USERNAME,textUserName);
            editor.putString(NODE_PASSWORD,textPassword);
            editor.putString(NODE_TYPE_UTILISATEUR,data);

            if (data.equalsIgnoreCase(TYPE_ASSISTANT)){
                Intent intent;/* = new Intent(this,Assistant.class);*/



                Toast.makeText(this,"Vous est un assistant",Toast.LENGTH_LONG).show();
            }
            else if (data.equalsIgnoreCase(TYPE_ASSISTEE)) {
                Toast.makeText(this,"Vous est un assistee",Toast.LENGTH_LONG).show();
                Intent intent;//
            }

        }


    }
}
