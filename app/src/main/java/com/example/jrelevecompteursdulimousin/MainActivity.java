package com.example.jrelevecompteursdulimousin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import plum.widget.ComboDialog;


import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements
        ComboDialog.OnClickComboDialogListener  {

    CompteurSQLLite compteurSQLLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);

        final CharSequence[] items = {"Claude","Agnès","Thierry", "Charles", "Alain"};
        final CharSequence[] values = {"1","2","3","4","5"};

        ComboDialog comboReleveur = new ComboDialog( "Choisir un releveur :",
                items,
                values,
                username,
                this );

        comboReleveur.setOnClickComboDialogListener(this);

        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        compteurSQLLite = new CompteurSQLLite(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Releveur r = compteurSQLLite.getReleveur(username.getText().toString());

                if(r != null && password.getText().toString().equals(r.motDePasse)) {
                    //correct
                    Intent intent = new Intent(getApplicationContext(), ControleurListeCompteur.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                }else if (r != null != password.getText().toString().equals(r.motDePasse)){
                    Toast.makeText(MainActivity.this,"Le mot de passe doit être identique à l'identifiant",Toast.LENGTH_SHORT).show();
                }else if (username.equals("") || password.equals("")){
                    //incorrect
                    Toast.makeText(MainActivity.this,"Veuillez entrer les informations demandées",Toast.LENGTH_SHORT).show();
            }}
        });
    }

    @Override
    public void onClickComboDialog(ComboDialog comboDialog) {
        String value = (String) comboDialog.value( comboDialog.getIndexSelected());
        String item = (String) comboDialog.item( comboDialog.getIndexSelected());
    }
}