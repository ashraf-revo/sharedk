package org.revo.Service

import org.revo.Domain.Image
import org.revo.Model.SearchCriteria

/**
 * Created by revo on 5/5/16.
 */
interface ImageService {
    Image Save(Image image)

    Image FindOne(String id)

    List<Image> Images(String lastId, int count)

    List<Image> Search(SearchCriteria searchCriteria)
}
