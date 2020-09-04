package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
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

        if(intent.getAction().equals("play_musice"))
        {
            mp.start();
        }
        if(intent.getAction().equals("pause_musice"))
        {
            mp.pause();
        }
        if(intent.getAction().equals("stop_musice"))
        {
            mp.stop();
            stopForeground(true);
            stopSelf();
        }


        if(intent.getAction().equals("start_service"))
        {
            Intent play_intent=new Intent(this,MyBackgroundService.class);
            play_intent.setAction("play_musice");
            PendingIntent playIntent=PendingIntent.getService(this,101,play_intent,0);
            ////
            Intent pause_intent=new Intent(this,MyBackgroundService.class);
            pause_intent.setAction("pause_musice");
            PendingIntent pauseIntent=PendingIntent.getService(this,101,pause_intent,0);
            ////
            Intent stop_intent=new Intent(this,MyBackgroundService.class);
            stop_intent.setAction("stop_musice");
            PendingIntent stopIntent=PendingIntent.getService(this,101,stop_intent,0);
            ////


            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(),"my_channel_01");
            notificationBuilder.setSmallIcon(R.drawable.ic_android_black_24dp);
            notificationBuilder.setContentTitle("Service Notification");
            notificationBuilder.setDefaults(Notification.DEFAULT_ALL);
            notificationBuilder.setContentText("background service notification");

            notificationBuilder.addAction(R.drawable.ic_play_arrow,"PLAY",playIntent);
            notificationBuilder.addAction(R.drawable.ic_pause,"PAUSE",pauseIntent);
            notificationBuilder.addAction(R.drawable.ic_stop,"STOP",stopIntent);


            Notification notification= notificationBuilder.build();
            startForeground(1011,notification);

            mp=MediaPlayer.create(getApplicationContext(),R.raw.naat);
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopForeground(true);
                    stopSelf();
                }
            });
        }

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        stopForeground(true);

    }
}
