package com.example.theodhor.picasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Dori on 7/9/2016.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(mThumbIds[position]).centerCrop().fit().placeholder(R.drawable.no_image).into(imageView);
        return imageView;
    }

    // Keep all Images in array
    public String[] mThumbIds = {
            "http://lorempixel.com/image_output/food-q-c-200-200-7.jpg",
            "http://lorempixel.com/image_output/fashion-q-c-200-200-6.jpg",
            "http://lorempixel.com/image_output/animals-q-c-200-200-1.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-2.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-4.jpg",
            "http://lorempixel.com/image_output/technics-q-c-100-100-4.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-1.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-2.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-4.jpg",
            "http://lorempixel.com/image_output/technics-q-c-100-100-4.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-1.jpg",
            "http://lorempixel.com/image_output/nature-q-c-100-100-2.jpg",
    };
}