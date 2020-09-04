package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MyBackgroundService myBackgroundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this,MyBackgroundService.class);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

        ////////////  Register Notification channel
        String CHANNEL_ID = "my_channel_01";// The id of the channel.;
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, "MyChannel", importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(mChannel);
        }
        //////////// Register  Notification channel

    }

    // ------------------- service connection  ------------------------------
    ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBackgroundService.MyBinder myBinder= (MyBackgroundService.MyBinder) iBinder;
            myBackgroundService=myBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    // ------------------- service connection  ------------------------------



    public void start_service(View view) {
        Intent intent=new Intent(this,MyBackgroundService.class);
       // startService(intent);
        ContextCompat.startForegroundService(this,intent);
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);

    }

    public void stop_service(View view) {
        Intent intent=new Intent(this,MyBackgroundService.class);
        unbindService(serviceConnection);
        stopService(intent);
    }



    public void pause(View view) {
        myBackgroundService.mp.pause();
    }

    public void play(View view) {
        myBackgroundService.mp.start();
    }
}
