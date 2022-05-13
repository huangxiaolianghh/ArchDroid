package com.huangxiaoliang.mvparchdemo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dylanc.loadinghelper.LoadingHelper;
import com.huangxiaoliang.mvparchdemo.R;

import androidx.annotation.NonNull;

/**
 * @author : HHotHeart
 * @date : 2021/9/23 10:04
 * @desc : 自定义加载布局
 */
public class CLoadingAdapter extends LoadingHelper.Adapter<LoadingHelper.ViewHolder> {

    private int height = ViewGroup.LayoutParams.MATCH_PARENT;

    public CLoadingAdapter() {
    }

    public CLoadingAdapter(int height) {
        this.height = height;
    }

    @NonNull
    @Override
    public LoadingHelper.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_loading_custom, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoadingHelper.ViewHolder holder) {
        ViewGroup.LayoutParams layoutParams = holder.getRootView().getLayoutParams();
        layoutParams.height = height;
        holder.getRootView().setLayoutParams(layoutParams);
    }
}