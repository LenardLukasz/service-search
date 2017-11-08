package sample.models.services;

import sample.models.utils.Config;
import sample.models.utils.HttpConfig;

public class MakeCallProbe {
    private static MakeCallProbe ourInstance = new MakeCallProbe();

    public static MakeCallProbe getInstance() {
        return ourInstance;
    }

    public MakeCallProbe() {
    }
 public void makeCall(String city){
     System.out.println(HttpConfig.makeHttpRequest(Config.APP_URL +"restaurants+in+" + city + "&key=" + Config.APP_ID ));


 }
}
// https://maps.googleapis.com/maps/api/place/textsearch/json?query=123+main+street&key=