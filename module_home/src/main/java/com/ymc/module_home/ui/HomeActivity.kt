package com.ymc.module_home.ui

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ymc.common.eventbusdata.LoginData
import com.ymc.common_base.BaseActivity
import com.ymc.common_base.arouter.ARouterConstant
import com.ymc.common_base.arouter.ARouterUtils
import com.ymc.module_home.R
import kotlinx.android.synthetic.main.home_activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@Route(path = ARouterConstant.HOME_ACTIVITY)
class HomeActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        btn_login.setOnClickListener {
            EventBus.getDefault().post(LoginData("123456", "111111"))
            ARouterUtils.startActivity(ARouterConstant.LOGIN_ACTIVITY)
        }
    }

    override fun initViewData() {

    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this);
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: LoginData?) {
        tv_content.text = "${event?.account}++++++${event?.password}"
    }

}