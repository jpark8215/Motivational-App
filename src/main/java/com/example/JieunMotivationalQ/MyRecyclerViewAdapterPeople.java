package com.example.JieunMotivationalQ;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.List;

public class MyRecyclerViewAdapterPeople extends RecyclerView.Adapter<MyRecyclerViewAdapterPeople.ViewHolder> {

    private final List<String> mDataNames;
    private final List<String> mDataPictures;
    private final LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private final Context context;

    // Data is passed into the constructor
    MyRecyclerViewAdapterPeople(Context context, List<String> dataNames, List<String> dataPictures) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataNames = dataNames;
        this.mDataPictures = dataPictures;
        this.context = context;
    }

    // Inflates the row layout from xml when needed
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_person, parent, false);
        return new ViewHolder(view);
    }

    // Binds the data to the TextView in each row
    @SuppressLint("DiscouragedApi")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String peopleNames = mDataNames.get(position);
        String peoplePictures = mDataPictures.get(position);

        //sets the correct name
        holder.myTextView.setText(peopleNames);

        //sets the correct picture
        holder.myCircularImageView.setImageResource(context.getResources().getIdentifier(
                "drawable/" + peoplePictures, null, context.getPackageName()));

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mDataNames.size();
    }


    // Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView myTextView;
        final CircularImageView myCircularImageView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.personName);
            myCircularImageView = itemView.findViewById(R.id.personPicture);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // Convenient method for getting data at click position
    String getItem(int id) {
        return mDataNames.get(id);
    }

    // Allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

