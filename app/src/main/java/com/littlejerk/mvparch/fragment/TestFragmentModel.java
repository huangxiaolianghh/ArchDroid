package com.littlejerk.mvparch.fragment;

import com.littlejerk.library.manager.toast.UIToast;
import com.littlejerk.library.mvp.BaseModel;

/**
 * @Author : HHotHeart
 * @Time : 2021/6/11 15:46
 * @Description : 描述
 */
public class TestFragmentModel extends BaseModel {

    @Override
    protected void initData() {
        UIToast.showLong("测试TestModel");
    }
}
