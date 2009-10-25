package com.qype.waveqype;

import com.google.wave.api.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import org.json.*;

public class WaveQypeServlet extends AbstractRobotServlet {
  
  @Override
  public void processEvents(RobotMessageBundle bundle) {
    Wavelet wavelet = bundle.getWavelet();
              
    // if (bundle.wasSelfAdded()) {
    //   Blip blip = wavelet.appendBlip();
    //   TextView textView = blip.getDocument();
    //   textView.append("Now you can search in the wave. Try typing:\n@waveqype \"pizza,Hamburg\"");
    // }

    for (Event e: bundle.getEvents()) {
      //TODO later version will go back and put words in all previously submitted blips.
      if (e.getType() == EventType.DOCUMENT_CHANGED) {
        Blip blip = e.getBlip();
        replaceQypeSearches(blip);
      }
    }
    
  }

  private void replaceQypeSearches(Blip blip){
    TextView doc = blip.getDocument();
    String blipContent = doc.getText();            //content of the blip

    String input = blipContent; //"qype:\"pizza lecker,frankfurt am main\"";
    
    Pattern p = Pattern.compile("@waveqype \"(.*),(.*)\"");
    Matcher m = p.matcher(input);
    StringBuffer sb = new StringBuffer();
    
    while (m.find()) {
      String matched = input.substring(m.start(), m.end());
      matched = matched.replace("@waveqype ", "");
      matched = matched.replace("\"","");
      
      String[] whereAndWhat = matched.split(",");
      String what  = whereAndWhat[0];
      String where = whereAndWhat[1];
      
      m.appendReplacement(sb, search(what,where));
      doc.replace(sb.toString());
    }
    
  }

  private String search(String what, String where) {
    try {
      URL url = new URL("http://api.qype.com/v1/places.json?show="+URLEncoder.encode(what)+"&in="+URLEncoder.encode(where)+"&consumer_key="+ApiKey.API_KEY);
      
      JSONObject jsonObject = new JSONObject(getResponseFromUrl(url));
      
      String result = "\n\n";
      JSONArray arr = (JSONArray) jsonObject.getJSONArray("results");
      for(int i=0; i< arr.length(); i++) {
        Place place = new Place( (JSONObject) ((JSONObject) arr.get(i)).get("place"));
        result = result + new Integer(i+1).toString() +") " + place.toString();
      }
      return result;
    } 
    catch (Exception e) {
      e.printStackTrace();
      return "Nothing found";
    }
    
  }
  
  private String getResponseFromUrl(URL url){
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      String line;
      StringBuffer buf = new StringBuffer();
      while ((line = reader.readLine()) != null) {
        buf.append(line);
      }
      reader.close();
      return buf.toString();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      return "";
    }
  }

}