package com.apps4humanity.funny.data;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps4humanity.funny.adapter.Tab2CategoryAdapter;
import com.apps4humanity.funny.prototype.Tab2CategoryPrototype;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by vedant on 4/12/2018.
 */

public class Tab2CategoryData {
    public Context context;
    public ProgressBar progressBar;
    public RecyclerView recyclerView;

    public ArrayList<Tab2CategoryPrototype> tab2CategoryPrototypes = new ArrayList<>();

    public Tab2CategoryData(Context context, RecyclerView recyclerView, final ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
        this.recyclerView = recyclerView;

        FirebaseDatabase.getInstance().getReference().child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                tab2CategoryPrototypes.clear();
                if (dataSnapshot.exists()) {
                    getUpdates(dataSnapshot);
                }
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getUpdates(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            String image_url = (String) ds.child("image_url").getValue();
            String title = (String) ds.child("name").getValue();
            String num_videos = (String) ds.child("num_videos").getValue();
            String desc_category = (String) ds.child("desc").getValue();
            String key = ds.getKey();

            Tab2CategoryPrototype tab2CategoryPrototype = new Tab2CategoryPrototype(image_url, title, num_videos, key, desc_category);
            tab2CategoryPrototypes.add(tab2CategoryPrototype);
        }

        if (tab2CategoryPrototypes.size() > 0) {
            // add the adapter here
            Tab2CategoryAdapter tab2CategoryAdapter = new Tab2CategoryAdapter(context, tab2CategoryPrototypes);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(tab2CategoryAdapter);
        } else {

            // no data available here

        }

    }

}
