package com.apps4humanity.funny.subActivity;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apps4humanity.funny.R;
import com.apps4humanity.funny.data.VideosRecyclerViewDataTab3;
import com.apps4humanity.funny.data.VideosTab2Data;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Tab2ActivityVideoList extends AppCompatActivity {
    ImageView back_arrow;
    ImageView category_imageview;
    TextView category_title_textview;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    private InterstitialAd mInterstitialAd;
    public String deviceId = "9DE2B8301ED0E80F1C657A43C4964A08";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab2_video_list);

        String title = getIntent().getStringExtra("title");
        String image_url = getIntent().getStringExtra("image_url");
        String key = getIntent().getStringExtra("key");

        back_arrow = (ImageView) findViewById(R.id.imageView7);
        category_imageview = (ImageView) findViewById(R.id.imageview_category);
        category_title_textview = (TextView) findViewById(R.id.title_item_category);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        Picasso.with(getApplicationContext()).load(Uri.parse(image_url)).into(category_imageview);
        category_title_textview.setText(title);

        loadData(key);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-2011247137347052~4474044515");
        AdView mAdView = (AdView) findViewById(R.id.adView2);
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
                        mInterstitialAd = new InterstitialAd(getApplicationContext());
                        mInterstitialAd.setAdUnitId(intern_id);

                        mInterstitialAd.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                mInterstitialAd.show();
                            }
                        });
                        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("" + deviceId).build());


                        //VideosRecyclerViewDataTab3 videosRecyclerViewDataTab3 = new VideosRecyclerViewDataTab3(getContext(), recyclerView, progressBar, android_id);
                    } catch (Exception e) {
                        //  Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                overridePendingTransition(R.transition.fade_in, R.transition.fadec_out);
            }
        });


    }

    public void loadData(String key) {

        try {
            VideosTab2Data videosTab2Data = new VideosTab2Data(Tab2ActivityVideoList.this, progressBar, recyclerView, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
