package com.qype.waveqype;

import com.google.wave.api.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import org.json.*;

public class QypeApiRequestTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    String what = "Apfelwein Solzer";
    String where = "Frankfurt am Main";
    try {
      URL url = new URL("http://api.qype.com/v1/places.json?show="+URLEncoder.encode(what)+"&in="+URLEncoder.encode(where)+"&consumer_key="+ApiKey.API_KEY);
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      StringBuffer buf = new StringBuffer();
      while ((line = reader.readLine()) != null) {
        buf.append(line);
      }
      reader.close();

      JSONObject jsonObject = new JSONObject(buf.toString());
      System.out.println(buf.toString()); 
    } 
    catch (Exception e) {
      e.printStackTrace();
    }
  }

}
