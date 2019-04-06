package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.Role;
import by.belstu.losik.audiospot.entity.User;
import by.belstu.losik.audiospot.repository.Repository;
import by.belstu.losik.audiospot.springconfig.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class UserRepositoryIT {
    private static final String EXPECTED_LOGIN = "testLogin";
    private static final String EXPECTED_PASS = "testPass";
    private static final String EXPECTED_NAME = "John";
    private static final String EXPECTED_SURNAME = "Doe";
    private static final float EXPECTED_DISCOUNT = 2.75f;
    private static final boolean EXPECTED_ACTIVE = true;
    private static final Role EXPECTED_ROLE = Role.CLIENT;
    private static final Long EXPECTED_ID = 42L;
    private static final String EXPECTED_CHANGED_PASS = "ChangedPassword";

    private User EXPECTED_STORED_USER = new User(EXPECTED_ID, EXPECTED_NAME, EXPECTED_SURNAME, EXPECTED_LOGIN,
            EXPECTED_PASS, EXPECTED_ROLE, EXPECTED_ACTIVE, EXPECTED_DISCOUNT);

    private User EXPECTED_UPDATED_USER = new User(EXPECTED_ID, EXPECTED_NAME, EXPECTED_SURNAME, EXPECTED_LOGIN,
            EXPECTED_CHANGED_PASS, EXPECTED_ROLE, EXPECTED_ACTIVE, EXPECTED_DISCOUNT);

    @Autowired
    private Repository<User> userRepository;

    @Test
    public void shouldFindUser() {
        findAndAssertUser(EXPECTED_ID, EXPECTED_STORED_USER);
    }

    @Test
    public void shouldSaveUser() {
        User testUser = new User(null, EXPECTED_NAME, EXPECTED_SURNAME, EXPECTED_LOGIN,
                EXPECTED_PASS, EXPECTED_ROLE, EXPECTED_ACTIVE, EXPECTED_DISCOUNT);

        userRepository.save(testUser);

        findAndAssertUser(testUser.getId(), testUser);
    }

    @Test
    public void shouldUpdateUser() {
        Optional<User> userOptional = userRepository.findById(EXPECTED_ID);

        if (!userOptional.isPresent()) {
            Assert.fail();
        }

        User testUser = userOptional.get();
        testUser.setPassword(EXPECTED_CHANGED_PASS);
        userRepository.update(testUser);

        findAndAssertUser(EXPECTED_ID, EXPECTED_UPDATED_USER);
    }

    @Test
    public void shouldDeleteUser() {
        userRepository.remove(EXPECTED_ID);
        Optional<User> userOptional = userRepository.findById(EXPECTED_ID);
        Assert.assertFalse(userOptional.isPresent());
    }

    private void findAndAssertUser(Long id, User expectedUser) {
        Optional<User> userOptional = userRepository.findById(id);
        Assert.assertTrue(userOptional.isPresent());

        Assert.assertEquals(expectedUser, userOptional.get());
    }
}