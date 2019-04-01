# ktor-to-heroku 
[English edition](/README.md)
==============
#### 介绍
`Ktor` 部署到 `Heroku` 的例子，同样适用于 `JavaFX`。

#### Ktor
`Ktor` 是一个使用强大的 `Kotlin` 语言在互联系统中构建异步服务器与客户端的框架。

Kotlin [官网](https://kotlinlang.org/) [中文网](https://www.kotlincn.net/)

Ktor [官网](https://ktor.io/) [中文网](https://ktor.kotlincn.net/)

#### Heroku
`Heroku` 是 `Salesforce` 旗下云服务商，提供方便便捷的各种云服务，如服务器、数据库、监控、计算等等。
并且他提供了免费版本，这使得我们这些平时想搞一些小东西的人提供了莫大的便捷，虽然他有时长和宕机的限制，但是对于个人小程序来说已经足够了。

Heroku [官网](https://www.heroku.com/) [注¹](#注)

遗憾的是，`Heroku` 不支持除 `Github` 以外的其它远程仓库，你可以将项目拉取到 `Github` 上进行部署，
也可以拉取到本地使用 `Heroku CLI` 进行部署，官网上有更详细的说明，这里不做讨论。

#### 项目要点
1. 当项目测试通了以后，在 [build.gradle](/build.gradle)构建脚本中添加名为`stage`的任务。
    ~~~groovy
    // 创建一个名为 'stage' 的任务，必须是 'stage'，当git push到Heroku远程库是会触发这个任务
    tasks.register("stage"){
        // 任务依赖于 `clean`和`installDist`， `installDist`是`application`插件内建的任务，
        // 作用是安装项目到指定目录，默认路径为:"$rootDir/build/install/${project.name}"。
        dependsOn("clean", "installDist")
        tasks["installDist"].mustRunAfter("clean") // 任务`installDist`必须在`clean`之后运行。
    }
    ~~~
1. 在根目录下创建 [app.json](/app.json) 的文件。
    ~~~json
    {
    "name": "KtorToHeroku",
    "description": "这是一个可将Ktor部署Heroku的例子",
    "image": "heroku/java",
    "addons": [ "heroku-postgresql" ]
    }
    ~~~~
    * `name`=项目名称
    * `description`=项目描述
    * `image`=系统镜像。
        告诉`Heroku`这是什么样的应用程序，以怎样的方式运行，如 `heroku/java` 是指这是一个Java应用程序，Heroku需要安装Jdk等运行所需要的工件。
        事实上 `Heroku` 将各种应用程序运行所需打包成一个个系统镜像，当确定应用类型后会自动加载该应用的系统镜像，有`Java`、`PHP`、`Node.js`、`Python`等等。
    * `addons`=插件。
        这是一个列表类型，可添加多个，用逗号隔开。`heroku-postgresql` 意思是安装 `postgresql` 数据库
1. 在根目录下创建 [system.properties](/system.properties) 的文件，这是 `Java` 虚拟机运行所需的属性。
    ~~~properties
    # Java以怎样的兼容方式运行，一般与 build.gradle 构建脚本中的 java.sourceCompatibility 相匹配。
    java.runtime.version=1.8
    ~~~
1. 在根目录下创建 [Procfile](/Procfile) 的文件。
    ~~~text
    web: build/install/KtorToHeroku/bin/KtorToHeroku
    ~~~
    * 这是服务器运行所必需要的文件，告诉 `Heroku` 从哪里启动服务器。
    * 前面第一点讲到每次 `git push` 到 `Heroku` 远程库都会触发 `stage` 的任务，
    `build/install/KtorToHeroku/bin/KtorToHeroku` 这个路径就是任务 `installDist` 将应用程序所分发的路径。
    
#### 注:
注¹: 如果不能访问，请自行科学上网。