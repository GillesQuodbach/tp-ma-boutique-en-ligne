package fr.fms;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MathTest {

    @Test
    void test_Add(){
        assertEquals(5, Math.add(3,2));
    }

    @Test
    void test_Mul(){
        assertEquals(15,Math.mul(3,5));
    }

    @Test
    void test_Sub(){
        assertEquals(2, Math.sub(5,3));
    }

    @Test
    void test_Div(){
        assertEquals(5, Math.div(25,5));
    }

    @Test
    void test_Pair(){
        assertTrue(Math.pair(4));
    }
}
