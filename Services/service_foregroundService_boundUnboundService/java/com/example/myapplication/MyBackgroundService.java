package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class MyBackgroundService extends Service {

    // -----------------  Service binding work
    IBinder binder=new MyBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    class  MyBinder extends Binder
    {
        public  MyBackgroundService getService()
        {
            return  MyBackgroundService.this;
        }
    }


    // -----------------  Service binding work





    public  MediaPlayer mp;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),"my_channel_01");
        notificationBuilder.setSmallIcon(R.drawable.ic_android_black_24dp);
        notificationBuilder.setContentTitle("Service Notification");
        notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
        notificationBuilder.setContentText("background service notification");
        Notification notification= notificationBuilder.build();
       startForeground(1011,notification);

        mp=MediaPlayer.create(getApplicationContext(),R.raw.naat);
        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        stopForeground(true);
    }
}
