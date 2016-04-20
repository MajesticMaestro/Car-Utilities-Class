package com.nwo.prodigy.care4project;

import android.os.StrictMode;
import android.widget.EditText;

import static com.nwo.prodigy.care4project.Constantes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Prodigy on 16-04-14.
 */
public class ConnectionPHP {
    URL url;
    String data;

    HttpURLConnection connection;
    BufferedReader reader;
    HashMap<String,String> nameValuepair;


    public ConnectionPHP(){
        nameValuepair = new HashMap<>();
        data = "";
        reader = null;
    }

    public void clearNameValuePair(){
        nameValuepair.clear();
    }

    public void addNom(EditText editNom){
        if (!VerificateurEntreeText.isTextEmpty(editNom)) {
            nameValuepair.put(NODE_NOM, editNom.getText().toString());
        }
    }

    public void addPrenom(EditText editPrenom){
        if (!VerificateurEntreeText.isTextEmpty(editPrenom)) {
            nameValuepair.put(NODE_PRENOM, editPrenom.getText().toString());
        }
    }

    public void addNomUtilisateur(EditText editNomUtilisateur){
        if (!VerificateurEntreeText.isTextEmpty(editNomUtilisateur)) {
            nameValuepair.put(NODE_USERNAME, editNomUtilisateur.getText().toString().toLowerCase());
        }
    }

    public void addEmail(EditText editEmail){
        if (!VerificateurEntreeText.isTextEmpty(editEmail)) {
            nameValuepair.put(NODE_EMAIL, editEmail.getText().toString().toLowerCase());
        }
    }

    public void addTelephone(EditText editTelephone){
        if (!VerificateurEntreeText.isTextEmpty(editTelephone)) {
            nameValuepair.put(NODE_TELEPHONE, editTelephone.getText().toString());
        }
    }

    public void addPassword(EditText editPassword){
        if (!VerificateurEntreeText.isTextEmpty(editPassword)) {
            nameValuepair.put(NODE_PASSWORD, editPassword.getText().toString());
        }
    }



    public void addTypeUtilistateur(String type){
        if (!VerificateurEntreeText.isTextEmpty(type)) {
            nameValuepair.put(NODE_TYPE_UTILISATEUR, type);
        }
    }


    //String variant
    public void addNom(String nom){
        if (!VerificateurEntreeText.isTextEmpty(nom)) {
            nameValuepair.put(NODE_NOM, nom);
        }
    }
    public void addPrenom(String prenom){
        if (!VerificateurEntreeText.isTextEmpty(prenom)) {
            nameValuepair.put(NODE_PRENOM, prenom);
        }
    }

    public void addTelephone(String telephone){
        if (!VerificateurEntreeText.isTextEmpty(telephone)) {
            nameValuepair.put(NODE_TELEPHONE, telephone);
        }
    }

    public void addNomUtilisateur(String nomUtilisateur){
        if (!VerificateurEntreeText.isTextEmpty(nomUtilisateur)) {
            nameValuepair.put(NODE_USERNAME, nomUtilisateur.toLowerCase());
        }
    }

    public void addEmail(String email){
        if (!VerificateurEntreeText.isTextEmpty(email)) {
            nameValuepair.put(NODE_EMAIL, email.toLowerCase());
        }
    }

    public void addPassword(String password){
        if (!VerificateurEntreeText.isTextEmpty(password)) {
            nameValuepair.put(NODE_PASSWORD, password);
        }
    }



    private void setUrl(String srtURL){
        try {
            url = new URL(srtURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public  void setAction(String action){
        nameValuepair.put(ACTION,action);
    }

    private String convertMapToURLPair(){
        StringBuilder sb = new StringBuilder();
        String dataFromMap = "";
        Iterator iterator = nameValuepair.entrySet().iterator();
        Map.Entry pair;
        boolean hasLessThan1Pair = true;
        while (iterator.hasNext()){

            pair = (Map.Entry) iterator.next();
            try {
                if (hasLessThan1Pair) {
                    dataFromMap += URLEncoder.encode((String) pair.getKey(), "UTF-8") + "=" + URLEncoder.encode((String) pair.getValue(), "UTF-8");
                    hasLessThan1Pair = false;
                }
                else{
                    dataFromMap += "&" + URLEncoder.encode((String) pair.getKey(), "UTF-8") + "=" + URLEncoder.encode((String) pair.getValue(), "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dataFromMap;
    }


    public void sendRequest(String destinationURL){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

        setUrl(destinationURL);



        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
            System.out.println(convertMapToURLPair());
            wr.write(convertMapToURLPair());
            wr.flush();
        }
        catch (Exception ex){
        }
    }

    public String getResponse(){
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = null;
            String textFromServer;

            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line);
            }

        }

        catch (Exception ex){
        }
        finally {

            try {
                if (reader != null)
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }


}
