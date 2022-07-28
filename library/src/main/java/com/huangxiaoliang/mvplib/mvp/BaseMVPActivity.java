package com.huangxiaoliang.mvplib.mvp;

import android.os.Bundle;

import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.util.ClassUtils;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/11 11:54</pre>
 * <pre>@desc MVP模式Activity基类</pre>
 */
@SuppressWarnings("rawtypes")
public abstract class BaseMVPActivity<P extends BasePresenter> extends BaseActivity {

    private P mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = null;
        mPresenter = ClassUtils.getSuperClassGenericType(this, 0);
        Objects.requireNonNull(mPresenter, "p can not be null");
        mPresenter.attachV(this);
        mPresenter.getMvpModel().setContext(getContext());
        mPresenter.getMvpModel().setIntent(getIntent());
        mPresenter.getMvpModel().initData();
        //Activity与presenter生命周期绑定
        getLifecycle().addObserver(mPresenter);
        super.onCreate(savedInstanceState);

    }

    /**
     * 获取Presenter实例
     *
     * @return see {@link BasePresenter}
     */
    public P getMvpPresenter() {
        return mPresenter;
    }

    /**
     * 重写拦截返回按键事件
     *
     * @return true 拦截，false 不拦截
     */
    @Override
    public boolean onInterceptBackPressed() {
        return mPresenter.onInterceptBackPressed();
    }

    /**
     * 在此之前会调用{@link BasePresenter#onDestroy(LifecycleOwner)}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(mPresenter);
        mPresenter = null;
        UILog.i("BaseMVPActivity onDestroy()被调用");
    }


//    Activity【onCreate】
//    Observer【onCreate】
//    Activity【onStart】
//    Observer【onStart】
//    Activity【onResume】
//    Observer【onResume】
//            ------------------------------
//    Observer【onPause】
//    Activity【onPause】
//    Observer【onStop】
//    Activity【onStop】
//    Observer【onDestroy】
//    Activity【onDestroy】

}
