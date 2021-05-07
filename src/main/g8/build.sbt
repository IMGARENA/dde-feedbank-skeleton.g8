
lazy val feedbankCore    = "com.imggaming" %% "feedbank-core"    % Version.FeedbankVersion % "test->test;compile->compile"
lazy val feedbankAdmin   = "com.imggaming" %% "feedbank-admin"   % Version.FeedbankVersion
lazy val feedbankService = "com.imggaming" %% "feedbank-service" % Version.FeedbankVersion

lazy val compilerOptions = Seq(
  "-unchecked",
  "-deprecation",
  "-feature",
  "-Xlint",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ywarn-dead-code",
  "-Xfatal-warnings"
)

lazy val commonSettings = Seq(
  scalaVersion := "2.13.5",
  scalacOptions ++= compilerOptions,
  resolvers ++= Resolver.ddeResolvers,
  organization := "com.imggaming"
)

lazy val common =
  (project in file("common"))
    .settings(
      commonSettings,
      libraryDependencies ++= Seq(
        feedbankCore
      )
    )

lazy val $feedname;format="camel"$ =
  (project in file("""$feedname;format="word-only,lower"$"""))
    .settings(
      commonSettings,
      libraryDependencies ++= Seq(
        feedbankAdmin,
        feedbankService
      )
    )
    .dependsOn(
      common
    )

lazy val service =
  (project in file("service"))
    .settings(
      name := """dde-feedbank-$sport;format="word-only,lower"$""",
      commonSettings
    )
    .dependsOn(
      $feedname;format="camel"$
    )
