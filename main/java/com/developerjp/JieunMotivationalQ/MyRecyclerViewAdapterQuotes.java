package com.developerjp.JieunMotivationalQ;

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

public class MyRecyclerViewAdapterQuotes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_QUOTE = 1;
    private static final int VIEW_TYPE_AD = 0;
    private static final int QUOTES_PER_AD = 8;
    private final List<String> mData;
    private final LayoutInflater mInflater;
    private final int selectedQuoteIndex = -1;

    // Data is passed into the constructor
    MyRecyclerViewAdapterQuotes(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        Collections.shuffle(this.mData);
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
                adViewHolder.adView.setVisibility(View.VISIBLE);
                break;
            case VIEW_TYPE_QUOTE:
                QuoteViewHolder quoteViewHolder = (QuoteViewHolder) holder;
                int quotePosition = position - position / (QUOTES_PER_AD + 1);
                String fullQuote = mData.get(quotePosition);

                String quoteText = fullQuote;
                String quoteAuthor = "";

                int lastHyphen = fullQuote.lastIndexOf(" - ");
                if (lastHyphen != -1) {
                    quoteText = fullQuote.substring(0, lastHyphen).trim();
                    quoteAuthor = fullQuote.substring(lastHyphen).trim();
                }

                quoteViewHolder.quoteTextView.setText(quoteText);
                quoteViewHolder.authorTextView.setText(quoteAuthor);
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
        final TextView quoteTextView;
        final TextView authorTextView;

        QuoteViewHolder(View itemView) {
            super(itemView);
            quoteTextView = itemView.findViewById(R.id.quote_text);
            authorTextView = itemView.findViewById(R.id.quote_author);
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
}
