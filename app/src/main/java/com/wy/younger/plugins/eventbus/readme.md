# eventbus:订阅，观察者模式

- EventBus对象，post方法

# 组成
- 设计三个Post：HandlerPost   AsyncPoster   BackgroundPoster
- HandlerPost 里面有PendingPostQueue  PendingPost
- handlerPoster 的enqueue将消息添加到PendingPostQueue中，然后发送一个空消息，在handleMessage中获取PendingPostQueue
从而对应起来发送的消息


- EventBus的register方法：

## 1. EventBus 初始化的三个步骤，直观上看用到 单例模式和Builder模式，将构造参数给分离了出来

## 相关类：
- subscriptionsByEventType : 内部是一个Map集合，可以根据 EventType 查找订阅事件。
- typesBySubscriber : 根据我们的订阅对象找到 EventType。
- stickyEvents : 粘性事件的缓存。
- 事件投递者 : mainThreadPoster, backgroundPoster, asyncPoster
- 根据订阅注解 ThreadMode 去选择不同的投递者，不同投递者投递事件，接收函数会执行在不同的线程中
- subscriberMethodFinder ：查找方法用的，内部维护了一个订阅方法的集合。

## 2.注册事件
- ignoreGeneratedIndex
- true：通过反射解析注解获取
- findUsingReflection

- false：注解apt实现，效率高些


## sticky事件是
