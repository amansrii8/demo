package com.example.dell.demoforarrk.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.demoforarrk.activity.DetailActivity;
import com.example.dell.demoforarrk.R;
import com.example.dell.demoforarrk.model.Results;

import java.util.ArrayList;

import static com.example.dell.demoforarrk.constants.Constants.RESULT_DETAIL;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.StarWarViewHolder>{

    ArrayList<Results> mResultsArrayList = new ArrayList<>();
    Context mContext;

    public RecyclerAdapter(Context context) {
        mContext = context;
    }

    @Override
    public StarWarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_namelist, parent, false);
        return new StarWarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(StarWarViewHolder holder, int position) {
        Results results = mResultsArrayList.get(holder.getAdapterPosition());
        holder.bindClickListener(results ,position);
    }

    @Override
    public int getItemCount() {
        return mResultsArrayList.size();
    }

    class StarWarViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        RelativeLayout container;

        public StarWarViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textview_name);
            container = itemView.findViewById(R.id.container);
        }

        void bindClickListener(final Results results, int position) {
            textViewName.setText(results.getName());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mContext == null) return;
                    Intent startIntent = new Intent(mContext, DetailActivity.class);
                    startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startIntent.putExtra(RESULT_DETAIL, results);
                    mContext.startActivity(startIntent);
                }
            });
        }
    }

    public void notify(ArrayList<Results> resultsArrayList) {
        mResultsArrayList.addAll(resultsArrayList);
       this.notifyDataSetChanged();
    }
}
