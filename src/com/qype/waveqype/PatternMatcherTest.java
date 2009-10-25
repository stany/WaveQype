package com.qype.waveqype;
import com.google.wave.api.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.*;

import org.json.*;

public class PatternMatcherTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
     String input = "Let's meet here: @waveqype \"pizza,hamburg\"";
     System.out.println(input);
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
       
       System.out.println(what);
       System.out.println(where);
         m.appendReplacement(sb, "REPLACED");
     }
     System.out.println(sb.toString());

  }

}
