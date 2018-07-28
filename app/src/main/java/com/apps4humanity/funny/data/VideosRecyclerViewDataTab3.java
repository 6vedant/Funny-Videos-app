package com.apps4humanity.funny.data;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps4humanity.funny.adapter.VideosRecyclerViewAdapter3;
import com.apps4humanity.funny.prototype.VideosRecyclerViewPrototype;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vedant on 4/1/2018.
 */

public class VideosRecyclerViewDataTab3 {


    RecyclerView recyclerView;
    public ArrayList<VideosRecyclerViewPrototype> videosRecyclerViewPrototypes = new ArrayList<>();
    ProgressBar progressBar;
    Context context;

    public VideosRecyclerViewDataTab3(final Context context, final RecyclerView recyclerView, final ProgressBar progressBar, final String android_id) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.progressBar = progressBar;

        if (android_id != null) {
            FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot dataSnapshot1 = dataSnapshot.child("user_choices").child("" + android_id);
                    if (dataSnapshot1.exists()) {
                        HashMap<Long, String> hashMap = new HashMap<>();
                        for (DataSnapshot ds : dataSnapshot1.getChildren()) {

                            hashMap.put((long) ds.getValue(), ds.getKey());
                        }
                        getUpdates(hashMap, dataSnapshot);
                        progressBar.setVisibility(View.GONE);
                    }

                    if (videosRecyclerViewPrototypes.size() > 0) {
                        VideosRecyclerViewAdapter3 videosRecyclerViewAdapter3 = new VideosRecyclerViewAdapter3(context, videosRecyclerViewPrototypes);
                        videosRecyclerViewAdapter3.notifyDataSetChanged();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(videosRecyclerViewAdapter3);

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, "No videos in favourites...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    progressBar.setVisibility(View.GONE);
                }
            });
        }


    }

    public void getUpdates(HashMap<Long, String> hashMap, DataSnapshot dataSnapshot) {

        for (final String val : hashMap.values()) {
            DataSnapshot dataSnapshot1 = dataSnapshot.child("vids").child(val);
            if (dataSnapshot1.exists()) {
                String thumb_url = (String) dataSnapshot1.child("thumb").getValue();
                String time = (String) dataSnapshot1.child("time").getValue();
                String title = (String) dataSnapshot1.child("title").getValue();
                String vid = (String) dataSnapshot1.child("vid").getValue();
                long views_t = (long) dataSnapshot1.child("view").getValue();

                VideosRecyclerViewPrototype videosRecyclerViewPrototype = new VideosRecyclerViewPrototype(val, title, thumb_url, vid, time, views_t);
                videosRecyclerViewPrototypes.add(videosRecyclerViewPrototype);

            }
        }

    }


}
