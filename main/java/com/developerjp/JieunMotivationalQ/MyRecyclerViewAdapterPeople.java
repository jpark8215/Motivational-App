package com.developerjp.JieunMotivationalQ;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class MyRecyclerViewAdapterPeople extends RecyclerView.Adapter<MyRecyclerViewAdapterPeople.ViewHolder> {

    private final List<String> mDataNames;
    private final List<String> mDataPictures;
    private final LayoutInflater mInflater;
    private final Context context;
    private ItemClickListener mClickListener;

    MyRecyclerViewAdapterPeople(Context context, List<String> dataNames, List<String> dataPictures) {
        this.mInflater = LayoutInflater.from(context);
        this.mDataNames = dataNames;
        this.mDataPictures = dataPictures;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String peopleNames = mDataNames.get(position);
        String peoplePictures = mDataPictures.get(position);

        // Set the correct name
        holder.myTextView.setText(peopleNames);

        // Set the correct picture
        int drawableResourceId = getDrawableResourceByName(peoplePictures);
        if (drawableResourceId != 0) {
            Glide.with(holder.itemView)
                    .load(drawableResourceId)
                    .into(holder.myCircularImageView);
        } else {
            // Handle case where resource is not found
            holder.myCircularImageView.setImageResource(R.drawable.main_image);
        }
    }

    @Override
    public int getItemCount() {
        return mDataNames.size();
    }

    String getItem(int id) {
        return mDataNames.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    private int getDrawableResourceByName(String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

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
            int position = getBindingAdapterPosition();
            if (position == RecyclerView.NO_POSITION) return;
            if (mClickListener != null) mClickListener.onItemClick(view, position);
        }
    }
}
