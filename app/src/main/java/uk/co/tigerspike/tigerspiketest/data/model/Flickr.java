
package uk.co.tigerspike.tigerspiketest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flickr implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("generator")
    @Expose
    private String generator;
    @SerializedName("items")
    @Expose
    private List<Image> images = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.description);
        dest.writeString(this.modified);
        dest.writeString(this.generator);
        dest.writeList(this.images);
    }

    public Flickr() {
    }

    protected Flickr(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.description = in.readString();
        this.modified = in.readString();
        this.generator = in.readString();
        this.images = new ArrayList<Image>();
        in.readList(this.images, Image.class.getClassLoader());
    }

    public static final Parcelable.Creator<Flickr> CREATOR = new Parcelable.Creator<Flickr>() {
        @Override
        public Flickr createFromParcel(Parcel source) {
            return new Flickr(source);
        }

        @Override
        public Flickr[] newArray(int size) {
            return new Flickr[size];
        }
    };
}
