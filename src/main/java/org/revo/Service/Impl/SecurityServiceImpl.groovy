package org.revo.Service.Impl

import org.revo.Domain.User
import org.revo.Service.SecurityService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

/**
 * Created by revo on 05/12/15.
 */
@Service
class SecurityServiceImpl implements SecurityService {
    @Override
    User GetCurrentUser() throws Exception {
        Authentication authentication = SecurityContextHolder.context.authentication
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                return authentication.principal as User
            } else {
                throw new Exception("not login user")
            }
        } else throw new Exception("not login user")
    }

    @Override
    User current() {
        Authentication authentication = SecurityContextHolder.context.authentication
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                return authentication.principal as User
            }
        }
        return new User()
    }
}