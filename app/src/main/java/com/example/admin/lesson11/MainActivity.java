package com.example.admin.lesson11;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements IActivityCallbacks {
    private ServiceConnection serviceConnection;
    private IMyAidlInterface iMyAidlInterface;

    @Override
    public void sendData(String data) {

        try {
            iMyAidlInterface.write(data);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getData() {
        try {
            return iMyAidlInterface.read();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return "";
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
        init();
    }

    private void init() {
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                iMyAidlInterface = null;
            }
        };
        Intent intent = new Intent(this, RemoteService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        fragmentManager.beginTransaction().add(R.id.framelayout1, fragment1).
                add(R.id.framelayout2, fragment2).commit();

    }


}
