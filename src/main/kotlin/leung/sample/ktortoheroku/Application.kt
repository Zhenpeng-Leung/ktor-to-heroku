package leung.sample.ktortoheroku

import io.ktor.application.Application
import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlin.random.Random


fun main() {
    // 引擎配置,如果使用多模块,这是必需的!
    val env = applicationEngineEnvironment {
        // 服务器端口
        connector {
            // 如果服务器定义了环境变量，使用服务器定义，而不是硬编码!
            host = "localhost"
            port = System.getenv("PORT")?.toInt() ?: 1080
        }
        //watchPaths = listOf("build/classes") // 检测文件改动,动态载入.在最终发布时,应该注释此行,否则服务器会有性能损失!
        // 加入模块,模块化能让你更好的管理项目,例如单个页面单个模块等等.
        modules.add(Application::htmlModule)
        modules.add(Application::cssModule)
        modules.add(Application::staticModule)
    }
    embeddedServer(Netty, env).start(wait = true)
}
