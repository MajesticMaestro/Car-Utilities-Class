package com.nwo.prodigy.care4project;

import android.widget.EditText;

/**
 * Created by Prodigy on 16-04-08.
 */
public class VerificateurEntreeText {

    //Methode pour verifier 1 ou plus String et verifie quil sont pas Vide
    static boolean isTextEmpty(String...text){

        boolean response = false;
        for(String t : text){
            if (t.isEmpty());{
            response = true;
            break;
            }
        }
        return response;
    }

    //Accept un ou + EditText et Verifie qu'ils sont pas vide
    static boolean isTextEmpty(EditText...text){
        boolean response = false;
        for(EditText t : text){
            if (t.toString().isEmpty());{
                response = true;
                break;
            }
        }
        return response;
    }

    static  boolean isTextTheSame(EditText text1, EditText text2){
        return text1.getText().toString().equals(text2.getText().toString());
    }

}
