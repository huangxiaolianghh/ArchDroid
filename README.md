# MVPArch
[![](https://jitpack.io/v/HHotHeart/MVPArch.svg)](https://jitpack.io/#HHotHeart/MVPArch)

一个可有效提高Android开发效率的MVP框架

- 封装Activity/Fragment基类-BaseActivity/BaseFragment（Fragment懒加载开关配置）
- 封装Activity/Fragment基类-BaseBindingActivity/BaseBindingFragment，支持ViewBinding的泛型，无需设置contentView、layouId
- 封装MVP模式Activity/Fragment基类-BaseMVPActivity/BaseMVPFragmentV与P层生命周期监听和绑定，解决诸多内存泄漏问题；除此之外还扩展封装了BaseBindingMVPActivity/BaseBindingMVPFragment以支持ViewBinding
- 使用 <a href="https://github.com/DylanCaiCoding/LoadingStateView">LoadingStateView</a>实现可定制化的页面LCE视图
- LoadingDialog加载框定制化，可随意切换
- 添加P层按键返回事件拦截
- 使用<a href="https://github.com/getActivity/TitleBar">TitleBar </a>实现可全局配置、页面可定制化的Title，不用每个页面写繁琐的xml代码
- 沉浸式状态栏及状态栏颜色设置
- 封装了Log、Toast，可自定义代理实现自己的Log、Toast
- 封装了图片加载器、事件通知管理器，可通过配置切换


## 项目引入该库

在你的 Project build.gradle文件中添加：

```groovy
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
在你的 Module build.gradle文件中添加：

```groovy
	dependencies {
          	implementation 'com.github.HHotHeart:MVPArch:1.0.8-beta.6'
        }
```
ViewBinding的配置，在你的 Module build.gradle文件中添加：

```groovy
android {

 	...
 	
    buildFeatures {
        viewBinding true
    }

	...

}
```

## 效果图

<table>
    <tr>
        <td ><center><img src="https://img-blog.csdnimg.cn/3547e6c032fa46ef834dddeec3a5d2aa.jpg"  width="50%" ></center></td>
        <td ><center><img src="https://img-blog.csdnimg.cn/3029919ac0cf4c878badd4436163d3a5.jpg" width="50%"  ></center></td>
    </tr>
</table>

<table>
    <tr>
        <td ><center><img src="https://img-blog.csdnimg.cn/983043b6cd874cb98f0576e280f02fc2.jpg"  width="50%" ></center></td>
        <td ><center><img src="https://img-blog.csdnimg.cn/048f45ec5c164084a78d889a08f61bbc.jpg" width="50%"  ></center></td>
    </tr>
</table>

<table>
    <tr>
        <td ><center><img src="https://img-blog.csdnimg.cn/b0a60d71761b469590af1e8d1838a297.jpg"  width="50%" ></center></td>
        <td ><center><img src="https://img-blog.csdnimg.cn/5b1ae3942dbd4df78a0f08a16a8e4f60.jpg" width="50%"  ></center></td>
    </tr>
</table>

## 功能实现

1. UILog、UIToast

框架默认实现了UILog、UIToast的代理[UILogDelegate](https://github.com/HHotHeart/MVPArch/blob/master/library/src/main/java/com/huangxiaoliang/mvplib/manager/log/UILogDelegate.java)、[UIToastDelegate](https://github.com/HHotHeart/MVPArch/blob/master/library/src/main/java/com/huangxiaoliang/mvplib/manager/toast/UIToastDelegate.java)，如果不满足需求，可实现自己定义的代理(实现UILog.LogDelegate、UIToast.ToastDelegate即可)，具体可参考框架的实现，这里简单实现了CustomLogDelegate

```java
package com.huangxiaoliang.mvparchdemo.util;

import android.util.Log;

import com.huangxiaoliang.mvplib.manager.MVPArchConfig;
import com.huangxiaoliang.mvplib.manager.log.UILog;

/**
 * @author : HHotHeart
 * @date : 2021/9/24 10:01
 * @desc : 自定义代理
 */
public class CustomLogDelegate implements UILog.LogDelegate {

    @Override
    public String getTag() {
        return "日志Tag";
    }

    @Override
    public UILog.LogDelegate init() {
        //做一些初始化工作
        return this;
    }

    @Override
    public void v(String tag, String msg, Object... obj) {
        Log.v(tag, msg);
    }

    @Override
    public void d(String tag, String msg, Object... obj) {
        //自己的Log库
        Log.d(tag, msg);
    }

    @Override
    public void i(String tag, String msg, Object... obj) {
        //自己的Log库
        Log.i(tag, msg);
    }

    @Override
    public void w(String tag, String msg, Object... obj) {
        //自己的Log库
        Log.w(tag, msg);

    }

    @Override
    public void e(String tag, String msg, Object... obj) {
        //自己的Log库
        Log.e(tag, msg);
    }

    @Override
    public void xml(String tag, String msg) {
        //自己的Log库
    }

    @Override
    public void json(String tag, String msg) {
        //自己的Log库
    }

    @Override
    public void printErrStackTrace(String tag, Throwable throwable) {
        //自己的Log库
    }
}
```

然后在Application中将代理设置给UILog

```java
     MVPArchConfig.getInstance().setLogDelegate(new CustomLogDelegate().init())
```

Log日志开关可通过MVPArchConfig配置

```java
     MVPArchConfig.getInstance().setLoggable(BuildConfig.DEBUG)
```
Toast的代理设置，如

```java
     UIToast.setDelegate(UIToast.ToastDelegate delegate);
```

2. EventManager、ILFactory

框架默认实现了[EventBusImpl](https://github.com/HHotHeart/MVPArch/blob/master/library/src/main/java/com/huangxiaoliang/mvplib/manager/event/EventBusImpl.java)事件通知和[GlideLoader](https://github.com/HHotHeart/MVPArch/blob/master/library/src/main/java/com/huangxiaoliang/mvplib/manager/imageloader/GlideLoader.java)图片加载器，可以自由切换（实现IEventBus、IImageLoader接口即可），实现了之后可通过MVPArchConfig配置，如替换GlideLoader、EventBusImpl

```java
     MVPArchConfig.getInstance().setImageLoader(IImageLoader imageLoader)
     MVPArchConfig.getInstance().setEventBus(IEventBus eventBus);
```

两者调用方式

```java
     EventManager.getBus().post(IEventBus.AbsEvent event);
     ILFactory.getLoader().loadNet(ImageView target, String url, IImageLoader.HOptions options);
```
3. LCE-T

框架实现了L（加载视图）、C（内容视图）、E（错误视图、空视图）、T（标题）的逻辑处理，这里主要使用了两个库<a href="https://github.com/DylanCaiCoding/LoadingHelper">LoadingHelper</a>和<a href="https://github.com/getActivity/TitleBar">TitleBar </a>，具体实现原理可去Github上看看，框架可全局配置LCE-T，如

```java
        MVPArchConfig.getInstance()
                .setLogDelegate(new CustomLogDelegate().init())
                .setLoggable(BuildConfig.DEBUG)
                .setLightStatusBar(false)
                .setStatusBarColor(Color.BLACK)
                .setTitleParam(new TitleParam()
                        .setLeftIcon(R.drawable.ic_arrow_back_black)
                        .setMiddleTextSize(17f)
                        .setMiddleTextColor(Color.BLACK)
                        .setTitleBarHeight(R.dimen.title_bar_height)
                        .setTittleBarBgColor(Color.WHITE)
                        .setBottomLineColor(Color.LTGRAY)
                        .setBottomLineHeight(0.5f));

        //设置全局LCE
        LoadingStateView.setViewDelegatePool(pool ->
        pool.register(new GLoadingViewDelegate(), new GErrorViewDelegate(), new GEmptyViewDelegate()));

```
其中GLoadingViewDelegate、GErrorViewDelegate、GEmptyViewDelegate是框架实现的默认全局LCE，可参考将其替换成自己项目的LCE。因为LCE-T的设置是通过LCEDelegate（实现ILCEView）实现的，要想改变代理实现，可自定义代理CustomLCEDelegate实现ILCEView接口，然后通过清单文件AndroidManifest.xml去配置自定义的代理，如

```java
        <meta-data
            android:name="MVPArch.LCEDelegate"
            android:value="com.huangxiaoliang.mvparchdemo.util.CustomLCEDelegate" />
```

```java
package com.huangxiaoliang.mvparchdemo.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.dylanc.loadingstateview.LoadingStateView;
import com.huangxiaoliang.mvplib.manager.lcet.GTitleBarViewDelegate;
import com.huangxiaoliang.mvplib.manager.lcet.ILCEView;
import com.huangxiaoliang.mvplib.manager.lcet.ITitleView;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONObject;

/**
 * @author : HHotHeart
 * @date : 2021/7/9 17:36
 * @desc : 自定义LCE代理类，需在清单文件注册meta
 */
public class CustomLCEDelegate implements ILCEView {

    private Context mContext = null;
    /**
     * 加载中、加载失败、空布局视图 https://github.com/DylanCaiCoding/LoadingStateView
     */
    private LoadingStateView mLoadingStateView = null;
    /**
     * 加载框 https://github.com/Kaopiz/KProgressHUD
     */
    private KProgressHUD mKProgressHUD = null;

    public CustomLCEDelegate(View contentView) {
        mContext = contentView.getContext();
        mLoadingStateView = new LoadingStateView(contentView);
    }

    /**
     * 获取真正的RootView
     *
     * @return
     */
    @Override
    public View getDecorView() {
        return mLoadingStateView.getDecorView();
    }

    /**
     * 空数据视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateEmptyView() {
        mLoadingStateView.showEmptyView();
    }

    /**
     * 错误视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateErrorView() {
        mLoadingStateView.showErrorView();
    }

    /**
     * 加载中视图
     * 调用此方法确保mLoadingHelper注册对应的Adapter
     */
    @Override
    public void stateLoadingView() {
        mLoadingStateView.showLoadingView();
    }

    /**
     * 显示内容视图
     */
    @Override
    public void stateContentView() {
        mLoadingStateView.showContentView();
    }

    @Override
    public void loadingDialogShow() {
        loadingDialogShow(null);
    }

    @Override
    public void loadingDialogShow(boolean cancelable) {
        loadingDialogShow(null, cancelable);

    }

    @Override
    public void loadingDialogShow(String msg) {
        loadingDialogShow(msg, false);

    }

    @Override
    public void loadingDialogShow(String msg, boolean cancelable) {
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(mContext);
        }
        if (!TextUtils.isEmpty(msg)) {
            mKProgressHUD.setLabel(msg);
        } else {
            mKProgressHUD.setLabel(null);
        }
        mKProgressHUD.setCancellable(cancelable);
        mKProgressHUD.show();
    }

    /**
     * 显示加载框的扩展
     *
     * @param msg
     * @param cancelable
     * @param extraData  拓展json字符串
     */
    @Override
    public void loadingDialogShow(String msg, boolean cancelable, JSONObject extraData) {

    }

    /**
     * 关闭加载框
     */
    @Override
    public void loadingDialogDismiss() {
        if (mKProgressHUD != null && mKProgressHUD.isShowing()) {
            mKProgressHUD.dismiss();
        }
    }

    @Override
    public void onDecorateTitleBar(ITitleView titleView) {
        if (titleView != null) {
            mLoadingStateView.setHeaders(new GTitleBarViewDelegate(titleView));
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void release() {
        mKProgressHUD = null;
        mContext = null;
        mLoadingStateView = null;
    }

    public LoadingStateView getLoadingViewState() {
        return mLoadingStateView;
    }

    public KProgressHUD getLoadingDialog() {
        return mKProgressHUD;
    }
}
```

其中KProgressHUD 的加载框可改变，LoadingStateView不可更改（LCE的实现原理）。如果是页面定制化，应该如何呢？比如我们的标题

```java
package com.huangxiaoliang.mvparchdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTitleDemoBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.lcet.ITitleView;
import com.huangxiaoliang.mvplib.manager.lcet.TitleParam;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 15:42</pre>
 * <pre>@desc 标题属性Demo</pre>
 */
public class TitleDemoActivity extends BaseBindingActivity<ActivityTitleDemoBinding> {
    /**
     * {@link TitleParam}的属性设置并不是很全面，如果需要设置{@link com.hjq.bar.TitleBar}的更多属性，可以获取它的实例对象进行设置，
     * 或者自己利用当前页面的{@link com.dylanc.loadingstateview.LoadingStateView}实现标题栏，参考{@link CustomLCEActivity}
     */
    @Override
    public ITitleView getPageTitleView() {
        return new TitleParam("Title Demo")
                .setRightText("完成").setRightTextColor(Color.WHITE).setRightTextSize(17f)
                .setTittleBarBgColor(Color.RED)
                .setOnTitleBarListener(new TitleParam.SimpleTitleBarListener() {
                    @Override
                    public void onLeftClick(View view) {
                        finish();
                    }

                    @Override
                    public void onRightClick(View view) {
                        UIToast.showShort("点击完成");
                    }
                });
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        ILFactory.getLoader().loadNet(findViewById(R.id.imageView1),
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-06-29%2F5ef9b315417b8.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657890578&t=11177abaff83a7971b98f5a40b97d1b2",
                IImageLoader.HOptions.defaultOptions());
    }
}
```

我们需要继承框架的BaseActivity，如果是MVP架构，可继承BaseMVPActivity或BaseBindingMVPActivity（Fragment同理），页面的标题相关属性会覆盖全局配置的属性。当然，页面LCE的配置也是可覆盖全局配置的LCE，如

```java
package com.huangxiaoliang.mvparchdemo.activity;

import android.os.Bundle;

import com.dylanc.loadingstateview.LoadingStateView;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityCustomLceBinding;
import com.huangxiaoliang.mvparchdemo.delegate.CLoadingViewDelegate;
import com.huangxiaoliang.mvparchdemo.listener.NetCallback;
import com.huangxiaoliang.mvparchdemo.util.CustomLCEDelegate;
import com.huangxiaoliang.mvparchdemo.util.HttpUtils;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingActivity;

import androidx.annotation.Nullable;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/9/23 10:39</pre>
 * <pre>@desc 自定义加载布局Demo</pre>
 */
public class CustomLCEActivity extends BaseBindingActivity<ActivityCustomLceBinding> {

    @Override
    public String getPageTitle() {
        return "自定义LCE";
    }

    @Override
    public void onBeforeBusiness(@Nullable Bundle savedInstanceState) {
        LoadingStateView loadingHelper = ((CustomLCEDelegate) getLCEDelegate()).getLoadingViewState();
        loadingHelper.register(new CLoadingViewDelegate());
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        stateLoadingView();
        HttpUtils.requestNet(this, new NetCallback<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                stateContentView();
            }

            @Override
            public void onFailure(String msg) {
                stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }
}
```

更多用法查看代码。

4. MVP模式

框架简易封装了MVP架构，使用了lifecycle管理Activity、Fragment和P层的生命周期，使用[RxLifecycle](https://github.com/trello/RxLifecycle)管理Rxjava和Activity、Fragment的生命周期，有效地避免内存泄漏和P层销毁，延时任务造成的空指针问题，Activity（Fragment同理）业务逻辑实现的AContract管理MVP契约类

```java
/**
 * @author : HHotHeart
 * @date : 2021/8/14 11:50
 * @desc : 描述
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
```

P层实现

```java

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 11:53
 * @Description : Activity Presenter
 */
public class MvpDemoActivityPresenter extends BasePresenter<MvpDemoActivityModel, MvpDemoActivity>
        implements AContract.MyActivityPresenter {
    private static final String TAG = "MvpDemoActivityPresenter";


    @Override
    public void loadData() {
        getMvpView().stateLoadingView();
        getMvpModel().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getMvpView().addDispose(d);
            }

            @Override
            public void onSuccess(Long o) {
                getMvpView().stateContentView();
                getMvpView().showToast();
            }

            @Override
            public void onFailure(String msg) {
                getMvpView().stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }

    @Override
    public void onReload() {
        getMvpView().stateLoadingView();
        getMvpModel().requestNet(new NetCallback<Long>() {
            @Override
            public void onSubscribe(Disposable d) {
                getMvpView().addDispose(d);
            }

            @Override
            public void onSuccess(Long aLong) {
                getMvpView().stateContentView();

            }

            @Override
            public void onFailure(String msg) {
                getMvpView().stateErrorView();
                UIToast.showShort(msg);
            }
        });
    }

    @Override
    public void onResume(@NonNull @NotNull LifecycleOwner owner) {
        super.onResume(owner);
    }


    /**
     * BasePresenter实现了和Activity或Fragment生命周期绑定，重写即可
     *
     * @param owner
     */
    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        super.onDestroy(owner);
        UILog.d(TAG, TAG + " onDestroy被调用");

    }

}
```

V层实现

```java
package com.huangxiaoliang.mvparchdemo.activity.mvp;

import android.os.Bundle;

import com.huangxiaoliang.mvparchdemo.R;
import com.huangxiaoliang.mvparchdemo.databinding.ActivityTestMvpBinding;
import com.huangxiaoliang.mvplib.manager.imageloader.IImageLoader;
import com.huangxiaoliang.mvplib.manager.imageloader.ILFactory;
import com.huangxiaoliang.mvplib.manager.log.UILog;
import com.huangxiaoliang.mvplib.manager.toast.UIToast;
import com.huangxiaoliang.mvplib.mvp.BaseBindingMVPActivity;

/**
 * <pre>@author HHotHeart</pre>
 * <pre>@date 2021/8/14 15:09</pre>
 * <pre>@desc Activity MVP例子</pre>
 */
public class MvpDemoActivity extends BaseBindingMVPActivity<MvpDemoActivityPresenter, ActivityTestMvpBinding>
        implements AContract.MyActivityView {

    private static final String TAG = "MvpDemoActivity";

    @Override
    public String getPageTitle() {
        return "Activity MVP模式";
    }

    @Override
    protected void onBusiness(Bundle savedInstanceState) {
        getBinding().btnTest.setText("btnTest Toast");
        ILFactory.getLoader().loadNet(getBinding().imageView1,
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-06-29%2F5ef9b315417b8.jpg&refer=http%3A%2F%2Fpic1.win4000.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1657890578&t=11177abaff83a7971b98f5a40b97d1b2",
                IImageLoader.HOptions.defaultOptions());

        findView(R.id.btn_test, v -> {
                    UIToast.showLong("测试Toast");
                }
        );
        UILog.e(TAG, "isVisible：" + isVisible(R.id.btn_test));

        getMvpPresenter().loadData();
    }


    @Override
    public void showToast() {
        UIToast.showLong("loadData加载完成");
    }

}
```

M层实现

```java

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 15:46
 * @Description : 描述
 */
public class MvpDemoActivityModel extends BaseModel implements AContract.MyActivityModel {

    @Override
    protected void initData() {
        UIToast.showLong("测试TestModel");
    }

    @Override
    public void requestNet(NetCallback<Long> netCallback) {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        if (netCallback != null) {
                            netCallback.onSubscribe(d);
                        }
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (netCallback != null) {
                            netCallback.onSuccess(aLong);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (netCallback != null) {
                            netCallback.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {
                        UILog.e("onComplete()");
                    }
                });

    }
}
```
BaseActivity和BaseFragment实现了Disposable的管理，每执行一个Rxjava任务时，应手动调用方法

```java
 		getMvpView().addDispose(d);
```

添加任务的Disposable，在页面销毁时会把任务中断。除此之外还可以调用

```java
		observable.compose(bindUntilEvent(ActivityEvent event));
```
将Rxjava任务与页面生命周期绑定，ActivityEvent对应Actiivity的生命周期，如ActivityEvent.DESTROY，具体可查看RxLifecycle的用法。

<a href="https://blog.csdn.net/HHHceo">我的博客</a>