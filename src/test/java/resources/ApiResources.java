package resources;

public enum ApiResources {
    //enum is special class in java which has collection of constants and methods
   AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
   DeletePlaceAPI("/maps/api/place/delete/json");



   private String resource;

    ApiResources(String resource) {

        this.resource=resource;
    }

    public String getResources()
    {

        return resource;

    }
}
