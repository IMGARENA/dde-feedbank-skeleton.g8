
lazy val feedbankCore    = "com.imggaming" %% "feedbank-core"    % Version.FeedbankVersion
lazy val feedbankAdmin   = "com.imggaming" %% "feedbank-admin"   % Version.FeedbankVersion
lazy val feedbankService = "com.imggaming" %% "feedbank-service" % Version.FeedbankVersion

lazy val commonSettings = Seq(
  scalaVersion := "2.13.5",
  resolvers ++= Resolver.ddeResolvers
)

lazy val $feedname;format="camel"$ =
  (project in file("""$feedname;format="norm"$"""))
    .settings(
      commonSettings
    )

lazy val service =
  (project in file("service"))
    .settings(
      name := """dde-feedbank-$name;format="word-only,lower"$""",
      libraryDependencies ++= Seq(
        feedbankService
      ),
      commonSettings
    )
