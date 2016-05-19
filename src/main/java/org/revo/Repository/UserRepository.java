package org.revo.Repository;

import org.revo.Domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by revo on 4/24/16.
 */
public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByEmail(String email);

    List<User> findAllBy(TextCriteria criteria, Pageable pageable);

    Optional<User> findTopByOrderByIdDesc();
}
