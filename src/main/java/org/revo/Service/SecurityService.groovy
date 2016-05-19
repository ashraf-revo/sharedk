package org.revo.Service

import org.revo.Domain.User

/**
 * Created by revo on 05/12/15.
 */
interface SecurityService {
    User GetCurrentUser() throws Exception
    User current()
}
