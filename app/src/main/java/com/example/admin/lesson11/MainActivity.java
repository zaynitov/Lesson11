package com.example.admin.lesson11;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IActivityCallbacks {

    public static final String ACTION_AIDL = "com.example.admin.lesson11.ISumNumbsAIDL";
   private IMyAidlInterface iMyAidlInterface;
   public SharedPreferences sharedPreferences;
    public static final String APP_PREFERENCES = "mysettings";

    @Override
    public void sendData(String data) {
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
           iMyAidlInterface = null;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, RemoteService.class);
        intent.setAction(ACTION_AIDL);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

    }
}
