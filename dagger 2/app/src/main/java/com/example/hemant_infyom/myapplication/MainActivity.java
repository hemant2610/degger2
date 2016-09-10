package com.example.hemant_infyom.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hemant_infyom.myapplication.di.InjectHelper;
import com.example.hemant_infyom.myapplication.model.Users;
import com.example.hemant_infyom.myapplication.service.ApiService;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    ApiService apiService;


    public ArrayList<Users> usersesArrayList = new ArrayList<Users>();

    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectHelper.getRootComponent().inject(this);
        ButterKnife.bind(this);


        fetchData();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData();
                swipeRefreshLayout.setRefreshing(true);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    void fetchData() {

        apiService.getData().enqueue(new Callback<List<Users>>() {
            @Override
            public void onResponse(Response<List<Users>> response, Retrofit retrofit) {


                Log.e("statusCode ", "" + response.raw().toString());


                Log.e("response.body()", "" + response.code());

                int code = response.code();
                if (code == 200) {
                    swipeRefreshLayout.setRefreshing(false);


                    usersesArrayList.removeAll(usersesArrayList);
                    usersesArrayList.addAll(response.body());
                    linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                    UserAdapterClass userAdapterClass = new UserAdapterClass(MainActivity.this, usersesArrayList);
                    mRecyclerView.setAdapter(userAdapterClass);


/*

                    Collections.sort(modelPriceArrayList, new Comparator<ModelPrice>() {

                        @Override
                        public int compare(ModelPrice p1, ModelPrice p2) {
                            return (int) p2.getPrice() - p1.getPrice();
                        }
                    });

*/

/*

                    Collections.sort(usersesArrayList, new Comparator<Users>() {
                        @Override
                        public int compare(Users lhs, Users rhs) {
                            return 0;
                        }
                    });

                    Collections.sort(usersesArrayList, (l, r) -> l.getName().compareTo(r.getName()));

*/


                    for (Users users : usersesArrayList) {
                        Log.e("Name", users.getName());
                    }


                } else {
                    Toast.makeText(MainActivity.this, "Try Some this ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.e("Error", "" + t.toString());
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, UserSettingActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}












