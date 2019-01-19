package com.wy.younger.第三方框架.网络

/**
 *@package:com.wy.younger.第三方框架.网络
 *@data on:2019/1/17 16:46
 *author:YOUNG
 *desc:TODO
 */


/**
 *
 * 使用：

OkHttpClient okHttpClient = new OkHttpClient();
final Request request = new Request.Builder()
.url(url)
.build();

final Call call = okHttpClient.newCall(request);
Response response = call.execute();

 *
 *
 * 请求 多路复用机制
 *
 * 重连机制
 *
 * protocol:网络协议  http1.1 http2.0 spdy3等
 *
 *
 * dispatcher：请求分发器  这里面通过三个queue和ThreadPoolExecutor维持着所有的请求， 支持同步和异步请求数据
 *1）调度线程池Disptcher实现了高并发，低阻塞的实现
2）采用Deque作为缓存，先进先出的顺序执行
3）任务在try/finally中调用了finished函数，控制任务队列的执行顺序，而不是采用锁，减少了编码复杂性提高性能。
 *
 *
 *
 *
 *
 *Interceptor:拦截器，接口   通过chain链接器各个拦截器的request和response
 *
 * 拦截器主要的作用是在请求之前和相应之后做一些自己需要的处理操作
 * interceptor是个接口，需要实现intercept方法，内部传入chain
 *
 * chain的真正实现是RealInterceptorChain
 *
 * 实现的interceptor在调用RealInterceptorChain的proceed方法时，必须调用一下chain的proceed方法
 * 只有这样才能实现所有的interceptor都能按照钩子的样式进行逐级调用
 * 当然对于最后一个CallServerInterceptor实现网络调用的拦截器是不需要调用proceed方法的
 * 我们自定义的拦截器一般都是在最前面的
 *
 * RealCall的getResponseWithInterceptorChain中，系统定义了
 * retryAndFollowUpInterceptor：重试重定向拦截器
 * BridgeInterceptor，内容拦截器
 * CacheInterceptor，缓存拦截器
 * ConnectInterceptor，拦截拦截器
 * networkInterceptors：websocket自定义网络拦截器
 * CallServerInterceptor：请求服务拦截器
 * 等拦截器
 *
 *
 *
 *
 * request：HttpUrl（scheme  url post mehtod header ）  method  Headers  RequestBody（mediatype，body）
 *
 *
 * RealCall
 *
 *
 * Builder()，使用默认配置
 *
 * Builder(OkhttpClient()) 使用自定义配置
 *
 * 构建者模式
 *
 *
 */

class OkHttpClient {

}