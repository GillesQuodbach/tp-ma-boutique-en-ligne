//package fr.fms;
//
//import fr.fms.business.IBusinessImpl;
//import fr.fms.entities.Article;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.text.MessageFormat;
//import java.time.Duration;
//import java.time.Instant;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class SpringStockMvcSecApplicationTests {
//@Autowired
//	IBusinessImpl business;
//
//private static Instant startedAt;
//
//@BeforeEach
//public static void beforeEachTest(){
//	System.out.println("avant chaque test");
//}
//
//@AfterEach
//public static void afterEachTest(){
//	System.out.println("après chaque test");
//}
//
//@BeforeAll
//public static void initStartingTime(){
//	System.out.println("appel avant tous tests");
//	startedAt = Instant.now();
//}
//
//@AfterAll
//public static void showTestDuration(){
//	System.out.println("Appel après tous les tests");
//	final Instant endedAt = Instant.now();
//	final long duration = Duration.between(startedAt, endedAt).toMillis();
//	System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
//}
//	@Test
//	void contextLoads() {
//		assertNotEquals(1, 2);
//	}
//
//@ParameterizedTest(name = "{0} x 0 doit être égal à 0")
//@ValueSource(ints = {1,2,42,1011,5089})
//public void multiply_shouldReturnZero(int arg){
//	assertEquals(0, arg*0);
//}
//
//@Timeout(1)
//@Test
//public void orderShouldComputeLess1Second(){
//	business.order();
//}
//
//
//	@Test
//	void testTotalAmountCart(){
//	// Arrange
//		business.addArtToCart(new Article((long)1, "Samsung", "Samsung S8", 250, 1, null));
//		business.addArtToCart(new Article((long)1, "Samsung", "Samsung S8", 250, 1, null));
//		business.addArtToCart(new Article((long)1, "Iphone", "Iphone 10", 500, 1, null));
//		business.addArtToCart(new Article((long)1, "Samsung", "Samsung S8", 250, 1, null));
//	//Act
//		double amount = business.getTotalAmount();
//
//		//Assert
//		assertEquals(amount, 1000);
//	}
//
//}
