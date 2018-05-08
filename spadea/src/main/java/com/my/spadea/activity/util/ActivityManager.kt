package com.my.spadea.activity.util

import android.support.v7.app.AppCompatActivity
import java.util.*

/**
 * Function : Activity栈管理类
 * Remarks  :
 * Created by Mr.C on 2018/5/8 0008.
 */
object ActivityManager{
        var mActivitys = Collections.synchronizedList(LinkedList<AppCompatActivity>());

        fun add(activity:AppCompatActivity){
                mActivitys?.add(activity);
        }

        fun remove(activity:AppCompatActivity){
                mActivitys?.remove(activity);
        }

        fun current():AppCompatActivity ?{
            return mActivitys.get(mActivitys.size-1)?:null
        }

        fun exit(){
            mActivitys?: return;
            for (ac in mActivitys){
                remove(ac)
            }
        }
}