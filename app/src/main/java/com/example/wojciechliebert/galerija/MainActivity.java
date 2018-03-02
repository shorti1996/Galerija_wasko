package com.example.wojciechliebert.galerija;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;

import com.example.wojciechliebert.galerija.Adapters.GridGalleryAdapter;
import com.example.wojciechliebert.galerija.Helpers.GravitySnapHelper;

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

        galleryRv.setLayoutManager(new GridLayoutManager(this, GRID_SPAN_COUNT));
        galleryRv.setAdapter(new GridGalleryAdapter(this, Utils.getImagesIdentifiers(this)));

        new GravitySnapHelper(Gravity.TOP, false, new GravitySnapHelper.SnapListener() {
            @Override
            public void onSnap(int position) {
                Log.d("Snapped", position + "");
            }
        }).attachToRecyclerView(galleryRv);
    }

}
