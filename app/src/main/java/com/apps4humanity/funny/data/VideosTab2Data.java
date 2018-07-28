package com.apps4humanity.funny.data;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps4humanity.funny.adapter.VideosRecyclerViewAdapter;
import com.apps4humanity.funny.prototype.VideosRecyclerViewPrototype;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by vedant on 4/12/2018.
 */

public class VideosTab2Data {
    public Context context;
    public RecyclerView recyclerView;
    public ProgressBar progressBar;

    public static ArrayList<VideosRecyclerViewPrototype> videosRecyclerViewPrototypes = new ArrayList<>();

    public VideosTab2Data(final Context context, final ProgressBar progressBar, final RecyclerView recyclerView, final String key) {
        this.context = context;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;

        FirebaseDatabase.getInstance().getReference()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        videosRecyclerViewPrototypes.clear();
                        DataSnapshot dataSnapshot1 = dataSnapshot.child("categories").child("" + key).child("videos");
                        for (DataSnapshot ds : dataSnapshot1.getChildren()) {

                            loadUpdates((String) ds.getValue(), dataSnapshot);
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        if (videosRecyclerViewPrototypes.size() > 0) {
                            VideosRecyclerViewAdapter videosRecyclerViewAdapter = new VideosRecyclerViewAdapter(context, videosRecyclerViewPrototypes);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            recyclerView.setAdapter(videosRecyclerViewAdapter);
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(context, "No data available at the moment...", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public void loadUpdates(String list, DataSnapshot dataSnapshot) {

        dataSnapshot = dataSnapshot.child("vids").child("" + list);
        if (dataSnapshot.exists()) {
            String thumb = (String) dataSnapshot.child("thumb").getValue();
            String time = (String) dataSnapshot.child("time").getValue();
            String vid_url = (String) dataSnapshot.child("vid").getValue();
            String title = (String) dataSnapshot.child("title").getValue();
            long view = (long) dataSnapshot.child("view").getValue();

            VideosRecyclerViewPrototype videosRecyclerViewPrototype = new VideosRecyclerViewPrototype(list, title, thumb, vid_url, time, view);
            videosRecyclerViewPrototypes.add(videosRecyclerViewPrototype);
        }
    }
}
