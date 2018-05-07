package apps.kelvin.makau.pos.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Food implements Parcelable {
    private int id;
    private String title;
    private double price;
    private boolean available;
    private double discount;
    //todo :change to string img_url
    private int image_url;
    private String notes;
    private String qty;

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }



    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    protected Food(Parcel in) {
        this.title = in.readString();
        this.price = in.readDouble();
        this.available = in.readByte() != 0;
        this.discount = in.readDouble();
        this.image_url = in.readInt();
        this.notes = in.readString();
        this.qty = in.readString();
    }

    public double getDiscount() {
        return discount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getImage_url() {
        return image_url;
    }

    public void setImage_url(int image_url) {
        this.image_url = image_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeDouble(this.price);
        dest.writeByte(this.available ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.discount);
        dest.writeInt(this.image_url);
        dest.writeString(this.notes);
        dest.writeString(this.qty);
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel source) {
            return new Food(source);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };
}
