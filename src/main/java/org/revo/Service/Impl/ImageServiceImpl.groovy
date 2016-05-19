package org.revo.Service.Impl

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor
import org.bson.types.ObjectId
import org.revo.Domain.Image
import org.revo.Model.SearchCriteria
import org.revo.Domain.User
import org.revo.Repository.ImageRepository
import org.revo.Repository.UserRepository
import org.revo.Service.CloudinaryService
import org.revo.Service.ImageService
import org.revo.Service.SecurityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.TextCriteria
import org.springframework.stereotype.Service

/**
 * Created by revo on 5/5/16.
 */
@Service
class ImageServiceImpl implements ImageService {
    @Autowired
    UserRepository userRepository
    @Autowired
    ImageRepository imageRepository
    @Autowired
    CloudinaryService cloudinaryService
    @Autowired
    MongoOperations mongoOperations
    @Autowired
    SecurityService securityService

    @Override
    Image Save(Image image) {
        if (image.file) {
            image.url = cloudinaryService.Upload(image.file)
            image.file = ""
        }
        imageRepository.save(image)
    }

    @Override
    Image FindOne(String id) {
        imageRepository.findOne(id)
    }

    List<Image> Images(String lastId, int count) {
        String lt = '$lt'
        if (!lastId) {
            Optional<Image> desc = imageRepository.findTopByOrderByIdDesc()
            if (desc.present) {
                lastId = desc.get().id
                lt = '$lte'
            } else return []
        }

        BasicDBObject whereQuery = new BasicDBObject();
//        if (own) whereQuery.put("_id", securityService.GetCurrentUser().id);

        whereQuery.put("_id", new BasicDBObject(lt, new ObjectId(lastId)));

        DBCursor cursor = mongoOperations.getCollection("image").find(whereQuery);
        List<Image> images = []
        cursor.sort(new BasicDBObject("_id", -1))
        cursor.limit(count)
        cursor.forEach {
            images << new Image(id: it["_id"], createdDate: it["createdDate"] as Date, url: it["url"], user: new User(id: it["user"]['id']))
        }
        List<User> users = userRepository.findAll(images*.user.id) as List<User>
        images.collect { image ->
            image.user = users.find {
                it.id == image.user.id
            }
            image
        }
    }

    @Override
    List<Image> Search(SearchCriteria searchCriteria) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(searchCriteria.content);
        imageRepository.findAllBy(criteria, new PageRequest(searchCriteria.page, 10, Sort.Direction.ASC, "id"))
    }
}
