package com.huangxiaoliang.mvparchdemo.delegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanc.loadingstateview.LoadingStateView;
import com.dylanc.loadingstateview.ViewType;
import com.huangxiaoliang.mvparchdemo.R;

import androidx.annotation.NonNull;

/**
 * @author : HHotHeart
 * @date : 2021/9/23 10:04
 * @desc : 自定义加载布局
 */
public class CLoadingViewDelegate extends LoadingStateView.ViewDelegate {

    public CLoadingViewDelegate() {
        super(ViewType.LOADING);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return inflater.inflate(R.layout.layout_loading_custom, parent, false);
    }
}