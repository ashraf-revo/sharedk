package org.revo.Domain

import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.Canonical
import org.hibernate.validator.constraints.URL
import org.revo.Util.JsonDateSerializer
import org.revo.Util.View
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import javax.persistence.Entity

/**
 * Created by revo on 5/5/16.
 */
@Entity
@Canonical
@Document
class Image {
    @Id
    @JsonView(View.Image.class)
    String id
    @JsonView(View.Image.class)
    String info
    @CreatedDate
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonView(View.Image.class)
    Date createdDate
    @URL
    @JsonView(View.Image.class)
    String url
    @CreatedBy
    @DBRef
    @JsonView(View.ImageUser.class)
    User user
    @Transient
    String file
}