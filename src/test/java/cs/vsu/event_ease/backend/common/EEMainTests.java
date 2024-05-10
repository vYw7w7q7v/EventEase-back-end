package cs.vsu.event_ease.backend.common;

import cs.vsu.event_ease.backend.entity.User;
import cs.vsu.event_ease.backend.repository.EEUserRepository;

import cs.vsu.event_ease.backend.utils.ColorPrint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class EEMainTests {

    @Autowired
    private EEUserRepository userRepository;

    @Test
    public void userEntityCRUDTest() {

        // save
        User user = new User("test@mail.ru", "test_login", "test_password", "John");
        userRepository.save(user);

        Optional<User> foundUserOptional = userRepository.findById(user.getUuid());
        Assertions.assertFalse(foundUserOptional.isEmpty());
        User foundUser = foundUserOptional.get();
        Assertions.assertEquals(foundUser, user);
        ColorPrint.println("created user: " + foundUser, AnsiColor.GREEN);

        // update

        user.setLogin("new_login");
        userRepository.save(user);
        foundUserOptional = userRepository.findById(user.getUuid());
        Assertions.assertFalse(foundUserOptional.isEmpty());
        foundUser = foundUserOptional.get();
        Assertions.assertEquals(foundUser, user);
        ColorPrint.println("updated user: " + foundUser, AnsiColor.GREEN);

        // delete

        userRepository.deleteById(user.getUuid());
        Assertions.assertFalse(userRepository.existsById(user.getUuid()));
        ColorPrint.println("user <" + user.getUuid() + "> is deleted", AnsiColor.GREEN);

    }
}
