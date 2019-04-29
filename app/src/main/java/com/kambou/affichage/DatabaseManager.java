package com.kambou.affichage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {

    // declaration des nom de base de données et version
    private static final String DATABASE_NAME = "ProjetIC.db";
    private static final int DATABASE_VERSION = 5;

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // requete de creation de la table des utilisateurs
        String sqlUser = "CREATE TABLE registeruser (" +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login TEXT NOT NULL," +
                "password TEXT NOT NULL" +
                ")";

        // excecution des requetes de creation de tables
        db.execSQL(sqlUser);
        db.execSQL(sqlPerson);

        Log.i("DATABSE", "onCreate launched");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // requetes de destruction des tables
        String sqlUser = "DROP TABLE registeruser";

        // excecution des requetes de destruction de tables
        db.execSQL(sqlUser);
        db.execSQL(sqlPerson);

        this.onCreate(db);

        Log.i("DATABSE", "onUpgrade launched");
    }

    // methode permetant d'ajouter un nouvel utilisateur dans la base de données
    public void insertUser(String login, String password) {
        login = login.replace("'", "''");
        password = password.replace("'", "''");

        String sql = "INSERT INTO registeruser (login, password) " +
                "VALUES ('" + login + "', '" + password + "')";
        this.getWritableDatabase().execSQL(sql);

        Log.i("DATABSE", "insertUser called");
    }

    // methode verifiant la presence de l'utilisateur dans la base de données
    public boolean login(String login, String password) {
        login = login.replace("'", "''");
        password = password.replace("'", "''");

        boolean isOk = false;

        // requete pour retrouver l'utilisateur
        String sql = "SELECT idUser FROM registeruser " +
                "WHERE login = '" + login + "' AND password = '" + password + "'";


        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0) {
            isOk = true;
        }

        return isOk;
    }
}