package apps.kelvin.makau.pos.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.adapters.FoodCartListAdapter;
import apps.kelvin.makau.pos.adapters.FoodListAdapter;
import apps.kelvin.makau.pos.models.CartItem;
import apps.kelvin.makau.pos.models.Food;

public class FoodCartFragment extends Fragment {
    RecyclerView foodcartrv;
    public FoodCartFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
       View v =  inflater.inflate(R.layout.foodcartlist_fragment,null);
        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<CartItem> list = new ArrayList<>();

        Bundle bundle = getArguments();

        if(bundle.containsKey("carts")) {
            ArrayList<Food> foods = bundle.getParcelable("carts");

            for(Food food:foods) {
                CartItem item = new CartItem();
                item.setTitle(food.getTitle());
                item.setPrice(food.getPrice());
                item.setNotes(food.getNotes());
                item.setQty(food.getQty());

                list.add(item);
            }
        }
            foodcartrv=view.findViewById(R.id.cartListRV);
        foodcartrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodcartrv.setAdapter(new FoodCartListAdapter(list,getActivity()));




    }

    //todo:fetch from some db/server
    private ArrayList<CartItem> getFoodCartList(){
        ArrayList<CartItem> list = new ArrayList<>(10);



       return list;
    }
}
