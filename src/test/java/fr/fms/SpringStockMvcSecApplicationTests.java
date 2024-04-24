package fr.fms;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)

class SpringStockMvcSecApplicationTests {

	@Autowired
	IBusinessImpl business;
	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	CategoryRepository categoryRepository;

	private static Instant startedAt;

	@BeforeEach
	private void beforeEachTest() {
		System.out.println("avant chaque test");
	}

	@AfterEach
	public void afterEachTest() {
		System.out.println("après chaque test");
	}

	@BeforeAll
	public static void initStartingTime() {
		System.out.println("appel avant tous les tests");
		startedAt = Instant.now();
	}

	@AfterAll
	public static void showTestDuration() {
		System.out.println("Appel après tous les tests");
		final Instant endedAt = Instant.now();
		final long duration  = Duration.between(startedAt, endedAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}

	@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
	@ValueSource(ints = {1, 2, 42, 1011, 5089})
	public void multiply_shouldReturnZero(int arg) {
		assertEquals(0, arg*0);
	}
	@Timeout(1)
	@Test
	public void orderShouldComputeLess1Second() {
		business.getCartContent();
	}

	@Test
	void testGetTotalAmountOrder(){
		business.addOneArticleToCart(new Article((long)1,"Samsung s8 2024",250, null,null));
		business.addOneArticleToCart(new Article((long)2,"Samsung s9 2024",350, null,null));

		assertEquals(business.getTotalAmountOrder(),600);
	}

}
