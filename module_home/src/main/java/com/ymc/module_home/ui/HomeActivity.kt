package com.ymc.module_home.ui

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ymc.common.eventbusdata.LoginData
import com.ymc.common_base.BaseActivity
import com.ymc.common_base.arouter.ARouterConstant
import com.ymc.common_base.arouter.ARouterUtils
import com.ymc.common_base.arouter.service.HelloService
import com.ymc.module_home.R
import kotlinx.android.synthetic.main.home_activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

//自定义分组
@Route(path = ARouterConstant.HOME_ACTIVITY,group = "group")
class HomeActivity : BaseActivity() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        btn_login.setOnClickListener {
            //ARouterUtils.changeActivity(ARouterConstant.LOGIN_ACTIVITY, hashMapOf("key" to "参数来了"))
            ARouter.getInstance().build(ARouterConstant.LOGIN_ACTIVITY).navigation()
            communicationFromARouter()
        }
    }

    /**
     * 通过ARouter方式来完成moduler之间的通信
     */
    fun communicationFromARouter(){
        val helloService =
            ARouter.getInstance().build("/test/testservice").navigation() as HelloService
        val say = helloService?.say("你好啊")
        Toast.makeText(this, say, Toast.LENGTH_LONG).show()
    }

    /**
     * 通过EventBus来完成moduler之间的通信
     */
    fun communicationFromEventBus(){
    }



    override fun initViewData() {
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this);
    }

    override fun onDestroy(){
        super.onDestroy()
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: LoginData?) {
        tv_content.text = "${event?.account}++++++${event?.password}"
    }

}