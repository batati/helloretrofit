package fi.solita.helloretrofit.models.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * Created by eetupa on 24/08/16.
 */

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Item implements Parcelable {

    private String contentCategory;
    private String contentType;
    private int contentIndex;
    private int orderIndex;
    private String title;
    private String url;
    private String contentId;
    private boolean isActive;
    private String icon;

    public Item (String title) {
        this.title = title;
    }

    public Item(String title, String contentCategory) {
        this.contentCategory = contentCategory;
        this.title = title;
    }

    /**
     * @return The contentCategory
     */
    public String getContentCategory() {
        return contentCategory;
    }

    /**
     * @param contentCategory The contentCategory
     */
    public void setContentCategory(String contentCategory) {
        this.contentCategory = contentCategory;
    }

    /**
     * @return The contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType The contentType
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return The contentIndex
     */
    public int getContentIndex() {
        return contentIndex;
    }

    /**
     * @param contentIndex The contentIndex
     */
    public void setContentIndex(int contentIndex) {
        this.contentIndex = contentIndex;
    }

    /**
     * @return The orderIndex
     */
    public int getOrderIndex() {
        return orderIndex;
    }

    /**
     * @param orderIndex The orderIndex
     */
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The contentId
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * @param contentId The contentId
     */
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    /**
     * @return The isActive
     */
    public boolean isIsActive() {
        return isActive;
    }

    /**
     * @param isActive The isActive
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.contentCategory);
        dest.writeString(this.contentType);
        dest.writeInt(this.contentIndex);
        dest.writeInt(this.orderIndex);
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.contentId);
        dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
        dest.writeString(this.icon);
    }

    protected Item(Parcel in) {
        this.contentCategory = in.readString();
        this.contentType = in.readString();
        this.contentIndex = in.readInt();
        this.orderIndex = in.readInt();
        this.title = in.readString();
        this.url = in.readString();
        this.contentId = in.readString();
        this.isActive = in.readByte() != 0;
        this.icon = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}

