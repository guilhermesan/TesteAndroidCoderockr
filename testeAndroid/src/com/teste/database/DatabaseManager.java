package com.teste.database;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;


public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    static private DatabaseHelper helper;
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
    }

    static public DatabaseHelper getHelper() {
        return helper;
    }

   
    
}