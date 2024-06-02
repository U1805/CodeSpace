# CodeSpace 项目说明

## 前端

### Introduction 

前端使用 [VUE](https://cn.vuejs.org/) 框架开发，需要先掌握 html/css/javascript 的基本使用，快速入门看[这里](https://developer.mozilla.org/zh-CN/docs/Learn/Getting_started_with_the_web/HTML_basics)


- 项目中使用 SCSS 库简化 CSS 样式编写
- VUE 统一使用 组合式 API (Composition API) 风格
- 本项目为单页面应用，除了注册登录界面，其他使用 Vue Router 管理路由

### Recommended IDE Setup

[VSCode](https://code.visualstudio.com/) + [Volar](https://marketplace.visualstudio.com/items?itemName=Vue.volar) (and disable Vetur).

### Project Setup

```sh
cd frontend
npm install
npm run dev
```

## 后端

### Introduction 

后端使用 Java SpringBoot Web 构建接口，一个接口大致如下构成：

- Controller：规定接口 API 的参数形式与返回
- Server：中间逻辑处理，一般分为 Interface 和 Implement 两部分，便于不同实现的复用
- Mapper：处理数据库，基本以 SQL 形式

---

- 接口返回值类型统一使用 Result 类  
- 接口风格统一为 Restful 风格，[相关介绍](http://ruanyifeng.com/blog/2014/05/restful_api.html)
- 后端接口使用 8080 端口（http://localhost:8080） 
- 后端接口实现后使用 [Postman](https://www.postman.com/) 测试


后端数据库使用 MySQL，账号密码 root/123456，数据库名为 codespace

### Recommended IDE Setup

IntelliJ IDEA，JDK 17

### Project Setup

安装 Maven 依赖，运行 CodeSpaceApplication.java