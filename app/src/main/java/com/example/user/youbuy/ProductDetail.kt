package com.example.user.youbuy

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_product_detail.*
import java.net.URL

class ProductDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val product = intent.extras.get("product") as Model.Product
        username.text = username.text.toString() + " " + product.owner?.username
        postedDate.text = postedDate.text.toString() + " " + product?.postedDate
        pName.text = pName.text.toString() + " " + product?.name
        pPrice.text = pPrice.text.toString() + " " + product?.price
        pLocation.text = pPrice.text.toString() + " " + product?.location
        pDescription.text = pDescription.text.toString() + " " + product?.description
        val url = URL(product.images?.get(0))
        val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        pImage.setImageBitmap(bmp)

    }
}
