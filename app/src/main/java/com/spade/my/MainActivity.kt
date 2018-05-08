package com.spade.my

import android.content.Intent
import android.os.Bundle
import com.my.spadea.activity.BaseActivity

class MainActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.ac_main
    }

    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?, intent: Intent?) {
    }

    override fun initListener() {
    }

    override fun loadNetRes(intent: Intent?) {
    }
}
