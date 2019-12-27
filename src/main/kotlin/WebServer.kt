package server

import Gamer
import GamerContent
import JsonConverterTool
import Request
import Response
import WebContent
import java.net.ServerSocket
import kotlin.concurrent.thread
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking




class WebServer(val gamerContent: WebContent, val port: Int = 80) {
    var running = true
   
    //val gson =
    //-> Gson()
    //-> GsonBuilder().setPrettyPrinting().create() // for pretty print feature

    fun handleSocket (req : Request, res : Response) {
        println(req.command)
        if(req.command.split("/")[1] == "getGamer") {
            if(req.command.isNotEmpty()){
                if(req.command.split("/").size == 2) {
                    // kun getGamer
                    val jsonString = JsonConverterTool().gamersListToJson(gamerContent.getGamer());
                    res.setBody(jsonString)
                    res.send()
                } else {
                    // getGamer by id
                    val id = req.command.split("/")[2];
                    val jsonString = JsonConverterTool().gamerToJson(gamerContent.getGamer(id.toInt()));
                    res.setBody(jsonString)
                    res.send()
                }
            }
        }

        // localhost:80/putGamer/id=500&nickname=christian&score=120
        if(req.command.split("/")[1] == "putGamer"){
            if(req.command.isNotEmpty()){
                val rawGamer = req.command.split("/")[2].split("&");
                val gamerId = rawGamer[0].split("=")[1];
                val gamerNickname = rawGamer[1].split("=")[1];
                val gamerScore = rawGamer[2].split("=")[1];


                if(gamerId.isNotEmpty() && gamerNickname.isNotEmpty() && gamerScore.isNotEmpty()){
                    val newGamer = Gamer(gamerId.toInt(), gamerNickname, gamerScore.toInt())
                    val jsonString = JsonConverterTool().gamerToJson(gamerContent.putGamer(newGamer))
                    res.setBody(jsonString)
                }
            }

        }
        if(req.command.split("/")[1] == "deleteGamer"){
            if(req.command.isNotEmpty()){
                val id = req.command.split("/")[2];
                val jsonString = JsonConverterTool().gamerToJson(gamerContent.deleteGamer(id.toInt()));
                res.setBody(jsonString);

            }

        }


    }

    fun start() {
        val serverSocket = ServerSocket(port)
        println("Server running...")
        while (running)
        {
            val socket = serverSocket.accept()
            thread {
                handleSocket(Request(socket.getInputStream()), Response(socket.getOutputStream()))
            }
        }
    }

    fun stop() {
        running = false;
        println("Server stopped...")
    }
}
fun main() {
    val content = GamerContent(/* filename, ... */)
    val server = WebServer(content,550)
    server.start()
    server.stop()
}
