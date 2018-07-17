package com.spade.my

import android.os.Bundle
import com.my.spadea.activity.BaseActivity
import kotlinx.android.synthetic.main.ac_main.*

class MainActivity : BaseActivity() {

    override fun layoutId(): Int {
        return R.layout.ac_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_hello.text="hello,world！"
    }
}
