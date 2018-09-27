import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.lang.Thread.sleep
import java.time.*


fun main(args: Array<String>) {
    val nowDate = LocalDateTime.now()
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondText("Hell World!", ContentType.Text.Plain)

            }
            get("/date") {
                call.respondText(nowDate.toString())
            }
            get("/wait") {
                var millisecond = call.parameters ["delay"]!!
                sleep (millisecond.toLong())
                call.respondText { "Sleep for " + millisecond + "ms completed" }


            }
        }
    }
    server.start(wait = true)
}

///wait?delay=10