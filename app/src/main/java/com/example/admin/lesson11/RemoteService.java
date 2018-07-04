package com.example.admin.lesson11;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class RemoteService extends Service {
    String keyForSharedPref = "WER";
    public static final String APP_PREFERENCES = "mysettings";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public String read() throws RemoteException {
                SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES,
                        Context.MODE_PRIVATE);
                return sharedPreferences.getString(keyForSharedPref, null);
            }

            @Override
            public void write(String stringToWrite) throws RemoteException {
                SharedPreferences sharedPreferences = getSharedPreferences(APP_PREFERENCES,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(keyForSharedPref, stringToWrite);
                editor.commit();
            }
        };
    }
}
