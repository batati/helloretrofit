package fi.solita.helloretrofit.models.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eetupa on 13/01/17.
 */

public class BlobHolder implements Parcelable {
    private String status;
    private List<Blob> data = new ArrayList<Blob>();
    private String message;

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

    public BlobHolder() {
    }

    protected BlobHolder(Parcel in) {
        this.status = in.readString();
        this.data = in.createTypedArrayList(Blob.CREATOR);
        this.message = in.readString();
    }

    public static final Parcelable.Creator<BlobHolder> CREATOR = new Parcelable.Creator<BlobHolder>() {
        @Override
        public BlobHolder createFromParcel(Parcel source) {
            return new BlobHolder(source);
        }

        @Override
        public BlobHolder[] newArray(int size) {
            return new BlobHolder[size];
        }
    };
}
