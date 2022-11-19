package kz.iitu.manufactureservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class},
        scanBasePackages = {"kz.iitu.cfaslib", "kz.iitu.manufactureservice"}
)
public class ManufactureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManufactureServiceApplication.class, args);
    }

}
