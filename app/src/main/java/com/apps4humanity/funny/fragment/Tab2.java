package com.apps4humanity.funny.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps4humanity.funny.R;
import com.apps4humanity.funny.data.Tab2CategoryData;
import com.apps4humanity.funny.data.VideosRecyclerViewData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by vedant on 3/27/2018.
 */

public class Tab2 extends Fragment {
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;
    public String deviceId = "9DE2B8301ED0E80F1C657A43C4964A08";




    public Tab2() {
        // Required empty public constructor
    }

    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.tab2, container, false);

        // from layout

        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) view.findViewById(R.id.video_tab2_recyclerview);

        MobileAds.initialize(getContext(), "ca-app-pub-2011247137347052~4474044515");
        AdView mAdView = (AdView) view.findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(deviceId)
                .build();
        mAdView.loadAd(adRequest);


        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String enable = (String) dataSnapshot.child("enable").getValue();
                if (enable.equals("yes")) {

                    try {
                        String intern_id = (String) dataSnapshot.child("inter_add_id").getValue();
                        mInterstitialAd = new InterstitialAd(getContext());
                        mInterstitialAd.setAdUnitId(intern_id);

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                mInterstitialAd.show();
                            }
                        });
                        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("" + deviceId).build());


                        Tab2CategoryData tab2CategoryData = new Tab2CategoryData(getContext(), recyclerView, progressBar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}