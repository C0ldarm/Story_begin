package vn.aitest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        userService.getRepository().deleteAll();

        userService.saveUser(new User("user", "user123", List.of(User.Role.USER)));
        userService.saveUser(new User("publisher", "pub123", List.of(User.Role.PUBLISHER)));
        userService.saveUser(new User("admin", "admin123", List.of(User.Role.ADMIN)));

        System.out.println("Seeded: user/user123 (USER), publisher/pub123 (PUBLISHER), admin/admin123 (ADMIN)");
    }
}