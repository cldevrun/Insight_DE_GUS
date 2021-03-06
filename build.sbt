name := "insight"

version := "1.0"

scalaVersion := "2.11.8"

val sparkVersion = "2.2.1"

resolvers ++= Seq(
  "apache-snapshots" at "http://repository.apache.org/snapshots/"
)

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "com.typesafe.play" %% "play-json" % "2.6.7",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.postgresql" % "postgresql" % "9.4.1208"
)

// Exclude jars in assembly
assemblyExcludedJars in assembly := {
  val cp = (fullClasspath in assembly).value
  cp filter { f =>
    f.data.getName.contains("spark-core") ||
      f.data.getName.contains("spark-sql")
  }
}

// Dealing with conflicting file paths
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}