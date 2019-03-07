# Activity

## 生命周期
- 打开：onCreate,onstart,onResume,onPause,onStop,onDestory
- A跳转B, A onPause,B onCreate,B onStart,B onResume,A onStop, B 返回A，B onPause,A onRestart,A onStart,A onResume,B onStop,B Destory
- 屏幕旋转 ：相当于关闭重新打开，会调用onSaveInstanceState，这里可以保存数据

## 各生命周期意义
- onCreate：初始化的一些工作，比如setContentView
- onStart：可见但无法交互
- onResume：可见可交互
- onPause:可做数据存储，停止动画，音乐，视频等
- onStop:此时Activity不可见，可以视情况做一些资源回收工作
- onDestory：回收工作，资源等
- onRestart：重新开启

## 数据传递
- 基本数据类型
- Serializable
- Percelable
- Bundle，不能传递大数据

## 启动模式
- standard：标准栈，新建一个activity就直接添加进去
- SingleTop：栈顶复用
- SingleTask：栈内复用，上面的会被kill掉
- SingleInstance：单独栈
- singleTask模式和singleTop模式时，非第一次启动，不会调用onCreate方法！但会走onNewIntent方法

## 跳转动画
- overridePendingTransition(enter,exit)
- style配置

## 启动源码
- ActivityThread开启Activity