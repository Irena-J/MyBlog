# 个人博客管理系统

### 项目背景

对个人博客、笔记的管理

### 开发环境

+ 编程语言：Java
+ 开发平台：Win10
+ 开发工具：IDEA编译器、Tomcat 服务器、MySQL数据库

### 技术栈

+ 数据库设计：使用 MySQL 数据库，JDBC 操作 DataSource，双重校验锁解决数据一致性问题
+ 前端：基于 Ajax、JSON 的数据请求和解析
+ 后端：使用 Servlet 处理 HTTP 请求

### 项目描述

基于 MVC 设计模式实现的网页版的简单博客系统，只支持单个用户对文章的 CURD

![image-20200825202053383](D:/typora/appdata/image-20200825202053383.png)

网页端显示页面：

+ 博客列表
+ 博客编辑详情页

当用户在博客列表页面执行某个操作时，比如新增博客，此时就会由客户端给服务器发送 HTTP 请求，请求就包含了用户行为，服务器根据行为完成对数据库响应操作

### 详细设计

+ 分析需求

+ 数据库设计，确定表结构
+ 实现数据库操作

+ 设计前后端交互接口

+ 实现客户端、服务器端操作逻辑

#### 分析需求

支持单个用户登录操作

实现针对文章的 CURD

#### 数据库设计

这里使用 MySQL 关系型数据库

###### 创建数据库

![image-20200825224202639](D:/typora/appdata/image-20200825224202639.png)

###### 设计用户表

+ id						primary key

+ name

+ create_time

![image-20200825224231462](D:/typora/appdata/image-20200825224231462.png) 

###### 设计文章表

+ article_id			 primary key
+ title                      not null
+ content               not null
+ user_id                foreign key
+ create_time

![image-20200825224411681](D:/typora/appdata/image-20200825224411681.png)



#### 数据库操作

###### 创建数据库对应实体类

###### 创建 DBUtil 类管理数据库连接

+ 使用 DataSource 作为数据库连接的创建方式
+ 设计释放 JDBC 资源的方法

###### 实现数据库相关操作，即封装文章的 CURD 操作

###### 完成单元测试，即文章 CURD 操作的测试



#### 前后端 API 设计



















