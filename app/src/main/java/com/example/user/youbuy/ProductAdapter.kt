package com.example.user.youbuy

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.graphics.BitmapFactory
import android.widget.Toast
import java.net.URL
import java.io.Serializable


class ProductAdapter(val productList: ArrayList<Model.Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    var sm: SessionManager? = null
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.content_product,p0,false)
        sm = SessionManager(p0?.context)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val product : Model.Product = productList[p1]
        Log.e("$p1", product.toString())

        p0.username.text = product.owner?.username
        p0.postedDate.text = product?.postedDate
        p0.pName.text = product?.name + "  |  " + product?.price + " €"
        val url = URL(product.images?.get(0))
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        p0.pImage.setImageBitmap(bmp)
        p0.pImage.setOnClickListener { view ->
            Log.e("Image Click Detail: ", product.toString())
            var intent: Intent
            if(sm!!.isLogin())
                intent = Intent(view.context,ProductDetail::class.java).apply {putExtra("product",product as Serializable)}
            else
            intent = Intent(view.context,LoginActivity::class.java).apply {putExtra("product",product as Serializable)}
            view.context.startActivity(intent)
        }

        if(sm!!.isLogin()){
            Log.e("Data: ", sm!!.showDetail())
            if(product.customerToWish!!.isNotEmpty()){
                val it = product.customerToWish?.iterator()
                it?.forEach {
                    if(it.id==sm!!.getDetailLogin().get("userid")){
                    Log.e("cust id & Wish id", it.id + " & " + sm!!.getDetailLogin().get("userid") )
                        p0.button.text = "UnWish"
                        //return
                    }
                }
            }
        }

        Log.e("Listener add: ", p0.button.text.toString())
        p0.button.setOnClickListener { view->
            if(sm!!.isLogin())
                WishUnWish(view, product.id!!, sm!!.getDetailLogin().get("userid")!!,p0.button.text.toString()).execute()
            else
                Toast.makeText(view.context,"Your are not Login", Toast.LENGTH_LONG).show()
        }

        fun getProduct(): ArrayList<Model.Product>{
            return productList
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username = itemView.findViewById(R.id.username) as TextView
        val postedDate = itemView.findViewById(R.id.postedDate) as TextView
        val pName = itemView.findViewById(R.id.pName) as TextView
        val pImage = itemView.findViewById(R.id.pImage) as ImageView
        var button = itemView.findViewById(R.id.button) as Button
    }
}