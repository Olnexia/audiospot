package by.belstu.losik.audiospot.repository.integration;

import by.belstu.losik.audiospot.entity.Order;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@ActiveProfiles("test")
@Transactional
public class OrderRepositoryIT {
    private static final Long EXPECTED_ID = 42L;
    private static final LocalDate EXPECTED_DATE = LocalDate.of(2019, 4, 6);
    private static final boolean EXPECTED_PAID = false;
    private static final boolean EXPECTED_CHANGED_PAID = true;

    private User TEST_USER = new User(EXPECTED_ID, "John", "Doe", "testLogin",
            "testPass", Role.CLIENT, true, 2.75f);

    private Order EXPECTED_STORED_ORDER = new Order(EXPECTED_ID, EXPECTED_DATE, EXPECTED_PAID,
            TEST_USER, Collections.emptyList());

    private Order EXPECTED_UPDATED_ORDER = new Order(EXPECTED_ID, EXPECTED_DATE, EXPECTED_CHANGED_PAID,
            TEST_USER, Collections.emptyList());


    @Autowired
    private Repository<Order> orderRepository;

    @Test
    public void shouldFindOrder() {
        findAndAssertOrder(EXPECTED_ID, EXPECTED_STORED_ORDER);
    }

    @Test
    public void shouldSaveOrder() {
        Order testOrder = new Order(null, EXPECTED_DATE, EXPECTED_PAID,
                TEST_USER, Collections.emptyList());

        orderRepository.save(testOrder);

        findAndAssertOrder(testOrder.getId(), testOrder);
    }

    @Test
    public void shouldUpdateOrder() {
        Optional<Order> orderOptional = orderRepository.findById(EXPECTED_ID);

        if (!orderOptional.isPresent()) {
            Assert.fail();
        }

        Order testOrder = orderOptional.get();
        testOrder.setPaid(EXPECTED_CHANGED_PAID);
        orderRepository.update(testOrder);

        findAndAssertOrder(EXPECTED_ID, EXPECTED_UPDATED_ORDER);
    }

    @Test
    public void shouldDeleteOrder() {
        orderRepository.remove(EXPECTED_ID);
        Optional<Order> orderOptional = orderRepository.findById(EXPECTED_ID);
        Assert.assertFalse(orderOptional.isPresent());
    }

    private void findAndAssertOrder(Long id, Order expectedOrder) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        Assert.assertTrue(orderOptional.isPresent());

        Assert.assertEquals(expectedOrder, orderOptional.get());
    }
}