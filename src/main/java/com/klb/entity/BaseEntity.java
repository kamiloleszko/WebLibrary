package com.klb.entity;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;

/**
 * Created by fmkam on 01.07.2017.
 */
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    protected Long id;


    public BaseEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
