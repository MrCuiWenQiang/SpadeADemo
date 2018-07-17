package com.my.spadea.activity.fragment

import android.os.Bundle
import android.view.View

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/17 0017.
 */
interface BaseFragmentInterface {
    /**
     * 指定layout布局ID
     * @return
     */
    fun getLayoutId(): Int

    /**
     * 在此方法内初始化控件
     */
    fun initview(v: View?)

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     * @param savedInstanceState
     */
    fun initData(savedInstanceState: Bundle?)
}