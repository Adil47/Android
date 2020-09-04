package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    ListView listView;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        storageReference = FirebaseStorage.getInstance().getReference();
        listView =findViewById(R.id.listView);
        ArrayList<String> lst=new ArrayList<>();
        lst.add("-7848034692007577510.mp4");
        lst.add("1053340471644476369.mp4");
        lst.add("-7848034692007577510.mp4");
        lst.add("1053340471644476369.mp4");
        lst.add("-7848034692007577510.mp4");
        lst.add("1053340471644476369.mp4");


       MyAdapter myAdapter=new MyAdapter(lst,this);
        listView.setAdapter(myAdapter);
    }
    class  MyAdapter extends BaseAdapter
    {
    ArrayList<String> lst;
    Context context;
    LayoutInflater inflater;
        public MyAdapter(ArrayList<String> lst, Context context) {
            this.lst = lst;
            this.context = context;
            inflater= (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return lst.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v=inflater.inflate(R.layout.video_item_layout,null);
            final VideoView videoView=v.findViewById(R.id.itemVideo);
            videoView.setMediaController(new MediaController(context));

            StorageReference videoReference=storageReference.child("Videos/"+lst.get(i));
            videoReference.getDownloadUrl()
                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            videoView.setVideoURI(uri);
                            Toast.makeText(context, ""+uri, Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, ""+e, Toast.LENGTH_LONG).show();
                        }
                    });


            return  v;
        }
    }
}
