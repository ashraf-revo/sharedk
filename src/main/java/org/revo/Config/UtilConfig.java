package org.revo.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.revo.Domain.User;
import org.revo.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * Created by revo on 4/24/16.
 */
@Configuration
public class UtilConfig {
    @Bean
    CommandLineRunner lineRunner(UserService userService, PasswordEncoder encoder) {
        return args -> {
            if (userService.Count() == 0) {
                User user = new User();
                user.setEmail("ashraf");
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
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dmmcfa9c0",
                "api_key", "553119935255391",
                "api_secret", "pkKIFJyAhX5xmQWVadko4VaCDi4"));
    }
}

