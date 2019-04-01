package leung.sample.ktortoheroku

import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.css.*
import kotlinx.css.properties.TextDecorationLine
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.css.properties.textDecoration
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.style

suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}

fun Application.cssModule() {
    routing {
        get("style1.css") {
            call.respondCss {
                // 元素选择器,详情请参考CSS Selector.
                rule("*") {
                    margin(0.px)
                    padding(0.px)
                }
                // Kotlin CSS定义了所有的单元素选择器DSL,同rule("body").
                body {
                    backgroundColor = Color.floralWhite
                }
                // 类名..
                rule(".mainPart") {
                    margin(0.px, LinearDimension.auto)
                    maxWidth = 960.px
                    minWidth = 400.px
                    height = 100.vh
                    backgroundColor = Color.white
                    border(1.px, BorderStyle.solid, Color.white)
                    boxShadow(Color.gray, 1.px, 2.px, 3.px, 0.px)
                }
                // Id..
                rule("#textContent") {
                    margin(10.vh, LinearDimension.auto)
                    width = LinearDimension.maxContent
                    fontSize = 1.5.rem
                    textDecoration(TextDecorationLine.unset)
                }
            }
        }
    }
}