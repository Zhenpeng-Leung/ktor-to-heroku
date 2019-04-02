package leung.sample.ktortoheroku

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.html.*

fun Application.htmlModule() {
    install(HSTS)
    install(DefaultHeaders)
    install(AutoHeadResponse)
    routing {
        // 默认的路径,同get("/")
        get {
            call.respondHtml {
                // head标签
                head {
                    title("Ktor To Heroku")
                    meta("referrer", "always", "utf-8")
                    meta {
                        httpEquiv = "X-UA-Compatible"
                        content = "IE=edge,chrome=1"
                    }
                    meta {
                        httpEquiv = "content-type"
                        content = "text/html"
                    }
                    //CSS外部链接,调用cssModule的style1.css,html与css分离.
                    link("/path/favicon.ico", "shortcut icon", "image/x-icon")
                    link("/style1.css","stylesheet",  "text/css")
                }
                // body..
                body {
                    // 元素-类名
                    div("mainPart") {
                        // 元素-id
                        p {
                            id = "textContent"
                            +"这是一个Ktor部署到Heroku的例子" // 文本内容用 +"文本内容" ,同<p>文本内容</p>
                        }
                    }
                }
            }
        }
    }
}