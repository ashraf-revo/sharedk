package org.revo.Controller

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Model.SearchCriteria
import org.revo.Service.SecurityService
import org.revo.Service.UserService
import org.revo.Util.View
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

/**
 * Created by revo on 5/12/16.
 */
@Controller
@RequestMapping("api/user")
class UserController {
    @Autowired

    UserService userService
    @Autowired
    SecurityService securityService

    @RequestMapping
    @JsonView(View.User.class)
    public ResponseEntity Account() {
        ResponseEntity.ok(securityService.GetCurrentUser())
    }

    @JsonView(View.User.class)
    @RequestMapping(value = "search", method = RequestMethod.POST)
    ResponseEntity Search(@Validated @RequestBody SearchCriteria searchCriteria) {
        ResponseEntity.ok(userService.Search(searchCriteria))
    }

    @JsonView(View.User.class)
    @RequestMapping(value = "users", method = RequestMethod.GET)
    ResponseEntity Users(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity.ok(userService.Users(id, 10))
    }
}
