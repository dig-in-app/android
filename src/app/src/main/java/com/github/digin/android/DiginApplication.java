package com.github.digin.android;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.github.digin.android.bitmap.BitmapCacheHost;
import com.github.digin.android.constants.ParseKeys;
import com.github.digin.android.listeners.OnChefQueryListener;
import com.github.digin.android.logging.Logger;
import com.github.digin.android.models.Chef;
import com.github.digin.android.repositories.ChefsStore;
import com.google.android.gms.cast.Cast;
import com.parse.Parse;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;

import java.util.List;

/**
 * Created by david on 7/16/14.
 */
@ReportsCrashes(
        formKey = "", // will not be used
        mailTo = "dmtschida2@gmail.com",
        customReportContent = { ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT },
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.crash_toast_text
)
public class DiginApplication extends Application implements BitmapCacheHost {


    private LruCache<String, Bitmap> mMemoryCache;


    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, ParseKeys.getAppId(this), ParseKeys.getClientKey(this));
        initMemoryCache();

        ACRA.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        clearCache();
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        assert (mMemoryCache != null);
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        assert (mMemoryCache != null);
        return mMemoryCache.get(key);
    }

    public void initMemoryCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 15;

        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
            }
        };
    }

    public void clearCache() {
        mMemoryCache.evictAll();
    }

}
