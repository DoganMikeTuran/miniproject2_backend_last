import java.io.InputStream
import java.io.OutputStream


enum class Protocol {GET, POST, PUT, DELETE}

class Request (i : InputStream) {

    var command : String = ""
    var protocol : Protocol = Protocol.valueOf("GET")

    init {
        val reader = i.bufferedReader()
        val line = reader.readLine()
        if(line.isNotEmpty()) {
            println(line)
            command = line.split(" ")[1]
            protocol = Protocol.valueOf(line.split(" ")[0])
        }
    }
}

class Response ( val o : OutputStream) {
    val body = StringBuilder();

    fun setBody (text: String)
    {
        body.append(text)
    }

    fun send()
    {
        val head = """
            HTTP/1.1 200 OK
            Content-Type: text/html; charset=UTF-8
            Access-Control-Allow-Origin: *
            Content-Length: ${body.length}
            Connection: close
            
        """.trimIndent()

        println(head)

        println(body)

        val writer = o.bufferedWriter()
        writer.append(head)
        writer.newLine()
        writer.append(body)
        writer.close()
    }
}