import sbt.librarymanagement.DependencyBuilders

object Resolver extends DependencyBuilders {

  val s3Prefix = "dde-artifacts/jars"

  val ddeResolvers = Seq(
    "DDE snapshots" at s"s3://s3-eu-west-1.amazonaws.com/\$s3Prefix/snapshots",
    "DDE releases" at s"s3://s3-eu-west-1.amazonaws.com/\$s3Prefix/releases"
  )
}
