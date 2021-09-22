package com.littlejerk.mvparch.activity;

import android.os.Bundle;

import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseActivity;
import com.littlejerk.mvparch.R;
import com.littlejerk.mvparch.listener.NetCallback;
import com.littlejerk.mvparch.util.HttpUtils;

import androidx.annotation.Nullable;

/**
 * @Author : HHotHeart
 * @Time : 2021/9/21 23:32
 * @Description : 加载Dialog样式Demo
 */
public class LoadingDialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_loading_dialog, "加载Dialog样式");

    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {

        findView(R.id.tv_content, view -> {
            loadingDialogShow();
            HttpUtils.requestNet(this, new NetCallback<Long>() {
                @Override
                public void onSuccess(Long aLong) {
                    loadingDialogDismiss();
                }

                @Override
                public void onFailure(String msg) {
                    loadingDialogDismiss();
                    UIToast.showShort(msg);

                }
            });
        });

    }

//    可重写替换原有的Dialog Loading样式
//    @Override
//    public void loadingDialogShow() {
//        super.loadingDialogShow();
//    }
//
//    @Override
//    public void loadingDialogDismiss() {
//        super.loadingDialogDismiss();
//    }
}