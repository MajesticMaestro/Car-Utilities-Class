package com.nwo.prodigy.care4project;

import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import static com.nwo.prodigy.care4project.Constantes.ACTION;
import static com.nwo.prodigy.care4project.Constantes.ACTION_CREE_COMPTE;
import static com.nwo.prodigy.care4project.Constantes.ACTION_LOGIN;
import static com.nwo.prodigy.care4project.Constantes.DEBUG_STR_URL;
import static com.nwo.prodigy.care4project.Constantes.NODE_EMAIL;
import static com.nwo.prodigy.care4project.Constantes.NODE_NOM;
import static com.nwo.prodigy.care4project.Constantes.NODE_PASSWORD;
import static com.nwo.prodigy.care4project.Constantes.NODE_PRENOM;
import static com.nwo.prodigy.care4project.Constantes.NODE_TELEPHONE;
import static com.nwo.prodigy.care4project.Constantes.NODE_TYPE_UTILISATEUR;
import static com.nwo.prodigy.care4project.Constantes.NODE_USERNAME;
import static com.nwo.prodigy.care4project.Constantes.STR_URL;
import static com.nwo.prodigy.care4project.Constantes.TEST_STR_URL;
import static com.nwo.prodigy.care4project.Constantes.TYPE_ASSISTANT;
import static com.nwo.prodigy.care4project.Constantes.TYPE_ASSISTEE;

public class CreeNouveauCompte extends AppCompatActivity {

    RadioButton radioButtonAssistant;
    RelativeLayout layout;
    EditText editNom;
    EditText editPrenom;
    EditText editNomUtilisateur;
    EditText editEmail;
    EditText editEmailConfirmation;
    EditText editTelephone;
    EditText editMotDepasse;
    EditText editMotDepasseConfirmation;
    Button btnCreeCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cree_nouveau_compte);


       /* //Background
        final Drawable bg_Blue =  getResources().getDrawable(R.drawable.blue_flat_bg_01);
        final Drawable bg_Green = getResources().getDrawable(R.drawable.green_flat_bg_01);
        //Bouton
        final Drawable btnGreen = getResources().getDrawable(R.drawable.btn_cree_green);
        final Drawable btnBlue = getResources().getDrawable(R.drawable.btn_cree_blue_gradiant);*/

        editNom = (EditText) findViewById(R.id.editNom);
        editPrenom = (EditText) findViewById(R.id.editPrenom);
        editNomUtilisateur = (EditText) findViewById(R.id.editNomUtilisateur);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editEmailConfirmation = (EditText) findViewById(R.id.editEmailConfirmation);
        editTelephone = (EditText) findViewById(R.id.editTelephone);
        editMotDepasse = (EditText) findViewById(R.id.editMotDePasse);
        editMotDepasseConfirmation = (EditText) findViewById(R.id.editMotDePasseConfirmation);


        btnCreeCompte = (Button) findViewById(R.id.btnCreeCompte);
        layout = (RelativeLayout) findViewById(R.id.newcomptebg);
        radioButtonAssistant = (RadioButton) findViewById(R.id.radiobtn0);

        //Change le backgroung lorsque l'utilisateur
       /* radioButtonAssistant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    layout.setBackground(bg_Blue);
                    btnCreeCompte.setBackground(btnGreen);

                }
                else {
                    layout.setBackground(bg_Green);
                    btnCreeCompte.setBackground(btnBlue);
                }
            }
        });*/


    }

    public void creeCompte(View view) {
        //Toast.makeText(this,"ok im in",Toast.LENGTH_LONG).show();

       if (!VerificateurEntreeText.isTextTheSame(editEmail,editEmailConfirmation)){
           //
           return;

       }
        if (!VerificateurEntreeText.isTextTheSame(editMotDepasse,editMotDepasseConfirmation)){
            //
            return;
        }
        String nom = editNom.getText().toString();
        String preNom = editPrenom.getText().toString();
        String nomUtilisateur = editNomUtilisateur.getText().toString().toLowerCase();
        String telephone = editTelephone.getText().toString();
        String email = editEmailConfirmation.getText().toString().toLowerCase();
        String motDePasse = editMotDepasse.getText().toString();

        ConnectionPHP connectionPHP = new ConnectionPHP();

        connectionPHP.addNomUtilisateur(nomUtilisateur);
        connectionPHP.addNom(nom);
        connectionPHP.addPassword(motDePasse);
        connectionPHP.addEmail(email);
        connectionPHP.addTelephone(telephone);
        connectionPHP.addPrenom(preNom);
        connectionPHP.addTypeUtilistateur(radioButtonAssistant.isChecked() ? TYPE_ASSISTANT:TYPE_ASSISTEE);
        connectionPHP.setAction(ACTION_CREE_COMPTE);
        connectionPHP.sendRequest(DEBUG_STR_URL);


        Toast.makeText(this,connectionPHP.getResponse(),Toast.LENGTH_LONG).show();


        Toast.makeText(this,"Enregistrement effectuer avec succes", Toast.LENGTH_LONG).show();

    }
}
