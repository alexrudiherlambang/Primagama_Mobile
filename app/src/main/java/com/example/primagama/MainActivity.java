package com.example.primagama;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.ListViewAdapter;
import com.Product;
import com.example.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewStub stubGrid;
    private ViewStub stubList;
    private ListView listView;
    private GridView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        //inflate ViewStube before get View

        stubList.inflate();
        stubGrid.inflate();

        listView = findViewById(R.id.mylistview);
        gridView = findViewById(R.id.mygridview);

        //get list product
        getProductList();

        //get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode",MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_LISTVIEW); //default is vie listview
        //Register item click
        listView.setOnItemClickListener(onItemClick);
        gridView.setOnItemClickListener(onItemClick);
        switchView ();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_admin) {
            Toast.makeText(this,"Yang kamu klik adalah item Admin",Toast.LENGTH_SHORT);

        } else if (id == R.id.nav_siswa) {
            Toast.makeText(this,"Yang kamu klik adalah item Siswa",Toast.LENGTH_SHORT);

        } else if (id == R.id.nav_ortu) {
            Toast.makeText(this,"Yang kamu klik adalah item Orang Tua",Toast.LENGTH_SHORT);

        } else if (id == R.id.nav_tentor) {
            Toast.makeText(this,"Yang kamu klik adalah item Tentor",Toast.LENGTH_SHORT);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchView() {
        if (VIEW_MODE_GRIDVIEW == currentViewMode) {
            //hide listview
            stubList.setVisibility((View.GONE));
            //display gridview
            stubGrid.setVisibility((View.VISIBLE));
        }else {
            //display listview
            stubList.setVisibility(View.VISIBLE);
            //hide gridview
            stubGrid.setVisibility(View.GONE);
        }
        setAdapters();
    }

    private void  setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listViewAdapter = new ListViewAdapter(this,R.layout.list_item, productList);
            listView.setAdapter(listViewAdapter);
        }else {
            gridViewAdapter = new GridViewAdapter(this, R.layout.grid_item, productList);
            gridView.setAdapter(gridViewAdapter);
        }
    }

    public List<Product> getProductList(){
        //pseudocode to get product,repalce your code to get real product here
        productList=new ArrayList<>();
        productList.add(new Product(R.drawable.gambar1,"Title 1", "Ini Deskripsi ke-1"));
        productList.add(new Product(R.drawable.gambar1,"Title 2", "Ini Deskripsi ke-2"));
        productList.add(new Product(R.drawable.gambar1,"Title 3", "Ini Deskripsi ke-3"));
        productList.add(new Product(R.drawable.gambar1,"Title 4", "Ini Deskripsi ke-4"));
        productList.add(new Product(R.drawable.gambar1,"Title 5", "Ini Deskripsi ke-5"));
        productList.add(new Product(R.drawable.gambar1,"Title 6", "Ini Deskripsi ke-6"));
        productList.add(new Product(R.drawable.gambar1,"Title 7", "Ini Deskripsi ke-7"));
        productList.add(new Product(R.drawable.gambar1,"Title 8", "Ini Deskripsi ke-8"));
        productList.add(new Product(R.drawable.gambar1,"Title 9", "Ini Deskripsi ke-9"));
        productList.add(new Product(R.drawable.gambar1,"Title 10", "Ini Deskripsi ke-10"));

        return productList;
    }
    AdapterView.OnItemClickListener onItemClick = new  AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            //Do any think when user click to item
            Toast.makeText(getApplicationContext(),productList.get(position).getTitle() + "-" +productList.get(position).getDescription(),Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                if(VIEW_MODE_LISTVIEW == currentViewMode) {
                    currentViewMode = VIEW_MODE_GRIDVIEW;
                }else {
                    currentViewMode = VIEW_MODE_LISTVIEW;
                }
                //Switch view
                switchView();
                //save view mode in share reference
                SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("currentViewMode", currentViewMode);
                editor.commit();

                break;
        }
        return true;
    }
}

// @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }