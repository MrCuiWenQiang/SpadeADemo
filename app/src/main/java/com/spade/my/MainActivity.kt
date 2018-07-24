package com.spade.my

import android.os.Bundle
import android.widget.Toast
import com.my.heartace.view.widget.dialog.Dialog
import com.my.heartace.view.widget.dialog.DialogAction
import com.my.heartace.view.widget.dialog.DialogAction.Companion.ACTION_TYPE_BLOCK
import com.my.spadea.activity.BaseActivity
import kotlinx.android.synthetic.main.ac_main.*

class MainActivity : BaseActivity() {
//    lateinit var mDialog: Dialog.MesssageBuilder

    override fun layoutId(): Int {
        return R.layout.ac_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        tv_hello.text = "hello,world！"

    }

    override fun initListener() {
        tv_hello.setOnClickListener {
           var mDialog = Dialog.CheckBoxMessageDialogBuilder(this)
            mDialog.setTitle("测试").setMessage("测试内容")
                    .addAction("确定", object : DialogAction.ActionListener {
                        override fun onClick(dialog: Dialog, index: Int) {
                            dialog.dismiss()
                        }
                    })
                    .addAction("取消",ACTION_TYPE_BLOCK, object : DialogAction.ActionListener {
                        override fun onClick(dialog: Dialog, index: Int) {
                            dialog.dismiss()
                        }
                    })
            mDialog.show(supportFragmentManager, "test")
        }
    }
}
