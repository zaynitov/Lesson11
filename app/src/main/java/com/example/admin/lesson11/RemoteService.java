package com.example.admin.lesson11;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class RemoteService extends Service {
    String stringForSP = "WER";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("We Are Binded");
        return new IMyAidlInterface.Stub() {
            @Override
            public String read() throws RemoteException {
                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.APP_PREFERENCES,
                        Context.MODE_PRIVATE);
                return sharedPreferences.getString(stringForSP, "DEFAULT");
            }


            @Override
            public void write(String string) throws RemoteException {
                SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.APP_PREFERENCES,
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(stringForSP,"HEY");
                editor.commit();
            }
        }


                ;


    }
}
