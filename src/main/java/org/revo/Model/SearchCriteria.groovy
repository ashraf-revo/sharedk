package org.revo.Model

import groovy.transform.Canonical
import org.hibernate.validator.constraints.NotBlank

import javax.validation.constraints.Min
import javax.validation.constraints.Size

/**
 * Created by revo on 5/12/16.
 */
@Canonical
class SearchCriteria {
    @NotBlank
    @Size(min = 2)
    String content
    @Min(0L)
    int page = 0
}
