package stepDefinations;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {

            StepDefinations m = new StepDefinations();
        if (StepDefinations.place_id == null)
        {
            m.add_place_payload_with("Pushpa", "French", "Hello");
            m.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Pushpa", "getPlaceAPI");
        }
    }
}
