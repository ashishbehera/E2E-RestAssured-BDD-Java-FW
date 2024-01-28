package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		StepDefinations def = new StepDefinations();
		if (StepDefinations.place_id == null) {
			def.add_place_payload_with("Radhe Ashram", "Vrindavan", "Hindi", "99876543215");
			def.user_calls_with_http_request("AddPlaceAPI", "Post");
			def.verify_place_id_created_map_to_using("Radhe Ashram", "getPlaceAPI");
		}

	}

}
