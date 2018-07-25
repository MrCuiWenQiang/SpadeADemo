package com.spade.my.view


import android.os.Bundle
import com.my.spadea.activity.BaseActivity
import com.spade.my.mvpmanager.MainMVPManager

class MainActivity : BaseActivity(), MainMVPManager.View {
    override fun initView(savedInstanceState: Bundle?) {
    }
}
