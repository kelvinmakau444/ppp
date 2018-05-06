package apps.kelvin.makau.pos.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.adapters.FoodCartListAdapter;
import apps.kelvin.makau.pos.interfaces.CartClickListener;
import apps.kelvin.makau.pos.models.CartItem;
import apps.kelvin.makau.pos.models.Food;

public class FoodCartFragment extends Fragment {
    RecyclerView foodcartrv;
    ArrayList<CartItem> list;
    FoodCartListAdapter foodListAdapter;
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
        list = new ArrayList<>();


        foodcartrv = view.findViewById(R.id.cartListRV);
        foodcartrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodListAdapter = new FoodCartListAdapter(list, getActivity(), new CartClickListener() {
            @Override
            public void numberPickerClicked(int pos, int value) {
                if (list != null) {
                    list.get(pos).setQty(String.valueOf(value));
                    foodListAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onRemove(int pos) {

                removeFood(pos);
            }
        });
        foodcartrv.setAdapter(foodListAdapter);


    }


    public void addFood(Food food) {
        if (isNotinList(food)) {
            CartItem cartItem = new CartItem();
            cartItem.setQty("1");
            cartItem.setTitle(food.getTitle());
            cartItem.setId(food.getId());
            cartItem.setPrice(food.getPrice() * 1);

            if (list != null) {
                list.add(cartItem);
            } else {
                list = new ArrayList<>();
                list.add(cartItem);
            }
            if (foodListAdapter != null) {
                foodListAdapter.notifyDataSetChanged();
            }


        } else {
            updateQty(food);
        }

    }

    private void updateQty(Food food) {
        if (list != null && list.size() > 0) {
            for (int a = 0; a < list.size(); a++) {
                if (list.get(a).getId() == food.getId()) {
                    int qty = Integer.valueOf(list.get(a).getQty());
                    qty++;
                    list.get(a).setQty(String.valueOf(qty));
                    foodListAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private boolean isNotinList(Food food) {
        if (list != null) {
            for (CartItem cartItem : list) {
                if (cartItem.getId() == food.getId()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void removeFood(int pos) {
        if (list != null && list.size() > 0) {
            list.remove(pos);
            foodListAdapter.notifyDataSetChanged();
        }
    }
}
