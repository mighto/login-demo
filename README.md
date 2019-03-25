# login-demo

## 一个简单的登录模拟

### 20190325：完成指定账号密码的登录

账号：admin

密码：password

#### 使用简介

1、启动服务后，调用localhost:8080/login?loginName=admin&password=password拿到返回的ticket。

2、通过ticket调用localhost:8080/user/getInfo?ticket=xxxxx拿到返回的用户信息。
