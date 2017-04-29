package com.getjavajob.kovarnevm.phonebook.common;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image_tbl")
public class Image extends BaseEntity {

    private String image;

    public Image() {
    }

    public Image(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
