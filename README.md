# spring项目(SSM项目)集成nacos注册配置中心

## 背景
公司老项目，一下子改造成springboot比较困难，只能一个模块一个模块的迁移， 迁移的同时，老项目(SSM项目)要继续运行，所以就有了此demo。

## nacos版本
> 尽量保证版本一致
* nacos server 2.2.0
* nacos client 1.1.2

## 快速开始
1. 启动nacos server
2. 启动本项目
2.1. 将resource/sql目录下的sql脚本ssm_template.sql导入自己的数据库
2.2. 修改resources/jdbc.properties
```properties
jdbc.user=root
jdbc.password=your_password
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8
```
2.3. 修改各环境下nacos配置信息, 如`nacos-config-dev.properties`
```properties
# nacos配置
nacos.server-addr=127.0.0.1:8848
# nacos.namespace 默认为public，不要配置，配置后会无限输出ClientWorker日志信息，除非是自定义的namespace;自定义的namespace时配置的是【命名空间ID】, 非【命名空间名称】
# nacos.namespace=public
nacos.group.id=DEFAULT_GROUP
nacos.data.id=spring-nacos-demo-config.yaml

# 服务名
service.name=spring-nacos-demo
# 服务启动端口
server.port=8080
# 服务所在组
server.group-name=public
```
2.4. 在nacos中添加应用配置文件
Data ID:spring-nacos-demo-config.yaml
Group:DEFAULT_GROUP
配置格式:YAML
配置内容:
```yaml
enterprise:
  name: 测试公司
  website: 'https://www.demo.com'
  department-list: 11-HR-人事部;13-R&D-研发部
```
2.5. 配置启动参数，此列用了`tomcat7-maven-plugin`
```shell
tomcat7:run -Dspring.profiles.active=dev
```
3. 启动项目

## 项目运行效果
1. 获取公司信息
```shell
# 请求
curl http://localhost:8080/config/enterprise

# 返回结果
{
  "companyName" : "测试公司",
  "website" : "https://www.demo.com"
}
```
2. 获取所有部门
```shell
# 请求
curl http://localhost:8080/config/departments
# 返回结果
[ {
  "id" : "11",
  "code" : "HR",
  "name" : "人事部"
}, {
  "id" : "13",
  "code" : "R&D",
  "name" : "研发部"
} ]

```
3.在naocs中修改配置，再次请求，配置会自动刷新

## 注意点(一些坑)
* 1.nacos.namespace 默认为public，不要配置，配置后会无限输出ClientWorker日志信息，除非是自定义的namespace;
自定义的namespace时配置的是【命名空间ID】, 非【命名空间名称】
[issue链接](https://github.com/alibaba/nacos/issues/2684)
* 相关配置类: `NacosConfigConfiguration` 和 `NacosDiscoveryConfiguration`；可合并为一个

## 参考资料:
* 本示例ssm模板: https://github.com/AlanLee97/ssm-template.git
* nacos与spring集成: https://nacos.io/zh-cn/docs/v2/ecology/use-nacos-with-spring.html
* Nacos为Map和List注入数据: https://chuanyichuan.github.io/2020/10/22/Nacos%E4%B8%BAMap%E5%92%8CList%E6%B3%A8%E5%85%A5%E6%95%B0%E6%8D%AE/index.html