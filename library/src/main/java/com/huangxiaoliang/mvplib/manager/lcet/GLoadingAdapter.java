package com.huangxiaoliang.mvplib.manager.lcet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dylanc.loadinghelper.LoadingHelper;
import com.huangxiaoliang.mvplib.R;

import androidx.annotation.NonNull;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/8 14:05
 * @Description : 加载中视图适配器
 */
public class GLoadingAdapter extends LoadingHelper.Adapter<LoadingHelper.ViewHolder> {

    private int height = ViewGroup.LayoutParams.MATCH_PARENT;

    public GLoadingAdapter() {
    }

    public GLoadingAdapter(int height) {
        this.height = height;
    }

    @NonNull
    @Override
    public LoadingHelper.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_loading, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoadingHelper.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.getRootView().getLayoutParams();
        layoutParams.height = height;
        holder.getRootView().setLayoutParams(layoutParams);
    }
}