package com.apps4humanity.funny.fragment;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.apps4humanity.funny.R;
import com.apps4humanity.funny.data.VideosRecyclerViewData;
import com.apps4humanity.funny.data.VideosRecyclerViewDataTab3;
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

public class Tab3 extends Fragment {

    private InterstitialAd mInterstitialAd;
    public String deviceId = "9DE2B8301ED0E80F1C657A43C4964A08";


    public Tab3() {
        // Required empty public constructor
    }

    public static Tab3 newInstance(String param1, String param2) {
        Tab3 fragment = new Tab3();
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

        View view = inflater.inflate(R.layout.tab3, container, false);

        final String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // from layout

        recyclerView = (RecyclerView) view.findViewById(R.id.video_tab3_recyclerview);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);


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


                        VideosRecyclerViewDataTab3 videosRecyclerViewDataTab3 = new VideosRecyclerViewDataTab3(getContext(), recyclerView, progressBar, android_id);
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

}
