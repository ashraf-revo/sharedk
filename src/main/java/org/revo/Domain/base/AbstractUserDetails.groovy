package org.revo.Domain.base

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import org.revo.Util.View
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by revo on 5/5/16.
 */
abstract class AbstractUserDetails implements UserDetails {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    int type

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        if (type == -1) return AuthorityUtils.createAuthorityList("ROLE_ADMIN")
        else if (type == 1) AuthorityUtils.createAuthorityList("ROLE_USER")
        else AuthorityUtils.createAuthorityList()
    }

    @JsonView(View.User.class)
    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @JsonView(View.User.class)
    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @JsonView(View.User.class)
    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @JsonView(View.User.class)
    @Override
    boolean isEnabled() {
        return true
    }

    @JsonView(View.User.class)
    @JsonProperty("authorities")
    public List<String> getRoles() {
        return authorities*.getAuthority()
    }

}
