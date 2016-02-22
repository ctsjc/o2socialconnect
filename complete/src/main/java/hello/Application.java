package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
// templateResolver.setTemplateMode(TemplateMode.HTML5);   DEPRECATED!!
        templateResolver.setTemplateMode("HTML5");
        SpringApplication.run(Application.class, args);
    }

}
