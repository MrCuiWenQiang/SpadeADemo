package com.spade.my.mvpmanager

/**
 * Function : MVP接口管理类
 * Remarks  :
 * Created by Mr.C on 2018/7/25 0025.
 */
interface BaseMVPManager {


    interface BaseViewInterface

    abstract class BasePresenterAbstractClass<T : BaseViewInterface> {
        var ViewTAG: T? = null
        protected var isLoad = true

        fun attr(ViewTAG: T) {
            this.ViewTAG = ViewTAG
        }

        fun lose() {
            isLoad = false
            ViewTAG = null
        }
    }

    abstract class BaseModelAbstract

    interface MVPCallback<T> {
        fun onSuccess(t: T)

        fun onfail(msg: String)
    }
}
