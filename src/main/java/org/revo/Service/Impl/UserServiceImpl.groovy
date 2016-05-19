package org.revo.Service.Impl

import com.mongodb.BasicDBObject
import com.mongodb.DBCursor
import org.bson.types.ObjectId
import org.revo.Domain.User
import org.revo.Model.SearchCriteria
import org.revo.Repository.UserRepository
import org.revo.Service.UserService
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
class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository
    @Autowired
    MongoOperations mongoOperations

    @Override
    int Count() {
        userRepository.count()
    }

    @Override
    User Save(User user) {
        userRepository.save(user)
    }

    @Override
    Optional<User> findByEmail(String email) {
        userRepository.findByEmail(email)
    }

    @Override
    List<User> Search(SearchCriteria searchCriteria) {
        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(searchCriteria.content);
        userRepository.findAllBy(criteria, new PageRequest(searchCriteria.page, 10, Sort.Direction.ASC, "id"))
    }

    @Override
    List<User> Users(String lastId, int count) {
        String lt = '$lt'
        if (!lastId) {
            Optional<User> desc = userRepository.findTopByOrderByIdDesc()
            if (desc.present) {
                lastId = desc.get().id
                lt = '$lte'
            } else return []
        }

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("_id", new BasicDBObject(lt, new ObjectId(lastId)));
        DBCursor cursor = mongoOperations.getCollection("user").find(whereQuery);
        List<User> users = []
        cursor.sort(new BasicDBObject("_id", -1))
        cursor.limit(count)
        cursor.forEach {
            users << new User(id: it["_id"], email: it["email"], password: it["password"], createdDate: it["createdDate"] as Date, url: it["url"], info: it["info"])
        }
        users
    }
}