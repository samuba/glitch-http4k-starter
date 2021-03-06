package glitch

import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.path
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun main() {

    val app = routes(
        "/" bind GET to {
            Response(OK).body("This is a http4k server running on kotlin v${KotlinVersion.CURRENT}")
        },
        "/greet/{name}" bind GET to { req ->
            val name = req.path("name")
            Response(OK).body("hello $name")
        }
    )

    app.asServer(Undertow(3000)).start()
    println("http4k server running...")
}