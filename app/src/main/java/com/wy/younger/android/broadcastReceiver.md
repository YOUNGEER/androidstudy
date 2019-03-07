# BroadcastReceiver

## 静态广播接收器
- 静态使用也就是配置在AndroidManifest.xml中配置意图过滤器来匹配
- 静态广播在Android8.0+，intent必须指定广播的component,才有效

## 动态广播接收器
- BR不需要在manifest中配置，代码注册和销毁

## 有序广播

## 本地广播

## 跨进程通信 ，Binder原理