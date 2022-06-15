package com.huangxiaoliang.mvplib.manager.lcet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanc.loadingstateview.LoadingStateView;
import com.dylanc.loadingstateview.ViewType;
import com.huangxiaoliang.mvplib.R;

import androidx.annotation.NonNull;

/**
 * <pre>author huanghuahong</pre>
 * <pre>date 2022/6/15 18:44</pre>
 * <pre>desc 加载中视图</pre>
 */
public class GLoadingViewDelegate extends LoadingStateView.ViewDelegate {

    public GLoadingViewDelegate() {
        super(ViewType.LOADING);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return inflater.inflate(R.layout.layout_loading, parent, false);
    }
}