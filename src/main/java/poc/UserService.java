package poc;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    public UserService() {
        System.out.println("CONSTRUCT UserService");
    }
}