package com.huangxiaoliang.mvplib.mvp;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.dylanc.viewbinding.base.ViewBindingUtil;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

/**
 * * @author HHotHeart
 * * @date 2022/6/25 11:12
 * * @desc ViewBinding功能的Activity基类
 */
public abstract class BaseBindingActivity<VB extends ViewBinding> extends BaseActivity {

    private VB binding;

    @Override
    protected final void initContentView(@Nullable Bundle savedInstanceState) {
        binding = ViewBindingUtil.inflateWithGeneric(this, getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * 外部不需要setContentView()
     *
     * @param view View
     */
    @Override
    public final void setContentView(View view) {
        super.setContentView(view);
    }

    /**
     * 外部不需要setContentView()
     *
     * @param layoutId Int
     */
    @Override
    public final void setContentView(int layoutId) {
        throw new IllegalArgumentException("BaseBindingActivity use ViewBinding，forbid setContentView()");
    }

    /**
     * 外部不需要setContentView()
     *
     * @param view   Activity的View
     * @param params LayoutParams
     */
    @Override
    public final void setContentView(View view, ViewGroup.LayoutParams params) {
        throw new IllegalArgumentException("BaseBindingActivity use ViewBinding，forbid setContentView()");
    }

    /**
     * 获取ViewBinding实例
     *
     * @return Binding实例
     */
    public VB getBinding() {
        return binding;
    }
}