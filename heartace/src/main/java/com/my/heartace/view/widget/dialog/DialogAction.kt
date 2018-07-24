package com.my.heartace.view.widget.dialog

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.my.heartace.R
import com.my.heartace.util.ResUtil

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/23 0023.
 */
class DialogAction constructor(var name: String) {
    companion object {
        val ACTION_TYPE_NORMAL = 0   //正常模式
        val ACTION_TYPE_BLOCK = 1    //异常模式
    }

    constructor(mName: String, listener: ActionListener) : this(mName) {
        this.type = type
        this.listener = listener
    }

    constructor(mName: String, type: Int, listener: ActionListener) : this(mName) {
        this.type = type
        this.listener = listener
    }

    var type: Int? = null
    var listener: ActionListener? = null

    fun generateAction(mContext: Context, dialog: Dialog, index: Int): Button {
        val button = create(mContext, dialog, index)
        when (type) {
            null, ACTION_TYPE_NORMAL -> button.setTextColor(ResUtil.getAttrColorStateList(mContext, R.attr.dialog_action_text_color))
            ACTION_TYPE_BLOCK -> button.setTextColor(ResUtil.getAttrColorStateList(mContext, R.attr.dialog_action_text_negative_color))
        }
        return button
    }


    fun create(mContext: Context, dialog: Dialog, index: Int): Button {
        val button = Button(mContext)
        button.run {
            val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams = params
            setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_action_text_size).toFloat())
            text = name
            isClickable = true
            setPadding(0, 0, 0, 0)
            background = ContextCompat.getDrawable(mContext, R.drawable.dg_action_button_backage)
            setOnClickListener {
                listener?.onClick(dialog, index)
            }
        }
        return button
    }


    interface ActionListener {
        fun onClick(dialog: Dialog, index: Int)
    }
}