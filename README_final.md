# TargetedCourse
## 前言
   `eduplatform`是一个集精准课程和学习助手于一体的在线教育平台，该平台旨在给学生以精准的试题服务、给教师以便捷的教学服务。而`TargetedCourse`则是其中提供精准课程与精品资源的部分。

## 项目介绍
　　基于Spring Boot、AngularJS的web项目以及基于Spring Boot、ionic、typescript的移动端项目。

### 组织结构

``` 
TargetedCourse
|
├─── src    -- src文件夹
|     ├─── main -- main文件夹
|     |      ├── java -- java代码文件夹
|     |      |     └── org.sklse.targetedcourse
|     |      |                   ├── bean                            -- 实体类文件夹
|     |      |                   ├── controller                      -- 控制器类文件夹
|     |      |                   ├── repository                      -- repository类文件夹
|     |      |                   ├── service                         -- 服务类文件夹
|     |      |                   └── TargetedCourseApplication.java  -- 入口类
|     |      |                                    
|     |      ├── resources -- 配置文件夹
|     |      |         ├── static                  -- 静态文件文件夹
|     |      |         ├── templates               -- 页面文件文件夹
|     |      |         ├── application.properties  -- 配置文件
|     |      |         └── Banner.txt              -- Banner文件
|     |	     |
|     |	     └── ionic —- ionic项目
|     |
|     └─── test -- 测试文件夹
|            └── java -- java代码文件夹
|                  └── org.sklse.targetedcourse
|         
├── pom.xml
├── .gitignore  -- .gitignore文件
└── README.md   -- readme文件
```

### 技术选型

#### 后端技术:
技术 | 名称 | 官网
----|------|----
Spring Framework | 容器  | [http://projects.spring.io/spring-framework/](http://projects.spring.io/spring-framework/)
Spring Boot | Spring Boot  | [https://spring.io/docs/](https://spring.io/docs)
Maven | 项目构建管理  | [http://maven.apache.org/](http://maven.apache.org/)

#### 前端技术:
技术 | 名称 | 官网
----|------|----
jQuery | 函式库  | [http://jquery.com/](http://jquery.com/)
Bootstrap | 前端框架  | [http://getbootstrap.com/](http://getbootstrap.com/)
Bootstrap-table | Bootstrap数据表格  | [http://bootstrap-table.wenzhixin.net.cn/](http://bootstrap-table.wenzhixin.net.cn/)
AngularJS | JavaScript框架  | [https://docs.angularjs.org/guide](https://docs.angularjs.org/guide)

#### 模块介绍

> 待完善

### 框架规范约定

约定优于配置(convention over configuration)，此框架约定了很多编程规范，下面一一列举：

```
- bean实体类，需要在名叫`bean`的包下，命名规则为数据表转驼峰规则，如`UserDetail.java`

- controller类，需要在以`controller`结尾的包下，类名以Controller结尾，如`UserController.java`

- repository，需要在叫名`repository`的包下，并以`Impl`结尾，如`UserServiceImpl.java`

- service类，需要在叫名`impl`的包下，并以`Impl`结尾，如`UserServiceImpl.java`

- service接口，需要在叫名`service`的包下，并以`Service`结尾，如`UserService.java`

- util类，需要在以`util`的包下，并以`Util`结尾，如`JsonUtil.java`

- 类名：首字母大写驼峰规则；方法名：首字母小写驼峰规则；常量：全大写；变量：首字母小写驼峰规则，尽量非缩写

- 静态资源文件放到`src/main/resource/static`目录下

- 提交说明：提交说明尽量统一，如[init]、[add]、[delete]、[update]等加上message；如需要回滚请注意备份

```

## 演示地址
演示地址：待完善