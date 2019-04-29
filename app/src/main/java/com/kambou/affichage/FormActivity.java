package com.kambou.affichage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FormActivity extends AppCompatActivity {

    // Declaration des composants
    private EditText txtnom;
    private EditText txtprenom;
    private EditText txtaddresse;
    private Button btnEnregistrer;
    private Spinner spnAge;

    private static final int SECOND_CALL_ID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Affectation des composants
        txtnom = findViewById(R.id.txtnom);
        txtprenom = findViewById(R.id.txtprenom);
        txtaddresse = findViewById(R.id.txtaddresse);
        spnAge = findViewById(R.id.spnAge);
        btnEnregistrer = findViewById(R.id.btnEnregistrer);

        // Definition d'une liste de 100 entiers
        List<Integer> ageArray = new ArrayList<>();
        for (int i=1; i<=100; i++) {
            ageArray.add(i);
        }

        // Injection de la liste dans le composant spinner
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, ageArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnAge.setAdapter(adapter);

        btnEnregistrer.setOnClickListener(btnEnregistrerListener);
    }

    private View.OnClickListener btnEnregistrerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Recuperation des informations saisies
            String nom = txtnom.getText().toString().trim();
            String prenom = txtprenom.getText().toString().trim();
            String addresse = txtaddresse.getText().toString().trim();
            String age = spnAge.getSelectedItem().toString().trim();

            // Declaration de la nouvelle page
            Intent displayIntent = new Intent(FormActivity.this, DisplayActivity.class);

            // Envoie des informations à la nouvelle page
            displayIntent.putExtra("nom", nom);
            displayIntent.putExtra("prenom", prenom);
            displayIntent.putExtra("addresse", addresse);
            displayIntent.putExtra("age", age);

            // Redirection vers la page d'affichage des informations
            startActivityForResult(displayIntent, SECOND_CALL_ID);
        }
    };
}
