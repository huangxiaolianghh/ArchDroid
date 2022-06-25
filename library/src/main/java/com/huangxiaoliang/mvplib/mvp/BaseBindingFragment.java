package com.huangxiaoliang.mvplib.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dylanc.viewbinding.base.ViewBindingUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2022/6/25 19:00</pre>
 * <pre>@desc ViewBinding功能的Fragment基类</pre>
 */
public abstract class BaseBindingFragment<VB extends ViewBinding> extends BaseFragment {

    private VB binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = ViewBindingUtil.inflateWithGeneric(this, inflater, container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected final void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(binding.getRoot());
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
     * 外部不需要设置setContentView()
     *
     * @param view View
     */
    @Override
    public final void setContentView(View view) {
        super.setContentView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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