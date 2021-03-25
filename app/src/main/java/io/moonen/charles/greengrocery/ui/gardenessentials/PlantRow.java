package io.moonen.charles.greengrocery.ui.gardenessentials;

import android.os.Parcel;
import android.os.Parcelable;

import io.moonen.charles.greengrocery.R;

public class PlantRow implements Parcelable {
    private String title;
    private int price;
    private int image;
    private String type;
    private boolean purchased;
    private int water;

    public PlantRow(String title, int price, int image, String type) {
        this.title = title;
        this.price = price;
        this.image = image;
        this.type = type;
        this.purchased = false;
        this.water = 0;
    }

    protected PlantRow(Parcel in) {
        title = in.readString();
        price = in.readInt();
        image = in.readInt();
        type = in.readString();
        purchased = in.readByte() != 0;
        water = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeInt(price);
        dest.writeInt(image);
        dest.writeString(type);
        dest.writeByte((byte) (purchased ? 1 : 0));
        dest.writeInt(water);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlantRow> CREATOR = new Creator<PlantRow>() {
        @Override
        public PlantRow createFromParcel(Parcel in) {
            return new PlantRow(in);
        }

        @Override
        public PlantRow[] newArray(int size) {
            return new PlantRow[size];
        }
    };

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return this.price;
    }

    public int getImage() {
        return this.image;
    }

    public String getType() {
        return this.type;
    }

    public boolean isPurchased() { return purchased; }

    public boolean fullyGrown() {
        if (water < 3) return false;
        else return true;
    }

    public void waterPlant() {
        water++;
    }

    public void purchase() {
        this.purchased = true;
        this.type = "Water";
        this.image = R.drawable.wateringcan;
    }

}
