package apps.kelvin.makau.pos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.interfaces.FoodListListerner;
import apps.kelvin.makau.pos.models.Food;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.MyHolder> {
ArrayList<Food> foods;
Food food;
Context ctx;
LayoutInflater inflater;
FoodListListerner foodListListerner;

    public FoodListAdapter(ArrayList<Food> foods, Context ctx,FoodListListerner listerner) {
        this.foods = foods;
        this.ctx = ctx;
        inflater=LayoutInflater.from(ctx);
        this.foodListListerner=listerner;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.food_item,null,false);
        return new MyHolder(v,foodListListerner);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
food = foods.get(position);

holder.food_pic.setImageResource(food.getImage_url());

DecimalFormat df = new DecimalFormat("0.00");
holder.price.setText(df.format(food.getPrice())+" Kshs");
holder.title.setText(food.getTitle());

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        WeakReference<FoodListListerner> listernerWeakReference;
        ImageView food_pic,available;
        TextView title,price;
        public MyHolder(View itemView,final FoodListListerner listerner) {
            super(itemView);

            listernerWeakReference=new WeakReference<FoodListListerner>(listerner);
            food_pic = itemView.findViewById(R.id.foodimageIV);
            available = itemView.findViewById(R.id.availableIV);
            title = itemView.findViewById(R.id.foodtitleTV);
            price =  itemView.findViewById(R.id.foodpriceTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Food f = foods.get(getAdapterPosition());
                    f.setNotes("gfghfhg");
                    f.setQty("65");



                    listernerWeakReference.get().onListClick(f,v);
                }
            });
        }
    }
}
