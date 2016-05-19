package org.revo.Controller

import com.fasterxml.jackson.annotation.JsonView
import org.revo.Domain.Image
import org.revo.Model.SearchCriteria
import org.revo.Service.ImageService
import org.revo.Util.View
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

/**
 * Created by revo on 5/5/16.
 */
@Controller
@RequestMapping(value = "api/image")
class ImageController {
    @Autowired
    ImageService imageService

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(View.ImageAndUser.class)
    ResponseEntity Images(@RequestParam(value = "id", required = false) String id) {
        ResponseEntity.ok(imageService.Images(id, 10))
    }

    @RequestMapping(method = RequestMethod.POST)
    @JsonView(View.ImageAndUser.class)
    ResponseEntity Save(@RequestBody Image image) {
        ResponseEntity.ok(imageService.Save(image))
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @JsonView(View.ImageAndUser.class)
    ResponseEntity One(@PathVariable("id") String id) {
        ResponseEntity.ok(imageService.FindOne(id))
    }

    @JsonView(View.ImageAndUser.class)
    @RequestMapping(value = "search", method = RequestMethod.POST)
    ResponseEntity Search(@Validated @RequestBody SearchCriteria searchCriteria) {
        ResponseEntity.ok(imageService.Search(searchCriteria))
    }
}