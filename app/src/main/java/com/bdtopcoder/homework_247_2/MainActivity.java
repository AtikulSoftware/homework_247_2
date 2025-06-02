package com.bdtopcoder.homework_247_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

   ArrayList<HashMap<String, String>> categoryList = new ArrayList<>();
   HashMap<String, String> categoryMap;

   ArrayList<HashMap<String, String>> songList = new ArrayList<>();
   HashMap<String, String> songMap;

   // variable
   GridView gridView;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      EdgeToEdge.enable(this);
      setContentView(R.layout.activity_main);
      ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
         Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
         v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
         return insets;
      });

      // initialize
      gridView = findViewById(R.id.gridView);

      // load List
      loadCategory();

      CategoryAdapter categoryAdapter = new CategoryAdapter();
      gridView.setAdapter(categoryAdapter);

   } // onCreate method end here ===========


   public class CategoryAdapter extends BaseAdapter {

      @Override
      public int getCount() {
         return categoryList.size();
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
         @SuppressLint("ViewHolder") View myView = layoutInflater.inflate(R.layout.grid_item, parent, false);

         ImageView tvImage = myView.findViewById(R.id.tvImage);
         TextView tvTitle = myView.findViewById(R.id.tvTitle);
         CardView categoryItem = myView.findViewById(R.id.categoryItem);

         HashMap<String, String> hashMap = categoryList.get(position);
         String title = hashMap.get("title");
         String catIcon = hashMap.get("catIcon");
         String album = hashMap.get("album");

         tvTitle.setText(title);

         // item click
         categoryItem.setOnClickListener(v -> {
            if (album.equals("ArtcelAlbum")) {
               // go to Artcell Album
               loadArtcellAlbum();
               SongList.arrayList = songList;
               startActivity(new Intent(MainActivity.this, SongList.class));
            } else if (album.equals("ShironamhinAlbum")) {
               // go to Artcell Album
               loadShironamhinAlbum();
               SongList.arrayList = songList;
               startActivity(new Intent(MainActivity.this, SongList.class));
            }
         });

         return myView;
      }

   } // CategoryAdapter end here ===========

   void loadCategory() {
      //=== 1 === //
      categoryMap = new HashMap<>();
      categoryMap.put("title", "Artcell");
      categoryMap.put("catIcon", String.valueOf(R.drawable.ic_launcher_background));
      categoryMap.put("album", "ArtcelAlbum");
      categoryList.add(categoryMap);

      //=== 2 === //
      categoryMap = new HashMap<>();
      categoryMap.put("title", "Shironamhin");
      categoryMap.put("catIcon", String.valueOf(R.drawable.ic_launcher_background));
      categoryMap.put("album", "ShironamhinAlbum");
      categoryList.add(categoryMap);
   } // loadCategory end here =========


   void loadArtcellAlbum() {
      songList.clear();

      songMap = new HashMap<>();
      songMap.put("title", "Sample Audio 1");
      songMap.put("description", "Artcell");
      songMap.put("songImg", String.valueOf(R.drawable.dukkho_bilash));
      songMap.put("song", "https://cdn.pixabay.com/audio/2025/05/29/audio_b92a610839.mp3");
      songList.add(songMap);

      songMap = new HashMap<>();
      songMap.put("title", "Sample Audio 2");
      songMap.put("description", "Artcell");
      songMap.put("songImg", String.valueOf(R.drawable.dhushor_somoy));
      songMap.put("song", "https://cdn.pixabay.com/audio/2025/05/21/audio_fa20813ea6.mp3");
      songList.add(songMap);

   } // loadArtcellAlbum end here ============

   void loadShironamhinAlbum() {
      songList.clear();

      songMap = new HashMap<>();
      songMap.put("title", "Sample Audio 1");
      songMap.put("description", "Shironamhin");
      songMap.put("songImg", String.valueOf(R.drawable.dukkho_bilash));
      songMap.put("song", "https://cdn.pixabay.com/audio/2024/11/29/audio_45bbd49c34.mp3");
      songList.add(songMap);

      songMap = new HashMap<>();
      songMap.put("title", "Sample Audio 2");
      songMap.put("description", "Shironamhin");
      songMap.put("songImg", String.valueOf(R.drawable.dhushor_somoy));
      songMap.put("song", "https://cdn.pixabay.com/audio/2025/05/26/audio_904a54595f.mp3");
      songList.add(songMap);

   } // loadArtcellAlbum end here ============


} // public class end here ==================