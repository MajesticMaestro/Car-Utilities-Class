package com.nwo.prodigy.care4project;

import android.content.Context;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import static com.nwo.prodigy.care4project.Constantes.*;


/**
 * Created by Prodigy on 16-04-08.
 */
public class ConnectionPDO {
    Context context;
    ArrayList<NameValuePair> donnees;
    public ConnectionPDO(Context context){
        this.context = context;
        donnees = new ArrayList<>();
    }

    public void addNom(EditText editNom){
        donnees.add(new BasicNameValuePair(NODE_NOM,editNom.getText().toString()));
    }
    public void addPrenom(EditText editPrenom){
        donnees.add(new BasicNameValuePair(NODE_PRENOM,editPrenom.getText().toString()));
    }
    public void addNomUtilisateur(EditText editNomUtilisateur){
        donnees.add(new BasicNameValuePair(NODE_USERNAME,editNomUtilisateur.getText().toString().toLowerCase()));
    }
    public void addEmail(EditText editEmail){
        donnees.add(new BasicNameValuePair(NODE_EMAIL,editEmail.getText().toString().toLowerCase()));
    }
    public void addTelephone(EditText editTelephone){
        donnees.add(new BasicNameValuePair(NODE_TELEPHONE,editTelephone.getText().toString()));
    }
    public void addPassword(EditText editPassword){
        donnees.add(new BasicNameValuePair(NODE_PASSWORD,editPassword.getText().toString()));
    }
    public void addTypeUtilistateur(String type){
        donnees.add(new BasicNameValuePair(NODE_TYPE_UTILISATEUR,type));
    }



    public  void sendRequest(){
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(STR_URL);
            httppost.setEntity(new UrlEncodedFormEntity(donnees));
            httpclient.execute(httppost);
        } catch (Exception e) {
            Toast.makeText(context, "Erreur lors de la connexion ", Toast.LENGTH_LONG).show();
            return;
        }

    }
    public void clearNameValuePairArray(){
        donnees.clear();
    }

    public void loginRequest(EditText editUsername, EditText editpassword){
        addNomUtilisateur(editUsername);
        addPassword(editpassword);

    }


}
