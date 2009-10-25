package com.qype.waveqype;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Place {
  
  JSONObject json;
  String title;
  String place_url;
  String webpage;
  String phone;
  String postcode;
  String city;
  String housenumber;
  String street;
  String country_code;
  
  
  public Place(JSONObject json) {
    // TODO Auto-generated constructor stub
    this.json = json;
    this.title       = parseAttribute(json, "title");
    this.webpage     = parseAttribute(json, "href");
    
    JSONObject address;
    JSONArray links;
    try {
      links = ((JSONArray) json.get("links"));
      for(int i=0; i< links.length(); i++) {
        if (((JSONObject) links.get(i)).get("rel").toString().equals("alternate")) {
          this.place_url = ( ((JSONObject) links.get(i)).get("href")).toString();
        }
      }

      address = ((JSONObject) json.get("address"));
      this.phone        = parseAttribute(address, "phone");
      this.postcode     = parseAttribute(address, "postcode");
      this.city         = parseAttribute(address, "city");
      this.housenumber  = parseAttribute(address, "housenumber");
      this.street       = parseAttribute(address, "street");
      this.country_code = parseAttribute(address, "country_code");
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  public String toString() {
    String result = "";
    result += this.title;
    if (this.street != "" || this.housenumber != "") {
      result += "\n";
      if (this.country_code == "UK" || this.country_code == "FR") {
        result += this.housenumber + " " + this.street;
      }
      else {
        result += this.street + " " + this.housenumber;
      }
    }
    if (this.postcode != "" || this.city != "") {
      result += "\n" + this.postcode;
      result += " "  + this.city;
    }
    if (this.phone.length() != 0) {
      result += "\n" + this.phone;
    }
    if (this.place_url.length() != 0) {
      result += "\n" + this.place_url;
    }
    if (this.webpage.length() != 0) {
      result += "\n" + this.webpage;
    }
    result += "\n\n";
    return result;
  }
  
  private String parseAttribute(JSONObject o, String attr) {
    try {
      return o.get(attr).toString();
    } catch (JSONException e) {
      return "";
    }
  }
}
