# 帝可得后台管理系统（服务端）

![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5-6DB33F?logo=springboot&logoColor=white)
![RuoYi](https://img.shields.io/badge/Framework-RuoYi%203.8.7-4B6BFB)
![MySQL](https://img.shields.io/badge/Database-MySQL-4479A1?logo=mysql&logoColor=white)

基于 RuoYi 3.8.7 脚手架二次开发的“帝可得”后台管理系统服务端，包含商品、货道、工单、订单、策略和设备等业务模块。

## 功能模块

| 模块 | 说明 |
| --- | --- |
| 商品管理 | 商品信息维护与分类 |
| 货道管理 | 货道、库存与设备关联 |
| 工单管理 | 运维工单创建、流转与查询 |
| 订单管理 | 订单查询与状态处理 |
| 策略管理 | 运营策略配置 |
| 设备管理 | 设备信息与状态维护 |

## 技术栈

- Java 17、Spring Boot、Spring MVC
- MyBatis、PageHelper、Druid
- MySQL、Redis
- JWT、AOP、Quartz
- Maven 多模块

## 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+

## 配置

核心配置位于 dkd-admin/src/main/resources/application.yml 和 application-druid.yml。

数据库默认库名：dkd。数据库脚本位于 sql/ 目录。

敏感配置已改为环境变量：

~~~bash
export MYSQL_USERNAME=root
export MYSQL_PASSWORD=your-secret-here
export REDIS_PASSWORD=
export TOKEN_SECRET=change-me
~~~

## 启动方式

1. 创建 MySQL 数据库 dkd，并导入 sql/ 下的脚本；
2. 启动 Redis；
3. 在项目根目录执行：

~~~bash
mvn clean package
java -jar dkd-admin/target/dkd-admin.jar
~~~

或在 IntelliJ IDEA 中运行 com.dkd.DkdApplication。

## 关联仓库

- 前端：[RuoYi-dkd-client](https://github.com/Smasun0721/RuoYi-dkd-client)

## 目录结构

~~~text
RuoYi-dkd-server/
├── dkd-admin/          # 启动模块与配置
├── dkd-common/         # 通用工具
├── dkd-framework/      # 框架配置
├── dkd-generator/      # 代码生成
├── dkd-manage/         # 业务模块
├── dkd-quartz/         # 定时任务
├── dkd-system/         # 系统模块
└── sql/                # 数据库脚本
~~~

## 说明

本项目基于 RuoYi 3.8.7 脚手架二次开发，保留原框架结构；业务代码在此基础上实现商品、货道、工单、订单等管理功能。
