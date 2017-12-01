package com.example.wojciechliebert.galerija;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.wojciechliebert.galerija.Adapters.GalleryPagerAdapter;
import com.example.wojciechliebert.galerija.Animations.ZoomOutPageTransformer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    final String imageIndex = "IMAGE_INDEX";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);

        mViewPager.setAdapter(new GalleryPagerAdapter(this, Utils.getImagesIdentifiers(this), mViewPager));
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
//        mImageIndex = mViewPager.getCurrentItem();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(imageIndex, mViewPager.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final int itemIndex = savedInstanceState.getInt(imageIndex, 0);
        ((GalleryPagerAdapter) mViewPager.getAdapter()).postGoTo(itemIndex);
    }

}
