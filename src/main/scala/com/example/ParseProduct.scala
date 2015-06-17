package com.example

import scala.io.Source
import java.nio.file.Files
import java.nio.file.Paths
import scala.collection.mutable
import org.apache.commons.lang3.StringUtils

object ParseProduct {
  
  def pchome(src: Array[String]) { }
  
  
  private val yahooImg = """https://tw.m.yimg.com/res/gdsale/st_pic/[\d]+/st\-([\d]+)\-s100\.jpg""".r
  
  private val yahooURL = "https://tw.buy.yahoo.com/gdsale"
  
  private val yahooReplace = (Array("[", "]", "(", ")", "（", "）", " ", "\"", "【", "】"), Array("", "-", "", "", "", "", "-", "", "", ""))
     
  def yahoo(src: Array[String]) {
    val name = src(1).substring(1)
    val img = src(7)
    
    src(7) = img.substring(1, img.length() - 1) match {
      case yahooImg(id) =>
        val urlName = StringUtils.replaceEach(name, yahooReplace._1, yahooReplace._2)
        "\"" + s"${yahooURL}/${urlName}-${id}.html" + "\"" 
        
      case _ => """"-""""
    } 
  }
  
  
  def main(args: Array[String]) {
    val bom = '\ufeff'
    
    val ec = "yahoostore"
    val src = s"/Users/kigi/${ec}.csv"
    val temp = s"/Users/kigi/${ec}-temp.csv"
    val dest = s"/Users/kigi/${ec}"
      
    val content = new StringBuffer()
    
    val set = mutable.HashSet.empty[String]
    
    val file = Source.fromFile(src, "UTF-8")
    
    
    file.getLines() foreach { line =>
      val tmp = line.replace("""\",""", "＂,").replace("""\N""", """"-"""").split("\",")

      if (!set.contains(tmp(0))) {
        //yahoo(tmp)
        content.append(tmp.mkString("\",")).append("\n")
        set += tmp(0)
      }
      
    }
    
    file.close()
    Files.write(Paths.get(temp), ('\ufeff' + content.toString()).getBytes("UTF-8"))
    
    
    
    val header = bom + """"編碼","品名","價格","第一層目錄","第二層目錄","第三層目錄","第四層目錄","商品連結"""" + "\n"
    
    val file2 = Source.fromFile(temp, "UTF-8")
    
    
    val lines = file2.getLines().toSeq
    
    val groups = lines.groupBy { line =>
      val tmp = line.split("\",")
      tmp(3)
    }
    
    groups foreach { elm =>
      val fileName = elm._1.substring(1).replace(java.io.File.separatorChar, '-')
      println(fileName, elm._2.size)
      
      Files.write(Paths.get(s"${dest}-${fileName}.csv"), (header + elm._2.mkString("\n")).getBytes("UTF-8"))
    }
    
    file2.close
    
    println("End")
  }
}