
package com.newyorktimesapi.example.network.Retrofit.ResponseModels;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Medium {

    @SerializedName("approved_for_syndication")
    private Long mApprovedForSyndication;
    @SerializedName("caption")
    private String mCaption;
    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("media-metadata")
    private List<MediaMetadatum> mMediaMetadata;
    @SerializedName("subtype")
    private String mSubtype;
    @SerializedName("type")
    private String mType;

    public Long getApprovedForSyndication() {
        return mApprovedForSyndication;
    }

    public void setApprovedForSyndication(Long approvedForSyndication) {
        mApprovedForSyndication = approvedForSyndication;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public List<MediaMetadatum> getMediaMetadata() {
        return mMediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
        mMediaMetadata = mediaMetadata;
    }

    public String getSubtype() {
        return mSubtype;
    }

    public void setSubtype(String subtype) {
        mSubtype = subtype;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
