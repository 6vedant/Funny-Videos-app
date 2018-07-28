package com.apps4humanity.funny.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps4humanity.funny.MyPlayerActivity;
import com.apps4humanity.funny.R;
import com.apps4humanity.funny.data.VideosRecyclerViewData;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by vedant on 3/27/2018.
 */

public class Tab1 extends Fragment {
    private InterstitialAd mInterstitialAd;

    public String add_unit_id;

    public String getAdd_unit_id() {
        return add_unit_id;
    }

    public String deviceId = "9DE2B8301ED0E80F1C657A43C4964A08";

    public void setAdd_unit_id(String add_unit_id) {
        this.add_unit_id = add_unit_id;
    }

    public Tab1() {
        // Required empty public constructor
    }

    public static Tab1 newInstance(String param1, String param2) {
        Tab1 fragment = new Tab1();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.tab1, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.video_tab1_recyclerview);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        setAdd_unit_id("ca-app-pub-2011247137347052/4474044515");


        FirebaseDatabase.getInstance().getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String enable = (String) dataSnapshot.child("enable").getValue();
                if (enable.equals("yes")) {
                    try {

                        String intern_id = (String) dataSnapshot.child("inter_add_id").getValue();
                        String banner_id = (String) dataSnapshot.child("banner_add_id").getValue();
                        VideosRecyclerViewData videosRecyclerViewData = new VideosRecyclerViewData(progressBar, getContext(), recyclerView);
                        setAdd_unit_id(banner_id);
                        mInterstitialAd = new InterstitialAd(getContext());
                        mInterstitialAd.setAdUnitId(intern_id);

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                mInterstitialAd.show();
                            }
                        });
                        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("" + deviceId).build());


                    } catch (Exception e) {
                        Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;


    }


    public static final String md5(final String s) {
        try {
            //create md5 dash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            //creating the hex string
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                String h = Integer.toHexString(0XFF & messageDigest[i]);

                while (h.length() < 2) {
                    h = "0" + h;
                    hexString.append(h);
                }
                return hexString.toString();
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    void loadAd() {
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Load the adView object witht he request
        mInterstitialAd.loadAd(adRequest);
    }


}
