package com.example.wojciechliebert.galerija;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by wojciech.liebert on 28.11.2017.
 */

public class Utils {

    public static ArrayList<Integer> getImagesIdentifiers(Context context) {
        int resID;
        int imgnum = 5;
        ArrayList<Integer> images = new ArrayList<>();

        do {
            resID = context.getResources()
                    .getIdentifier("room" + String.format("%02d", imgnum),
                            "drawable", context.getPackageName());
            if (resID != 0){
                images.add(resID);
            }
            imgnum++;
        }
        while (resID != 0);

        return images;
    }

}
