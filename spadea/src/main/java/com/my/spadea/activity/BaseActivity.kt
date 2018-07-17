package com.my.spadea.activity

//    import kotlinx.android.synthetic.main.activity_main.*

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.my.spadea.activity.util.ActivityManager

/**
 * Function : 基类的Activity
 * Remarks  :调整接口方法调用顺序 实现出栈入栈管理,提供常用TAG
 * Created by Mr.C on 2018/5/8 0008.
 */
abstract class BaseActivity : AppCompatActivity(), BaseActivityInterface {

    val TAG: String? = javaClass.name

    var isLoad = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (layoutId() == -1) {
            throw RuntimeException("$TAG layoutId can not is -1 !")
        }
        setContentView(layoutId())
        ActivityManager.add(this)
        initView(savedInstanceState)
        initData(savedInstanceState, intent)
        initListener()
    }

    /**
     * 将网络资源访问放在此
     */
    override fun onResume() {
        super.onResume()
        if (!isLoad) {
            loadNetRes(intent)
            isLoad = !isLoad
        }
    }

    override fun onDestroy() {
        ActivityManager.remove(this)
        super.onDestroy()
    }

    /**
     * 获取传递来的值为控件赋值
     */
    open fun initData(savedInstanceState: Bundle?, intent: Intent?) {}

    /**
     * 为控件设置事件
     */
    open fun initListener() {}

    /**
     *  获取Intent传递来的值 进行网络访问
     */
    open fun loadNetRes(intent: Intent?) {}
}