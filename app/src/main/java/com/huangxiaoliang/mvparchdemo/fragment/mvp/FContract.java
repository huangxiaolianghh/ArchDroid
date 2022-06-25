package com.huangxiaoliang.mvparchdemo.fragment.mvp;


import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvplib.mvp.IView;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 11:50</pre>
 * <pre>@desc Fragment MVP 宿主类</pre>
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
