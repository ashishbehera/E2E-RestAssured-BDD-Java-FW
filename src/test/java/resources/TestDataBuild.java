package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayLoad(String name,String address,String language, String phoneNumber) {
		AddPlace ap = new AddPlace();
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguaage(language);
		ap.setPhone_number(phoneNumber);
		ap.setName(name);
		ap.setWebsite("http://google.com");
		List<String> myList = new ArrayList<>();
		myList.add("shoe park");
		myList.add("shop");
		Location myLocation = new Location();
		myLocation.setLat(-38.383494);
		myLocation.setLng(33.427362);
		ap.setLocation(myLocation);
		return ap;
	}
	
	public String deletePlacePayload(String place_id) {
		return "{\r\n    \"place_id\":\""+place_id+"\"\r\n}";
	
	}

}
