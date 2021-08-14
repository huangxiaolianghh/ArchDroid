package com.littlejerk.library.manager.lcet;

import android.app.Activity;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.dylanc.loadinghelper.LoadingHelper;
import com.hjq.bar.OnTitleBarListener;
import com.hjq.bar.TitleBar;
import com.littlejerk.library.R;
import com.littlejerk.library.manager.MVPArchConfig;

import androidx.annotation.NonNull;

import static com.littlejerk.library.util.CommonUtils.NO_COLOR;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/9 9:30
 * @Description : TitleBar适配器（简单实现）
 */
public class TitleBarAdapter extends LoadingHelper.Adapter<TitleBarAdapter.ViewHolder> {

    TitleParam titleParam;

    public TitleBarAdapter(ITitleView titleParam) {
        this.titleParam = (TitleParam) titleParam;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.layout_title_bar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder) {
        //TitleBar高度
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.titleBar.getLayoutParams();
        params.height = (int) MVPArchConfig.getInstance().getTitleParam().getTitleBarHeight();
        holder.titleBar.setLayoutParams(params);
        //左边按钮
        if (titleParam.isLeftIconVisible()) {
            int id = titleParam.getLeftIcon() == -1 ? MVPArchConfig.getInstance().getTitleParam().getLeftIcon() : titleParam.getLeftIcon();
            holder.titleBar.setLeftIcon(id);
        } else {
            holder.titleBar.setLeftIcon(null);
        }
        //中间文字
        if (!TextUtils.isEmpty(titleParam.getMiddleText())) {
            holder.titleBar.setTitle(titleParam.getMiddleText());
            if (NO_COLOR == titleParam.getMiddleTextColor()) {
                holder.titleBar.setTitleColor(MVPArchConfig.getInstance().getTitleParam().getMiddleTextColor());
            } else {
                holder.titleBar.setTitleColor(titleParam.getMiddleTextColor());
            }
            if (0 == titleParam.getMiddleTextSize()) {
                holder.titleBar.setTitleSize(TypedValue.COMPLEX_UNIT_PX, MVPArchConfig.getInstance().getTitleParam().getMiddleTextSize());
            } else {
                holder.titleBar.setTitleSize(TypedValue.COMPLEX_UNIT_PX, titleParam.getMiddleTextSize());
            }
        }
        //右边文字
        if (TextUtils.isEmpty(titleParam.getRightText())) {
            holder.titleBar.getRightView().setVisibility(View.GONE);
        } else {
            holder.titleBar.getRightView().setVisibility(View.VISIBLE);
            holder.titleBar.setRightTitle(titleParam.getRightText());
            if (NO_COLOR != titleParam.getRightTextColor()) {
                holder.titleBar.setRightColor(titleParam.getRightTextColor());
            }
            if (titleParam.getRightTextSize() > 0) {
                holder.titleBar.setRightSize(TypedValue.COMPLEX_UNIT_PX, titleParam.getRightTextSize());
            }
        }
        //TitleBar背景色
        if (NO_COLOR == titleParam.getTittleBarBgColor()) {
            holder.titleBar.setBackgroundColor(MVPArchConfig.getInstance().getTitleParam().getTittleBarBgColor());
        } else {
            holder.titleBar.setBackgroundColor(titleParam.getTittleBarBgColor());
        }
        //下划线
        if (!MVPArchConfig.getInstance().getTitleParam().isBottomLineVisible()) {
            holder.titleBar.setLineVisible(false);
        } else if (!titleParam.isBottomLineVisible()) {
            holder.titleBar.setLineVisible(false);
        } else {
            holder.titleBar.setLineVisible(true)
                    .setLineColor(MVPArchConfig.getInstance().getTitleParam().getBottomLineColor())
                    .setLineSize((int) MVPArchConfig.getInstance().getTitleParam().getBottomLineHeight());
            if (NO_COLOR == titleParam.getBottomLineColor()) {
                holder.titleBar.setBackgroundColor(MVPArchConfig.getInstance().getTitleParam().getTittleBarBgColor());
            } else {
                holder.titleBar.setBackgroundColor(titleParam.getTittleBarBgColor());
            }
        }
        //简单监听器
        if (titleParam.getOnTitleBarListener() != null) {
            holder.titleBar.setOnTitleBarListener(titleParam.getOnTitleBarListener());
        } else {
            holder.titleBar.setOnTitleBarListener(new OnTitleBarListener() {
                @Override
                public void onLeftClick(View view) {
                    holder.getActivity().finish();
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


    public static class ViewHolder extends LoadingHelper.ViewHolder {
        private final TitleBar titleBar;

        ViewHolder(@NonNull View rootView) {
            super(rootView);
            titleBar = rootView.findViewById(R.id.title_bar);
        }

        private Activity getActivity() {
            return (Activity) getRootView().getContext();
        }

    }

}