package ank.cowin.cowinco;


import ank.cowin.controller.CowinController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ank.cowin")
public class CowinCoApplication {

    @Autowired
    CowinController cowinController;

    public static void main(String[] args) {
        SpringApplication.run(CowinCoApplication.class, args);
    }
}
