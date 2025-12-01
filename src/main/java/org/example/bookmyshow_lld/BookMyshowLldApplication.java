package org.example.bookmyshow_lld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookMyshowLldApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookMyshowLldApplication.class, args);
    }

}
