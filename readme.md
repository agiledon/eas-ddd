[![Build Status](https://travis-ci.org/agiledon/eas-ddd.svg?branch=master)](https://travis-ci.org/agiledon/eas-ddd)
[![codecov](https://codecov.io/gh/agiledon/eas-ddd/branch/master/graph/badge.svg)](https://codecov.io/gh/agiledon/eas-ddd)

### 项目介绍

EAS-DDD是我为GitChat课程《[领域驱动设计实战（战略篇）](https://gitbook.cn/gitchat/column/5b3235082ab5224deb750e02)》与《[领域驱动设计实战（战术篇）](https://gitbook.cn/gitchat/column/5cbed2f6f00736695f3a8699)》提供的实战项目案例。该案例取材自我参与过的真实项目，整个案例的代码完全按照领域驱动设计的过程进行建模、设计与编码。

通过访问本Repository对应的[Wiki](https://github.com/agiledon/eas-ddd/wiki)，可以了解EAS项目的需求、建模过程与建模产出物；更为详细的分析设计内容，请订阅我在GitChat上发布的课程。每个领域场景对应的用户故事、拆分的任务，请访问本Repository的[Issue](https://github.com/agiledon/eas-ddd/issues)。

### 环境

本项目的开发基于Java语言进行开发，具体环境包括：

```
Java: Java 8+
Maven: 3
Spring: 5.1.10+
Spring Boot：2.1.9
MyBatis：3.5.3
Druid：1.1.20
MySQL: 8.0 Community
```

我个人认为JPA ORM更加符合DDD的设计，在另外一个采用DDD开发的项目[Payroll-DDD](https://github.com/agiledon/payroll-ddd)，我选择的持久化框架就是Spring Data JPA。本项目之所以采用MyBatis，是考虑到MyBatis在国内企业软件开发领域中更为常见。同时，我也希望通过本项目说明使用MyBatis作为持久化框架，同样可以做DDD。

### 数据库环境准备

项目默认的数据库用户名为sa，密码为123456，数据库主机为localhost，数据库为eas-db。在安装了MySQL 8.0后，若数据库服务器信息与默认信息不同，请修改如下文件。在使用flywaydb执行数据库脚本时，需要确保数据库配置正确，并已经创建了eas-db数据库。

**flywaydb的数据库配置**

在`pom.xml`文件的`<plugins>`中配置了如下内容：

```xml
<plugin>
   <groupId>org.flywaydb</groupId>
   <artifactId>flyway-maven-plugin</artifactId>
   <version>${flyway.version}</version>
   <configuration>
        <driver>${db.driver}</driver>
        <url>${db.url}</url>
        <user>${db.username}</user>
        <password>${db.password}</password>
   </configuration>
</plugin>
```

在同一个pom文件的属性配置部分，配置了数据库的相关属性：

```xml
<properties>
    <db.driver>com.mysql.jdbc.Driver</db.driver>
    <db.url>jdbc:mysql://localhost:3306/eas-db?serverTimezone=UTC</db.url>
    <db.username>sa</db.username>
    <db.password>123456</db.password>
</properties>
```

一旦准备好flywaydb的环境，就可以运行命令执行DB的清理：

```
mvn package flyway:clean
```

或执行命令执行DB的迁移：

```
mvn package flyway:migrate
```

若要忽略运行单元测试，可以在Maven命令后面加上参数：

```
-DskipTests
```

**测试环境准备**

本项目采用测试驱动开发对领域层的类与方法进行了编码实现，因而领域层的相关方法基本皆为单元测试所覆盖。

应用服务则通过集成测试保障其正确性。若要运行集成测试，需通过flywaydb执行SQL脚本，以准备集成测试所必须的数据库环境。如果数据库配置与我的配置不同，除了要修改`pom.xml`文件中的flywaydb的配置之外，还需要各个限界上下文模块`test/resources/spring-mybatis.xml`文件的相关配置，如：

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">
    <context:component-scan base-package="xyz.zhangyi.ddd.eas.trainingcontext" />

    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          init-method="init" destroy-method="close">
        <property name="url" value="jdbc:mysql://localhost:3306/eas-db?serverTimezone=UTC"/>
        <property name="username" value="sa"/>
        <property name="password" value="123456"/>
        <property name="connectionProperties" value="com.mysql.jdbc.Driver"/>
    </bean>
</beans>    
```

#### 运行测试

默认情况下，如果运行`mvn test`则只会运行单元测试。如果确保数据库已经准备好，且通过flywaydb确保了数据库的表结构与测试数据已经准备好，可以运行`mvn integration-test`。该命令会运行所有测试，包括单元测试和集成测试。

**注意：**项目中所有的单元测试以`Test`为测试类后缀，所有集成测试以`IT`为测试类后缀。

### 运行Spring Boot服务

整个项目采用了单体架构，故而所有限界上下文的远程服务都通过一个统一的主程序入口，即eas-entry模块下的EasApplication。Spring Boot的应用配置在该模块的resources文件夹下，文件名为`application.yml`，内容为：

```yml
mybatis:
  mapperLocations: classpath:mapper/*.xml
  type-aliases-package: xyz.zhangyi.ddd.eas.trainingcontext.domain
  type-handlers-package: xyz.zhangyi.ddd.eas.trainingcontext.gateway.acl.impl.persistence.typehandlers

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/eas-db?serverTimezone=UTC
    username: sa
    password: 123456
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 使用druid数据源
    type: com.alibaba.druid.pool.DruidDataSource
```

若要启动Spring Boot服务，需确保数据库的配置正确。在没有配置端口的情况下，默认端口号为8080。你也可以在`application.yml`中指定端口。

可以直接在IDE下运行`EasApplication`启动Spring Boot服务。

如你所见，当前项目采用了单体架构，但是可以非常容易迁移到微服务架构。若采用Spring Boot公开服务，需要在每个模块的`pom.xml`文件中，加入`spring-boot-starter`的依赖。可以参考`eas-entry`模块的依赖配置。