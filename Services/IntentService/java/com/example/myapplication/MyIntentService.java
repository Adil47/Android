package com.example.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class MyIntentService extends IntentService {
    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyIntentService", "onCreate: -------");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String Name=intent.getExtras().getString("Name");
        for (int i=1; i<=10;i++)
        {
            Log.d("MyIntentService", Name+" ---- "+i);
            SystemClock.sleep(1000);
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyIntentService", "onDestroy: -------");
    }
}
