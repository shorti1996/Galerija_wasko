package com.example.wojciechliebert.galerija.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wojciechliebert.galerija.PagerActivity;
import com.example.wojciechliebert.galerija.R;

import java.util.ArrayList;

/**
 * Created by wojciech.liebert on 28.11.2017.
 */

public class GridGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String IMAGE_INDEX_EXTRA = "image-index-extra";

    private Context mContext;
    private ArrayList<Integer> mImageList;

    public GridGalleryAdapter(Context context, ArrayList<Integer> imageList) {
        mContext = context;
        mImageList = imageList;
    }

    private class GalleryItemViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ImageView galleryImage;

        public GalleryItemViewHolder(View itemView) {
            super(itemView);
            galleryImage = itemView.findViewById(R.id.gallery_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, PagerActivity.class);
            intent.putExtra(IMAGE_INDEX_EXTRA, getAdapterPosition());
            mContext.startActivity(intent);

            // Don't because the adapter is fucking stoopid
//            Pair<View, String> p1 = Pair.create(((View) galleryImage), "image_transition");
//            ActivityOptionsCompat options = ActivityOptionsCompat.
//                    makeSceneTransitionAnimation((Activity) mContext, p1);
//
//            mContext.startActivity(intent, options.toBundle());
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
