package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Uri img_uri=null;
    Uri video_uri=null;

    VideoView videoView;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imgView);

        videoView=findViewById(R.id.videoView);
        videoView.setMediaController(new MediaController(this));
        storageReference = FirebaseStorage.getInstance().getReference();


    }

    public void selectImage(View view) {
        Intent intent =new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==101)
        {
           // img_uri=data.getData();
            video_uri=data.getData();

            // imageView.setImageURI(img_uri);
            videoView.setVideoURI(video_uri);
            videoView.start();

        }
    }

    public void upload_image(View view)
    {
        Random random=new Random();
       // String name=random.nextLong()+".png";
        String name=random.nextLong()+".mp4";

        StorageReference imgFolderReference=storageReference.child("Videos/"+name);
      //  UploadTask imgUploadTask=imgFolderReference.putFile(img_uri);
        UploadTask imgUploadTask=imgFolderReference.putFile(video_uri);
        imgUploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
              new AlertDialog.Builder(MainActivity.this)
                      .setTitle("Upload Successfuly")
                      .setMessage(taskSnapshot.getMetadata().getName())
                      .create().show();
            }
        });
        imgUploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Eror in uploading")
                        .setMessage(e+"")
                        .create().show();
            }
        });
    }

    public void fetchImage(View view) {
//        StorageReference imgReference=storageReference.child("Images/-3849875682649204320.png");
//         Task<Uri> uriTask= imgReference.getDownloadUrl();
//         uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
//             @Override
//             public void onSuccess(Uri uri)
//             {
//                 Picasso.with(MainActivity.this).load(uri).into(imageView);
//             }
//         });

        StorageReference imgReference=storageReference.child("Videos/-7848034692007577510.mp4");
        Task<Uri> uriTask= imgReference.getDownloadUrl();
        uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri)
            {
              videoView.setVideoURI(uri);
              videoView.start();
            }
        });

    }

    public void nextPage(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }
}
