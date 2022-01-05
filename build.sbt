name := "fsbt-adts"

version := "0.0.001"

scalaVersion := "2.13.7"

crossScalaVersions := Seq("2.12.15", "2.13.7")

libraryDependencies ++= Seq(
  "com.logicovercode" %% "docker-definitions" % "0.0.004",
  "org.scalatest" %% "scalatest" % "3.2.10" % Test
)

val projectSourceDirs = List("core")
Compile / unmanagedSourceDirectories ++= projectSourceDirs.map(dir => (Compile / baseDirectory).value / dir)

organization := "com.logicovercode"

val techLead = Developer(
  "techLead",
  "techLead",
  "techlead@logicovercode.com",
  url("https://github.com/logicovercode")
)
developers := List(techLead)

homepage := Some(
  url("https://github.com/logicovercode/fsbt-adts")
)
scmInfo := Some(
  ScmInfo(
    url("https://github.com/logicovercode/fsbt-adts"),
    "git@github.com:logicovercode/fsbt-adts.git"
  )
)

licenses += ("MIT", url("https://opensource.org/licenses/MIT"))

//publishing related settings

//crossPaths := false
//publishMavenStyle := true
publishTo := Some(Opts.resolver.sonatypeStaging)

//below is not yet working as expected (exploring ...)
publishConfiguration := publishConfiguration.value.withOverwrite(true)
publishLocalConfiguration := publishLocalConfiguration.value.withOverwrite(true)

val fSbtAdtsProject = project in file(".")
