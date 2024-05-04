package org.itmo.application2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("org.itmo.dataaccesslayer2.repositories")
@EntityScan(basePackages = {"org.itmo.dataaccesslayer2"})
@SpringBootApplication(scanBasePackages = {"org.itmo.servicelayer2", "org.itmo.application2"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
