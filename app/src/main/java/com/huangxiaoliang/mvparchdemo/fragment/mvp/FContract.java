package com.huangxiaoliang.mvparchdemo.fragment.mvp;


import com.huangxiaoliang.mvplib.mvp.IView;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;

/**
 * @author : HHotHeart
 * @date : 2021/8/14 11:50
 * @desc : 描述
 */
public class FContract {

    public interface MyFragmentModel {
        void requestNet(NetCallback<Long> netCallback);

    }

    public interface MyFragmentView extends IView {
        void showToast();

    }

    public interface MyFragmentPresenter {
        void loadData();

        default void onReload() {

        }
    }


}
