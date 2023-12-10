package data;

import model.Pet;

public class DataForPets {
    public static Object [] [] createADog() {
        return new Object[][] {
                {
                        Pet.newEntity()
                                .withId(37390).withCategoryId(3).withCategoryName("dogs").withName("Lucky")
                                .withPhotoUrls("https://images.app.goo.gl/zfEnLCeEmSSMqaoi9").build()


                }
        };
    }
    public static Object [] [] createACat() {
        return new Object[][] {
                {
                        Pet.newEntity()
                                .withId(18593).withCategoryId(1).withCategoryName("cats").withName("Barsik")
                                .withPhotoUrls("https://images.app.goo.gl/RB1c4JyXPovgwtWT7").build()


                }
        };
    }
}
