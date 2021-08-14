package com.littlejerk.mvparch.activity.testmvp;


import com.littlejerk.library.mvp.IView;

/**
 * @author : HHotHeart
 * @date : 2021/8/14 11:50
 * @desc : 描述
 */
public class AContract {

    public interface MyActivityModel {
        void requestNet();
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
