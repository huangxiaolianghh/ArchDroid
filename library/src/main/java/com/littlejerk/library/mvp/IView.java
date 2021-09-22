package com.littlejerk.library.mvp;

import android.app.Activity;
import android.view.View;

import com.littlejerk.library.manager.lcet.ILCEView;

import androidx.annotation.IdRes;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/6 0:57
 * @Description : 页面相关对外属性接口
 */
public interface IView extends ILCEView {

    Activity getContext();

    void inVisible(View view);

    void visible(View view);

    void gone(View view);

    boolean isVisible(View view);

    boolean isGone(View view);

    <V extends View> V findView(@IdRes int viewId);

    <V extends View> V findView(@IdRes int viewId, View.OnClickListener l);

    void addDispose(Disposable disposable);

    void unDispose();

}
