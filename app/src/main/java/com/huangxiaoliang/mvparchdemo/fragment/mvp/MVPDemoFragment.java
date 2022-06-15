package com.huangxiaoliang.mvparchdemo.fragment.mvp;

import android.os.Bundle;
import android.widget.ImageView;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.FragmentTestMvpBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseMVPFragment;

import androidx.annotation.Nullable;
import butterknife.BindView;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/15 17:35
 * @Description : 描述
 */
public class MVPDemoFragment extends BaseMVPFragment<MVPDemoFragmentPresenter> implements FContract.MyFragmentView {
    private static final String TAG = "MVPDemoFragment";

    @BindView(R.id.imageView1)
    ImageView mImageView1;

    public static MVPDemoFragment newInstance(String data) {
        MVPDemoFragment demoFragment = new MVPDemoFragment();
        Bundle args = new Bundle();
        args.putString("MVP_DATA", data);
        demoFragment.setArguments(args);
        return demoFragment;
    }

    @Override
    protected void initContentView(@Nullable Bundle savedInstanceState) {
        FragmentTestMvpBinding binding = FragmentTestMvpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void doBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(mImageView1,
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=218024201,1599297029&fm=26&gp=0.jpg",
                IImageLoader.HOptions.defaultOptions());
        getMvpPresenter().loadData();

    }

    @Override
    public void showToast() {
        UIToast.showShort("loadData加载完成");
    }

}
