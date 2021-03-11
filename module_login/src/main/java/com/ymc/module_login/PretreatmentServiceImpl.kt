package com.ymc.module_login

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.service.PretreatmentService

/**
 * Created by lixiang on 2020/12/18
 * Describe:
 */
class PretreatmentServiceImpl : PretreatmentService{
    override fun onPretreatment(context: Context?, postcard: Postcard?): Boolean {
        return false
    }

    override fun init(context: Context?) {
    }

}
