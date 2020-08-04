package com.example.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MahasiswaAdapter.AdapterListener {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;
    protected LinearLayoutManager linearLayoutManager;
    SwipeRefreshLayout swipeRefreshLayout;
    Context context;
    private int totalItem = 0;
    private int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;

        addData();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new MahasiswaAdapter(this,mahasiswaArrayList,this);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(scrollListener);
        adapter.notifyDataSetChanged();
        adapter.setFooterVisible(true);


        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(context,"Refresh",Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (totalItem == linearLayoutManager.findLastCompletelyVisibleItemPosition()){
                addData();
            }
        }
    };

    public static void startThisActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //search
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void addData(){
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Maulana", "1414370309", "123456789"));
        mahasiswaArrayList.add(new Mahasiswa("Yonk", "1214234560", "987654321"));
        mahasiswaArrayList.add(new Mahasiswa("Nugraha", "1214230345", "987648765"));
        mahasiswaArrayList.add(new Mahasiswa("adi", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("udin", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("suin", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("siska", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("risa", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("rini", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("ucuk", "1214378098", "098758124"));
        mahasiswaArrayList.add(new Mahasiswa("ucup", "1214378098", "098758124"));
        totalItem += mahasiswaArrayList.size();
//        adapter.setFooterVisible(true);
//        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(int position, Mahasiswa item) {
        Toast.makeText(this,item.getNama(),Toast.LENGTH_LONG).show();
    }


    public void stopRefreshing() {
        swipeRefreshLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 100);
    }


    @Override
    public void onClickDelete(int position) {
        mahasiswaArrayList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyDataSetChanged();
    }
}
