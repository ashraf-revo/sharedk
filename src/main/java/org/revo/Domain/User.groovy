package org.revo.Domain

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import groovy.transform.Canonical
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.URL
import org.revo.Domain.base.AbstractUserDetails
import org.revo.Util.JsonDateSerializer
import org.revo.Util.View
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

import javax.persistence.Entity
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

/**
 * Created by revo on 4/24/16.
 */
@Entity
@Canonical
@Document
class User extends AbstractUserDetails {
    @Id
    @JsonView(View.User.class)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String id
    @JsonView(View.User.class)
    @NotBlank
    @Email
    @Indexed(unique = true)
    String email
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonView(View.User.class)
    @NotBlank
    @Size(min = 4, max = 60)
    String password
    @JsonView(View.User.class)
    @URL
    String url
    @CreatedDate
    @JsonSerialize(using = JsonDateSerializer.class)
    @JsonView(View.User.class)
    Date createDate
    @JsonView(View.User.class)
    @Indexed
    String info
    @JsonView(View.User.class)
    @NotBlank
    @Indexed
    String username
    @JsonView(View.User.class)
    @Indexed(unique = true)
    @Pattern(regexp = /[0-9]{11}/)
    String phone

    @Override
    String getUsername() {
        return email
    }
}
