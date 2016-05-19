package org.revo.Util

import org.revo.Domain.User
import org.revo.Service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.AuditorAware
import org.springframework.stereotype.Component

/**
 * Created by revo on 5/5/16.
 */
@Component
class SpringSecurityAuditorAware implements AuditorAware<User> {
    @Autowired
    SecurityService securityService

    @Override
    User getCurrentAuditor() {
        securityService.current()
    }
}