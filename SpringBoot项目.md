# SpringBoot项目
项目访问地址：http://118.190.27.19:8080/

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

```
// 连接时间和读取时间
// 连接时间：发送请求端 连接到 url目标地址端的时间。
//          受到距离长短和网络速度的影响
// 读取时间：指连接成功后  获取数据的时间
//          受到数据量和服务器处理速度的影响
```

​	1） 通过创建url打开远程连接（HttpURLConnection）

​	2） 设置相应参数（超时时间和请求头）

​	3） 发送请求

​	4） 接收结果（使用InputStream和BufferedReader）

```java
public class HttpURLConnectionUtil {

        public static String doGet(String urlStr) {
            HttpURLConnection conn = null;
            InputStream is = null;
            BufferedReader br = null;
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urlStr);
                // 通过url打开一个远程连接 强转类型
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // 连接时间和读取时间
                // 连接时间：发送请求端 连接到 url目标地址端的时间。
                //          受到距离长短和网络速度的影响
                // 读取时间：指连接成功后  获取数据的时间
                //          受到数据量和服务器处理速度的影响
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(60000);
                // 设定请求头参数的方式：如指定接收json数据  服务端的key值为content-type
                conn.setRequestProperty("Accept", "application/json");

                // 发送请求
                conn.connect();
                if (conn.getResponseCode() != 200) {
                    // TODO 此处应该增加异常处理
                    return "";
                }
                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String line;
                // 逐行读取 不为空就继续
                while ((line = br.readLine()) != null) {
                    result.append(line);
                    System.out.println(line);
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (is != null) {
                        is.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return result.toString();
        }
```

## Day 4

#### (五) 使用Jsoup解析html格式数据

1、Jsoup

​	是html的解析器，可以解析html文本和直接解析URL地址。

引入依赖

```
<!-- https://mvnrepository.com/artifact/org.jsoup/jsoup -->
<dependency>
    <groupId>org.jsoup</groupId>
    <artifactId>jsoup</artifactId>
    <version>1.13.1</version>
</dependency>

```

```java
//        Document document = Jsoup.parse(htmlStr);
        // 通过标签名找到元素
//        Elements element = document.getElementsByTag("p");
//        System.out.println(element);
        // 通过id找到元素
//        document.getElementById()
        // 通过正则表达式找到元素
//        Elements element = document.select("a[href]);

```

2、提供不同数据源的切换查询

1）增加了 controller方法

```java
@GetMapping("/list/{id}")
public String listById(Model model,@PathVariable String id){
    List<DataBean> list = dataService.listById(Integer.parseInt(id));
    model.addAttribute("dataList", list);
    return "list";
}
```

@PathVariavle 将接收到的地址数据，映射到方法的参数中

2） 完善service

```java
@Override
public List<DataBean> listById(int id) {
    if (id==2) {
        return JsoupHandler.getData();
    }
    return list();
}
```

3）处理数据的方法

```
public class JsoupHandler {

    // 丁香医生
    public static String urlStr = "https://ncov.dxy.cn/ncovh5/view/pneumonia?from=timeline";

    public ArrayList<DataBean>  getData() {
        ArrayList<DataBean> result = new ArrayList<>(34);

        try {
            Document doc = Jsoup.connect(urlStr).get();
//            Elements scripts = doc.select("script");
            // 找到指定的标签数据
            Element oneScript = doc.getElementById("getAreaStat");
            String data = oneScript.data();
            // 字符串截取出json格式的数据
            String subData = data.substring(data.indexOf("["), data.lastIndexOf("]") + 1);
            Gson gson = new Gson();

            ArrayList list = gson.fromJson(subData, ArrayList.class);
            for (int i = 0; i < list.size(); i++) {
                Map map = (Map) list.get(i);
                String name = (String) map.get("provinceName");
                double nowConfirm = (double) map.get("currentConfirmedCount");
                double confirm = (double) map.get("confirmedCount");
                double heal = (double) map.get("curedCount");
                double dead = (double) map.get("deadCount");

                DataBean dataBean = new DataBean(name,(int)nowConfirm,(int)confirm,(int)heal,(int)dead);
                result.add(dataBean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
```



4）验证

分别访问http://localhost:8080/list/1 和http://localhost:8080/list/2

通过省份的名称来区分不同渠道的数据结果



#### (六)  增加数据存储逻辑

**1、引入相关的依赖**

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.2.0</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.19</version>
</dependency>
```

**2、配置数据库**

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://118.190.27.19:3306/epidemic?serverTimezone=UTC&useUnicode=true&useSSL=false&characterEncoding=utf8
spring.datasource.username=root
spring.datasource.password=zdefys
```



**3、使用mybatis-plus进行增删改查的操作**

**4、初始化数据存储的逻辑**

@PostConstruct

修饰的方法，在服务器加载Servlet时运行，而且只执行一次

@Scheduled

1） fixedRate = 10000 指定频率的执行任务 从方法执行开始就计时。

​	假设方法执行5s  那么第一次执行开始过了10s后，开始第二次执行

2） fixedDelay = 10000 指定间隔的执行任务  从方法执行完成开始计时

​	假设方法执行5s  那么第一次执行完成过了10s后，开始第二次执行

3）cron表达式

​	<https://cron.qqe2.com/>

​	把6个位置用空格分隔，指代不同单位的时间，执行的规律

​	秒、分钟、小时、日期、月份、星期、（年，可选）

