package com.example.user.youbuy

import android.os.AsyncTask
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.content_product.view.*
import org.json.JSONObject

class WishUnWish(val view:View, val productID: String, val customerID:String, val btnVal:String): AsyncTask<Void, Void, Boolean>(){

    var URL = "https://rocky-ocean-68053.herokuapp.com/api/Wishlists"
    var sm: SessionManager? = SessionManager(view.context)

    override fun doInBackground(vararg params: Void?): Boolean? {

        if(btnVal.equals("UnWish") || btnVal.isEmpty())
            URL = "https://rocky-ocean-68053.herokuapp.com/api/Wishlists/unWish"
        else
            URL = "https://rocky-ocean-68053.herokuapp.com/api/Wishlists"

        var result: Boolean = false
        try {
            Log.e("Wish/UnWish ", " --------------- Xalled $URL")
            var postParams : JSONObject = JSONObject()
            postParams.put("productId",productID)
            postParams.put("customerId",customerID)
            val requestQueue = Volley.newRequestQueue(view.context)
            val objectRequest = JsonObjectRequest(
                Request.Method.POST,
                URL,
                postParams,
                Response.Listener { response ->
                    Log.e("response: " , response.toString())
                    if(btnVal.isNotEmpty())
                        if(view.button.text.toString().equals("Wish",true))
                            view.button.text = "UnWish"
                        else
                            view.button.text = "Wish"
//                    Log.e(view.button.text.toString(), "Completed"+this)
                    result = true
                },
                Response.ErrorListener { error ->
                    Log.e("Error Response ", error.toString())
                    result = false
                }
            )

            if(sm!!.isLogin())
                requestQueue.add(objectRequest)
            else
                Toast.makeText(view.context,"Your are not Login", Toast.LENGTH_LONG).show()
            Log.e("Called", "its already.")

            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            return false
        }
        return result
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
    }
}