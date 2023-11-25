package interfaces;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Provide;
import net.jqwik.time.api.DateTimes;
import net.jqwik.time.api.arbitraries.LocalDateTimeArbitrary;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.Arrays;

public interface IStore {
    @Provide
    default JSONObject randomOrder() {
        Arbitrary<Integer> id = Arbitraries.integers().between(1, 9000000);
        LocalDateTimeArbitrary date = DateTimes.dateTimes();
        JSONObject body = new JSONObject();
        body.put("id", id.sample());
        body.put("petId", id.sample());
        body.put("quantity", id.sample());
        body.put("shipDate", date.sample().toString());
        return body;
    }
    default String getOrderStatus() {
        var random = new SecureRandom();
        var statuses = Arrays.asList("placed", "approved", "delivered");
        return statuses.get(random.nextInt(statuses.size()));
    }
    default int getRandomNumber() {
        return (int) ((Math.random() * (10 - 1)) + 1);
    }
}
