package com.zeowls.a700apptask;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import com.zeowls.a700apptask.ServiceHandler.NetworkService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/*
 * Created by root on 8/2/17.
 */

public class MyApplication extends Application {
    private NetworkService networkService;
    public static String USER_KEY;

    @Override
    public void onCreate() {
        super.onCreate();
        USER_KEY = BuildConfig.USER_ID;
        networkService = new NetworkService();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(config);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}
