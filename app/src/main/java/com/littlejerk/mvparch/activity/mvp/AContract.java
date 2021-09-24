package com.littlejerk.mvparch.activity.mvp;


import com.littlejerk.library.mvp.IView;
import com.littlejerk.mvparch.listener.NetCallback;

/**
 * @author : HHotHeart
 * @date : 2021/8/14 11:50
 * @desc : 描述
 */
public class AContract {

    public interface MyActivityModel {
        void requestNet(NetCallback<Long> netCallback);
    }

    public interface MyActivityView extends IView {
        void showToast();

    }

    public interface MyActivityPresenter {
        void loadData();

        default void onReload() {

        }
    }
}
