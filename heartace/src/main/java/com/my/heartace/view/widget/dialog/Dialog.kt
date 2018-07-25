package com.my.heartace.view.widget.dialog

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
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
    var callBack: DialogCallBack? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        callBack?.create(this)
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


    override fun onDestroy() {
        callBack?.destory(this)
        super.onDestroy()
    }

    /**
     * 消息对话框
     */
    class MesssageBuilder(val context: Context) : Builder<MesssageBuilder>(context) {
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

    /**
     * 带选择的消息对话框
     */
    class CheckBoxMessageDialogBuilder(var mContext: Context) : Builder<CheckBoxMessageDialogBuilder>(mContext) {

        private var message: String? = null
        private var mDrawable: Drawable? = null
        private var textView: TextView? = null
        private var mScrollView: WrapContentScrollView? = null
        private var mIsChecked = false

        init {
            initCentent(mContext)
        }

        private fun initCentent(mContext: Context) {
            mDrawable = ResUtil.getAttrDrawable(mContext, R.attr.dialog_content_select)
            mScrollView = WrapContentScrollView(mContext)
            textView = TextView(mContext)
            textView!!.setLineSpacing(DisplayUtil.dpToPx(2).toFloat(), 1.0f)
            textView!!.setTextColor(ResUtil.getAttrColor(mContext, R.attr.dialog_message_color_gray))
            textView!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_message_textsize).toFloat())
            textView!!.gravity = Gravity.CENTER_VERTICAL
            mScrollView!!.addView(textView)

        }

        fun setMessage(message: String): CheckBoxMessageDialogBuilder {
            this.message = message
            return this
        }

        fun setMessage(resid: Int): CheckBoxMessageDialogBuilder {
            return setMessage(mContext.resources.getString(resid))
        }

        fun getChecked() = textView?.isSelected

        fun setChecked(checked: Boolean): CheckBoxMessageDialogBuilder {
            if (checked != mIsChecked) {
                mIsChecked = checked
                textView?.isSelected = mIsChecked
            }
            return this
        }

        override fun createContentView(dialog: Dialog, layoutGroup: ViewGroup) {
            if (!TextUtils.isEmpty(message)) {
                mScrollView!!.setMaxHeight(contentAreaMaxHeight.toInt())
                textView!!.text = message
                textView!!.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_left),
                        if (hasTitle()) ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_top) else ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_notitle_top),
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_right),
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_bottom))
                mDrawable!!.setBounds(0, 0, mDrawable!!.intrinsicWidth, mDrawable!!.intrinsicHeight)
                textView!!.setCompoundDrawables(mDrawable, null, null, null)
                textView!!.compoundDrawablePadding = DisplayUtil.dpToPx(12)
                textView!!.setOnClickListener { setChecked(!mIsChecked) }
                setChecked(mIsChecked)
                layoutGroup.addView(mScrollView)
            }
        }
    }

    /**
     * 展示进度对话框
     */
    class ProgressDialogBuilder(var mContext: Context) : Builder<ProgressDialogBuilder>(mContext) {
        private var message: String? = null

        private var textView: TextView? = null
        private var progressView: ProgressBar? = null
        private var linearLayout: LinearLayout? = null

        init {
            linearLayout = LinearLayout(mContext)
            linearLayout?.let {
                it.orientation = LinearLayout.HORIZONTAL
                it.gravity = Gravity.CENTER_VERTICAL
                it.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

            }
            initProgress()
            initText()
        }

        fun initText() {
            textView = TextView(mContext)
            textView?.let {
                it.setLineSpacing(DisplayUtil.dpToPx(2).toFloat(), 1.0f)
                it.setTextColor(ResUtil.getAttrColor(mContext, R.attr.dialog_message_color_gray))
                it.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(mContext, R.attr.dialog_message_textsize).toFloat())
                it.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                it.gravity = Gravity.CENTER_VERTICAL
                it.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_content_grpup_padding_left), 0,
                        ResUtil.getAttrDimen(mContext, R.attr.dialog_content_grpup_padding_right), 0
                )
                linearLayout?.addView(it)
            }
        }

        fun initProgress() {
            progressView = ProgressBar(mContext)
            progressView?.let {
                it.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                linearLayout?.addView(it)
            }
        }

        fun setMessage(message: String?): ProgressDialogBuilder {
            if (!TextUtils.isEmpty(message)) {
                textView?.text = message
            }
            return this
        }

        override fun createContentView(dialog: Dialog, layoutGroup: ViewGroup) {
            val padding =if (!hasTitle()) ResUtil.getAttrDimen(mContext, R.attr.dialog_content_padding_notitle_top) else 0
            linearLayout?.setPadding(ResUtil.getAttrDimen(mContext, R.attr.dialog_content_grpup_padding_left), padding,
                    ResUtil.getAttrDimen(mContext, R.attr.dialog_content_grpup_padding_right), padding)
            layoutGroup.addView(linearLayout)
        }
    }
}