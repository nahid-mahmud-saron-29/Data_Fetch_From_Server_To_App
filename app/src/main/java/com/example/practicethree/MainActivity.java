package com.example.practicethree;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
        ListView listView ;
        MyADapter myAdapter = new MyADapter();
        HashMap<String, String> hashMapOne = new HashMap<>();
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.listView);
        dataCenter();
        listView.setAdapter(myAdapter);
    } //lastBracketFirst
    public class MyADapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.layout,parent,false);
            ImageView imageLayout = myView.findViewById(R.id.imageLayout);
            TextView nameLayout = myView.findViewById(R.id.nameLayout);
            LinearLayout linearLayout = myView.findViewById(R.id.linearLayout);

            HashMap<String, String> hashMapTwo = arrayList.get(position);
            String imageStringOne = hashMapTwo.get("imageDataCenter");
            String nameStringOne = hashMapTwo.get("nameDataCenter");

            Glide.with(MainActivity.this).load(imageStringOne).into(imageLayout);
            nameLayout.setText(nameStringOne);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myInt = new Intent(MainActivity.this, MainActivity2.class);
                    myInt.putExtra("A", imageStringOne);
                    myInt.putExtra("B", nameStringOne);
                    startActivity(myInt);
                }
            });

            return myView;
        }
    } //lastBracketMyAdapter

    public void dataCenter(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.107/placement/fetch.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i=0; i<jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String imageStringTwo = jsonObject.getString("imagePHP");
                                String nameStringTwo = jsonObject.getString("namePHP");

                                hashMapOne = new HashMap<>();
                                hashMapOne.put("imageDataCenter", imageStringTwo);
                                hashMapOne.put("nameDataCenter", nameStringTwo);
                                arrayList.add(hashMapOne);

                            }
                            myAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(stringRequest);
    } //lastBracketDataCenter
} //lastBracketSecond