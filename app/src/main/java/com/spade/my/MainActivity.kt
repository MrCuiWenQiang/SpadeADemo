package com.spade.my

import android.content.Intent
import android.os.Bundle
import com.my.spadea.activity.BaseActivity
import com.my.spadea.net.NetHelper
import com.my.spadea.net.callback.HttpConnectCallBack
import com.my.spadea.util.LogUtil

class MainActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.ac_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        LogUtil.isDebug = true
        val params = hashMapOf<String,String>()
        params.set("wd","123")
        NetHelper.post("https://www.baidu.com/s",null, object : HttpConnectCallBack<String>(String::class.java) {
            override fun onFail(code: Int, reason: String) {
                LogUtil.e(reason)
            }

            override fun onSuccess(code: Int, data: String?) {
                LogUtil.e(data?:"success!")
            }

        })
    }

    override fun initData(savedInstanceState: Bundle?, intent: Intent?) {
    }

    override fun initListener() {
    }

    override fun loadNetRes(intent: Intent?) {
    }
}
