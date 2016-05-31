package org.revo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.data.mongodb.config.EnableMongoAuditing

@SpringBootApplication
@EnableMongoAuditing
@EnableConfigurationProperties
class SharedApplication {

    static void main(String[] args) {
        SpringApplication.run SharedApplication, args
    }
}
