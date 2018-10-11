name := "S3"

organization := "hu.zoltankollar"

version := "1.0"

scalaVersion := "2.12.4"

scalacOptions ++= Seq(
  "-language:higherKinds",
  "-feature",
)

libraryDependencies ++= Seq(
  "org.apache.logging.log4j" % "log4j-core" % "2.8.2",
  "org.apache.logging.log4j" % "log4j-api" % "2.8.2",
  "com.amazonaws" % "aws-java-sdk-s3" % "1.11.424",
  "com.typesafe" % "config" % "1.3.3",
  "com.google.inject" % "guice" % "4.2.0",
  "hu.zoltankollar" %% "functional" % "1.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalamock" %% "scalamock" % "4.1.0" % Test,
)
