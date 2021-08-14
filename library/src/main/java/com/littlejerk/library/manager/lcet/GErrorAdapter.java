package com.littlejerk.library.manager.lcet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanc.loadinghelper.LoadingHelper;
import com.littlejerk.library.R;

import androidx.annotation.NonNull;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/8 14:36
 * @Description : 错误视图适配器
 */
public class GErrorAdapter extends LoadingHelper.Adapter<GErrorAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.layout_error, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder) {
        holder.btnReload.setOnClickListener(v -> {
            if (holder.getOnReloadListener() != null) {
                holder.getOnReloadListener().onReload();
            }
        });
    }

    public static class ViewHolder extends LoadingHelper.ViewHolder {

        private final View btnReload;

        ViewHolder(@NonNull View rootView) {
            super(rootView);
            btnReload = rootView.findViewById(R.id.btn_reload);
        }
    }
}
