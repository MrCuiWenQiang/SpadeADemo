package com.my.spadea.activity

import android.content.Intent
import android.os.Bundle

/**
 * Function : 规范 BaseActivity常用接口方法
 * Remarks  :
 * Created by Mr.C on 2018/5/8 0008.
 */
interface BaseActivityInterface {
    /**
     * 设置布局LayoutId
     */
    fun layoutId():Int = -1

    /**
     * 初始化控件 （使用findViewById()   or 为控件设置初始状态）
     */
    fun initView(savedInstanceState : Bundle?)

    /**
     * 获取传递来的值为控件赋值
     */
    fun initData(savedInstanceState : Bundle?,intent: Intent?)

    /**
     * 为控件设置事件
     */
    fun initListener()

    /**
     *  获取Intent传递来的值 进行网络访问
     */
    fun loadNetRes(intent: Intent?)
}