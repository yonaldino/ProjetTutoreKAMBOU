package com.kambou.affichage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    // Declaration des composants
    private TextView labelnom;
    private TextView labelprenom;
    private TextView labeladdresse;
    private TextView labelage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Affectation des composants
        labelnom = findViewById(R.id.labelnom);
        labelprenom = findViewById(R.id.labelprenom);
        labeladdresse = findViewById(R.id.labeladdresse);
        labelage = findViewById(R.id.labelage);

        // Recuperation des informations depuis l'Intent
        String nom = this.getIntent().getExtras().getString("nom");
        String prenom = this.getIntent().getExtras().getString("prenom");
        String addresse = this.getIntent().getExtras().getString("addresse");
        String age = this.getIntent().getExtras().getString("age");

        // affichage des informations sur la page
        labelnom.setText(nom);
        labelprenom.setText(prenom);
        labeladdresse.setText(addresse);
        labelage.setText(age);
    }
}