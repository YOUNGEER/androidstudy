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
 * protocol:网络协议  http1.1 http2.0 spdy3等
 *
 *
 * dispatcher：分发器  这里面通过三个queue和ThreadPoolExecutor维持着所有的请求， 支持同步和异步请求数据
 *
 *
 *Interceptor:拦截器，接口   通过chain链接器各个拦截器的request和response
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
 *
 */

class OkHttpClient {

}