package com.littlejerk.library.manager.lcet;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dylanc.loadinghelper.LoadingHelper;
import com.littlejerk.library.R;

import androidx.annotation.NonNull;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/8 14:33
 * @Description : 空视图适配器
 */
public class GEmptyAdapter extends LoadingHelper.Adapter<LoadingHelper.ViewHolder> {

    @NonNull
    @Override
    public LoadingHelper.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new LoadingHelper.ViewHolder(inflater.inflate(R.layout.layout_empty, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LoadingHelper.ViewHolder holder) {

    }
}