package com.example.theodhor.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.RequestHandler;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    ImageView firstImage, secondImage, thirdImage;

    private static final String IMAGE_1 = "http://uxmastery.com/wp-content/uploads/2015/06/sitepoint-logo-195x195.jpg";
    private static final String IMAGE_2 = "http://uxmastery.com/wp-content/uploads/2015/06/sitepoint-logo-195x195.jpg";
    private static final String IMAGE_3 = "https://www.sitepoint.com/premium/books/git-fundamentals/online/images/sitepoint-logo-2012.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstImage = (ImageView)findViewById(R.id.image_1);
        secondImage = (ImageView)findViewById(R.id.image_2);
        thirdImage = (ImageView)findViewById(R.id.image_3);

        //The simplest way
        Picasso.with(this).load(IMAGE_1).centerCrop().fit().into(firstImage);


        //First method
        Picasso.with(this)
                .load(IMAGE_1)
                .centerCrop()
                .fit()
                .placeholder(getResources().getDrawable(R.drawable.placeholder,null))
                .error(getResources().getDrawable(R.drawable.no_image,null))
                .rotate(45)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .priority(Picasso.Priority.HIGH)
                .into(firstImage);

        //Second method
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                secondImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                secondImage.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                secondImage.setImageDrawable(getResources().getDrawable(R.drawable.prepare_load,null));
            }
        };

        target.onBitmapFailed(getResources().getDrawable(R.drawable.no_image,null));

        Picasso.with(this).load(IMAGE_2).priority(Picasso.Priority.HIGH).into(target);

        //Third method
        Picasso.Builder picassoBuilder = new Picasso.Builder(this);
        //picassoBuilder.indicatorsEnabled(true)
        picassoBuilder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.e("TAG","Failed");
            }
        });
        Picasso picasso = picassoBuilder.build();
        picasso.setLoggingEnabled(true);
        picasso.setIndicatorsEnabled(true);
        picasso.load(IMAGE_3).centerCrop().resize(400,100).placeholder(R.drawable.no_image).into(thirdImage);

    }
}