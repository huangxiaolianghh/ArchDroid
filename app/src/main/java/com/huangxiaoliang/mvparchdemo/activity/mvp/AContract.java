package com.huangxiaoliang.mvparchdemo.activity.mvp;


import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvplib.mvp.IView;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 11:50</pre>
 * <pre>@desc 契约类</pre>
 */
public class AContract {

    public interface MyActivityModel {
        void requestNet(NetCallback<Long> netCallback);
    }

    public interface MyActivityView {
        void showToast();

    }

    public interface MyActivityPresenter {
        void loadData();

        default void onReload() {

        }
    }
}
