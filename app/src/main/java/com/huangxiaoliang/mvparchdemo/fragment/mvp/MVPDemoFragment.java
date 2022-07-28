package com.huangxiaoliang.mvparchdemo.fragment.mvp;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.databinding.FragmentTestMvpBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.ImageLoaderFactory;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingMVPFragment;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/6/15 17:35</pre>
 * <pre>@desc MVP Fragment</pre>
 */
public class MVPDemoFragment extends BaseBindingMVPFragment<MVPDemoFragmentPresenter, FragmentTestMvpBinding>
        implements FContract.MyFragmentView {

    private static final String TAG = "MVPDemoFragment";

    public static MVPDemoFragment newInstance(String data) {
        MVPDemoFragment demoFragment = new MVPDemoFragment();
        Bundle args = new Bundle();
        args.putString("MVP_DATA", data);
        demoFragment.setArguments(args);
        return demoFragment;
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        getMvpPresenter().loadData();
    }

    @Override
    public void showImageView(String imgUrl) {
        ImageLoaderFactory.get().loadNet(getBinding().imageView1, imgUrl);
    }

    @Override
    public void showToast() {
        UIToast.showShort("loadData加载完成");
    }

}
