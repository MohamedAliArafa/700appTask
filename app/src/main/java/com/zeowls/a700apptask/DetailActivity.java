package com.zeowls.a700apptask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.zeowls.a700apptask.Adapters.NestedAdapter;
import com.zeowls.a700apptask.DataModel.Product;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent in = getIntent();
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(in.getStringExtra("Title"));
        List<Product> products = in.getParcelableArrayListExtra("products");
        RecyclerView mProductsRecycler = (RecyclerView) findViewById(R.id.products_recycle);
        NestedAdapter mProductsAdapter = new NestedAdapter(DetailActivity.this, products);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(DetailActivity.this, 2, GridLayoutManager.VERTICAL, false);
        mProductsRecycler.setLayoutManager(mGridLayoutManager);
        mProductsRecycler.setHasFixedSize(true);
        mProductsRecycler.setFocusable(false);
        mProductsRecycler.setAdapter(mProductsAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
