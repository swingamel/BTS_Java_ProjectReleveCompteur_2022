package com.example.jrelevecompteursdulimousin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import plum.widget.MessageDialog;

public class ControleurFicheCompteur extends MainActivity implements View.OnClickListener, MessageDialog.OnClickMessageDialogListener {

    String id;
    CompteurSQLLite csl;
    Compteur c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_fiche_compteur);

        csl = new CompteurSQLLite(this);
        int i = getIntent().getIntExtra("pos", -1);
        c =  csl.getListeCompteur().get(i);

        TextView itemNom = (TextView)findViewById(R.id.nom);
        itemNom.setText(c.nom);
        TextView itemVille = (TextView)findViewById(R.id.ville);
        itemVille.setText(c.ville);
        TextView itemRue = (TextView)findViewById(R.id.rue);
        itemRue.setText(c.rue);
        TextView itemCP = (TextView)findViewById(R.id.cp);
        itemCP.setText(c.codePostal);
        TextView itemIndexAncien = (TextView)findViewById(R.id.indexAncien);
        itemIndexAncien.setText(c.indexAncien + "");
        TextView itemIndexNouveau = (TextView)findViewById(R.id.indexNouveau);
        itemIndexNouveau.setText(c.indexNouveau + "");

        Button valider = findViewById(R.id.valider);
        Button annuler = findViewById(R.id.annuler);
        valider.setOnClickListener(this);
        annuler.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
             case R.id.annuler:
            TextView annuler = (TextView) view.findViewById(R.id.annuler);
                 MessageDialog.show(this,
                         "Voulez-vous quittez ?",
                         "OUI","NON",  this);


            break;
            case R.id.valider:
            EditText itemIndexNouveau = (EditText) findViewById(R.id.indexNouveau2);


            if (itemIndexNouveau.getText().toString().equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(), "Le nouvel index est vide", Toast.LENGTH_SHORT);
                toast.show();
                return;
          }
            c.indexNouveau= Integer.parseInt(itemIndexNouveau.getText().toString());


            if (c.indexNouveau <= c.indexAncien) {
                Toast toast = Toast.makeText(getApplicationContext(), "Le nouvel index est inférieur à l'ancien index", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }

            if (c.indexNouveau - c.indexAncien > 800 ) {
                Toast toast = Toast.makeText(getApplicationContext(), "L'index est supérieur a 800", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
                csl.updateCompteur(c);
            finish();

    break;
        }
    }
    @Override
    public void onClickMessageDialog(MessageDialog messageDialog, char c) {
        switch (c) {
            case 'G':
                finish();
                break;
            case 'D':
                break;
        }
    }}
