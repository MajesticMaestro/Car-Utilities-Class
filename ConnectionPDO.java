package com.nwo.prodigy.care4project;

import android.content.Context;
import android.os.StrictMode;
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
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.nwo.prodigy.care4project.Constantes.*;


/**
 * Created by Prodigy on 16-04-08.
 */
public class ConnectionPDO {
    static final String NODE_REPONSE = "";

    String Action;
    Context context;
    ArrayList<NameValuePair> donnees;
    HttpResponse response;

    public ConnectionPDO(Context context){
        this.context = context;
        donnees = new ArrayList<>();
    }

    public void setAction(String action){
        Action = action;
    }

    public void addNom(EditText editNom){

      if (!VerificateurEntreeText.isTextEmpty(editNom)) {
            donnees.add(new BasicNameValuePair(NODE_NOM, editNom.getText().toString()));
        }
    }
    public void addPrenom(EditText editPrenom){

        if (!VerificateurEntreeText.isTextEmpty(editPrenom)) {
            donnees.add(new BasicNameValuePair(NODE_PRENOM, editPrenom.getText().toString()));
        }
    }
    public void addNomUtilisateur(EditText editNomUtilisateur) {
        if (!VerificateurEntreeText.isTextEmpty(editNomUtilisateur)){
            donnees.add(new BasicNameValuePair(NODE_USERNAME, editNomUtilisateur.getText().toString().toLowerCase()));
        }
    }
    public void addEmail(EditText editEmail){
        if (!VerificateurEntreeText.isTextEmpty(editEmail)) {
            donnees.add(new BasicNameValuePair(NODE_EMAIL, editEmail.getText().toString().toLowerCase()));
        }
    }
    public void addTelephone(EditText editTelephone){

        if (!VerificateurEntreeText.isTextEmpty(editTelephone)) {
            donnees.add(new BasicNameValuePair(NODE_TELEPHONE, editTelephone.getText().toString()));
        }
    }
    public void addPassword(EditText editPassword){

        if (!VerificateurEntreeText.isTextEmpty(editPassword)) {
            donnees.add(new BasicNameValuePair(NODE_PASSWORD, editPassword.getText().toString()));
        }
    }
    public void addMessage(EditText editMessage){

        if (!VerificateurEntreeText.isTextEmpty(editMessage)) {
            donnees.add(new BasicNameValuePair(NODE_MESSAGE, editMessage.getText().toString()));
        }
    }
    public void addTypeUtilistateur(String type){
        if (!VerificateurEntreeText.isTextEmpty(type)) {
            donnees.add(new BasicNameValuePair(NODE_TYPE_UTILISATEUR, type));
        }
    }
    public void addMessage(String message){

        if (!VerificateurEntreeText.isTextEmpty(message)) {
            donnees.add(new BasicNameValuePair(NODE_MESSAGE, message));
        }
    }

    public void addNomUtilisateur(String nomUtilisateur){

        if (!VerificateurEntreeText.isTextEmpty(nomUtilisateur)) {
            donnees.add(new BasicNameValuePair(NODE_USERNAME, nomUtilisateur.toLowerCase()));
        }
    }
    public void addPassword(String password){
        if (!VerificateurEntreeText.isTextEmpty(password)) {
            donnees.add(new BasicNameValuePair(NODE_PASSWORD, password));
        }
    }



    public  void sendRequest(String URL){
        if(Action.isEmpty()){
            return;
        }
        donnees.add(new BasicNameValuePair(ACTION,Action));
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(URL);
            httppost.setEntity(new UrlEncodedFormEntity(donnees));
            response = httpclient.execute(httppost);


        } catch (Exception e) {
            Toast.makeText(context, "Erreur lors de la connexion ", Toast.LENGTH_LONG).show();
            return;
        }
    }

    //TODO
    public void getResponse(){
        XMLDOMParser parser = new XMLDOMParser();
        InputStream is = null;
        String resultat = "";
        HttpEntity entity = response.getEntity();
        try {
            is = entity.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        }
        Document doc = parser.getDocument(resultat);
        NodeList nodeList = doc.getElementsByTagName(getTagName());








    }
    public void clearNameValuePairArray(){
        donnees.clear();
    }

    public void loginRequest(EditText editUsername, EditText editpassword){
        addNomUtilisateur(editUsername);
        addPassword(editpassword);

    }
    private String getTagName (){
        String tagName = "";
        switch (Action){
            case ACTION_LOGIN:
                tagName = "";
                break;
            case ACTION_CREE_COMPTE:
                tagName = "";
                break;
            case ACTION_UPDATE_PROFIL:
                tagName = "";
                break;


        }
        return tagName;
    }


}
