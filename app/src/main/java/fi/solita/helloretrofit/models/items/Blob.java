package fi.solita.helloretrofit.models.items;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eetupa on 13/01/17.
 */

public class Blob implements Parcelable {

    public int id;
    public String name;
    public String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
    }

    public Blob() {
    }

    protected Blob(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Blob> CREATOR = new Parcelable.Creator<Blob>() {
        @Override
        public Blob createFromParcel(Parcel source) {
            return new Blob(source);
        }

        @Override
        public Blob[] newArray(int size) {
            return new Blob[size];
        }
    };
}
