package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayload(String name,String language, String add)
    {
        AddPlace p=new AddPlace();
        p.setAccuracy(String.valueOf(50));
        p.setAddress(add);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setName(name);
        List<String> myList=new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        Location l=new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);
        return p;
    }

    public String deletePlacePlayload(String placeId){
        return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";

    }
}
