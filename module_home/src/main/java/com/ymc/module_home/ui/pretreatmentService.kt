package com.ymc.module_home.ui

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.PretreatmentService

/**
 * Created by lixiang on 2020/12/21
 * Describe:
 */
@Route(path = "path/daa")
class pretreatmentService:PretreatmentService{


    override fun init(context: Context?) {
        Log.d("BBBBB","我是预处理")
    }

    override fun onPretreatment(context: Context?, postcard: Postcard?): Boolean {
        //跳转前预处理，返回false表示自行处理跳转
        Log.d("BBBBB","我是预处理onPretreatment")

        return false
    }
}