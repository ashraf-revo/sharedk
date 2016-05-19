package org.revo.Service.Impl

import org.revo.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Created by revo on 4/24/16.
 */
@Service
class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService

    @Override
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        userService.findByEmail(email).orElseThrow {
            new UsernameNotFoundException("cant find this one")
        }
    }
}
