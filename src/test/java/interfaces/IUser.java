package interfaces;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Provide;
import net.jqwik.web.api.Web;
import org.json.JSONObject;

public interface IUser {
    @Provide
    default JSONObject randomUser() {
        Arbitrary<Integer> userNameLength = Arbitraries.integers().between(0, 120);
        Arbitrary<Integer> nameLength = Arbitraries.integers().between(2, 10);
        Arbitrary<String> firstName = Arbitraries.strings().alpha().ofLength(nameLength.sample());
        Arbitrary<String> lastName = Arbitraries.strings().alpha().ofLength(nameLength.sample());
        Arbitrary<String> username = Arbitraries.strings().withCharRange('a', 'z').ofLength(userNameLength.sample());
        Arbitrary<String> email = Web.emails();
        Arbitrary<String> password = Arbitraries.strings().withCharRange('a', 'z').ofLength(5).numeric().ofLength(3);
        Arbitrary<String> phone = Arbitraries.strings().withChars('0', '1', '2', '3', '4', '5', '6', '7', '8', '9').ofLength(7);
        JSONObject body = new JSONObject();
        body.put("username", username.sample());
        body.put("firstName", firstName.sample());
        body.put("lastName", lastName.sample());
        body.put("email", email.sample());
        body.put("password", password.sample());
        body.put("phone", phone.sample());
        return body;
    }
    default int getRandomQuantity(int max, int min){
        return (int) ((Math.random() * (max - min)) + min);
    }
}
