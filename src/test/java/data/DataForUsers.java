package data;
import model.User;

public class DataForUsers {
    public static Object [] [] createUser() {
        return new Object[][] {
                {
                        User.newEntity()
                                .withUsername("Leenixi").withFirstName("Lysippos").withLastName("L’Huilier")
                                .withEmail("trideipattoullei-1121@email.com").withPassword("7c88bsWHd0Lq")
                                .withPhone(" 8(070)20983 ").withUserStatus(0).build()

                }
        };
    }
}
