package apps.kelvin.makau.pos.activities;


import android.animation.Animator;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.adapters.NavMenuAdapter;
import apps.kelvin.makau.pos.fragments.FoodCartFragment;
import apps.kelvin.makau.pos.fragments.FoodListFragment;
import apps.kelvin.makau.pos.models.CartItem;
import apps.kelvin.makau.pos.models.Food;
import apps.kelvin.makau.pos.models.MyMenus;
import apps.kelvin.makau.pos.util.CircleAnimationUtil;


public class MainActivity extends AppCompatActivity {
    public static android.support.v4.app.Fragment fragmentL = null;
    public static android.support.v4.app.Fragment fragmentR = null;
    FoodCartFragment foodCartFragment;
    ArrayList<CartItem> items;
    ArrayList<CartItem> list;
    CartItem item;
    private boolean isXLARGE = false;
    ArrayList<Food> cart;
    ImageView dest;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawer);

        FoodListFragment foodlistf = new FoodListFragment();
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();

        dest = findViewById(R.id.cartRL);
        transaction.add(R.id.parent_left, foodlistf);

        fragmentL = new FoodListFragment();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.parent_left, fragmentL, "fragmentLEFT").commit();


        if (findViewById(R.id.parent_right) != null) {


            fragmentR = new FoodCartFragment();
            // android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.parent_right, fragmentR, "FRAGMENTCART").commit();

            dest.setVisibility(View.GONE);
            isXLARGE = true;
        } else {
            isXLARGE = false;
        }
        //transaction.commit();




        dest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cart.size() > 0) {
                    fragmentR = new FoodCartFragment();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", list);
                    fragmentR.setArguments(bundle);
                    setFragment(fragmentR);
                }
            }
        });

        ActionBar actionBar= getSupportActionBar();


        cart= new ArrayList<>();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        }



        if(drawerToggle==null){
            drawerToggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
            /*drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {

                }

                @Override
                public void onDrawerOpened(View drawerView) {

                }

                @Override
                public void onDrawerClosed(View drawerView) {

                }

                @Override
                public void onDrawerStateChanged(int newState) {

                }
            });*/
        }
        drawerToggle.syncState();
        setupNavMenu();


        if(savedInstanceState!=null){
            return;

        }








    }

    public void addToCart(Food food) {
//        if (isXLARGE) {
//            FoodCartFragment foodCartFragment = (FoodCartFragment) getSupportFragmentManager().findFragmentById(R.id.parent_right);
//            foodCartFragment.addFood(food);
//
//        } else {
//            makeAnimation(food, dest);
//            addFood(food);
//            //TODO DO YOUR NORMAL PHONE CART METHODS HERE IE ADD TO THE CART ICON
//        }
        addFood(food);

    }

    public void addFood(Food food) {
        if (!isXLARGE) {
            makeAnimation(food, dest);
        }


        if (isNotinList(food)) {
            CartItem cartItem = new CartItem();
            cartItem.setQty("1");
            cartItem.setDiscount(food.getDiscount());
            cartItem.setTitle(food.getTitle());
            cartItem.setId(food.getId());
            cartItem.setPrice(food.getPrice() * 1);

            if (list != null) {
                list.add(cartItem);
            } else {
                list = new ArrayList<>();
                list.add(cartItem);
            }

            refreshCart(list);


        } else {
            updateQty(food);

            refreshCart(list);
        }

    }

    private void refreshCart(ArrayList<CartItem> list) {
//        if(isXLARGE) {
//            FoodCartFragment foodCartFragment = (FoodCartFragment)getSupportFragmentManager().findFragmentById(R.id.parent_right);
//            foodCartFragment.refresh(list);
//        }
        //else {
        Log.d("remove", " refresh list size " + list.size());
        FoodCartFragment foodCartFragment = (FoodCartFragment) getSupportFragmentManager().findFragmentByTag("FRAGMENTCART");
        if (foodCartFragment != null) {
            foodCartFragment.refresh(list);
        }
        //}
    }

    private void refresh(ArrayList<CartItem> list) {
        FoodCartFragment foodCartFragment = (FoodCartFragment) getSupportFragmentManager().findFragmentById(R.id.parent_left);
        foodCartFragment.refresh(list);

    }

    private void updateQty(Food food) {
        if (list != null && list.size() > 0) {
            for (int a = 0; a < list.size(); a++) {
                if (list.get(a).getId() == food.getId()) {
                    int qty = Integer.valueOf(list.get(a).getQty());
                    qty++;
                    list.get(a).setQty(String.valueOf(qty));
                    refreshCart(list);
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

    public void removeFood(int id) {
        Log.d("remove", " AT Main " + id);

        if (list != null && list.size() > 0) {
            for (int a = 0; a < list.size(); a++) {
                if (list.get(a).getId() == id) {
                    Log.d("remove", " removing " + a);

                    list.remove(a);
                }

            }
            refreshCart(list);
        }
    }

    public void removeFromCart(int position) {

    }
    private void setupNavMenu() {
        ListView menu = findViewById(R.id.nav_menu);
        ArrayList<MyMenus> menus = setMenus();
        menu.setAdapter(new NavMenuAdapter(menus,MainActivity.this));

        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                drawerLayout.closeDrawer(Gravity.START);

            }
        });

    }

    private ArrayList<MyMenus> setMenus() {
        ArrayList<MyMenus> menus = new ArrayList<>();
        MyMenus menus1 = new MyMenus(R.drawable.ic_sales,"Sales");
        menus.add(menus1);
        MyMenus menus2 = new MyMenus(R.drawable.ic_receipts,"Receipts");
        menus.add(menus2);

        MyMenus menus3 = new MyMenus(R.drawable.ic_items,"Items");
        menus.add(menus3);

        MyMenus menus4 = new MyMenus(R.drawable.ic_reports,"Reports");
        menus.add(menus4);

        return menus;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //  getMenuInflater().inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch (item.getItemId()) {
            case R.id.cart:

                break;
            default:
        }
        return super.onOptionsItemSelected(item);

    }

//    @Override
//    public void onListClick(Food f, View v) {
//         makeAnimation(f,v);
//
//
//    }

    private void makeAnimation(final Food f, View v) {


        new CircleAnimationUtil().attachActivity(this).setTargetView(v).setMoveDuration(1000).setDestView(dest).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                cart.add(f);

                TextView cart_count= findViewById(R.id.cart_count);
                cart_count.setText(String.valueOf(cart.size()));
                Toast.makeText(MainActivity.this,"Added to cart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        }).startAnimation();


    }

    void setFragment(Fragment fragment) {
        // fragment = new FragmentSearch();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.parent_left, fragment, "FRAGMENTCART").commit();
    }

    void popOutFragments() {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }
}
