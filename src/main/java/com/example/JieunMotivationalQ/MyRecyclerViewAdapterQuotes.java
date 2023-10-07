package com.example.JieunMotivationalQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class MyRecyclerViewAdapterQuotes extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int selectedQuoteIndex = -1; // Initialize with an invalid index

    private final List<String> mData;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private final AdRequest adRequest;

    private static final int VIEW_TYPE_QUOTE = 0;
    private static final int VIEW_TYPE_AD = 1;

    // data is passed into the constructor
    MyRecyclerViewAdapterQuotes(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.adRequest = new AdRequest.Builder().build();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
            return VIEW_TYPE_AD;
        } else {
            return VIEW_TYPE_QUOTE;
        }
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_QUOTE) {
            View view = mInflater.inflate(R.layout.cardview_quotes, parent, false);
            return new QuoteViewHolder(view);
        } else {
            View adView = mInflater.inflate(R.layout.ad_view_item, parent, false);
            return new AdViewHolder(adView);
        }
    }

    // binds the data to the TextView
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof QuoteViewHolder) {
            QuoteViewHolder quoteViewHolder = (QuoteViewHolder) holder;
            String quote = mData.get(position);

            // Check if the current position is the selected quote index
            if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
                quoteViewHolder.myTextView.setText(mData.get(selectedQuoteIndex));
                quoteViewHolder.myTextView.setVisibility(View.VISIBLE);
            } else {
                quoteViewHolder.myTextView.setText(quote);
                quoteViewHolder.myTextView.setVisibility(
                        (selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size() && position != 0)
                                ? View.GONE
                                : View.VISIBLE
                );
            }
        } else if (holder instanceof AdViewHolder) {
            // Handle the AdViewHolder
            AdViewHolder adViewHolder = (AdViewHolder) holder;
            AdView adView = adViewHolder.mAdView;
            adView.loadAd(adRequest);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ViewHolder for quotes
    private static class QuoteViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        QuoteViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.personName);
        }
    }

    // ViewHolder for ads
    private static class AdViewHolder extends RecyclerView.ViewHolder {
        AdView mAdView;

        AdViewHolder(View itemView) {
            super(itemView);
            mAdView = itemView.findViewById(R.id.adView);
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

//package com.example.JieunMotivationalQ;
//
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//
//import java.util.List;
//
//public class MyRecyclerViewAdapterQuotes extends RecyclerView.Adapter<MyRecyclerViewAdapterQuotes.ViewHolder> {
//    private int selectedQuoteIndex = -1; // Initialize with an invalid index
//
//    private final List<String> mData;
//    private final LayoutInflater mInflater;
//    private ItemClickListener mClickListener;
//    private final AdRequest adRequest;
//
//    private static final int VIEW_TYPE_QUOTE = 0;
//    private static final int VIEW_TYPE_AD = 1;
//
//    // data is passed into the constructor
//    MyRecyclerViewAdapterQuotes(Context context, List<String> data) {
//        this.mInflater = LayoutInflater.from(context);
//        this.mData = data;
//        this.adRequest = new AdRequest.Builder().build();
//
//    }
//
//
//    // inflates the row layout from xml when needed
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.cardview_quotes, parent, false);
//        return new ViewHolder(view);
//
//    }
//
//
//    // binds the data to the TextView
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        String quote = mData.get(position);
//
//
//        // Check if the current position is the selected quote index
//        if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
//            holder.myTextView.setText(mData.get(selectedQuoteIndex));
//            holder.myTextView.setVisibility(View.VISIBLE);
//
//        } else {
//            holder.myTextView.setText(quote);
//            holder.myTextView.setVisibility(
//                    (selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size() && position != 0)
//                            ? View.GONE
//                            : View.VISIBLE
//            );
//        }
//    }
//
//
//    // total number of rows
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//
//    // stores and recycles views as they are scrolled off screen
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView myTextView;
//
//        ViewHolder(View itemView) {
//            super(itemView);
//            myTextView = itemView.findViewById(R.id.personName);
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View view) {
//            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
//        }
//    }
//
//    // ViewHolder for ads
//    private class AdViewHolder extends RecyclerView.ViewHolder {
//        AdView mAdView;
//
//        AdViewHolder(View itemView) {
//            super(itemView);
//            mAdView = itemView.findViewById(R.id.adView);
//        }
//    }
//
//
//    // return the appropriate view type based on the position
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0 && selectedQuoteIndex >= 0 && selectedQuoteIndex < mData.size()) {
//            return VIEW_TYPE_AD;
//        } else {
//            return VIEW_TYPE_QUOTE;
//        }
//    }
//
//
//    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
//    void setClickListener(ItemClickListener itemClickListener) {
//        this.mClickListener = itemClickListener;
//    }
//
//    // parent activity will implement this method to respond to click events
//    public interface ItemClickListener {
//        void onItemClick(View view, int position);
//    }
//
//
//    // Setter method to set the selected quote index
//    public void setSelectedQuoteIndex(int index) {
//        selectedQuoteIndex = index;
//
//        // If a quote is selected, keep only that quote in the data list
//        if (index >= 0 && index < mData.size()) {
//            String selectedQuote = mData.get(index);
//            mData.clear();
//            mData.add(selectedQuote);
//        }
//
//        notifyDataSetChanged();
//    }
//}