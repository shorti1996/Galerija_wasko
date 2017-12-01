package com.example.wojciechliebert.galerija;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.wojciechliebert.galerija.Adapters.GalleryPagerAdapter;
import com.example.wojciechliebert.galerija.Animations.DepthPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new GalleryPagerAdapter(this, Utils.getImagesIdentifiers(this), mViewPager));
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
    }
}
