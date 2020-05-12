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

存储数据的处理方式：

如：	一篇文章-》 语义分析 -》 关键字提取 -》根据关键字及出现次数等---》 倒排存储

关键字搜索：

【查询分析】 关键字相关的近义词、反义词、关联信息（IP、地址、用户信息）



爬虫分类：

通用型爬虫、垂直型爬虫（对特定内容的采集）

![通用爬虫框架图](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\通用爬虫框架图.png)

网站的首页作为种子、爬虫去采集能够分解出多少不重复的子连接，及其数据。

采集/下载页面  -》 分解为数据本身（存储）、新的链接（依次向下爬取，类似树的深度遍历）-》 直到没有新链接代表采集完成（链接使用队列来存储）

（三）爬虫数据的分析

1.浏览器开发者工具

分析数据源

1）腾讯新闻出品

<https://news.qq.com/zt2020/page/feiyan.htm#/?nojump=1>



工具的打开方式：F12/ctrl+shift+I/更多工具-开发者工具 / 右键-检查

选中Network-Preserve log（保存持续的日志），重新刷新页面，可以看到网页所有的请求连接和返回数据。



想拿到表格中 国内疫情的数据情况

![1588945701012](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\国内疫情数据.png)



分析的方式：
通过搜索框，搜索想要获得数据的具体数值，如：944、758等等

分析后的请求地址：

https://view.inews.qq.com/g2/getOnsInfo?name=disease_h5

返回数据的格式：json

2）丁香医生出品

<https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline>

分析的方式如上：

分析后的请求地址：

https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline

返回的数据格式是html







2、postman（模拟http请求的工具）

验证分析出来的请求地址，在排除上下文环境后，是否依然能够拿到数据。

比如有的请求，依赖cookie、依赖动态的参数等等

![1588946083725](G:\学习\Java\Java笔记\leveltwo\SpringBoot项目课\images\postman.png)



3、爬虫破解问题

拿到数据是核心的一步。

公开数据只要是非恶意就允许采集，非恶意是指模仿人的行为采集的行为，不会高并发或者恶意攻击。

隐私数据都是由强大的加密处理的，防爬虫的手段是安全领域内的一大问题。

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
1) 引入gson依赖

```
<dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.8.5</version>
</dependency>
```
 2） 解析文本数据

 ```java
public class DataHandler {

    public static String testStr = "{\"name\":\"zdefys科技\"}";


    public static List<DataBean> getData() throws Exception {
//        Gson gson = new Gson();
//        Gson gson1 = new GsonBuilder().create();
//        Map map = gson.fromJson(testStr, Map.class);
//        System.out.println(map);

        // 读取文件中的文本内容，然后再转化为java对象
//        File file = new File("tmp.txt");
        FileReader fr = new FileReader("tmp.txt");
        char[] cBuf = new char[1024];
        int cRead = 0;
        StringBuilder builder = new StringBuilder();
        while ((cRead = fr.read(cBuf)) > 0) {
            builder.append(new String(cBuf, 0, cRead));
        }
        fr.close();
//        System.out.println(builder.toString());
        Gson gson = new Gson();
        Map map = gson.fromJson(builder.toString(), Map.class);
//        System.out.println(map);
        ArrayList arrayList = (ArrayList) map.get("areaTree");
        Map dataMap = (Map) arrayList.get(0);
        ArrayList childrenList = (ArrayList) dataMap.get("children");

        // 遍历然后转化
        List<DataBean> result = new ArrayList<>(34);
        for (int i = 0; i < childrenList.size(); i++) {
            Map tmp = (Map) childrenList.get(i);
            // 区域名字
            String name = (String) tmp.get("name");
            Map totalMap = (Map) tmp.get("total");
            // 现有确认
            double nowConfirm = (Double) totalMap.get("nowConfirm");
            // 累计确诊
            double confirm = (Double) totalMap.get("confirm");
            // 治愈人数
            double heal = (Double) totalMap.get("heal");
            // 死亡人数
            double dead = (Double) totalMap.get("dead");

            DataBean dataBean = new DataBean(name,(int)nowConfirm,(int)confirm,(int)heal,(int)dead);
            result.add(dataBean);
        }

        return result;
    }
}
 ```



## Day3

#### （三） 将数据展示在页面中

1.编写service和controller

```java
public interface DataService {

    List<DataBean> list();
}
@Service
public class DataServiceImpl implements DataService {

    @Override
    public List<DataBean> list() {
        List<DataBean> result = null;
        try {
            result = DataHandler.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
@Controller
public class DataController {

    @Autowired
    DataService dataService;

    @GetMapping("/")
    public String list(Model model){
        List<DataBean> list = dataService.list();
        model.addAttribute("dataList", list);
        return "list";
    }

}

```

2.编写静态页面

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" >
    <title>Title</title>
</head>
<body>
    <h2>国内疫情情况如下</h2>

    <br>

    <table>
        <thead>
            <tr>
                <th>地区</th>
                <th>现有确诊</th>
                <th>累计确诊</th>
                <th>治愈</th>
                <th>死亡</th>
            </tr>
        </thead>
        <tbody>
        <tr th:each="data:${dataList}">
            <td th:text="${data.area}">name</td>
            <td th:text="${data.nowConfirm}">nowConfirm</td>
            <td th:text="${data.confirm}">confirm</td>
            <td th:text="${data.heal}">heal</td>
            <td th:text="${data.dead}">dead</td>
        </tr>
        </tbody>
    </table>
</body>
</html
```

#### (四) 转为实时数据

涉及知识点：用java代码模拟http请求

1、复习get和post请求

​	分别在使用场景、参数传递方式、数据大小限制、安全性等方面的异同。

2、HttpURLConnection

#### (五) 使用Jsoup解析html格式数据

#### (六)  增加数据存储逻辑

