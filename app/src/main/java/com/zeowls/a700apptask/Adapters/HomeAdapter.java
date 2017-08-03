package com.zeowls.a700apptask.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.a700apptask.DataModel.Category;
import com.zeowls.a700apptask.DataModel.Product;
import com.zeowls.a700apptask.DetailActivity;
import com.zeowls.a700apptask.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by root on 8/2/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Category> data;

    public HomeAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    public List<Category> getData() {
        return data;
    }

    public void setData(List<Category> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return getViewHolder(parent, inflater);
    }

    @NonNull
    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        View v1 = inflater.inflate(R.layout.category_list_item, parent, false);
        return new ViewHolder(v1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder VH = (ViewHolder) holder;
        Category obj = data.get(position);
        VH.data = obj;
        VH.cateTitle.setText(obj.getName());
        VH.mNestedRecyclerView.setHasFixedSize(true);
        VH.mNestedRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollHorizontally() {
                return true;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        VH.mNestedRecyclerView.setNestedScrollingEnabled(false);
        NestedAdapter adapter = new NestedAdapter(context, obj.getProducts());
        VH.mNestedRecyclerView.setAdapter(adapter);
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    /*Helpers */

    public void add(Category r) {
        data.add(r);
        notifyItemInserted(data.size() - 1);
    }

    public void addAll(List<Category> moveResults) {
        for (Category result : moveResults) {
            add(result);
        }
    }

    private void remove(Category r) {
        int position = data.indexOf(r);
        if (position > -1) {
            data.remove(position);
            notifyDataSetChanged();
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void addLoadingFooter() {
        add(new Category());
    }

    public void removeLoadingFooter() {
        int position = data.size() - 1;
        Category result = getItem(position);

        if (result != null) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

    private Category getItem(int position) {
        return data.get(position);
    }

   /*  View Holders    */

    /**
     * Main list's content ViewHolder
     */
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerView mNestedRecyclerView;
        TextView cateTitle;
        TextView mMoreTextView;
        public Category data;

        ViewHolder(View itemView) {
            super(itemView);
            cateTitle = itemView.findViewById(R.id.header_text);
            mMoreTextView = itemView.findViewById(R.id.more_text);
            mNestedRecyclerView = itemView.findViewById(R.id.products_recycle);
            mMoreTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.more_text) {
                ArrayList<Product> products = new ArrayList<>();
                for (Product p : data.getProducts()){
                    products.add(p);
                }
                context.startActivity(new Intent(context, DetailActivity.class)
                        .putParcelableArrayListExtra("products", products)
                        .putExtra("Title", data.getName()));
            }
        }
    }
}
