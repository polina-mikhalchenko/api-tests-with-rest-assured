package data;

import model.Order;
import model.Pet;

public class DataForOrders {
    public static Object [] [] createOrder() {
        return new Object[][] {
                {
                        Order.newEntity()
                                .withId(1).withPetId(2).withQuantity(1)
                                .withShipDate("2025-11-03T04:36:14.802+0000").withComplete(true).build()


                }
        };
    }
}
