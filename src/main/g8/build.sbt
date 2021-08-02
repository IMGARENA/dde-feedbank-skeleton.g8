import com.typesafe.sbt.SbtGit.git

val akkaCoreVersion = "2.6.9"
val akkaHttpVersion = "10.2.0"

val feedbankVersion = "0.7.2"

lazy val commonDeps = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe" % "config" % "1.4.0",
  "com.typesafe.akka" %% "akka-actor" % akkaCoreVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaCoreVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaCoreVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "io.kamon" %% "kamon-prometheus" % "2.2.0",
  "io.spray" %% "spray-json" % "1.3.5",
  "com.imggaming" %% "feedbank-core" % feedbankVersion % "test->test;compile->compile",
  "com.imggaming" %% "dde-service-discovery" % "0.26",
  "com.lightbend.akka" %% "akka-stream-alpakka-amqp" % "2.0.2"
)

// Core test dependencies
lazy val testDeps = Seq(
  "com.typesafe.akka" %% "akka-testkit" % akkaCoreVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.scalatest" %% "scalatest" % "3.2.0" % Test
)

// Dependencies needed only by the service submodule
lazy val serviceDeps = Seq(
  "com.imggaming" %% "feedbank-service" % feedbankVersion,
  "com.imggaming" %% "feedbank-admin" % feedbankVersion,
  "com.imggaming" %% "dde-tcp-common" % "0.12"
)

// Combined prod + test deps for the common submodule
lazy val allCommonDeps = commonDeps ++ testDeps

// CI should use fatal warnings, but if you want to turn that off locally to avoid warnings being
// promoted to errors while you're still getting your code compiling, you can set this env var
lazy val useFatalWarnings = !sys.env.get("DDE_NO_FATAL_WARNINGS").contains("1")
lazy val fatalWarnings = if (useFatalWarnings) Some("-Xfatal-warnings") else None

// Easy tweaking of scale factor because our build might run on a very slow CI box
// More info on scale factor at https://www.scalatest.org/user_guide/using_scalatest_with_sbt
lazy val scalatestScaleFactor = sys.env.getOrElse("SCALATEST_SCALE_FACTOR", "1.0")

lazy val repoPrefix = "s3://s3-eu-west-1.amazonaws.com/dde-artifacts/jars"
lazy val snapshotRepo = "DDE snapshots" at s"\$repoPrefix/snapshots"
lazy val releaseRepo = "DDE releases" at s"\$repoPrefix/releases"

lazy val compilerOptions = Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ywarn-dead-code"
) ++ fatalWarnings

lazy val sharedSettings = Seq(
  scalacOptions ++= compilerOptions,
  organization := "com.imggaming",
  version := "0.0.1-SNAPSHOT",
  versionScheme := Some("early-semver"),
  scalaVersion := "2.13.6",
  testOptions ++= Seq(
    Tests.Setup { cl =>
      // Workaround to avoid slf4j complaining about multi-threading during initialisation
      // Gets a root logger at the start of test runs to force it to finish initialising first
      cl.loadClass("org.slf4j.LoggerFactory")
        .getMethod("getLogger", cl.loadClass("java.lang.String"))
        .invoke(null, "ROOT")
    },
    Tests.Argument(TestFrameworks.ScalaTest, "-F", scalatestScaleFactor)
  ),
  Test / publishArtifact := true,
  // Some of our dependencies still have commons-logging, but we have jcl-over-slf4j from other
  // dependencies, which is a drop-in replacement as should be taken instead
  excludeDependencies += "commons-logging" % "commons-logging",
  // FIXME: Add coverage and then enable this
  //  coverageFailOnMinimum := true,
  coverageMinimumStmtTotal := 85,
  resolvers ++= Seq(snapshotRepo, releaseRepo),
  publishTo := Some(if (isSnapshot.value) snapshotRepo else releaseRepo),
  autoAPIMappings := true
)

lazy val common = {
  (project in file("common"))
    .settings(
      libraryDependencies ++= allCommonDeps,
      sharedSettings
    )
}

lazy val $feedname;format="camel"$ = {
  (project in file("$feedname;format="word,lower"$"))
    .settings(
      name := "dde-feedbank-$sport;format="word,lower"$-$feedname;format="word,lower"$",
      sharedSettings
    )
    .dependsOn(common % "test->test;compile->compile")
}

lazy val service = {
  (project in file("service"))
    .settings(
      name := "dde-feedbank-$sport;format="word,lower"$-service",
      sharedSettings,
      libraryDependencies ++= serviceDeps
    )
    .dependsOn(common % "test->test;compile->compile", $feedname;format="camel"$)
}

lazy val root = {
  (project in file("."))
    .aggregate(common, $feedname;format="camel"$, service)
    .settings(publish := {})
}
