<h1 align="center">Welcome to miaosha 👋</h1>
<p>
  <img alt="Version" src="https://img.shields.io/npm/v/miaosha.svg">
  <a href="https://github.com/gb145234/miaosha.git">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" target="_blank" />
  </a>
</p>

> ### 基于 SpringBoot+Maven+Mybatis+Redis+RabbitMQ 的高并发秒杀系统

## 🏠 [主页](https://github.com/gb145234/miaosha.git)

## 原理

秒杀与其他业务最大的区别在于，在秒杀的瞬间，系统的并发量和吞吐量会非常大，与此同时，网络的流量也会瞬间变大。

对于网络流量瞬间变大问题，最常用的办法就是将页面静态化，也就是我们常说的前后端分离。把静态页面直接缓存到用户的浏览器中，当用户需要获取数据时，就从服务端接口动态获取。这样会大大节省网络的流量，如果再加上CDN优化，一般都不会有大问题。

对于系统并发量变大问题，这里的核心在于如何在大并发的情况下保证数据库能扛得住压力，因为大并发的瓶颈在于数据库。如果用户的请求直接从前端传到数据库，显然，数据库是无法承受几十万上百万甚至上千万的并发量的。因此，我们能做的只能是减少对数据库的访问。例如，前端发出了100万个请求，通过我们的处理，最终只有10个会访问数据库，这样就会大大提升系统性能。再针对秒杀这种场景，因为秒杀商品的数量是有限的，因此这种做法刚好适用。

那么具体是如何来减少对数据库的访问的呢？

假如，某个商品可秒杀的数量是10，那么在秒杀活动开始之前，把商品的ID和数量加载到Redis缓存。当服务端收到请求时，首先预减Redis中的数量，如果数量减到小于0时，那么随后的访问直接返回秒杀失败的信息。也就是说，最终只有10个请求会去访问数据库。

如果商品数量比较多，比如1万件商品参与秒杀，那么就有1万*10=10万个请求并发去访问数据库，数据库的压力还是会很大。这里就用到了另外一个非常重要的组件：消息队列。我们不是把请求直接去访问数据库，而是先把请求写到消息队列中，做一个缓存，然后再去慢慢的更新数据库。这样做之后，前端用户的请求可能不会立即得到响应是成功还是失败，很可能得到的是一个排队中的返回值，这个时候，需要客户端去服务端轮询，因为我们不能保证一定就秒杀成功了。当服务端出队，生成订单以后，把用户ID和商品ID写到缓存中，来应对客户端的轮询就可以了。

这样处理以后，我们的应用是可以很简单的进行分布式横向扩展的，以应对更大的并发。

当然，秒杀系统还有很多要处理的事情，比如限流防刷、分布式Session等等。

## 开发工具

IntelliJ IDEA 2017.3.1

## 开发环境

| JDK  | Maven | Mysql | SpringBoot    | redis | RabbitMQ |
| ---- | ----- | ----- | ------------- | ----- | -------- |
| 1.8  | 3.2.2 | 5.6   | 1.5.9.RELEASE | 3.3   | 3.7.14   |

## 使用说明

1. 使用git clone <https://github.com/gb145234/miaosha> 将项目下载并导入到IDEA里
2. 运行sql文件夹下的sql文件
3. 到src/main/resources下的application.properties下修改你的数据库链接用户名与密码
4. 安装redis、mysql、rabbitmq、maven等环境并集成mysql、redis、rabbitmq
5. 根据miaosha.sql文件创建相应数据库以及相关的表
6. 启动前，检查并修改application.properties文件中相关的redis、mysql、rabbitmq等配置信息
7. 在数据库秒杀商品表里面设置合理的秒杀开始时间与结束时间
8. 用户登录地址：<http://localhost:8080/login/to_login>
9. 商品秒杀列表地址：<http://localhost:8080/goods/to_list>

## 项目描述

1. 使用分布式Seesion，让多台服务器可以响应。

2. 使用redis做缓存提高访问速度和并发量，减少数据库压力。

3. 页面缓存+URL缓存+对象缓存，让页面优化，提升访问速度。

4. 使用页面静态化，缓存页面至浏览器，前后端分离降低服务器压力。

5. 使用消息队列完成异步下单，提升用户体验，削峰和降流。

6. 安全性优化：双重md5密码校验，秒杀接口地址的隐藏，接口限流防刷，数学公式验证码。

## 图片演示

商品列表页面

![](https://github.com/gb145234/miaosha/blob/master/images/list.png)

商品详情页面

![](https://github.com/gb145234/miaosha/blob/master/images/goodsdetail.png)

商品秒杀倒计时

![](https://github.com/gb145234/miaosha/blob/master/images/wait.png)

成功秒杀页面

![](https://github.com/gb145234/miaosha/blob/master/images/miaoshasuccess.png)


## 作者

👤 **Gao Bo**

* Github: [@gb145234](https://github.com/gb145234)
* 博客:https://www.cnblogs.com/kyoner/

## 🤝 Contributing

Contributions, issues and feature requests are welcome!<br />Feel free to check [issues page](https://github.com/gb145234/miaosha/issues).

## Show your support

Give a ⭐️ if this project helped you!

