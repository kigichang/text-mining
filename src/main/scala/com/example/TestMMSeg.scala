package com.example

import java.awt.Color
import java.io.Reader
import java.io.StringReader
import scala.collection.JavaConversions.seqAsJavaList
import scala.collection.mutable
import com.chenlb.mmseg4j.ComplexSeg
import com.chenlb.mmseg4j.Dictionary
import com.chenlb.mmseg4j.MMSeg
import wordcloud.CollisionMode
import wordcloud.WordCloud
import wordcloud.WordFrequency
import wordcloud.bg.CircleBackground
import wordcloud.font.scale.SqrtFontScalar
import wordcloud.palette.ColorPalette
import wordcloud.PolarWordCloud
import java.io.FileReader

object TestMMSeg {

  System.setProperty("mmseg.dic.path", ".")
  protected lazy val dic = Dictionary.getInstance
  
  def seg = new ComplexSeg(dic)
  
  def segWords(input: Reader): Array[String] = {
    
    val mmseg = new MMSeg(input, seg)
    
    val ret = mutable.ArrayBuffer.empty[String]
    
    var word = mmseg.next()
    
    while (word != null) {
      ret += word.getString
      word = mmseg.next()
    }
    
    ret.toArray
  }
  
  def main(args: Array[String]) {
    /*
     val txt = """印尼籍富家千金女張愛雲，為愛走天涯嫁來台灣後，經濟陷入困境，為撐起家中經濟，在艷陽天下賣麵賺錢養家，還在困境中教導小孩「學會感恩，有餘力就當志工回饋社會」。
45歲的印尼籍華僑張愛雲，父親在當地是有名的建築師，兒時進出都有轎車接送，豪宅中還有傭人幫忙打理家務，「不知道吃苦的感受是什麼」。
張愛雲的外祖父來自台灣屏東，對台灣並不陌生。19年前，在親戚牽線下，認識台籍丈夫，雙方一見鍾情，張就此嫁到台灣。
「我父親當時很反對，但我執意要嫁，生了3個孩子後才知道為三餐奔走的辛苦」，張愛雲的丈夫從事修理沙發業，收入不穩定，為了撐起家中經濟，她到麵攤工作賺錢養家，每天工作12小時，經常累到倒頭就睡。
「還好孩子沒變壞」，張愛雲工作時，3個兒子放學後，會到日月光慈善事業基金會接受課後輔導，一待就是9年。
「我常告訴孩子要學會感恩」，張愛雲的長子今年19歲、老么國中畢業。她常買便當濟助遊民，孩子也很懂事，課後之餘，參加淨山、淨溪活動，也曾到柬埔寨當志工，教貧童寫功課。"""
* */
    
    val file = new FileReader("all_products.csv")
     
     val result = segWords(file)
     
     println(result.mkString("|"))
     
     
     val map = result.groupBy { s =>  s } mapValues { _.size } 
     
     val lst2 = new java.util.ArrayList[WordFrequency]
     
     map.toList foreach { elm => println(elm._1, elm._2); lst2.add(new WordFrequency(elm._1, elm._2)) }
     
     val wordCloud = new PolarWordCloud(600, 600, CollisionMode.PIXEL_PERFECT)
     
     wordCloud.setPadding(2)
     
     wordCloud.setBackground(new CircleBackground(300))
     
     wordCloud.setColorPalette(new ColorPalette(new Color(0xD5CFFA), new Color(0xBBB1FA), new Color(0x9A8CF5), new Color(0x806EF5)))
     
     wordCloud.setFontScalar(new SqrtFontScalar(12, 45))
     
     wordCloud.build(lst2)
     
     wordCloud.writeToFile("circle.png")
     
     file.close
    
  }
}