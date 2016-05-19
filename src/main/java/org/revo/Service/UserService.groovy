package org.revo.Service

import org.revo.Domain.User
import org.revo.Model.SearchCriteria

/**
 * Created by revo on 5/5/16.
 */
interface UserService {
    int Count()

    User Save(User user)

    Optional<User> findByEmail(String email)

    List<User> Search(SearchCriteria searchCriteria)

    List<User> Users(String lastId, int count)

}