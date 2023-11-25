package interfaces;

import jsonBody.Category;
import jsonBody.PetBody;
import model.Pet;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public interface IPet {
    default String getPetStatus() {
        var random = new SecureRandom();
        var statuses = Arrays.asList("available", "pending", "sold");
        return statuses.get(random.nextInt(statuses.size()));
    }
    default PetBody createPetWithPOJO(Pet pet, String status) {
        Category category = new Category(pet.getCategoryId(), pet.getCategoryName());
        ArrayList<String> pic = new ArrayList<>();
        pic.add(pet.getPhotoUrls());
        return new PetBody(pet.getId(), category, pet.getName(), pic, status);
    }
}
