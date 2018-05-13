package com.example.an.hotel;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HotelApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("hotelroom.realm")
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
