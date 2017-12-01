package com.example.wojciechliebert.galerija;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.wojciechliebert.galerija.Adapters.GalleryPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagerActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        ButterKnife.bind(this);

        viewPager.setAdapter(new GalleryPagerAdapter(this, Utils.getImagesIdentifiers(this), viewPager));
    }
}
