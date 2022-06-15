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
 * <pre>date 2022/6/15 18:50</pre>
 * <pre>desc 空视图</pre>
 */
public class GEmptyViewDelegate extends LoadingStateView.ViewDelegate {

    public GEmptyViewDelegate() {
        super(ViewType.EMPTY);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return inflater.inflate(R.layout.layout_empty, parent, false);
    }
}