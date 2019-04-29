package com.kambou.affichage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    // Déclaration des composants
    private EditText txtLogin;
    private EditText txtPassword;
    private EditText txtPasswordConf;
    private Button btnRegister;
    private DatabaseManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        // Affectation des composants
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        txtPasswordConf = findViewById(R.id.txtPasswordConf);
        btnRegister = findViewById(R.id.btnRegister);


        // Enregistrement du nouvel utilisateur
        btnRegister.setOnClickListener(btnRegisterListener);

        dm = new DatabaseManager(this);
    }


    private View.OnClickListener btnRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Recuperation des informations entrées par l'utilisateur
            String login = txtLogin.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String passwordConf = txtPasswordConf.getText().toString().trim();

            if (password != "" && password.equals(passwordConf) && login != "") {
                dm.insertUser(login, password);

                // Redirection vers la page de saisie des personnes
                Intent formIntent = new Intent(RegisterActivity.this, FormActivity.class);
                startActivity(formIntent);

            } else {
                Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_LONG).show();
            }
        }
    };
}
