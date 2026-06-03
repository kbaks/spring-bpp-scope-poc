package poc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(App.class)) {
            System.out.println("--- Context started ---");

            // This line is important for the prototype case.
            // It requests the BeanPostProcessor bean after the first BPP instance
            // has already been registered.
            context.getBean(UserBeanPostProcessor.class);

            System.out.println("--- Done ---");
        }
    }
}