package com.example

import org.apache.commons.lang3.StringUtils

object Hello {
  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    
    
    val img = """https://tw.m.yimg.com/res/gdsale/st_pic/[\d]+/st\-([\d]+)\-s100\.jpg""".r
    
    println(img.findFirstMatchIn("""https://tw.m.yimg.com/res/gdsale/st_pic/5487/st-5487515-s100.jpg""").get)
     
     
    val name = """[快]Goodyear固特異高壓氣旋式車用吸塵器"""
    
    println(StringUtils.replaceEach(name, Array("[", "]", " "), Array("", "-", "-")))
  }
}
