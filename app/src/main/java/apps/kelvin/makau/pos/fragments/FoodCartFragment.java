package apps.kelvin.makau.pos.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.activities.MainActivity;
import apps.kelvin.makau.pos.adapters.FoodCartListAdapter;
import apps.kelvin.makau.pos.interfaces.CartClickListener;
import apps.kelvin.makau.pos.models.CartItem;

public class FoodCartFragment extends Fragment {
    RecyclerView foodcartrv;
    ArrayList<CartItem> list;
    FoodCartListAdapter foodListAdapter;
    private TextView cartTotal, cartSubTotal, cartDiscount;
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
        if (getArguments() != null && !getArguments().getBoolean("isXLARGE", false)) {

            list = (ArrayList<CartItem>) getArguments().getSerializable("data");
        }
        cartSubTotal = view.findViewById(R.id._cartSubTotal);
        cartTotal = view.findViewById(R.id._cartTotal);
        cartDiscount = view.findViewById(R.id._cartDiscount);


        foodcartrv = view.findViewById(R.id.cartListRV);
        foodcartrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        foodListAdapter = new FoodCartListAdapter(list, getActivity(), new CartClickListener() {
            @Override
            public void numberPickerClicked(int pos, int value) {
                if (list != null) {

                    if (value > 0) {
                        list.get(pos).setQty(String.valueOf(value));
                    }
                    foodListAdapter.notifyDataSetChanged();
                    refreshTotal();
                }
            }

            @Override
            public void onRemove(int pos) {


                Log.d("remove", " AT POST " + pos);
                removeFood(list.get(pos).getId());
                // foodListAdapter.notifyDataSetChanged();
                // refreshTotal();
            }
        });
        foodcartrv.setAdapter(foodListAdapter);
        refreshTotal();


    }

    public void refresh(ArrayList<CartItem> cartItems) {
        Log.d("remove", " refresh after remove " + cartItems.size());
        if (list != null) {
            list.clear();
            list.addAll(cartItems);
        } else {
            list = new ArrayList<>();
            list.addAll(cartItems);
        }
        foodListAdapter.notifyDataSetChanged();
        refreshTotal();
    }

    private void refreshTotal() {
        double subTotal = 0.0;
        double discount = 0.0;
        if (list != null && list.size() > 0) {
            for (CartItem cartItem : list) {
                int qty = Integer.valueOf(cartItem.getQty());
                subTotal += (qty * cartItem.getPrice());
                discount += (qty * (cartItem.getDiscount()));
            }

        }
        cartSubTotal.setText(String.valueOf(subTotal) + " Ksh");
        cartTotal.setText(String.valueOf(subTotal - discount) + " Ksh");
        cartDiscount.setText(String.valueOf(discount) + " Ksh");
    }


//    public void addFood(Food food) {
//        if (isNotinList(food)) {
//            CartItem cartItem = new CartItem();
//            cartItem.setQty("1");
//            cartItem.setDiscount(food.getDiscount());
//            cartItem.setTitle(food.getTitle());
//            cartItem.setId(food.getId());
//            cartItem.setPrice(food.getPrice() * 1);
//
//            if (list != null) {
//                list.add(cartItem);
//            } else {
//                list = new ArrayList<>();
//                list.add(cartItem);
//            }
//            if (foodListAdapter != null) {
//                foodListAdapter.notifyDataSetChanged();
//            }
//
//
//            refreshTotal();
//        } else {
//            updateQty(food);
//            refreshTotal();
//        }
//
//    }
//
//    private void updateQty(Food food) {
//        if (list != null && list.size() > 0) {
//            for (int a = 0; a < list.size(); a++) {
//                if (list.get(a).getId() == food.getId()) {
//                    int qty = Integer.valueOf(list.get(a).getQty());
//                    qty++;
//                    list.get(a).setQty(String.valueOf(qty));
//                    foodListAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }
//
//    private boolean isNotinList(Food food) {
//        if (list != null) {
//            for (CartItem cartItem : list) {
//                if (cartItem.getId() == food.getId()) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    private void removeFood(int id) {
        Log.d("remove", " AT ID " + id);

        ((MainActivity) Objects.requireNonNull(getActivity())).removeFood(id);
    }
}
