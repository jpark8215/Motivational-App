package com.example.JieunMotivationalQ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class MyRecyclerViewAdapterQuotes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int selectedQuoteIndex = -1; // Initialize with an invalid index

    private final List<String> mData;
    private final LayoutInflater mInflater;

    private static final int VIEW_TYPE_QUOTE = 0;
    private static final int VIEW_TYPE_AD = 1;

    // Data is passed into the constructor
    MyRecyclerViewAdapterQuotes(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
            return VIEW_TYPE_AD;
        } else {
            return VIEW_TYPE_QUOTE;
        }
    }

    // Inflates the row layout from XML when needed
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_AD) {
            View view = mInflater.inflate(R.layout.cardview_quotes, parent, false);
            return new AdViewHolder(view);
        } else {
            View view = mInflater.inflate(R.layout.cardview_quotes, parent, false);
            return new QuoteViewHolder(view);
        }
    }

    // Binds the data to the ViewHolder
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuoteViewHolder) {
            QuoteViewHolder quoteViewHolder = (QuoteViewHolder) holder;
            String quote = mData.get(position);

            // Check if the current position is the selected quote index
            if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
                quoteViewHolder.personNameTextView.setText(mData.get(selectedQuoteIndex));
                quoteViewHolder.personNameTextView.setVisibility(View.VISIBLE);
            } else {
                quoteViewHolder.personNameTextView.setText(quote);
                quoteViewHolder.personNameTextView.setVisibility(
                        (selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size() && position != 0)
                                ? View.GONE
                                : View.VISIBLE
                );
            }
        }
    }

    // Total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ViewHolder for quotes
    private static class QuoteViewHolder extends RecyclerView.ViewHolder {
        final TextView personNameTextView;

        QuoteViewHolder(View itemView) {
            super(itemView);
            personNameTextView = itemView.findViewById(R.id.personName);
        }
    }

    // ViewHolder for AdView
    private static class AdViewHolder extends RecyclerView.ViewHolder {
        final AdView adView;

        AdViewHolder(View itemView) {
            super(itemView);
            adView = itemView.findViewById(R.id.adView);

            // Set the ad unit ID and load the ad here
//            adView.setAdUnitId("ca-app-pub-3940256099942544~3347511713");
            adView.setAdUnitId("ca-app-pub-2201141547916408~1568858261");


            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }

    // Setter method to set the selected quote index
    @SuppressLint("NotifyDataSetChanged")
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

