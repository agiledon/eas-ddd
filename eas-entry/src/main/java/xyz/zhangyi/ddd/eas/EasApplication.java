package xyz.zhangyi.ddd.eas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class EasApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasApplication.class, args);
    }
}
