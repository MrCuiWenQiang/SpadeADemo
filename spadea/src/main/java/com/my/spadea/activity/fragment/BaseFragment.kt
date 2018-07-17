package com.my.spadea.activity.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Function :基本的Fragment类
 * Remarks  :
 * Created by Mr.C on 2018/7/17 0017.
 */
abstract class BaseFragment : Fragment(), BaseFragmentInterface {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(getLayoutId(), container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        initview(view)
        initData(savedInstanceState)
        initListener()
    }

    /**
     * 自由选择实现
     * 此方法放置事件
     */
    fun initListener() {}
}