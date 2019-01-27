package com.example.user.youbuy

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.user.youbuy.R.id.imageView
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.InputStream
import java.net.URL


class ProductAdapter(val productList: ArrayList<Model.Product>): RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.content_product,p0,false)
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
        p0.pDescription.text = "Description: \n\t" + product?.description
Log.e("URL : -------- ", product.images?.get(0))
        val url = URL(product.images?.get(0));
//        val url = URL("https://assets.exercism.io/tracks/java-bordered-turquoise.png")
//        p0.pImage.setImageBitmap(url.content as Bitmap)
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        p0.pImage.setImageBitmap(bmp)

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username = itemView.findViewById(R.id.username) as TextView
        val postedDate = itemView.findViewById(R.id.postedDate) as TextView
        val pName = itemView.findViewById(R.id.pName) as TextView
        val pDescription = itemView.findViewById(R.id.pDescription) as TextView
        val pImage = itemView.findViewById(R.id.pImage) as ImageView
        val button = itemView.findViewById(R.id.button) as Button
        val button2 = itemView.findViewById(R.id.button2) as Button
        val button3 = itemView.findViewById(R.id.button3) as Button
    }
}