package com.developerjp.JieunMotivationalQ;

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

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MyRecyclerViewAdapterQuotes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<String> mData;
    private final LayoutInflater mInflater;
    private int selectedQuoteIndex = -1;

    private static final int VIEW_TYPE_QUOTE = 1;
    private static final int VIEW_TYPE_AD = 0;
    private static final int QUOTES_PER_AD = 8;

    // Data is passed into the constructor
    MyRecyclerViewAdapterQuotes(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public int getItemViewType(int position) {
        // Set view type based on position
        return (position > 0 && position % (QUOTES_PER_AD + 1) == 0) ? VIEW_TYPE_AD : VIEW_TYPE_QUOTE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_AD:
                view = mInflater.inflate(R.layout.adview_layout, parent, false);
                return new AdViewHolder(view);
            case VIEW_TYPE_QUOTE:
                view = mInflater.inflate(R.layout.cardview_quotes, parent, false);
                return new QuoteViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_AD:
                AdViewHolder adViewHolder = (AdViewHolder) holder;
                // Hide the first ad
                adViewHolder.adView.setVisibility(position > 0 ? View.VISIBLE : View.GONE);
                break;
            case VIEW_TYPE_QUOTE:
                QuoteViewHolder quoteViewHolder = (QuoteViewHolder) holder;
                // Calculate the position of the quote in the original data list
                int quotePosition = position - position / (QUOTES_PER_AD + 1);
                String quote = mData.get(quotePosition);
                quoteViewHolder.personNameTextView.setText(quote);
                quoteViewHolder.personNameTextView.setVisibility(View.VISIBLE);
                // Handle quote-specific binding
                break;
        }
    }

    @Override
    public int getItemCount() {
        // Return the count of both ad and quote views
        return mData.size() + (mData.size() / QUOTES_PER_AD);
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
    public static class AdViewHolder extends RecyclerView.ViewHolder {
        final AdView adView;

        AdViewHolder(View itemView) {
            super(itemView);
            adView = itemView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
        }
    }

    // Method to get a random quote index
    private int getRandomQuoteIndex() {
        if (mData.size() > 0) {
            Random random = new Random();
            // Exclude the currently selected quote index to avoid repetition
            int newIndex = selectedQuoteIndex;
            while (newIndex == selectedQuoteIndex) {
                newIndex = random.nextInt(mData.size());
            }
            selectedQuoteIndex = newIndex;
            return newIndex;
        } else {
            return -1;
        }
    }

    // Method to shuffle the list of quotes randomly
    private void shuffleQuotes() {
        Collections.shuffle(mData);
        notifyDataSetChanged();
    }

    // Setter method to set the selected quote index
    @SuppressLint("NotifyDataSetChanged")
    public void setSelectedQuoteIndex(int index) {
        // No need to clear mData if you want to show both ad and quote views
        shuffleQuotes();
    }
}
