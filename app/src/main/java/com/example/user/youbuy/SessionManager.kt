package com.example.user.youbuy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import java.io.Serializable

class SessionManager(val context: Context){
//    private var sp: SharedPreferences? = null
//    private var editor: SharedPreferences.Editor? = null
    private val KEY_USERNAME: String? = "username"
    private val KEY_PASSWORD: String? = "pass"
    private val is_Login: String? = "loginstatus"
    private val SHARE_NAME: String? = "loginsession"
    var sp = context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE)
    var editor = sp.edit()
    var product: Model.Product = Model.Product()

    fun storeLogin(userId:String?, name:String?){
        editor.putBoolean(is_Login,true)
        editor.putString(KEY_USERNAME, userId)
        editor.putString(KEY_PASSWORD, name)
        editor.commit()
    }

//    fun checkLogin(prodcut: Model.Product){
//        if(!this.login()){
//            val login = Intent(context,ProductDetail::class.java).apply {putExtra("product",product as Serializable)}
//            context.startActivity(login)
//        }
//    }

    fun isLogin(): Boolean{
        return sp.getBoolean(is_Login,false)
    }

    fun getDetailLogin(): HashMap<String, String> {
        var map: HashMap<String,String> = HashMap()
        map.put(KEY_USERNAME!!,sp.getString(KEY_USERNAME,null))
        map.put(KEY_PASSWORD!!,sp.getString(KEY_PASSWORD,null))
        return map
    }

    fun logout(){
        val sharedpreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedpreferences.edit()
        editor.clear()
        editor.commit()
    }

}