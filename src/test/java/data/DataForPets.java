package data;

import model.Pet;

public class DataForPets {
    public static Object [] [] createPet() {
        return new Object[][] {
                {
                        Pet.newEntity()
                                .withId(37390).withCategoryId(3).withCategoryName("dogs").withName("Lucky")
                                .withPhotoUrls("https://images.app.goo.gl/zfEnLCeEmSSMqaoi9").build()


                }
        };
    }
}
