package vn.com.smoke.springlocktransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringLockTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringLockTransactionApplication.class, args);
    }

}
