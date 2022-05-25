package com.example.jrelevecompteursdulimousin;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

    public class CompteurArrayAdapter extends ArrayAdapter<Compteur> {
        public CompteurArrayAdapter(Context context, ArrayList<Compteur> lesCompteurs) {
            super(context, 0, lesCompteurs);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            Compteur compteur = getItem(position);


            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_compteur,
                        parent,
                        false);
            }

            ImageView imageView = (ImageView) convertView.findViewById(R.id.yes);
            imageView.setVisibility(View.GONE);
            imageView = (ImageView) convertView.findViewById(R.id.no);
            imageView.setVisibility(View.GONE);
                if (compteur.indexNouveau != 0) {
                    imageView = (ImageView) convertView.findViewById(R.id.yes);
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView = (ImageView) convertView.findViewById(R.id.no);
                    imageView.setVisibility(View.VISIBLE);
                    TextView nom =(TextView) convertView.findViewById(R.id.itemNom);
                    nom.setTypeface(null, Typeface.BOLD);
                    TextView rue =(TextView) convertView.findViewById(R.id.itemRue);
                    rue.setTypeface(null, Typeface.BOLD);
                    TextView ville =(TextView) convertView.findViewById(R.id.itemVille);
                    ville.setTypeface(null, Typeface.BOLD);
                    TextView cp =(TextView) convertView.findViewById(R.id.itemCP);
                    cp.setTypeface(null, Typeface.BOLD);
                }


                TextView itemNom = (TextView) convertView.findViewById(R.id.itemNom);
                TextView itemVille = (TextView) convertView.findViewById(R.id.itemVille);
                TextView itemRue = (TextView) convertView.findViewById(R.id.itemRue);
                TextView itemCP = (TextView) convertView.findViewById(R.id.itemCP);
                TextView itemIndexAncien = (TextView) convertView.findViewById(R.id.itemIndexAncien);
                TextView itemIndexNouveau = (TextView) convertView.findViewById(R.id.itemIndexNouveau);


                // Populate the data into the template view using the data object
                itemNom.setText(compteur.nom);
                itemVille.setText(compteur.ville);
                itemRue.setText(compteur.rue);
                itemCP.setText(compteur.codePostal);
                itemIndexAncien.setText(compteur.indexAncien + "");
                itemIndexNouveau.setText(compteur.indexNouveau + "");


                // Return the completed view to render on screen
                return convertView;
            }
        }