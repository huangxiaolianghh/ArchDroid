package com.huangxiaoliang.mvplib.manager.lcet;

import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dylanc.loadingstateview.LoadingStateView;
import com.dylanc.loadingstateview.ViewType;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.huangxiaoliang.mvplib.R;
import com.huangxiaoliang.mvplib.manager.MVPArchConfig;
import com.huangxiaoliang.mvplib.util.MvpArchUtils;

import androidx.annotation.NonNull;

import static com.huangxiaoliang.mvplib.util.MvpArchUtils.NO_COLOR;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/6/15 19:10</pre>
 * <pre>@desc 标题栏</pre>
 */
public class GTitleBarViewDelegate extends LoadingStateView.ViewDelegate {

    TitleParam titleParam;

    public GTitleBarViewDelegate(ITitleView titleParam) {
        super(ViewType.TITLE);
        this.titleParam = (TitleParam) titleParam;
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_mvparch_title_bar, parent, false);
        decorateView(view);
        return view;
    }

    /**
     * 设置标题栏属性
     *
     * @param view TitleBar
     */
    private void decorateView(View view) {

        //TitleBar高度
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.height = (int) MVPArchConfig.get().getTitleParam().getTitleBarHeight();
        view.setLayoutParams(params);
        TitleBar titleBar = view.findViewById(R.id.title_bar);
        //左边按钮
        if (titleParam.isLeftIconVisible()) {
            int id = titleParam.getLeftIcon() == -1 ? MVPArchConfig.get().getTitleParam().getLeftIcon() : titleParam.getLeftIcon();
            titleBar.setLeftIcon(id);
        } else {
            titleBar.setLeftIcon(null);
        }
        //中间文字
        if (!TextUtils.isEmpty(titleParam.getMiddleText())) {
            titleBar.setTitle(titleParam.getMiddleText());
            if (NO_COLOR == titleParam.getMiddleTextColor()) {
                titleBar.setTitleColor(MVPArchConfig.get().getTitleParam().getMiddleTextColor());
            } else {
                titleBar.setTitleColor(titleParam.getMiddleTextColor());
            }
            if (0 == titleParam.getMiddleTextSize()) {
                titleBar.setTitleSize(TypedValue.COMPLEX_UNIT_PX, MVPArchConfig.get().getTitleParam().getMiddleTextSize());
            } else {
                titleBar.setTitleSize(TypedValue.COMPLEX_UNIT_PX, titleParam.getMiddleTextSize());
            }
        }
        //右边文字和图标
        if (TextUtils.isEmpty(titleParam.getRightText()) && titleParam.getRightIcon() == -1) {
            titleBar.getRightView().setVisibility(View.GONE);
        } else {
            titleBar.getRightView().setVisibility(View.VISIBLE);
            titleBar.setRightTitle(titleParam.getRightText());
            if (NO_COLOR != titleParam.getRightTextColor()) {
                titleBar.setRightColor(titleParam.getRightTextColor());
            }
            if (titleParam.getRightTextSize() > 0) {
                titleBar.setRightSize(TypedValue.COMPLEX_UNIT_PX, titleParam.getRightTextSize());
            }
            if (titleParam.getRightIcon() != -1) {
                titleBar.setRightIcon(titleParam.getRightIcon());
            }
        }
        //TitleBar背景色
        if (NO_COLOR == titleParam.getTittleBarBgColor()) {
            titleBar.setBackgroundColor(MVPArchConfig.get().getTitleParam().getTittleBarBgColor());
        } else {
            titleBar.setBackgroundColor(titleParam.getTittleBarBgColor());
        }
        //下划线
        if (!MVPArchConfig.get().getTitleParam().isBottomLineVisible()) {
            titleBar.setLineVisible(false);
        } else if (!titleParam.isBottomLineVisible()) {
            titleBar.setLineVisible(false);
        } else {
            titleBar.setLineVisible(true)
                    .setLineSize((int) MVPArchConfig.get().getTitleParam().getBottomLineHeight());
            if (NO_COLOR == titleParam.getBottomLineColor()) {
                titleBar.setLineColor(MVPArchConfig.get().getTitleParam().getBottomLineColor());
            } else {
                titleBar.setLineColor(titleParam.getBottomLineColor());
            }
        }
        //简单监听器
        if (titleParam.getOnTitleBarListener() != null) {
            titleBar.setOnTitleBarListener(titleParam.getOnTitleBarListener());
        } else {
            titleBar.setOnTitleBarListener(new OnTitleBarListener() {
                @Override
                public void onLeftClick(View view) {
                    MvpArchUtils.getActivityFromView(titleBar).finish();
                }

                @Override
                public void onTitleClick(View view) {

                }

                @Override
                public void onRightClick(View view) {

                }
            });

        }

    }
}