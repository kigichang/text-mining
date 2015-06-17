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
    
    /*
    val header = bom + """"編碼","品名","價格","第一層目錄","第二層目錄","第三層目錄"""" + "\n"
    
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
    
      
    val storeFile = Source.fromFile("/Users/kigi/store.csv", "UTF-8")
    
    val storeSeq = storeFile.getLines().toSeq.drop(1)
    
    val stores = storeSeq.map { line => 
      val tmp = line.split(',')
      
      if (tmp.length != 3)
        println(line)
        
      (tmp(1), tmp(2).substring(1, tmp(2).length() - 1))
    }.toMap
    
    //for ((k, v) <- stores) {
    //  println(k, v)
    //}
    
    storeFile.close
    
    val file3 = Source.fromFile("/Users/kigi/gohappy.csv", "UTF-8")
    val header3 = bom + """"商品編號","促銷小標","商品名稱","品牌","廠商建議價","網路價","促銷價","目前價格","全點數兌換","點+金的金額","點+金的點數","小圖","促銷開始日期","促銷結束日期","是否在促銷","館名稱","分身目錄","作者","出版社","ISBN","出版日期","寄送方式""""

    
    val lines = file3.getLines().toSeq.drop(1)
    
    val groups = lines groupBy { line =>
      val tmp = line.split("\",")
      if (tmp.length != 22)
        println(tmp.length, line)
      
      
      val pos = tmp(15).indexOf(":>")
      
      val storeName = if (pos > 0) {
        val sid = tmp(15).substring(2, pos)
        stores.get(sid).getOrElse("Unknow2")
      }
      else "查無分類"
      
      //if (storeName == "Unknow2") println(storeName, line)
      storeName
    }
    
    
    for ((k, v) <- groups) {
      val fileName = k.replace(java.io.File.separatorChar, '-')
      //println(fileName, v.mkString("\n"))
      val cons = v map { line =>
        val tmp = line.split("\",")
        tmp(15) = "\"" + k
        tmp.mkString("\",") 
      }
      
      Files.write(Paths.get(s"/Users/kigi/gohappy2-${fileName}.csv"), (header3 + "\n" + cons.mkString("\n")).getBytes("UTF-8"))
    }
    
    
    file3.close
    
    
    println("End")
  }
}