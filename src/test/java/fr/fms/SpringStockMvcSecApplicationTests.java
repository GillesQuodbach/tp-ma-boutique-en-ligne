package fr.fms;
import fr.fms.entities.Article;
import fr.fms.entities.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringStockMvcSecApplicationTests {


    @Test
    void contextLoads() {
        int value1 = 1;
        int value2 = 2;
        assertNotEquals(value1, value2);
    }

}
