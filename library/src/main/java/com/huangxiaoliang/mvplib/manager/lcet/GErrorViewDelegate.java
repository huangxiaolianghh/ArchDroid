package com.huangxiaoliang.mvplib.manager.lcet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanc.loadingstateview.LoadingStateView;
import com.dylanc.loadingstateview.ViewType;
import com.huangxiaoliang.mvplib.R;

import androidx.annotation.NonNull;

/**
 * <pre>@author huanghuahong</pre>
 * <pre>@date 2022/6/15 18:47</pre>
 * <pre>@desc 错误视图</pre>
 */
public class GErrorViewDelegate extends LoadingStateView.ViewDelegate {

    public GErrorViewDelegate() {
        super(ViewType.ERROR);
    }

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_mvparch_error, parent, false);
        view.findViewById(R.id.btn_reload).setOnClickListener(v -> {
            if (getOnReloadListener() != null) {
                getOnReloadListener().onReload();
            }
        });
        return view;
    }
}