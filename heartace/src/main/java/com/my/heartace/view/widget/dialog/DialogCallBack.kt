package com.my.heartace.view.widget.dialog

/**
 * Function : 监听Dialog创建   销毁状态
 * Remarks  :
 * Created by Mr.C on 2018/7/24 0024.
 */
interface DialogCallBack {
    /**
     * 对话框创建
     */
    fun create(dialog: Dialog)

    /**
     * 对话框销魂
     */
    fun destory(dialog: Dialog)
}