package com.example.user.youbuy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.JsonArrayRequest;
import com.google.gson.*;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

public class GetProducts extends AppCompatActivity {
    public Model.Product myProduct[];
//    String URL = "http://localhost:3000/api/Products";
//    String URL = "https://rocky-ocean-68053.herokuapp.com/api/Products";
    String URL = "https://rocky-ocean-68053.herokuapp.com/api/Products?filter[where][status]=approved&filter[order]=postedDate DESC";
    RecyclerView recyclerView;
    String filter = null;
Context context;

    protected GetProducts (Context context, String filter) {
        this.context = context;
        this.filter = filter;
        Log.e("Api "," --------------- onCreate");
        if(filter != "")
            URL = URL + "&filter[where][name][regexp]=^"+filter+"/i";
        System.out.println("Filtered URL: " +URL);
//        recyclerView = findViewById(R.id.recyclerView1);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayout.VERTICAL,false));
    }

    protected Model.Product[] getAllProducts(final RecyclerView recyclerView){
        Log.e("Api "," --------------- Xalled");
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest objectRequest = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new GsonBuilder().create();
                        myProduct = gson.fromJson(response.toString(),Model.Product[].class);
                        Log.e("--  Product Detail : ", myProduct[0].toString());
                        ArrayList<Model.Product> productList = new ArrayList<Model.Product>(Arrays.asList(myProduct));
//                        recyclerView = findViewById(R.id.recyclerView1);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayout.VERTICAL,false));
                        recyclerView.setAdapter(new ProductAdapter(productList));
                        }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error Response ",error.toString());
                    }
                }
        );

        requestQueue.add(objectRequest);
        Log.e("Called", "its already executed.");
        return myProduct;
    }
}
