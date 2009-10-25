package com.qype.waveqype;

import com.google.wave.api.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import org.json.*;


public class JSONParserTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      JSONObject jsonObject = new JSONObject(mockResponseFromFile());
      
      String result = "\nSearch results (top qype places):\n";
      JSONArray arr = (JSONArray) jsonObject.getJSONArray("results");
      for(int i=0; i< arr.length(); i++) {
        Place place = new Place( (JSONObject) ((JSONObject) arr.get(i)).get("place"));
        result = result + new Integer(i+1).toString() +") " + place.toString();
      }
      System.out.println(result);
    } 
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("Nothing found");
    }

  }
  
  private static String mockResponseFromFile() {
    try {
      FileInputStream fis = new FileInputStream("src/com/qype/waveqype/qype_api_response.json");
      InputStreamReader reader = new InputStreamReader(fis, "UTF-8");
      BufferedReader br = new java.io.BufferedReader (reader) ;  
    
      String result = "";
      String line = "";
      do  {  
        line =  ( String )  br.readLine (  ) ; 
        result += line + "\r\n"; 
      }  while  ( line != null ) ; 
      return result; 
    } catch (Exception e1) {
      // TODO Auto-generated catch block
      return "";
    }
  }

}
