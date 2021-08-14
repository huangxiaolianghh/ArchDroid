package com.littlejerk.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.littlejerk.library.manager.log.UILog;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * @Author : HHotHeart
 * @Time : 2021/6/1 11:26
 * @Description : 公共类工具
 */
public class CommonUtils {
    private static final String TAG = "CommonUtils";

    public static final int NO_COLOR = Color.TRANSPARENT;

    private CommonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 根据View获取Activity对象
     *
     * @param view
     * @return
     */
    public static Activity getActivityFromView(View view) {
        if (null != view) {
            Context context = view.getContext();
            while (context instanceof ContextWrapper) {
                if (context instanceof Activity) {
                    return (Activity) context;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        return null;
    }

    /**
     * 获取assets目录下的图片
     *
     * @param context
     * @param fileName
     * @return
     */
    public static Bitmap getBitmapFromAssert(Context context, String fileName) {
        Bitmap bitmap = null;
        AssetManager assetManager = context.getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);//filename是assets目录下的图片名
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }


    /**
     * 根据宽高比设置显示宽高
     *
     * @param targetView
     * @param picWidth
     * @param picHeight
     * @param isByWidth
     */
    public static void resizeViewHeightAndWidth(View targetView, float picWidth, float picHeight, boolean isByWidth) {
        if (targetView == null) return;

        targetView.post(new Runnable() {
            @Override
            public void run() {
                if (isByWidth) {
                    int width = targetView.getWidth();
                    ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
                    layoutParams.height = (int) (width * picHeight / picWidth);
                    targetView.setLayoutParams(layoutParams);
                } else {
                    int height = targetView.getHeight();
                    ViewGroup.LayoutParams layoutParams = targetView.getLayoutParams();
                    layoutParams.width = (int) (height * picWidth / picHeight);
                    targetView.setLayoutParams(layoutParams);
                }
            }
        });
    }

    /**
     * 判断是否为主线程
     *
     * @return
     */
    public static boolean isMain() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


    /**
     * 切换主线程
     *
     * @param runnable
     */
    public static void runOnMain(Runnable runnable) {
        if (runnable != null) {
            boolean isMain = Looper.getMainLooper() == Looper.myLooper();
            if (isMain) {
                runnable.run();
            } else {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(runnable);
            }

        }
    }

    /**
     * URL解码
     *
     * @param url
     * @param defStr
     * @return
     */
    public static String decodeUrlUTF8(String url, String defStr) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException mE) {
            mE.printStackTrace();
            url = defStr;
        }
        return url;
    }

    /**
     * URL编码
     *
     * @param url
     * @param defStr
     * @return
     */
    public static String encodeUrlUTF8(String url, String defStr) {
        try {
            url = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException mE) {
            mE.printStackTrace();
            url = defStr;
        }
        return url;
    }

    /**
     * 根据清单文件key获取value(String)
     *
     * @param name
     * @return
     */
    public static String getManifestsMetaStr(String name) {
        String meta = "";
        try {
            meta = Utils.getApp()
                    .getPackageManager()
                    .getApplicationInfo(AppUtils.getAppPackageName(), PackageManager.GET_META_DATA)
                    .metaData.getString(name);
        } catch (Exception ex) {
            UILog.e(TAG, ex.getMessage());
        }
        return meta;
    }

    /**
     * 不同单位的值转px
     *
     * @param value
     * @param unit
     * @return
     */
    public static float applyDimension(final float value, final int unit) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }
}
