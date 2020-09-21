package com.ymc.module_home.ui

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ymc.common.eventbusdata.LoginData
import com.ymc.common.utils.ToastUtils
import com.ymc.common_base.BaseActivity
import com.ymc.common_base.arouter.ARouterConstant
import com.ymc.common_base.arouter.service.HelloService
import com.ymc.module_home.R
import kotlinx.android.synthetic.main.home_activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


@Route(path = ARouterConstant.HOME_ACTIVITY)
class HomeActivity : BaseActivity() {
    /*@Autowired
    var helloService1: HelloService? = null*/

    override fun getLayoutId(): Int {
        return R.layout.home_activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        btn_login.setOnClickListener {
            EventBus.getDefault().post(LoginData("123456", "111111"))
            //ARouterUtils.startActivity(ARouterConstant.LOGIN_ACTIVITY)
            val helloService = ARouter.getInstance().
            build("/test/testservice").navigation() as HelloService

            val say = helloService?.say("你好啊")
            Toast.makeText(this,say,Toast.LENGTH_LONG).show()
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