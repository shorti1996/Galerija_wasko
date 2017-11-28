package com.example.wojciechliebert.galerija.Adapters;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wojciechliebert.galerija.R;

import java.util.List;

/**
 * Created by wojciech.liebert on 28.11.2017.
 */

public class GridGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Integer> mImageList;

    public GridGalleryAdapter(Context context, List<Integer> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    private class GalleryItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView galleryImage;

        public GalleryItemViewHolder(View itemView) {
            super(itemView);
            galleryImage = itemView.findViewById(R.id.gallery_image);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gallery_item_vh, parent, false);
        int height = ((View)parent.getParent()).getHeight() / 4;
        itemView.setMinimumHeight(height);
        ((ConstraintLayout)itemView).setMaxHeight(height);
        return new GalleryItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ImageView imageView = ((GalleryItemViewHolder)holder).galleryImage;
        Glide.with(mContext)
                .load(mImageList.get(position))
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }
}
