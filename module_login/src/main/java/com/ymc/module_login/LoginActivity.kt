package com.ymc.module_login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.ymc.common.eventbusdata.LoginData
import com.ymc.common_base.BaseActivity
import com.ymc.common_base.arouter.ARouterConstant
import kotlinx.android.synthetic.main.login_activity_login.*
import org.greenrobot.eventbus.EventBus


/**
 * 登陆界面
 */

@Route(path = ARouterConstant.LOGIN_ACTIVITY,name = "测试服务")
open class LoginActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.login_activity_login
    }

    override fun initView(savedInstanceState: Bundle?) {
        tvLogin.setOnClickListener {
            //Log.d("BBBBB",key)
            if (etPhone.text.isNullOrEmpty()){
                Toast.makeText(applicationContext,"手机号不能为空",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etPhone.text.toString().trim().length < 11){
                Toast.makeText(applicationContext,"手机号必须11位",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etCode.text.isNullOrEmpty()){
                Toast.makeText(applicationContext,"验证码不能为空",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (etCode.text.toString().trim().length < 6){
                Toast.makeText(applicationContext,"验证码位6位数字",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(applicationContext,"登录成功",Toast.LENGTH_SHORT).show()
            EventBus.getDefault().post(LoginData(etPhone.text.toString().trim(),etCode.text.toString().trim()))
            finish()
        }
    }

    override fun initViewData() {
    }

}