package com.example.jrelevecompteursdulimousin;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ControleurListeCompteur extends MainActivity {

    private ListView lv;
    CompteurSQLLite compteurSQLLite;
    CompteurArrayAdapter arrayAdapterCompteur;
    Compteur compteur = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_liste_compteur);

        Compteur c = new Compteur();

        compteurSQLLite = new CompteurSQLLite(this);

        ListView lvCompteur = findViewById(R.id.lvCompteur);
        //final SimpleCursorAdapter simpleCursorAdapter = compteurSQLLite.populateListViewFromDB();
        //lvCompteur.setAdapter(simpleCursorAdapter);
        ArrayList lesCompteurs = compteurSQLLite.getListeCompteur();
        arrayAdapterCompteur = new CompteurArrayAdapter(this, lesCompteurs);
        lvCompteur.setAdapter(arrayAdapterCompteur);

        lvCompteur.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                compteur = (Compteur) adapterView.getItemAtPosition(i);
                //compteur = (Compteur) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getApplicationContext(),ControleurFicheCompteur.class);

                intent.putExtra("pos", i);
                startActivity(intent);
            }
        });
    }
    protected void onRestart() {
        super.onRestart();
        ListView lvCompteur = findViewById(R.id.lvCompteur);
        //final SimpleCursorAdapter simpleCursorAdapter = compteurSQLLite.populateListViewFromDB();
        //lvCompteur.setAdapter(simpleCursorAdapter);
        ArrayList lesCompteurs = compteurSQLLite.getListeCompteur();
        arrayAdapterCompteur = new CompteurArrayAdapter(this, lesCompteurs);
        lvCompteur.setAdapter(arrayAdapterCompteur);
        arrayAdapterCompteur.notifyDataSetChanged();

    }
}
