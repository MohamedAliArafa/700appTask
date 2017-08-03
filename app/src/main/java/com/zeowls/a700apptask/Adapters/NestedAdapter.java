package com.zeowls.a700apptask.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zeowls.a700apptask.DataModel.Product;
import com.zeowls.a700apptask.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/*
 * Created by root on 8/3/17.
 */

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> data;


    public NestedAdapter(Context context, List<Product> data) {
        this.mContext = context;
        this.data = data;
    }


    @Override
    public NestedAdapter.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list_item, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(NestedAdapter.ViewHolder holder, int position) {
        Product obj = data.get(position);
        holder.nestedItemName.setText(obj.getName());
        holder.nestedPrice.setText(holder.format.format(obj.getPrice()));
        Glide.with(mContext).load(obj.getPicture()).fitCenter().into(holder.nestedImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView nestedImage, fav;
        Button buyBtn;
        TextView nestedItemName;
        TextView nestedPrice;
        LinearLayout nestedLinearClickReceiver;
        NumberFormat format;
        DecimalFormatSymbols decimalFormatSymbols;

        ViewHolder(View view) {
            super(view);
            nestedImage = view.findViewById(R.id.product_item_image);
            nestedItemName = view.findViewById(R.id.product_item_title);
            nestedPrice = view.findViewById(R.id.product_item_price);
            nestedLinearClickReceiver = view.findViewById(R.id.nested_click_receiver);
            fav = view.findViewById(R.id.product_fav_icon);
            buyBtn = view.findViewById(R.id.buy_button);
            fav.setOnClickListener(this);
            buyBtn.setOnClickListener(this);
            nestedLinearClickReceiver.setOnClickListener(this);
            format = NumberFormat.getCurrencyInstance(Locale.getDefault());
            decimalFormatSymbols = ((DecimalFormat) format).getDecimalFormatSymbols();
            decimalFormatSymbols.setCurrencySymbol("EGP ");
            ((DecimalFormat) format).setDecimalFormatSymbols(decimalFormatSymbols);
        }


        @Override
        public void onClick(View view) {
            //ToDo Add OnClick for Item Details
        }
    }
}