package com.my.spadea.net.json

import com.alibaba.fastjson.JSON

/**
 * Function : Json 工具类  提供 数据类与JSON串转换
 * Remarks  :
 * Created by Mr.C on 2018/5/8 0008.
 */
    object JsonUtil {

        fun convertObjectToJson(data:Any): String?{
            var json : String? = null;
            try {
                json = JSON.toJSONString(data)
            }catch (exception:Exception){
            }
            return json
          }

        fun <T : Any> convertJsonToObject(json:String ,t:Class<T>) : T?{
            var dataBean : T? = null;
            try {
                dataBean = JSON.parseObject(json,t)
            }catch (exception:Exception){
            }
            return dataBean;
        }

    }