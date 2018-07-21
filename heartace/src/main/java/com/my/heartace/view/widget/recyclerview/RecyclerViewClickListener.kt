package com.my.heartace.view.widget.recyclerview

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/7/18 0018.
 */
class RecyclerViewClickListener constructor(private val context: Context) : RecyclerView.OnItemTouchListener, GestureDetector.SimpleOnGestureListener() {

    private var touchSlop: Int = 0

    private var isConsume = false

    private var rv: RecyclerView? = null

    private var onItemClickListener: OnItemClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    private lateinit var mGestureDetector: GestureDetector

    init {
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop()
        mGestureDetector = GestureDetector(context, this)
    }

    constructor(context: Context, onItemClickListener: OnItemClickListener) : this(context) {
        this.onItemClickListener = onItemClickListener
    }

    constructor(context: Context, onItemClickListener: OnItemClickListener?, onItemLongClickListener: OnItemLongClickListener) : this(context) {
        this.onItemClickListener = onItemClickListener
        this.onItemLongClickListener = onItemLongClickListener
    }

    override fun onTouchEvent(rv: RecyclerView?, e: MotionEvent?) {
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        this.rv = rv
        return mGestureDetector.onTouchEvent(e)
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }


    override fun onSingleTapUp(e: MotionEvent): Boolean {
        val view = rv!!.findChildViewUnder(e.x, e.y)
        val position = rv!!.getChildLayoutPosition(view)
        onItemClickListener?.let {
            isConsume = it.onItemClick(view, position)
        }
        return isConsume
    }

    override fun onLongPress(e: MotionEvent) {
        val view = rv!!.findChildViewUnder(e.x, e.y)
        val position = rv!!.getChildLayoutPosition(view)
        onItemLongClickListener?.let {
            it.onItemLongClick(view, position)
        }
    }

    /**
     * 滑动检测
     */
    open override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return super.onFling(e1, e2, velocityX, velocityY)
    }

    /**
     * 点击事件
     */
    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int): Boolean
    }

    /**
     * 长按事件
     */
    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, position: Int): Boolean
    }
}
