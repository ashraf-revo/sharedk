package org.revo.Repository;

import org.revo.Domain.Image;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by revo on 5/5/16.
 */
public interface ImageRepository extends CrudRepository<Image, String> {
    Optional<Image> findTopByOrderByIdDesc();

    List<Image> findAllBy(TextCriteria criteria, Pageable pageable);

}
