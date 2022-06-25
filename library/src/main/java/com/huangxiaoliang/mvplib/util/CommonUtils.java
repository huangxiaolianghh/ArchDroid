package com.huangxiaoliang.mvplib.util;

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
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.huangxiaoliang.mvplib.R;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/6/23 19:40</pre>
 * <pre>@desc 公共类工具</pre>
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
     * @param view View
     * @return Activity
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
     * @param context  上下文
     * @param fileName 文件名
     * @return Bitmap图片
     */
    public static Bitmap getBitmapFromAssert(Context context, String fileName) {
        Bitmap bitmap = null;
        AssetManager assetManager = context.getAssets();
        try {
            //filename是assets目录下的图片名
            InputStream inputStream = assetManager.open(fileName);
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }

    /**
     * 根据宽高比设置显示宽高
     *
     * @param targetView 目标View
     * @param picWidth   宽
     * @param picHeight  高
     * @param isByWidth  是否以宽为基准
     */
    public static void resizeViewHeightAndWidth(@NonNull View targetView, float picWidth, float picHeight, boolean isByWidth) {
        targetView.post(() -> {
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
        });
    }

    /**
     * 判断是否为主线程
     *
     * @return 是否为主线程
     */
    public static boolean isMain() {
        return Looper.getMainLooper() == Looper.myLooper();
    }


    /**
     * 切换主线程
     *
     * @param runnable 任务Runnable
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
     * @param url    解码字符URL
     * @param defStr 默认字符
     * @return 解码后字符
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
     * @param url    编码字符URL
     * @param defStr 默认字符
     * @return 编码后字符
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
     * @param name 清单文件声明的metaName
     * @return meta Value值
     */
    public static String getManifestsMetaStr(String name) {
        String meta = "";
        try {
            meta = Utils.getApp()
                    .getPackageManager()
                    .getApplicationInfo(AppUtils.getAppPackageName(), PackageManager.GET_META_DATA)
                    .metaData.getString(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return meta;
    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
