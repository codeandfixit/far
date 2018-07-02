package no.mbs.far;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FarApplication {

    private final FindAndReplace far;

    @Autowired
    public FarApplication(FindAndReplace far) {
        this.far = far;
    }

    public static void main(String[] argst) {
        SpringApplication.run(FarApplication.class, argst);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> far.execute();
    }
}
