package com.example.user.youbuy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import java.io.Serializable

class SessionManager(val context: Context){
//    private var sp: SharedPreferences? = null
//    private var editor: SharedPreferences.Editor? = null
    private val KEY_USER_ID: String? = "userid"
    private val KEY_NAME: String? = "pass"
    private val is_Login: String? = "loginstatus"
    private val SHARE_NAME: String? = "loginsession"
    var sp = context.getSharedPreferences(SHARE_NAME,Context.MODE_PRIVATE)
    var editor = sp.edit()
    var product: Model.Product = Model.Product()

    fun storeLogin(userId:String?, name:String?){
        editor.putBoolean(is_Login,true)
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_NAME, name)
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
        map.put(KEY_USER_ID!!,sp.getString(KEY_USER_ID,null))
        map.put(KEY_NAME!!,sp.getString(KEY_NAME,null))
        return map
    }

    fun showDetail(): String{
        return " ${sp.getBoolean(is_Login, false)} ${sp.getString(KEY_USER_ID, null)} ${sp.getString(KEY_NAME, null)}"
    }

    fun logout(){
        //val sharedpreferences = context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE)
        //val editor: SharedPreferences.Editor = sharedpreferences.edit()
        editor.clear()
        editor.commit()
    }

}