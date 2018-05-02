package apps.kelvin.makau.pos.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.adapters.FoodListAdapter;
import apps.kelvin.makau.pos.interfaces.ClickListener;
import apps.kelvin.makau.pos.interfaces.FoodListListerner;
import apps.kelvin.makau.pos.models.Food;

public class FoodListFragment extends Fragment {
    RecyclerView foodrv;
    FoodCartFragment foodCartFragment;
    public FoodListFragment() {
        super();
    }

FoodListListerner listener;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       return inflater.inflate(R.layout.foodlist_fragment,container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
               foodrv=view.findViewById(R.id.foodlistRV);
               foodrv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
               foodrv.setAdapter(new FoodListAdapter(getFoodList(), getActivity(), new FoodListListerner() {
                   @Override
                   public void onListClick(Food food) {
                       if(listener!=null)
                        listener.onListClick(food);
                   }
               }));

        


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FoodListListerner){
            listener = (FoodListListerner) context;
        }
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof FoodListListerner){
            listener = (FoodListListerner) activity;
        }
    }*/
    //todo:fetch from some db/server
    private ArrayList<Food> getFoodList(){
        ArrayList<Food> list = new ArrayList<>(10);
        Food one = new Food();
        one.setAvailable(true);
        one.setImage_url(R.drawable.ic_launcher);
        one.setPrice(100);
        one.setDiscount("0");
        one.setTitle("Chapo mix");
        list.add(one);

        Food two = new Food();
        two.setAvailable(true);
        two.setImage_url(R.drawable.food);
        two.setPrice(100);
        one.setDiscount("0");
        two.setTitle("Chapo mix");
        list.add(two);


        Food three = new Food();
        three.setAvailable(true);
        three.setImage_url(R.drawable.food);
        three.setPrice(100);
        one.setDiscount("0");
        three.setTitle("Chapo mix");
        list.add(three);

        Food four = new Food();
        four.setAvailable(true);
        four.setImage_url(R.drawable.food);
        four.setPrice(100);
        one.setDiscount("0");
        four.setTitle("Chapo mix");
        list.add(four);

        Food five = new Food();
        five.setAvailable(true);
        five.setImage_url(R.drawable.food);
        five.setPrice(100);
        one.setDiscount("0");
        five.setTitle("Chapo mix");
        list.add(five);

        Food six = new Food();
        six.setAvailable(true);
        six.setImage_url(R.drawable.food);
        six.setPrice(1000);
        one.setDiscount("0");
        six.setTitle("Chapo mix");
        list.add(six);

        Food seven = new Food();
        seven.setAvailable(true);
        seven.setImage_url(R.drawable.food);
        seven.setPrice(100);
        one.setDiscount("0");
        seven.setTitle("Chapo mix");
        list.add(seven);

        Food eight = new Food();
        eight.setAvailable(true);
        eight.setImage_url(R.drawable.food);
        eight.setPrice(100);
        one.setDiscount("0");
        eight.setTitle("Chapo mix");
        list.add(eight);

        Food nine = new Food();
        nine.setAvailable(true);
        nine.setImage_url(R.drawable.food);
        nine.setPrice(100);
        one.setDiscount("0");
        nine.setTitle("Chapo mix");
        list.add(nine);

        Food ten = new Food();
        ten.setAvailable(true);
        ten.setImage_url(R.drawable.food);
        ten.setPrice(100);
        one.setDiscount("0");
        ten.setTitle("Chapo mix");
        list.add(ten);

       return list;
    }
}
