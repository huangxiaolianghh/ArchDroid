package com.littlejerk.mvparch.fragment.mvp;

import android.os.Bundle;
import android.widget.ImageView;

import com.littlejerk.library.manager.imageloader.IImageLoader;
import com.littlejerk.library.manager.imageloader.ILFactory;
import com.littlejerk.library.manager.log.UILog;
import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseMVPFragment;
import com.littlejerk.mvparch.R;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/15 17:35
 * @Description : 描述
 */
public class MVPDemoFragment extends BaseMVPFragment<MVPDemoFragmentPresenter> implements FContract.MyFragmentView {
    private static final String TAG = "TestMVPFragment";

    @BindView(R.id.imageView1)
    ImageView mImageView1;

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.fragment_test_mvp);
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(mImageView1,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());
        getP().loadData();

    }

    @Override
    public void showToast() {
        UIToast.showShort("loadData加载完成");
    }

}
