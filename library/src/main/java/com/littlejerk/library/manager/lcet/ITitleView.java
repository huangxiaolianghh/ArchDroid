package com.littlejerk.library.manager.lcet;

import android.graphics.drawable.Drawable;

import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;

/**
 * @author : HHotHeart
 * @date : 2021/7/15 11:45
 * @desc : Title属性接口
 */
public interface ITitleView {

    /**
     * 左边图标相关属性接口
     */
    ITitleView setLeftIcon(@DrawableRes int leftIconRes);

    default ITitleView setLeftIcon(Drawable drawable) {
        return null;
    }

    int getLeftIcon();

    ITitleView setLeftIconVisible(boolean isLeftIconVisible);

    boolean isLeftIconVisible();

    /**
     * 中间title相关属性接口
     */
    ITitleView setMiddleText(String middleText);

    String getMiddleText();

    float getMiddleTextSize();

    ITitleView setMiddleTextSize(float middleTextSize);

    ITitleView setMiddleTextSize(@DimenRes int middleTitleSize);

    int getMiddleTextColor();

    ITitleView setMiddleTextColor(int middleTextColor);


    /**
     * 右边title相关属性接口
     */
    ITitleView setRightText(String rightText);

    String getRightText();

    ITitleView setRightTextSize(float rightTextSize);

    ITitleView setRightTextSize(@DimenRes int rightTextSizeRes);

    float getRightTextSize();

    ITitleView setRightTextColor(int rightTextColor);

    int getRightTextColor();


    /**
     * 右边图标相关属性接口
     */
    ITitleView setRightIcon(@DrawableRes int rightIconRes);

    default ITitleView setRightIcon(Drawable drawable) {
        return null;
    }

    int getRightIcon();

    ITitleView setRightIconVisible(boolean isRightIconVisible);

    boolean isRightIconVisible();


    /**
     * TittleBar相关属性接口
     */
    ITitleView setTittleBarBgColor(int tittleBarBgColor);

    int getTittleBarBgColor();

    ITitleView setTitleBarHeight(float titleBarHeight);

    ITitleView setTitleBarHeight(@DimenRes int titleBarHeight);

    float getTitleBarHeight();


    /**
     * TittleBar下方细线相关属性接口
     */
    ITitleView setBottomLineColor(int bottomLineColor);

    int getBottomLineColor();

    ITitleView setBottomLineHeight(float bottomLineHeight);

    ITitleView setBottomLineHeight(@DimenRes int bottomLineHeight);

    float getBottomLineHeight();

    boolean isBottomLineVisible();

    ITitleView setBottomLineVisible(boolean bottomLineVisible);

}
