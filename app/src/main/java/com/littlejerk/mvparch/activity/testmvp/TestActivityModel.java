package com.littlejerk.mvparch.activity.testmvp;

import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseModel;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 15:46
 * @Description : 描述
 */
public class TestActivityModel extends BaseModel implements AContract.MyActivityModel {

    @Override
    protected void initData() {
        UIToast.showLong("测试TestModel");
    }

    @Override
    public void requestNet() {

    }
}
