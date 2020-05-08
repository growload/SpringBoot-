# SpringBoot项目

## Day1

### 【课程目标】

1.SpringBoot的常见应用

2.业务功能的开发思路

3.爬虫的底层原理

4.对技术的应用有一定思考

### 【项目的诞生】

项目成员：项目经理 （PM）

产品经理PD、UI设计师UED、前端工程师FE、后端工程师RD

测试工程师QA、运维工程师OP



### 【功能的诞生】

产品经理->需求评审会 -> UI设计师（交互）- >  UI评审  -> 技术 -->  技术方案设计 ->  开发（1/3）->测试和修改



### 【爬虫的基础知识】

爬虫背景知识

“搜索引擎”是一种帮助用户搜索他们需要内容的计算机程序。

连接人与内容

![搜索引擎架构图](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\搜索引擎架构图.png)

爬虫分类：

通用型爬虫、垂直型爬虫（对特定内容的采集）

![通用爬虫框架图](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\通用爬虫框架图.png)

爬虫数据的分析

1.浏览器开发者工具

分析数据源

1）腾讯新闻出品

<https://news.qq.com/zt2020/page/feiyan.htm#/?nojump=1>

2）丁香医生出品

<https://ncov.dxy.cn/ncovh5/view/pneumonia>

工具的打开方式：F12/ctrl+shift+I/更多工具-开发者工具 / 右键-检查

选中Network-Preserve log（保存持续的日志），重新刷新页面，可以看到网页所有的请求连接和返回数据。



想拿到表格中 国内疫情的数据情况

![1588945701012](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\国内疫情数据.png)



分析的方式：
通过搜索框，搜索想要获得数据的具体数值，如：944、758等等

分析后的请求地址：

https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5

返回数据的格式json



2、postman（模拟http请求的工具）

验证分析出来的请求地址，在排除上下文环境后，是否依然能够拿到数据。

比如有的请求，依赖cookie、依赖动态的参数等等

![1588946083725](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\postman.png)



## Day2

### 【解析数据】

#### （一）认识JSON

JSON = JavaScript Object Notation （JavaScript对象表示法）

本质上，是存储和交换文本信息的语法。是一种轻量级的文本数据格式。



java领域内解析json的工具：gson、fastjson、jackson



JSON和Java实体类

|    JSON     |           Java实体类           |
| :---------: | :----------------------------: |
|   string    |        java.lang.String        |
|   number    |   java.lang.Number（Double）   |
| true、false |       java.lang.Boolean        |
|    null     |              null              |
|    array    |  java.util.List（ArrayList）   |
|   object    | java.util.Map（LinkedTreeMap） |



#### （二）Gson

是google推出的，用来在json数据和java对象之间进行转换的类库。

```java
Gson gson = new Gson();
Gson gson1 = new GsonBuilder().create();

// 将对象obj转化为json字符串
String jsonStr = gson.toJson(obj);
// 将json字符串转化为java对象
T obj = gson.fromJson（jsonStr,class）;

```



使用方式：

