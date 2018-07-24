package com.my.heartace.view.widget.dialog

import android.content.Context
import android.view.ViewGroup

/**
 * Function : 为列表式的DialogBuilder提供基础组件
 * Remarks  :
 * Created by Mr.C on 2018/7/24 0024.
 */
abstract class MenuBuilder<T>(mContext: Context) :Builder<T>(mContext){
    override fun createContentView(dialog: Dialog, layoutGroup: ViewGroup) {

    }
}