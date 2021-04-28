
lazy val feedbankCore    = "com.imggaming" %% "feedbank-core"    % Version.FeedbankVersion
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
  scalaOptions ++= compilerOptions,
  resolvers ++= Resolver.ddeResolvers,
  organization := "com.imggaming"
)

lazy val $feedname;format="camel"$ =
  (project in file("""$feedname;format="norm"$"""))
    .settings(
      commonSettings
    )

lazy val service =
  (project in file("service"))
    .settings(
      name := """dde-feedbank-$sport;format="word-only,lower"$""",
      libraryDependencies ++= Seq(
        feedbankService
      ),
      commonSettings
    )
