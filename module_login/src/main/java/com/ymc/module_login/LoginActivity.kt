package com.ymc.module_login

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.ymc.common.eventbusdata.LoginData
import com.ymc.common_base.BaseActivity
import com.ymc.common_base.arouter.ARouterConstant
import com.ymc.common_base.arouter.ARouterUtils
import com.ymc.common_base.arouter.service.HelloService
import kotlinx.android.synthetic.main.login_activity_login.*
import org.greenrobot.eventbus.EventBus

/**
 * 登陆界面
 */

@Route(path = ARouterConstant.LOGIN_ACTIVITY,name = "测试服务")
open class LoginActivity : BaseActivity(),HelloService {
    override fun getLayoutId(): Int {
        return R.layout.login_activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        tvLogin.setOnClickListener {
            Toast.makeText(applicationContext,"登录......",Toast.LENGTH_LONG).show()
            EventBus.getDefault().post(LoginData("123456","111111"))
            ARouterUtils.startActivity(ARouterConstant.HOME_ACTIVITY)
        }
    }

    override fun initViewData() {
    }

    override fun say(name: String):String {
        Toast.makeText(this,name,Toast.LENGTH_LONG).show()
        return name
    }

    override fun init(context: Context?) {

    }

}