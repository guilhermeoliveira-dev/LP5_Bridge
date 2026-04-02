package org.example.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SaleTest {

    @Test
    void testSaleCreationAndGetters() {
        Sale sale = new Sale("Notebook", 3500.00);

        assertEquals("Notebook", sale.product());
        assertEquals(3500.00, sale.price());
    }
}