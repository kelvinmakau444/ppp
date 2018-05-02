package apps.kelvin.makau.pos.models;

public class MyMenus {
    int menu_icon;
    String menu_title;

    public MyMenus() {
    }

    public MyMenus(int menu_icon, String menu_title) {
        this.menu_icon = menu_icon;
        this.menu_title = menu_title;
    }

    public int getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(int menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }
}
