package fi.solita.helloretrofit.models.items;

/**
 * Created by eetupa on 24/08/16.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

import fi.solita.helloretrofit.models.items.Item;

@Generated("org.jsonschema2pojo")
public class ItemHolder implements Parcelable {

    private String status;
    private List<Item> data = new ArrayList<Item>();
    private String message;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return The data
     */
    public List<Item> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Item> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ItemHolder() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeTypedList(this.data);
        dest.writeString(this.message);
    }

    protected ItemHolder(Parcel in) {
        this.status = in.readString();
        this.data = in.createTypedArrayList(Item.CREATOR);
        this.message = in.readString();
    }

    public static final Creator<ItemHolder> CREATOR = new Creator<ItemHolder>() {
        @Override
        public ItemHolder createFromParcel(Parcel source) {
            return new ItemHolder(source);
        }

        @Override
        public ItemHolder[] newArray(int size) {
            return new ItemHolder[size];
        }
    };
}