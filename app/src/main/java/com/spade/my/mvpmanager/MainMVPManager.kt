package com.spade.my.mvpmanager

/**
 * Function :主页的MVP接口 抽象方法管理类
 * Remarks  :
 * Created by Mr.C on 2018/7/25 0025.
 */
class MainMVPManager {
    interface View : BaseMVPManager.BaseViewInterface

    class Presenter : BaseMVPManager.BasePresenterAbstractClass<View>()

    class Model : BaseMVPManager.BaseModelAbstract()
}