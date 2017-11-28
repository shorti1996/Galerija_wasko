package com.example.wojciechliebert.galerija;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wojciechliebert.galerija.Adapters.GridGalleryAdapter;

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
    protected void onStart() {
        super.onStart();

        galleryRv.setLayoutManager(new GridLayoutManager(this, GRID_SPAN_COUNT));
        galleryRv.setAdapter(new GridGalleryAdapter(this, Utils.getImagesIdentifiers(this)));
    }
}
