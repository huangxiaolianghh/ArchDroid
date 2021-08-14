package com.littlejerk.library.manager.lcet;

import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;

import com.blankj.utilcode.util.Utils;
import com.hjq.bar.OnTitleBarListener;
import com.littlejerk.library.util.CommonUtils;

import androidx.annotation.DimenRes;

import static com.littlejerk.library.util.CommonUtils.NO_COLOR;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/9 17:07
 * @Description : TitleBar属性
 */
public class TitleParam implements ITitleView {

    /**
     * 左边按钮元素
     */
    private int leftIconRes = -1;
    private boolean isLeftIconVisible = true;

    /**
     * 中间文字元素
     */
    private String middleText;
    private float middleTextSize;
    private int middleTextColor = NO_COLOR;

    /**
     * 右边文字元素
     */
    private String rightText;
    private float rightTextSize;
    private int rightTextColor = NO_COLOR;
    private int rightIconRes = -1;
    private boolean isRightIconVisible = false;

    /**
     * TitleBar背景色和高度元素
     */
    private int titleBarBgColor = NO_COLOR;
    private float titleBarHeight;

    /**
     * 底部下划线元素
     */
    private int bottomLineColor = NO_COLOR;
    private float bottomLineHeight;
    private boolean bottomLineVisible = true;

    private OnTitleBarListener onTitleBarListener;

    public TitleParam() {
    }

    public TitleParam(String middleText) {
        this.middleText = middleText;
    }

    @Override
    public int getLeftIcon() {
        return leftIconRes;
    }

    @Override
    public TitleParam setLeftIcon(int leftIconRes) {
        this.leftIconRes = leftIconRes;
        return this;
    }

    @Override
    public boolean isLeftIconVisible() {
        return isLeftIconVisible;
    }

    @Override
    public TitleParam setLeftIconVisible(boolean isLeftIconVisible) {
        this.isLeftIconVisible = isLeftIconVisible;
        return this;
    }


    @Override
    public String getMiddleText() {
        return middleText;
    }

    @Override
    public TitleParam setMiddleText(String middleText) {
        this.middleText = middleText;
        return this;
    }

    @Override
    public float getMiddleTextSize() {
        return middleTextSize;
    }

    @Override
    public TitleParam setMiddleTextSize(float middleTextSize) {
        this.middleTextSize = CommonUtils.applyDimension(middleTextSize, TypedValue.COMPLEX_UNIT_SP);
        return this;
    }

    @Override
    public TitleParam setMiddleTextSize(@DimenRes int middleTextSizeRes) {
        this.middleTextSize = Resources.getSystem().getDimension(middleTextSizeRes);
        return this;
    }

    @Override
    public int getMiddleTextColor() {
        return middleTextColor;
    }

    @Override
    public TitleParam setMiddleTextColor(int middleTextColor) {
        this.middleTextColor = middleTextColor;
        return this;
    }

    @Override
    public String getRightText() {
        return rightText;
    }

    @Override
    public TitleParam setRightText(String rightText) {
        this.rightText = rightText;
        return this;
    }

    @Override
    public int getRightTextColor() {
        return rightTextColor;
    }

    @Override
    public TitleParam setRightTextColor(int rightTextColor) {
        this.rightTextColor = rightTextColor;
        return this;
    }

    @Override
    public TitleParam setRightTextSize(float rightTextSize) {
        this.rightTextSize = CommonUtils.applyDimension(rightTextSize, TypedValue.COMPLEX_UNIT_SP);
        return this;
    }

    @Override
    public TitleParam setRightTextSize(@DimenRes int rightTextSizeRes) {
        this.rightTextSize = Utils.getApp().getResources().getDimension(rightTextSizeRes);
        return this;
    }

    @Override
    public float getRightTextSize() {
        return rightTextSize;
    }


    @Override
    public TitleParam setRightIcon(int rightIconRes) {
        this.rightIconRes = rightIconRes;
        return this;
    }

    @Override
    public int getRightIcon() {
        return rightIconRes;
    }

    @Override
    public TitleParam setRightIconVisible(boolean isRightIconVisible) {
        this.isRightIconVisible = isRightIconVisible;
        return this;
    }

    @Override
    public boolean isRightIconVisible() {
        return isRightIconVisible;
    }

    @Override
    public TitleParam setTittleBarBgColor(int titleBarBgColor) {
        this.titleBarBgColor = titleBarBgColor;
        return this;
    }

    @Override
    public int getTittleBarBgColor() {
        return titleBarBgColor;
    }


    @Override
    public TitleParam setTitleBarHeight(float titleBarHeightDp) {
        this.titleBarHeight = CommonUtils.applyDimension(titleBarHeightDp, TypedValue.COMPLEX_UNIT_DIP);
        return this;
    }

    @Override
    public TitleParam setTitleBarHeight(@DimenRes int titleBarHeightRes) {
        this.titleBarHeight = Utils.getApp().getResources().getDimension(titleBarHeightRes);
        return this;
    }

    @Override
    public float getTitleBarHeight() {
        return titleBarHeight;
    }

    @Override
    public TitleParam setBottomLineColor(int bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
        return this;
    }

    @Override
    public int getBottomLineColor() {
        return bottomLineColor;
    }

    @Override
    public TitleParam setBottomLineHeight(float bottomLineHeightDp) {
        this.bottomLineHeight = CommonUtils.applyDimension(bottomLineHeightDp, TypedValue.COMPLEX_UNIT_DIP);
        return this;
    }

    @Override
    public TitleParam setBottomLineHeight(@DimenRes int bottomLineHeightRes) {
        this.bottomLineHeight = Utils.getApp().getResources().getDimension(bottomLineHeightRes);
        return this;
    }

    @Override
    public float getBottomLineHeight() {
        return bottomLineHeight;
    }

    @Override
    public TitleParam setBottomLineVisible(boolean bottomLineVisible) {
        this.bottomLineVisible = bottomLineVisible;
        return this;
    }

    @Override
    public boolean isBottomLineVisible() {
        return bottomLineVisible;
    }


    public OnTitleBarListener getOnTitleBarListener() {
        return onTitleBarListener;
    }

    public TitleParam setOnTitleBarListener(OnTitleBarListener onTitleBarListener) {
        this.onTitleBarListener = onTitleBarListener;
        return this;
    }

    /**
     * TitleBar的简单实现
     */
    public static class SimpleTitleBarListener implements OnTitleBarListener {

        @Override
        public void onLeftClick(View view) {

        }

        @Override
        public void onTitleClick(View view) {

        }

        @Override
        public void onRightClick(View view) {

        }
    }

}
