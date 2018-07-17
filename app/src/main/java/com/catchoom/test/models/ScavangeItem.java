package com.catchoom.test.models;

import com.craftar.CraftARQueryImage;
import com.craftar.ImageRecognition;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by artcslee on 4/9/18.
 */
public class ScavangeItem extends RealmObject {
    @PrimaryKey
    private String id;
    @Required
    private String title;
    @Required
    private String area;

    private int imgId;

    public ScavangeItem() {}

    public ScavangeItem(String id, String title, int imgId, String area) {
        this.id = id;
        this.title = title;
        this.imgId = imgId;
        this.area = area;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getImgId() {
        return imgId;
    }

    public String getArea() {
        return area;
    }
}
