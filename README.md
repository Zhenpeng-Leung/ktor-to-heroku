# ktor-to-heroku

[中文版](/README.zh.md)
=======

#### Introduction
The example of `Ktor` deployed to `Heroku` also applies to `JavaFX`.

#### Ktor 
[Ktor](https://ktor.io/) is a framework for building asynchronous servers and clients in connected systems using the powerful [Kotlin](https://kotlinlang.org/) programming language. 
#### Heroku
[Heroku](https://www.heroku.com/) is a cloud service provider of `Salesforce`, providing convenient and convenient cloud services such as server, database, monitoring, computing and so on.
And he provided a free version, which made us a lot of convenience for those who usually want to do some small things, although he duration time and downtime restrictions, but for personal small programs is enough.

Unfortunately, `Heroku` does not support remote repositories other than `Github`, you can pull the project to `Github` for deployment.
It can also be pulled to the local deployment using `Heroku CLI`. The official website has a more detailed description, which is not discussed here.

#### Project Highlights
1. When the project is tested, add a task called `stage` in the [build.gradle](/build.gradle) build script.
    ~~~groovy
    // Create a task called `stage`, which must be `stage`, which will be triggered when `git push` to `Heroku` remote repository
    tasks.register("stage") {
        dependsOn("build") // The task depends on `build`.
    }
    ~~~
1. Create a file for [app.json](/app.json) in the root directory.
    ~~~json
    {
    "name": "KtorToHeroku",
    "description": "This is an example of how Ktor can deploy Heroku",
    "image": "heroku/java",
    "addons": [ "heroku-postgresql" ]
    }
    ~~~~
    * `name`=project name
    * `description`=project description
    * `image`= system image.
        Tell `Heroku` what kind of application is this way, such as `heroku/java` means that this is a Java application,
        Heroku needs to install Jdk and other artifacts needed to run.In fact `Heroku` packages various applications to 
        be packaged into a system image. When the application type is determined, the application's system image is automatically loaded,
        with `Java`, `PHP`, `Node.js`, `Python` and so on.
    * `addons`= plugin.
        This is a list type that can be added multiple times, separated by commas. `heroku-postgresql` means to install the `postgresql` database
1. Create a file for [system.properties](/system.properties) in the root directory, which tells `Heroku` which version of `Java` to use.
    ~~~properties
    java.runtime.version=1.8
    ~~~
1. Create a file for [Procfile](/Procfile) in the root directory.
    ~~~markdown
    web: java -jar build/libs/KtorToHeroku.jar
    ~~~
    * This is a necessary file for the server to run, telling `Heroku` where to start the server.
    * The first point mentioned is that every time `git push` to `Heroku` remote library will trigger the `stage` task, 
    and `stage` depends on `build`, `build` will trigger `jar` again.
    `build/libs/KtorToHeroku.jar` This path is the path where the task `jar` will package the application.
    
#### View project final results
[KtorToHeroku](https://leung-ktortoheroku.herokuapp.com/)