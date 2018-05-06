package apps.kelvin.makau.pos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.Interface.ValueChangedListener;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.interfaces.CartClickListener;
import apps.kelvin.makau.pos.models.CartItem;

public class FoodCartListAdapter extends RecyclerView.Adapter<FoodCartListAdapter.MyHolder> {
ArrayList<CartItem> items;
CartItem item;
Context ctx;
LayoutInflater inflater;
    CartClickListener cartClickListener;

    public FoodCartListAdapter(ArrayList<CartItem> items, Context ctx, CartClickListener cartClickListener) {
        this.items = items;
        this.ctx = ctx;
        this.cartClickListener = cartClickListener;
        inflater=LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.foodcart_item,null,false);
        return new MyHolder(v, cartClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        item = items.get(position);


DecimalFormat df = new DecimalFormat("0.00");
holder.price.setText(df.format(item.getPrice())+" Kshs");
        holder.title.setText(item.getTitle());
        holder.notes.setText("Notes" + item.getNotes());
        holder.qty.setValue(Integer.valueOf(item.getQty()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        WeakReference<CartClickListener> listernerWeakReference;

        ImageView imgDelete;
        TextView title, price, notes;
        com.travijuu.numberpicker.library.NumberPicker qty;

        public MyHolder(View itemView, final CartClickListener cartClickListener) {
            super(itemView);

            listernerWeakReference = new WeakReference<CartClickListener>(cartClickListener);

            imgDelete = itemView.findViewById(R.id.img_delete);
            title = itemView.findViewById(R.id._cartItemTitle);
            price =  itemView.findViewById(R.id._cartItemTotal);
            notes =  itemView.findViewById(R.id._cartItemNotes);
            qty =  itemView.findViewById(R.id._cartItemQty);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listernerWeakReference.get().onRemove(getAdapterPosition());
                }
            });

            qty.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            qty.setValueChangedListener(new ValueChangedListener() {
                @Override
                public void valueChanged(int value, ActionEnum action) {
                    Log.d("numbv", value + "  Chna");
                    listernerWeakReference.get().numberPickerClicked(getAdapterPosition(), value);
                }
            });
        }
    }
}
