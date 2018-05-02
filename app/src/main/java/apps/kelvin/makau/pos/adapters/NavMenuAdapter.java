package apps.kelvin.makau.pos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import apps.kelvin.makau.pos.R;
import apps.kelvin.makau.pos.models.MyMenus;

public class NavMenuAdapter extends BaseAdapter {
    ArrayList<MyMenus> myMenus;
    Context ctx;
    MyMenus menu;

    public NavMenuAdapter(ArrayList<MyMenus> myMenus, Context ctx) {
        this.myMenus = myMenus;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return myMenus.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(ctx).inflate(R.layout.nav_menu_item, null);
            ImageView icon = view.findViewById(R.id.menu_icon);
            TextView  title = view.findViewById(R.id.menu_title);
            menu= myMenus.get(i);
            icon.setImageResource(menu.getMenu_icon());
            title.setText(menu.getMenu_title());

        return view;
    }
}
