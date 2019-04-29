package com.kambou.affichage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    // Déclaration des composants
    private EditText txtLogin;
    private EditText txtPassword;
    private Button btnConnect;
    private Button btnRegister;
    private DatabaseManager dm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Affectation des composants
        txtLogin = findViewById(R.id.txtLogin);
        txtPassword = findViewById(R.id.txtPassword);
        btnConnect = findViewById(R.id.btnConnect);
        btnRegister = findViewById(R.id.btnRegister);

        // Tentative de connexion
        btnConnect.setOnClickListener(btnConnectListener);

        // Appel vers la page d'inscription
        btnRegister.setOnClickListener(btnRegisterListener);

        dm = new DatabaseManager(this);

    }

    // listener appelant la page d'enregistrement d'utilisateurs
    private View.OnClickListener btnRegisterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        }
    };

    // listener permetant de s'identifier
    private View.OnClickListener btnConnectListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // Récuperation et verification des informations de connexion de l'utilisateur

            // Récuperation des informations entrées par l'utilisateur
            String login = txtLogin.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();

            // Verification des informations utilisateurs
            // Méthode verifiant les informations de l'utilisateur
            boolean isUser = dm.login(login, password);

            if (isUser == true) {
                Intent formIntent = new Intent(LoginActivity.this, FormActivity.class);
                startActivity(formIntent);
            } else {
                Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_LONG).show();
            }
        }
    };
}
