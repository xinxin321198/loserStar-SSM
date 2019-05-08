# loserStar-SSM
## 一个简单SSM基础框架
* ## 此项目是为了方便临时需要开发一些小功能时候可以很快捷的直接拿来用，很大众很简单的架构，基于maven和gradle构建（gradle的配置好久没更新了，基本都是更新maven的）
* ## 基于springMVC+spring+mybatis作为基础，且依赖自己的工具库[loserStarUtils](https://github.com/xinxin321198/loserStarUtils)
* ## dbSql目录下BaseDb.sql里是mysql的数据库基础表结构的demo
* ## 逐步会加入一些新的用到的框架集成进去，如果不需要的框架可以直接剔除就行了
* ## 集成了shiro作为权限框架（只是简单引入，还未与RBAC模型对接）
* ## 集成了jfinal的DB record部分，可与mybatis共存，jfinal比起myabtis有自己的优势，因为有时维护那种无源码、无文档、无表字段备注的三无项目你根本无法把业务关系理清爽，又要写Mapper.xml又建Entity的特别麻烦，不如直接上sql来的方便
* ## 对于jfinal查询数据的模式，自己又觉得不太理想，在[loserstarUtils](https://github.com/xinxin321198/loserStarUtils)中又封装了一下jfinal查数据的方式，更符合三层架构的开发规范

* ### *2019.05 帮朋友做了一个上传excel解析数据保存到mysql，并且统计的功能，以此作为一个常规开发的demo 功能地址为：[http:127.0.0.1:8080/loserStar-SSM/oreDatapurchased.do](http:127.0.0.1:8080/loserStar-SSM/oreDatapurchased.do)；此功能集成了[百度fex-team团队的WebUploader文件上传组件](https://github.com/fex-team/webuploader)和[jqwidgets](https://www.jqwidgets.com/)，功能相关表结构在dbSql目录下的oreDb.sql中，excel模板在excelTamplate目录下*

> PS:
> ##### gradle：运行gradle eclipse生成eclipse相关工程文件，导入运行（或直接用IDE的> 相关插件导入也行）
> * ##### gradle文件头插件使用java才能执行Java的工程构建命令
> * ##### gradle文件头插件使用war才能生成Java web工程
> * ##### gradle文件头插件使用eclipse才能生成eclipse相关工程文件
> * ##### maven：运行mvn eclipse:eclipse生成相关的eclipse工程文件，导入运行（或直接 用IDE的相关插件导入也行）
> * ##### 导入后项目右键-maven-update project，让eclipse重新生成.project 和.classpath文件
------