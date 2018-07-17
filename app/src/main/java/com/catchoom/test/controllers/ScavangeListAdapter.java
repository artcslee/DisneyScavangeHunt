package com.catchoom.test.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.catchoom.test.R;
import com.catchoom.test.activities.RecognitionFinderActivity;
import com.catchoom.test.models.ScavangeItem;

/**
 * Created by artcslee on 4/9/18.
 */
public class ScavangeListAdapter extends RecyclerView.Adapter<ScavangeListAdapter.ScavangerItemViewHolder> {
    private List<ScavangeItem> scavangeItemList;

    public ScavangeListAdapter(List<ScavangeItem> data) {
        scavangeItemList = data;

    }

    @Override
    public ScavangerItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.scavange_list_row, parent, false);
        return new ScavangerItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScavangerItemViewHolder holder, int position) {
        final ScavangeItem obj = scavangeItemList.get(position);
        holder.title.setText(obj.getTitle());
        holder.img.setImageResource(obj.getImgId());
    }

    @Override
    public int getItemCount() {
        return scavangeItemList.size();
    }

    public class ScavangerItemViewHolder extends RecyclerView.ViewHolder {
        private final Context context;
        ImageView img;
        TextView title;
        ImageButton searchButton;

        public ScavangerItemViewHolder (View view) {
            super(view);
            context = view.getContext();
            title = view.findViewById(R.id.scavange_item_title);
            img = view.findViewById(R.id.scavange_item_img);

            searchButton = view.findViewById(R.id.scavange_search_button);
            searchButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent launchersActivity = new Intent(context, RecognitionFinderActivity.class);
                    context.startActivity(launchersActivity);
                }
            });

        }
    }
}
