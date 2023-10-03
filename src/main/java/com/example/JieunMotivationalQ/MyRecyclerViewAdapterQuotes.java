package com.example.JieunMotivationalQ;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.List;

public class MyRecyclerViewAdapterQuotes extends RecyclerView.Adapter<MyRecyclerViewAdapterQuotes.ViewHolder> {
    private int selectedQuoteIndex = -1; // Initialize with an invalid index

    private final List<String> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;

//    Todo continue adding admob
    private AdView mAdView;
    protected void onCreate(Bundle savedInstanceState) {

        MobileAds.initialize(mAdView.getContext(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = mAdView.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
//    End of ad

    // data is passed into the constructor
    MyRecyclerViewAdapterQuotes(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_quotes, parent, false);
        return new ViewHolder(view);
    }



    // binds the data to the TextView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String quote = mData.get(position);

        // Check if the current position is the selected quote index
        if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
            holder.myTextView.setText(mData.get(selectedQuoteIndex));
            holder.myTextView.setVisibility(View.VISIBLE);
        } else {
            holder.myTextView.setText(quote);
            holder.myTextView.setVisibility(
                    (selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size() && position != 0)
                            ? View.GONE
                            : View.VISIBLE
            );
        }
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.personName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    // Setter method to set the selected quote index
    public void setSelectedQuoteIndex(int index) {
        selectedQuoteIndex = index;

        // If a quote is selected, keep only that quote in the data list
        if (index >= 0 && index < mData.size()) {
            String selectedQuote = mData.get(index);
            mData.clear();
            mData.add(selectedQuote);
        }

        notifyDataSetChanged();
    }
}