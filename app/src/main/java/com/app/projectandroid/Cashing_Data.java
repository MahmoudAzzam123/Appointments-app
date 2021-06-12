package com.app.projectandroid;

import android.app.Application;
import com.google.firebase.database.FirebaseDatabase;

public class Cashing_Data extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }
}
