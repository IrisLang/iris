import Dependencies._

ThisBuild / scalaVersion := "3.1.3"
ThisBuild / version      := "0.0.0"

lazy val root = (project in file("."))
	.enablePlugins(BuildInfoPlugin)
	.settings(
		assembly / assemblyOutputPath := file(s"./build/iris-${version.value}.jar"),
		buildInfoKeys := Seq[BuildInfoKey](version),
		buildInfoPackage := "iris",
		name := "iris",
		libraryDependencies += scalaTest % Test
	)
