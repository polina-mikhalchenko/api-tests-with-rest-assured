package data;
import model.User;

public class DataForUsers {
    public static Object [] [] user1() {
        return new Object[][] {
                {
                        User.newEntity()
                                .withUsername("Leenixi").withFirstName("Lysippos").withLastName("L’Huilier")
                                .withEmail("trideipattoullei-1121@email.com").withPassword("7c88bsWHd0Lq")
                                .withPhone(" 8(070)20983 ").withUserStatus(0).build()

                }
        };
    }
    public static Object [] [] user2() {
        return new Object[][] {
                {
                        User.newEntity()
                                .withUsername("DRL").withFirstName("John").withLastName("Jones")
                                .withEmail("jones1121@email.com").withPassword("y6l4/wq")
                                .withPhone("93003").withUserStatus(1).build()

                }
        };
    }
}
