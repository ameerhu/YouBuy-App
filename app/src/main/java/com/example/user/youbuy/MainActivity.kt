package com.example.user.youbuy

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

import android.os.StrictMode
import android.view.KeyEvent
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val KEY: Int = 11111
    private var sm: SessionManager? = null
    private var recyclerView: RecyclerView? = null
    var URL :String = "https://rocky-ocean-68053.herokuapp.com/api/Products?filter[where][status]=approved&filter[order]=postedDate DESC"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (android.os.Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

         recyclerView = findViewById(R.id.recyclerView1) as RecyclerView
//        products data from api
          GetProducts(this, URL,"").getAllProducts(recyclerView, textView1)
        println("Called api :::::::::::::")


        fab.setOnClickListener { view ->
            if(sm!!.isLogin()) {
                val it = GetProducts.getMyProduct().iterator()
                it.forEach {
                    WishUnWish(view, it.id!!, sm!!.getDetailLogin().get("userid")!!,"").execute()
                }
                Snackbar.make(view, "You are successfully CheckOut.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            }
            else
                Snackbar.make(view, "You must login first.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        imageButton.setOnClickListener { view ->
            if(!searchTerm.text.toString().isEmpty())
                GetProducts(this,URL,searchTerm.text.toString()).getAllProducts(recyclerView, textView1)
            Toast.makeText(this,"Click on search",Toast.LENGTH_LONG).show()
            hideKeyboard(view)
        }

        searchTerm.setOnKeyListener { v, keyCode, event ->
            var filter: String = ""
            if(event.action == KeyEvent.ACTION_UP)
                if(searchTerm.text.toString().isEmpty())
                    GetProducts(this, URL,"").getAllProducts(recyclerView, textView1)
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    if(!searchTerm.text.toString().isEmpty())
                        filter = "&filter[where][name][regexp]=^"+searchTerm.text.toString()+"/i"
                            GetProducts(this, URL, filter).getAllProducts(recyclerView, textView1)
                    //Toast.makeText(this,"Key Processed code: "+event.keyCode,Toast.LENGTH_LONG).show()
                    hideKeyboard(v)
                }
             false
        }

        searchTerm.setOnFocusChangeListener { v, hasFocus ->
            Log.e("Focus ", "Called")
            if(!hasFocus)
                hideKeyboard(v)
        }



        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        sm = SessionManager(this)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
//       inputMethodManager.hideSoftInputFromWindow(this.currentFocus.windowToken,0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        var setValue = if(sm!!.isLogin())
            "Logout"
        else
            "Login"
        menu.findItem(R.id.action_settings).title = setValue
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        var setValue = if(sm!!.isLogin())
            "Logout"
        else
            "Login"
        menu.findItem(R.id.action_settings).title = setValue
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.e("Item title", item.title.toString())

        when (item.title.toString()) {
//            R.id.action_settings -> return true
            "Logout" -> {
                sm!!.logout()
                if(!sm!!.isLogin())
                    Toast.makeText(this,"You are successfully Logout",Toast.LENGTH_LONG).show()
                return true
            }
            "Login" -> {
                this.startActivity(Intent(this,LoginActivity::class.java).apply { putExtra("empty",true) })
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.product_list-> {
                GetProducts(this, URL,"").getAllProducts(recyclerView, textView1)
                fab.hide()
            }
            R.id.wish_list -> {
                if(sm!!.isLogin()){
                    val wishListURL = "https://rocky-ocean-68053.herokuapp.com/api/Customers/${sm!!.getDetailLogin().get("userid")}/wishlist"
                    GetProducts(this, wishListURL,"").getAllProducts(recyclerView, textView1)
                    fab.show()
                }
                else
                    Toast.makeText(this,"You must first login",Toast.LENGTH_LONG).show()
            }
            R.id.nav_manage -> {
                if(sm!!.isLogin()){
                    val myProURL = "https://rocky-ocean-68053.herokuapp.com/api/Customers/${sm!!.getDetailLogin().get("userid")}/owns"
                    GetProducts(this, myProURL,"").getAllProducts(recyclerView, textView1)
                    fab.hide()
                }
                else
                    Toast.makeText(this,"You must first login",Toast.LENGTH_LONG).show()
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
