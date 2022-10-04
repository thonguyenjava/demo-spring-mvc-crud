package demo.spingmvccrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoSpingMvcCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpingMvcCrudApplication.class, args);
    }

}
