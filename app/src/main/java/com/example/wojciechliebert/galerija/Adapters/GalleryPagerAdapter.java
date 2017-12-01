package com.example.wojciechliebert.galerija.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wojciechliebert.galerija.R;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by wojciech.liebert on 28.11.2017.
 */

public class GalleryPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final LinkedList<Integer> mImageList;
    private final ViewPager mViewPager;

    private int mFocusedPage;

    public GalleryPagerAdapter(Context context, ArrayList<Integer> imageList, ViewPager viewPager) {
        mContext = context;
        mImageList = new LinkedList<>(imageList);
        mViewPager = viewPager;

        setWeirdListener();
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setWeirdListener() {
        mViewPager.clearOnPageChangeListeners();
        OnPageChangedLoopingListener mOnPageChangedLoopingListener = new OnPageChangedLoopingListener();
        mViewPager.addOnPageChangeListener(mOnPageChangedLoopingListener);
        mOnPageChangedLoopingListener.prepare();
        notifyDataSetChanged();
        final int index = ((Activity) mContext).getIntent().getIntExtra(GridGalleryAdapter.IMAGE_INDEX_EXTRA, 0);
        post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(index + 1, false);
            }
        });

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        int imgResource = mImageList.get(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.single_image, collection, false);
        ImageView image = layout.findViewById(R.id.single_image_image);
        Glide.with(mContext)
                .load(imgResource)
                .into(image);
        TextView tv = layout.findViewById(R.id.single_image_index);
        tv.setText(String.valueOf(position));
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public class OnPageChangedLoopingListener implements ViewPager.OnPageChangeListener {

        public void prepare() {
            int last = mImageList.getLast();
            int first = mImageList.getFirst();
            mImageList.addLast(first);
            mImageList.addFirst(last);
            post(new Runnable() {
                @Override
                public void run() {
                    mViewPager.setCurrentItem(1, false);
                }
            });
        }

        @Override
        public void onPageSelected(int position) {
            mFocusedPage = position;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int state) {

            if (state == ViewPager.SCROLL_STATE_IDLE) {
                if (mFocusedPage == 0) {
                    mViewPager.setCurrentItem(mImageList.size() - 2, false);
                } else if (mFocusedPage == mImageList.size() - 1) {
                    mViewPager.setCurrentItem(1, false);
                }
            }
        }
    }

    private boolean post(Runnable runnable) {
        return new android.os.Handler(mContext.getMainLooper()).post(runnable);
    }

    public void postGoTo(final int position) {
        post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(position);
            }
        });
    }
}
