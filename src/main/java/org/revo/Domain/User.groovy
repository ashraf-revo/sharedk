package org.revo.Domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.Canonical
import org.hibernate.validator.constraints.URL
import org.revo.Domain.base.AbstractUserDetails
import org.revo.Util.JsonDateSerializer
import org.revo.Util.View
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.TextIndexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

import javax.persistence.Entity

/**
 * Created by revo on 4/24/16.
 */
@Entity
@Canonical
@Document
class User extends AbstractUserDetails {
    @Id
    @JsonView(View.User.class)
    String id
    @TextIndexed
    @JsonView(View.User.class)
    String email
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonView(View.User.class)
    String password
    @JsonView(View.User.class)
    @URL
    String url
    @CreatedDate
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonView(View.User.class)
    Date createDate
    @TextIndexed
    String info

    @Override
    String getUsername() {
        return email
    }
}
