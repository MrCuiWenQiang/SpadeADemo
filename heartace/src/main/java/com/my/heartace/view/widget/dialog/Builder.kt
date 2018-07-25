package com.my.heartace.view.widget.dialog

import android.content.Context
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.my.heartace.R
import com.my.heartace.util.ResUtil
import com.my.heartace.util.ScreenUtil

/**
 * Function : 构建模式 -基类的对话框
 * Remarks  :
 * Created by Mr.C on 2018/7/21 0021.
 */
abstract class Builder<T> constructor(private val context: Context) : DialogCallBack {

    protected var contentAreaMaxHeight: Double = ScreenUtil.getWindowHeight(context) * 0.75
    protected var title: String? = null
    protected val actions: MutableList<DialogAction> = mutableListOf()
    private var dialog: Dialog? = null


    fun setTitle(title: String): T {
        this.title = title
        return this as T
    }

    fun setTitle(resId: Int): T {
        val title = context.resources.getString(resId)
        return setTitle(title)
    }

    fun addAction(name: String, listener: DialogAction.ActionListener): T {
        return addAction(name, DialogAction.ACTION_TYPE_NORMAL, listener)
    }

    fun addAction(name: String, type: Int, listener: DialogAction.ActionListener): T {
        val action = DialogAction(name, type, listener)
        actions.add(action)
        return this as T
    }

    fun hasTitle(): Boolean {
        return !TextUtils.isEmpty(title)
    }

    fun show(manager: FragmentManager, tag: String) {
        if (isHave(manager, tag)) {
            if (dialog == null) {
                dialog = create()
            }
            dialog!!.show(manager, tag)
        }
    }

    private fun isHave(manager: FragmentManager, tag: String): Boolean = manager.findFragmentByTag(tag) == null

    fun create(): Dialog {
        val linearlayout = LayoutInflater.from(context)
        val view = linearlayout.inflate(R.layout.dg_layout, null)
        var dialog = Dialog()
        dialog.layoutView = view
        dialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog)

        val topGroup = view.findViewById<LinearLayout>(R.id.dg_top_group)
        val contentGroup = view.findViewById<LinearLayout>(R.id.dg_centent_group)
        val bottomGroup = view.findViewById<LinearLayout>(R.id.dg_bottom_group)

        createTopView(dialog, topGroup)
        createContentView(dialog, contentGroup)
        createBottomView(dialog, bottomGroup)
        dialog.callBack = this
        return dialog
    }


    fun createTopView(dialog: Dialog, layoutGroup: ViewGroup) {
        if (hasTitle()) {
            val textView = TextView(context)
            textView.run {
                ellipsize = TextUtils.TruncateAt.END
                text = title
                setTextColor(ResUtil.getAttrColor(context, R.attr.dialog_title_color_black))
                setTextSize(TypedValue.COMPLEX_UNIT_PX, ResUtil.getAttrDimen(context, R.attr.dialog_title_textsize).toFloat())
                setPadding(ResUtil.getAttrDimen(context, R.attr.dialog_padding_horizontal),
                        ResUtil.getAttrDimen(context, R.attr.dialog_title_margin_top),
                        ResUtil.getAttrDimen(context, R.attr.dialog_padding_horizontal),
                        ResUtil.getAttrDimen(context, R.attr.dialog_title_margin_top))
                val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT)
                layoutParams = params
                gravity = Gravity.CENTER_VERTICAL
                setSingleLine(true)
                layoutGroup.addView(this)
            }
        }
    }

    abstract fun createContentView(dialog: Dialog, layoutGroup: ViewGroup)

    fun createBottomView(dialog: Dialog, layoutGroup: ViewGroup) {
        val index = actions.size
        if (index > 0) {
            val linearLayout = LinearLayout(context)
            linearLayout.orientation = LinearLayout.HORIZONTAL
            val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            linearLayout.layoutParams = layoutParams
            linearLayout.setPadding(ResUtil.getAttrDimen(context, R.attr.dialog_padding_horizontal),
                    ResUtil.getAttrDimen(context, R.attr.dialog_action_container_margin_top),
                    ResUtil.getAttrDimen(context, R.attr.dialog_padding_horizontal),
                    ResUtil.getAttrDimen(context, R.attr.dialog_action_container_margin_bottom))

            for (i in 0 until index) {
                val action = actions.get(i)
                val button = action.generateAction(context, dialog, i)
                linearLayout.addView(button)
            }
            layoutGroup.addView(linearLayout)
        }
    }

    open override fun create(dialog: Dialog) {
    }

    open override fun destory(dialog: Dialog) {
    }
}