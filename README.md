# CodeSpace 项目说明

## log

2024-06-18: 

- [x] 上传成功积分提示
- [x] 上传失败提示（支持实现语言：C、C++、C#、Java、JavaScript、Golang、Python、伪代码）
- [x] 上传文件自动填写算法名为文件名
- [x] 登录/注册失败提示
- [x] 登录界面按钮布局优化；注册界面密码强度提示
- [x] 用户编辑个人信息（图片上传接口还没写，所以暂时改不了图片）
- [x] 管理员登录时用户界面名字旁添加管理员标识

2024-06-19:

- [x] 添加管理员用户管理（查看用户信息，查看用户所有算法库，删除用户）
- [x] 添加管理员删除算法库功能
- [x] 算法信息界面显示创建时间与修改时间
- [x] 搜索结果显示作者名，并添加删除按钮
- [x] 删除算法库时扣除对应得分
- [x] 用户和管理员可以修改算法库的基本信息 
- [x] 用户编辑算法库文件内容
- [x] 显示文件内容时，实现对应代码高亮

## 前端

### Introduction 

前端使用 [VUE](https://cn.vuejs.org/) 框架开发，需要先掌握 html/css/javascript 的基本使用，快速入门看[这里](https://developer.mozilla.org/zh-CN/docs/Learn/Getting_started_with_the_web/HTML_basics)


- 项目中使用 SCSS 库简化 CSS 样式编写
- VUE 统一使用 组合式 API (Composition API) 风格
- 本项目为单页面应用，除了注册登录界面，其他使用 Vue Router 管理路由

### Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + Vue-Official插件.

### Project Setup

```sh
# 自行安装 node.js
cd frontend
npm install
npm run dev
```

## 后端

### Introduction 

后端使用 Java SpringBoot Web 构建接口，一个接口大致如下构成：

- Controller：规定接口 API 的参数形式与返回
- Server：中间逻辑处理，一般分为 Interface 和 Implement 两部分，便于不同实现的复用
- Mapper：处理数据库，基本以 SQL 形式，掌握 [Mybatis](https://www.bilibili.com/video/BV1MT4y1k7wZ/)

---

- 接口返回值类型统一使用 Result 类  
- 接口风格统一为 Restful 风格，[相关介绍](http://ruanyifeng.com/blog/2014/05/restful_api.html)
- 后端接口使用 8080 端口（http://localhost:8080） 
- 后端接口实现后使用 [Postman](https://www.postman.com/) 测试


后端数据库使用 MySQL 8.3.0，账号密码 root/123456，数据库名为 codespace

`@RequestBody LoginInfo loginInfo` -> LoginInfo 在 `pojo` 中定义实体类  
`@PathParam("username") String username`

- 导出数据库：`mysqldump -u root -p codespace > codespace.sql`
- 导入数据库：`mysql -uroot -p codespace < codespace.sql` // 先创建 codespace 数据库

### Recommended IDE Setup

IntelliJ IDEA，JDK 17

### Project Setup

安装 Maven 依赖，运行 CodeSpaceApplication.java