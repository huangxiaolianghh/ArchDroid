package com.littlejerk.library.manager;

import android.graphics.Color;

import com.littlejerk.library.R;
import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.lcet.TitleParam;

import androidx.annotation.LayoutRes;

/**
 * @author : HHotHeart
 * @date : 2021/7/6 12:35
 * @desc : 框架全局参数管理类
 */
public class MVPArchConfig {

    private static final String TAG = "MVPArchConfig";
    // #log
    public static String LOG_TAG = "MVPArch";
    public static String SP_TAG = "MVPArch";

    // #cache
    public static String CACHE_SP_NAME = "MVPArch_CACHE_SP";
    public static String CACHE_DISK_DIR = "MVPArch_CACHE_DISK";

//    // #router
//    public static int ROUTER_ANIM_ENTER = Router.RES_NONE;
//    public static int ROUTER_ANIM_EXIT = Router.RES_NONE;

    public static int IL_LOADING_RES = -1;
    public static int IL_ERROR_RES = -1;

    /**
     * 图片加载引擎
     */
    private IImageLoader mImageLoader;
    /**
     * TitleBar相关元素
     */
    private TitleParam mTitleParam;
    private int mTitleLayoutId;
    /**
     * 默认状态栏为白底黑字
     */
    private int mStatusBarColor = Color.WHITE;
    private boolean mLightStatusBar = true;

    /**
     * UILog日志开关，默认开启
     */
    private boolean mIsLoggable = true;

    private MVPArchConfig() {
    }

    public static MVPArchConfig getInstance() {
        return Holder.sMVPArchConfig;
    }

    private static class Holder {
        private static final MVPArchConfig sMVPArchConfig = new MVPArchConfig();
    }

    /**
     * 获取图片加载器，初始化工厂{@link com.littlejerk.library.manager.imageloader.ILFactory}
     * 默认使用框架的{@link com.littlejerk.library.manager.imageloader.GlideLoader}
     *
     * @return 图片加载器{@link IImageLoader}
     */
    public IImageLoader getImageLoader() {
        return mImageLoader;
    }


    /**
     * 设置图片加载器
     *
     * @param imageLoader 图片加载器{@link IImageLoader}
     * @return 建造者模式
     */
    public MVPArchConfig setImageLoader(IImageLoader imageLoader) {
        mImageLoader = imageLoader;
        return this;
    }

    /**
     * 获取全局标题
     *
     * @return 标题属性接口实现类{@link TitleParam}
     */
    public TitleParam getTitleParam() {
        //默认Title属性
        if (mTitleParam == null) {
            mTitleParam = new TitleParam()
                    .setLeftIcon(R.drawable.ic_arrow_back_black)
                    .setTittleBarBgColor(Color.WHITE)
                    .setTitleBarHeight(R.dimen.title_bar_height)
                    .setMiddleTextSize(17f)
                    .setMiddleTextColor(Color.BLACK);
        }
        return mTitleParam;
    }

    /**
     * 设置全局标题
     *
     * @param titleParam 标题属性接口实现类{@link TitleParam}
     */
    public MVPArchConfig setTitleParam(TitleParam titleParam) {
        mTitleParam = titleParam;
        return this;
    }

    /**
     * 获取标题布局
     * 使用框架{@link com.hjq.bar.TitleBar}
     *
     * @return 标题布局id
     */
    public int getTitleLayoutId() {
        return mTitleLayoutId;
    }

    /**
     * 设置标题布局
     * 使用框架{@link com.hjq.bar.TitleBar}
     *
     * @param titleLayoutId 标题布局id
     */
    public MVPArchConfig setTitleLayoutId(@LayoutRes int titleLayoutId) {
        mTitleLayoutId = titleLayoutId;
        return this;
    }


    /**
     * 获取状态栏设置亮或深色系
     *
     * @return 是否为亮系
     */
    public boolean isLightStatusBar() {
        return mLightStatusBar;
    }

    /**
     * 设置状态栏亮或深色系
     *
     * @param lightStatusBar 是否为亮系
     */
    public MVPArchConfig setLightStatusBar(boolean lightStatusBar) {
        mLightStatusBar = lightStatusBar;
        return this;
    }

    /**
     * 获取状态栏颜色值
     *
     * @return 状态栏颜色值
     */
    public int getStatusBarColor() {
        return mStatusBarColor;
    }

    /**
     * 设置状态栏颜色值
     *
     * @param statusBarColor 状态栏颜色值
     */
    public MVPArchConfig setStatusBarColor(int statusBarColor) {
        mStatusBarColor = statusBarColor;
        return this;
    }

    /**
     * 获取UILog日志开关状态
     *
     * @return 是否开启UILog日志
     */
    public boolean isLoggable() {
        return mIsLoggable;
    }

    /**
     * 设置UILog日志开关
     *
     * @param loggable 开启UILog日志
     * @return 建造者模式
     */
    public MVPArchConfig setLoggable(boolean loggable) {
        mIsLoggable = loggable;
        return this;
    }
}
