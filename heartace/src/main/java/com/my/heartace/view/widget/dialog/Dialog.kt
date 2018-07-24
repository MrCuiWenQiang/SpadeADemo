package com.my.heartace.view.widget.dialog

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.my.heartace.R
import com.my.heartace.util.DisplayUtil
import com.my.heartace.util.ResUtil
import com.my.heartace.view.widget.group.WrapContentScrollView

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/21 0021.
 */
class Dialog : DialogFragment() {

    var layoutView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val window = dialog.window
        val params = window.attributes
        params.let {
            it.height = LinearLayout.LayoutParams.WRAP_CONTENT
            it.width = LinearLayout.LayoutParams.MATCH_PARENT
            window.attributes = it
        }
    }

    class MesssageBuilder(context: Context) : Builder<MesssageBuilder>(context) {
        var mScrollView: WrapContentScrollView = WrapContentScrollView(context)
        var textView: TextView = TextView(context)

        init {
            textView.setLineSpacing(DisplayUtil.dpToPx(2).toFloat(), 1F)
            textView.setTextColor(ResUtil.getAttrColor(context, R.attr.dialog_message_color_gray))
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(context, R.attr.dialog_message_textsize).toFloat())
            mScrollView.setMaxHeight(contentAreaMaxHeight.toInt())
            mScrollView.addView(textView)
        }

        fun setMessage(message: String): MesssageBuilder {
            textView.text = message
            return this
        }

        override fun createContentView(dialog: Dialog, layoutGroup: ViewGroup) {
            if (!TextUtils.isEmpty(textView.text) && layoutGroup.indexOfChild(mScrollView) == -1) {
                textView.setPadding(ResUtil.getAttrDimen(context, R.attr.dialog_content_padding_left),
                        if (hasTitle()) ResUtil.getAttrDimen(context, R.attr.dialog_content_padding_top) else ResUtil.getAttrDimen(context, R.attr.dialog_content_padding_notitle_top),
                        ResUtil.getAttrDimen(context, R.attr.dialog_content_padding_right),
                        ResUtil.getAttrDimen(context, R.attr.dialog_content_padding_bottom))
                layoutGroup.addView(mScrollView)
            }
        }
    }
}