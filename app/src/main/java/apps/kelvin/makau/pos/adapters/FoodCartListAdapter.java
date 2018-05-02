package apps.kelvin.makau.pos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.models.CartItem;
import apps.kelvin.makau.pos.models.Food;

public class FoodCartListAdapter extends RecyclerView.Adapter<FoodCartListAdapter.MyHolder> {
ArrayList<CartItem> items;
CartItem item;
Context ctx;
LayoutInflater inflater;

    public FoodCartListAdapter(ArrayList<CartItem> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
        inflater=LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.foodcart_item,null,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = items.get(position);


DecimalFormat df = new DecimalFormat("0.00");
holder.price.setText(df.format(item.getPrice())+" Kshs");
        holder.title.setText(item.getTitle());
        holder.notes.setText(item.getNotes());
        holder.qty.setText(item.getQty());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        TextView title,price,notes,qty;
        public MyHolder(View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id._cartItemTitle);
            price =  itemView.findViewById(R.id._cartItemTotal);
            notes =  itemView.findViewById(R.id._cartItemNotes);
            qty =  itemView.findViewById(R.id._cartItemQty);
        }
    }
}
