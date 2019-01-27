package com.example.user.youbuy

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

class GetAllProducts {
    fun getProductItem(){
        Log.e("Start","Connecting ......")
        val connection = URL("https://rocky-ocean-68053.herokuapp.com/api/Products").openConnection() as HttpURLConnection
        connection.connect()
        println(connection.responseCode)
        println(connection.getHeaderField("Content-Type"))
        val data = connection.inputStream.use { it.reader().use { reader -> reader.readText() } }
        println(data)
        Log.e("End","Finishing ......")
    }
}