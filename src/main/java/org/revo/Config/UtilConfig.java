package org.revo.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.revo.Domain.Image;
import org.revo.Domain.User;
import org.revo.Model.SearchCriteria;
import org.revo.Service.ImageService;
import org.revo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by revo on 4/24/16.
 */
@Configuration
public class UtilConfig {
    @Bean
    CommandLineRunner lineRunner(UserService userService, PasswordEncoder encoder, ImageService imageService) {
        return args -> {
            if (userService.Count() == 0) {
                User user = new User();
                user.setEmail("ashraf1@gmail.com");
                user.setUsername("ashraf");
                user.setPassword(encoder.encode("ashraf"));
                user.setType(1);
                userService.Save(user);
            }
        };
    }

    @Bean
    public LoggingEventListener eventListener() {
        return new LoggingEventListener();
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener(Validator validator) {
        return new ValidatingMongoEventListener(validator);
    }

    @Bean
    public Cloudinary cloudinary(CloudinaryProperties cloudinaryProperties) {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudinaryProperties.getCloud_name(),
                "api_key", cloudinaryProperties.getApi_key(),
                "api_secret", cloudinaryProperties.getApi_secret()));
    }
}
