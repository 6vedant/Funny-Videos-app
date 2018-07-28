package com.apps4humanity.funny.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps4humanity.funny.MainActivity;
import com.apps4humanity.funny.R;
import com.apps4humanity.funny.libs.AnimationUtils;
import com.apps4humanity.funny.prototype.Tab2CategoryPrototype;
import com.apps4humanity.funny.subActivity.Tab2ActivityVideoList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by vedant on 4/12/2018.
 */

public class Tab2CategoryAdapter extends RecyclerView.Adapter<Tab2CategoryAdapter.MyViewHolder> {

    int previousPosition = 0;
    public Context context;
    Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<Tab2CategoryPrototype> tab2CategoryPrototypes;

    public Tab2CategoryAdapter(Context context, ArrayList<Tab2CategoryPrototype> tab2CategoryPrototypes) {
        this.context = context;
        this.tab2CategoryPrototypes = tab2CategoryPrototypes;
        Activity activity1 = (Activity) context;
        setActivity(activity1);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.title_category.setText(tab2CategoryPrototypes.get(position).getTitle_category());
        holder.num_video_category.setText(tab2CategoryPrototypes.get(position).getNum_videos());
        holder.desc_video_category.setText(tab2CategoryPrototypes.get(position).getDesc());
        Picasso.with(context).load(Uri.parse(tab2CategoryPrototypes.get(position).getImage_url())).into(holder.image_category);


        //add the fluidic effect
        previousPosition = position;
        if (position > previousPosition) {
            AnimationUtils.animate(holder, true);
        } else {
            AnimationUtils.animate(holder, false);
        }
        previousPosition = position;


        // now set on the click listener for the itemview
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // now open the fragment containing it

                Intent intent = new Intent(context, Tab2ActivityVideoList.class);
                intent.putExtra("title",tab2CategoryPrototypes.get(position).getTitle_category());
                intent.putExtra("image_url",tab2CategoryPrototypes.get(position).getImage_url());
                intent.putExtra("key",tab2CategoryPrototypes.get(position).getKey());
                context.startActivity(intent);
                getActivity().overridePendingTransition(R.transition.slideup, R.transition.slidedown);
            }
        });


    }

    @Override
    public int getItemCount() {
        return tab2CategoryPrototypes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_category, num_video_category, desc_video_category;
        ImageView image_category;

        public MyViewHolder(View itemView) {
            super(itemView);
            title_category = (TextView) itemView.findViewById(R.id.title_item_category);
            num_video_category = (TextView) itemView.findViewById(R.id.num_videos_item_category);
            image_category = (ImageView) itemView.findViewById(R.id.imageview_item_category);
            desc_video_category = (TextView) itemView.findViewById(R.id.desc_item_category);
        }
    }
}
