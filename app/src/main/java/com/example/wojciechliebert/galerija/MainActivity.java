package com.example.wojciechliebert.galerija;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    final int GRID_SPAN_COUNT = 2;

    @BindView(R.id.gallery_rv)
    RecyclerView galleryRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        galleryRv.setLayoutManager(new GridLayoutManager(this, GRID_SPAN_COUNT));
        galleryRv.setAdapter(new GridGalleryAdapter(this, getImagesIdentifiers()));
    }

    private ArrayList<Integer> getImagesIdentifiers() {

        int resID = 0;
        int imgnum = 1;
        ArrayList<Integer> images = new ArrayList<>();

        do {
            resID = getResources().getIdentifier("room" + String.format("%02d", imgnum), "drawable", getApplicationContext().getPackageName());
            if (resID != 0){
                images.add(resID);
            }
            imgnum++;
        }
        while (resID != 0);

        return images;
    }




    private class GridGalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
}
