package com.bdtopcoder.homework_247_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class SongList extends AppCompatActivity {

   public static ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

   boolean isPlayIcon = false;

   ListView listView;
   MediaPlayer mediaPlayer;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.activity_song_list);
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });

      // initialize
      listView = findViewById(R.id.listView);

      // initialize audio
      mediaPlayer = new MediaPlayer();
      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

      // Adapter কে পরিচয় করিয়ে দেওয়া হয়েছে এবং list এর মধ্যে set করে দেওয়া হয়েছে ।
      MyAdapter myAdapter = new MyAdapter();
      listView.setAdapter(myAdapter);

   } // onCreate method end here =========

   public class MyAdapter extends BaseAdapter {

      @Override
      public int getCount() {
         return arrayList.size();
      }

      @Override
      public Object getItem(int position) {
         return null;
      }

      @Override
      public long getItemId(int position) {
         return 0;
      }

      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
         LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         @SuppressLint("ViewHolder") View myView = layoutInflater.inflate(R.layout.list_item, parent, false);

         // পরিচয় করিয়ে দেওয়া হয়েছে ।
         TextView tvTitle = myView.findViewById(R.id.tvTitle);
         TextView tvDescription = myView.findViewById(R.id.tvDescription);
         ImageView tvSong = myView.findViewById(R.id.tvSong);
         ImageView imgPlay = myView.findViewById(R.id.imgPlay);

         // list থেকে data get করা হয়েছে ।
         HashMap<String, String> hashMap1 = arrayList.get(position);
         String title = hashMap1.get("title");
         String description = hashMap1.get("description");
         String songImg = hashMap1.get("songImg");
         String song = hashMap1.get("song");

         // data list item এ set করে দেওয়া হয়েছে ।
         tvTitle.setText(title);
         tvDescription.setText(description);
         tvSong.setImageResource(Integer.parseInt(songImg));

//         int mySong = Integer.parseInt(song);

         // onClick লেখা হয়েছে ।
         imgPlay.setOnClickListener(v -> {
            try {
               if (imgPlay.getTag() == null || imgPlay.getTag().toString().equals("NOT_PLAYING")) {
                  if (mediaPlayer == null) {
                     mediaPlayer = new MediaPlayer();
                  } else {
                     mediaPlayer.reset();
                  }

                  mediaPlayer.setDataSource(song);
                  mediaPlayer.prepare();
                  mediaPlayer.start();

                  imgPlay.setImageResource(R.drawable.stop_icon);
                  imgPlay.setTag("PLAYING_NOW");

                  // When playback is completed
                  mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                     @Override
                     public void onCompletion(MediaPlayer mp) {
                        imgPlay.setImageResource(R.drawable.play_icon);
                        imgPlay.setTag("NOT_PLAYING");
                        mediaPlayer.release();
                        mediaPlayer = null;
                     }
                  });

               } else if (imgPlay.getTag().toString().equals("PLAYING_NOW")) {
                  if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                     mediaPlayer.stop();
                     mediaPlayer.release();
                     mediaPlayer = null;
                  }

                  imgPlay.setImageResource(R.drawable.play_icon);
                  imgPlay.setTag("NOT_PLAYING");
               }
            } catch (Exception e) {
                e.printStackTrace();
            }
         });


         return myView;
      }


   } // MyAdapter end here =================


} // public class end here ================