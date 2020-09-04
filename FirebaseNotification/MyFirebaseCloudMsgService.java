package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.WindowManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseCloudMsgService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
     //   Log.d("Cloud Msg", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
           // Log.d("Cloud msg ", "Message data payload: " + remoteMessage.getData());

            Intent intent=new Intent("notification_broadcast");
            intent.putExtra("title","firebase data");
            intent.putExtra("message",remoteMessage.getData()+"");

            getApplication(). sendBroadcast(intent);


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
           // Log.d("cloud Notification", "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Intent intent=new Intent("notification_broadcast");
            intent.putExtra("title",remoteMessage.getNotification().getTitle()+"");
            intent.putExtra("message",remoteMessage.getNotification().getBody()+"");

            getApplication(). sendBroadcast(intent);
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d("Token---", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
      //  sendRegistrationToServer(token);
    }


}
