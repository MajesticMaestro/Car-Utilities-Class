package com.nwo.prodigy.care4project;

import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import static com.nwo.prodigy.care4project.Constantes.NODE_EMAIL;
import static com.nwo.prodigy.care4project.Constantes.NODE_NOM;
import static com.nwo.prodigy.care4project.Constantes.NODE_PASSWORD;
import static com.nwo.prodigy.care4project.Constantes.NODE_PRENOM;
import static com.nwo.prodigy.care4project.Constantes.NODE_TELEPHONE;
import static com.nwo.prodigy.care4project.Constantes.NODE_TYPE_UTILISATEUR;
import static com.nwo.prodigy.care4project.Constantes.NODE_USERNAME;
import static com.nwo.prodigy.care4project.Constantes.STR_URL;
import static com.nwo.prodigy.care4project.Constantes.TYPE_ASSISTANT;
import static com.nwo.prodigy.care4project.Constantes.TYPE_ASSISTEE;

public class CreeNouveauCompte extends AppCompatActivity {

    Switch userTypeSwitch;
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
        final Drawable bg_Blue =  getResources().getDrawable(R.drawable.blue_bg_01);
        final Drawable bg_Green = getResources().getDrawable(R.drawable.green_gradiant_bg_01);
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
        userTypeSwitch = (Switch) findViewById(R.id.switchUserType);

        //Change le backgroung lorsque l'utilisateur
       /* userTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        //
        ConnectionPDO connectionPDO = new ConnectionPDO(getBaseContext());
        connectionPDO.addEmail(editEmail);


        ArrayList<NameValuePair> donnees = new ArrayList<NameValuePair>();
        donnees.add(new BasicNameValuePair(ACTION, ACTION_CREE_COMPTE));
        donnees.add(new BasicNameValuePair(NODE_NOM, nom));
        donnees.add(new BasicNameValuePair(NODE_PRENOM, preNom));
        donnees.add(new BasicNameValuePair(NODE_USERNAME, nomUtilisateur));
        donnees.add(new BasicNameValuePair(NODE_EMAIL, email));
        donnees.add(new BasicNameValuePair(NODE_TELEPHONE, telephone));
        donnees.add(new BasicNameValuePair(NODE_PASSWORD, motDePasse));
        donnees.add(new BasicNameValuePair(NODE_TYPE_UTILISATEUR,userTypeSwitch.isChecked() ? TYPE_ASSISTEE:TYPE_ASSISTANT));

        // Envoie de la commande http
        try {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(STR_URL);
            httppost.setEntity(new UrlEncodedFormEntity(donnees));
            httpclient.execute(httppost);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),
                    "Erreur lors de la connexion ", Toast.LENGTH_LONG).show();
            return;

        }
        Toast.makeText(this,"Enregistrement effectuer avec succes", Toast.LENGTH_LONG).show();

    }
}
