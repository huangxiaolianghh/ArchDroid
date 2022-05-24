package com.huangxiaoliang.mvplib.mvp;


/**
 * @author : HHotHeart
 * @date : 2021/6/11 00:05
 * @desc : 描述
 */
public interface IPresenter<V> {

    /**
     * 绑定View
     *
     * @param view View实例
     */
    void attachV(V view);

    /**
     * 当前Presenter是否有View的实例
     *
     * @return
     */
    boolean isAttachV();


//    /**
//     * 在框架中 {@link Activity#onStart()}  时会默认调用 {@link IPresenter#onStart()}
//     */
//    default void onStart() {
//
//    }
//
//    /**
//     * 在框架中 {@link Activity#onResume()}  时会默认调用 {@link IPresenter#onResume()}
//     */
//    default void onResume() {
//
//    }
//
//    /**
//     * 在框架中 {@link Activity#onPause()}  时会默认调用 {@link IPresenter#onPause()}
//     */
//    default void onPause() {
//
//    }
//
//    /**
//     * 在框架中 {@link Activity#onStop()}  时会默认调用 {@link IPresenter#onStop()}
//     */
//    default void onStop() {
//
//    }
//
//    /**
//     * 在框架中 {@link Activity#onDestroy()}  时会默认调用 {@link IPresenter#onDestroy()}
//     */
//    default void onDestroy() {
//
//    }
}
