package com.example

import scala.io.Source
import scala.collection.mutable
import org.apache.commons.lang3.StringUtils
import java.nio.file.Files
import java.nio.file.Paths

object ParseMomo {

  
  def main(args: Array[String]) {
    
    val bom = '\ufeff'
    
    /*
    val content = new StringBuffer()
    
    val set = mutable.HashSet.empty[String]
    
    val file = Source.fromFile("/Users/kigi/momo.csv", "UTF-8")
    
    
    file.getLines() foreach { line =>
      val tmp = line.split("\",")
      
      if (!set.contains(tmp(0))) {
        content.append(tmp.mkString("\",")).append("\n")
        set += tmp(0)
      }
      
    }
    
    file.close()
    Files.write(Paths.get("/Users/kigi/momo2.csv"), ('\ufeff' + content.toString()).getBytes("UTF-8"))
    */
    
    /*val header = bom + """"編碼","品名","價格","第一層目錄","第二層目錄","第三層目錄"""" + "\n"
    
    val file2 = Source.fromFile("/Users/kigi/momo2.csv", "UTF-8")
    
    
    val lines = file2.getLines().toSeq
    
    val groups = lines.groupBy { line =>
      val tmp = line.split("\",")
      tmp(3)
    }
    
    groups foreach { elm =>
      val fileName = elm._1.substring(1).replace(java.io.File.separatorChar, '-')
      println(fileName, elm._2.size)
      
      Files.write(Paths.get(s"/Users/kigi/momo2-${fileName}.csv"), (header + elm._2.mkString("\n")).getBytes("UTF-8"))
    }
    
    file2.close
    */
      
    val file3 = Source.fromFile("/Users/kigi/gohappy.csv", "UTF-8")
    val header3 = bom + file3.getLines().take(1).next()
    
    val lines = file3.getLines().toSeq.drop(1)
    
    val groups = lines.groupBy { line =>
      val tmp = line.split(',')
      if (tmp.length < 15)
        println(line)
      //else
      //  println(tmp(15))
    }
    
    
    file3.close
    println("End")
  }
}