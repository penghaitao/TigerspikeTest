
package uk.co.tigerspike.tigerspiketest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("media")
    @Expose
    private Media media;
    @SerializedName("date_taken")
    @Expose
    private String dateTaken;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("published")
    @Expose
    private String published;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("author_id")
    @Expose
    private String authorId;
    @SerializedName("tags")
    @Expose
    private String tags;

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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublished() {
        return published.replaceAll("[A-Z]", " ");
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public static class Media implements Parcelable {

        @SerializedName("m")
        @Expose
        private String m;

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.m);
        }

        public Media() {
        }

        protected Media(Parcel in) {
            this.m = in.readString();
        }

        public static final Creator<Media> CREATOR = new Creator<Media>() {
            @Override
            public Media createFromParcel(Parcel source) {
                return new Media(source);
            }

            @Override
            public Media[] newArray(int size) {
                return new Media[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeParcelable(this.media, flags);
        dest.writeString(this.dateTaken);
        dest.writeString(this.description);
        dest.writeString(this.published);
        dest.writeString(this.author);
        dest.writeString(this.authorId);
        dest.writeString(this.tags);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.media = in.readParcelable(Media.class.getClassLoader());
        this.dateTaken = in.readString();
        this.description = in.readString();
        this.published = in.readString();
        this.author = in.readString();
        this.authorId = in.readString();
        this.tags = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
