# ComponentMaster

> V1.0.0  

组件化(kotlin)

## 架构图

![组件架构图](https://upload-images.jianshu.io/upload_images/6188347-2175e13adf3f6ae7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 感谢

逼供大佬 [What](https://github.com/y1xian/What)

一.组件化开发规范 

  1、资源命名规则
  所有资源文件的命名都需要以业务模块名为前缀，注意不要与其他业务模块前缀名冲突。假设我们在开发"登录"
  相关的业务，业务模块名为"login"，则相关资源文件命名例子：
  layout文件：login_activity_quicklogin.xml、login_activity_register.xml
  anim文件：login_slide_in.xml
  mipmap文件：login_btn_submit.png
  string：<string name="login_submit">提交</string>
  包括但不限于以上这些资源文件，所有资源文件的命名都必须遵循该规则，否则可能在集成的时候会被冲突掉。
  当然还有一种方式是在build.gradle文件添加如下配置：
  resourcePrefix "module_login"
  这个在打包编译时会自动为所有资源加上前缀，但是不管加不加该配置，还是强烈建议资源命名增加前缀。
  
  2、数据存储
  尽量不要使用数据库来存储数据，特殊情况除外。某些ORM框架需要数据库表集中管理，这样不利于实行业务模块组件化。
  使用SharedPreferences时，每个业务模块只管理自己模块需要的数据，SharedPreferences文件名需要通过业务
  前缀来区分，防止不同组件间数据发生冲突。
  当某些数据需要全局共享时，可以考虑下沉到底层模块。
  
  3、发布本地maven库进行管理
  
  4、黄牛刀无法使用问题
     这是因为在library中生成的R文件，这些属性值都不是常量，而@BindView注解这里是需要传入常量值的，
     这些id都是 public static int 类型的，所以这些地方都会报错，ButterKnife提供了插件来解决这个问题。
     classpath 'com.jakewharton:butterknife-gradle-plugin:8.4.0',
     使用R2而不是R
     同样，click事件里也必须采用R2.id来替换R.id，但是onClick()方法里不能使用switch case语句，
     必须使用if else来代替，在方法体内部是不能使用R2.id的，R2类只限于在外部注解中使用。
  

  
  
 
  



