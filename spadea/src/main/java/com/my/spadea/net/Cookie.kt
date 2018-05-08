package com.my.spadea.net

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Function : cookie管理类
 * Remarks  :
 * Created by Mr.C on 2018/5/8 0008.
 */
class Cookie : CookieJar {

    var cookies : MutableList<Cookie>  = mutableListOf()

    override fun saveFromResponse(url: HttpUrl?, cookies: MutableList<Cookie>?) {
        if (cookies!=null && cookies.size>0){
            this.cookies = cookies;
        }
    }

    override fun loadForRequest(url: HttpUrl?): MutableList<Cookie> {
        return cookies
    }
}