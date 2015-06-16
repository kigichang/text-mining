name := """text-mining"""

version := "1.0"

scalaVersion := "2.11.6"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"

libraryDependencies += "com.chenlb.mmseg4j" % "mmseg4j-core-with-dic" % "1.8.6"

libraryDependencies += "org.jsoup" % "jsoup" % "1.8.2"

libraryDependencies += ("com.kennycason" % "kumo" % "1.1").exclude("org.languagetool", "language-en")