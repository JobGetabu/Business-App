package com.job.businessapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.DisplayMetrics;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

/**
 * Created by JOB on 8/24/2017.
 */

public class ImageRequester {

    private static volatile ImageRequester instance;
    private final RequestQueue mRequestQueue;
    private static ImageLoader loader;
    private final int maxSize;


    private ImageRequester(Context ctx){
        this.mRequestQueue =  Volley.newRequestQueue(ctx.getApplicationContext());
        mRequestQueue.start();

        maxSize = calculateMaxBytes(ctx);
        loader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            final LruCache<String,Bitmap> lruCache = new LruCache<String,Bitmap>(maxSize){
                @Override
                protected int sizeOf(String url, Bitmap bitmap) {
                    return bitmap.getByteCount();
                }
            };
            @Override
            public synchronized Bitmap getBitmap(String url) {
                return lruCache.get(url);
            }

            @Override
            public synchronized void putBitmap(String url, Bitmap bitmap) {
                 lruCache.put(url,bitmap);
            }
        });
    }

    public static synchronized ImageRequester getInstance(Context ctx) {
        ImageRequester result = instance;
        if (instance == null) {
            synchronized (ImageRequester.class){
                result = instance;
                if (result == null) {
                    result = instance = new ImageRequester(ctx);
                }
            }
        }
        return result;
    }

    public static void setImagFromUrlr(NetworkImageView view, String url){
        view.setImageUrl(url, loader);
    }

    private int calculateMaxBytes(Context ctx){
        DisplayMetrics displayMetrics = ctx.getResources().getDisplayMetrics();
        final int screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        return screenBytes * 3;
    }

}
