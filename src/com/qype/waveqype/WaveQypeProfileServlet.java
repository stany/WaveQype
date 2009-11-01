package com.qype.waveqype;

import com.google.wave.api.ProfileServlet;

@SuppressWarnings("serial")

public class WaveQypeProfileServlet extends ProfileServlet{
  //Avatar
  @Override
  public String getRobotAvatarUrl() {
    return "http://waveqype.appspot.com/imgs/qype_logo.jpg";
  }

  //Name
  @Override
  public String getRobotName() {
    return "WaveQype";
  }

  //URL
  @Override
  public String getRobotProfilePageUrl() {
    return "http://waveqype.appspot.com";
  }

}
