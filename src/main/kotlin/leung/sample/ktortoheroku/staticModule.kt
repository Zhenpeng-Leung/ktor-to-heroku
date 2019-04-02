package leung.sample.ktortoheroku

import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.HSTS
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.routing.routing

fun Application.staticModule(){
    routing {
        // 静态路径可以为你加载不能动态生成的文件,如图像/脚本等,当然也可以加载html/css等文件.
        static("path"){
            // 将资源目录下的image文件夹映射到静态路径中.
            // 如: /path/favicon.ico 会在image文件夹内查找favicon.ico文件.
            resources("image")
        }
    }
}