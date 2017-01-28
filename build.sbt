name := "FelisCatusZero"

version := "0.0.1"

scalaVersion := "2.12.1"

val javaVersion = "1.8"

fork in run := true

scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8", // yes, this is 2 args
  "-feature",
  "-language:implicitConversions",
  "-unchecked",
  "-target:jvm-".concat(javaVersion)
)

javaOptions in (Test,run) ++= Seq(
  "-Xms2g",
  "-Xmx4g",
  "-Xss2g",
  "-source", javaVersion,
  "-target", javaVersion
)

resolvers ++= Seq(
  "Sonatype OSS Releases"  at "http://oss.sonatype.org/content/repositories/releases/",
  "spotlight-releases-repository" at "https://github.com/dbpedia-spotlight/maven-repo/raw/master/releases"
)

//Add Apache UIMA Framework
libraryDependencies ++= {
  val uimaGroupId = "org.apache.uima"
  val uimaVersion = "2.8.1"
  val uimaFitVersion = "2.1.0"
  Seq(
    uimaGroupId % "uimaj-core"                % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-bootstrap"           % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-tools"               % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-examples"            % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-document-annotation" % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-cpe"                 % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-adapter-vinci"       % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimaj-adapter-soap"        % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "jVinci"                    % uimaVersion,// withSources() withJavadoc(),
    uimaGroupId % "uimafit-core"           % uimaFitVersion// withSources() withJavadoc()
  )
}

//Add Configuration Framework
libraryDependencies ++= Seq(
  "commons-cli" % "commons-cli" % "1.3.1",// withSources() withJavadoc(),
  "args4j" % "args4j" % "2.32",// withSources() withJavadoc(),
  "com.typesafe" % "config" % "1.3.0",// withSources() withJavadoc(),
  "com.iheart" %% "ficus" % "1.4.0"// withSources() withJavadoc()
)

//Add Unit Test Framework
libraryDependencies ++= Seq(
  "junit" % "junit" % "4.12" % "test",// withSources() withJavadoc(),
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",// withSources() withJavadoc(),
  "org.hamcrest" % "hamcrest-all" % "1.3" % "test"// withSources() withJavadoc()
)

//Add Template Framework
libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-xml" % "1.0.5",// withSources() withJavadoc(),
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4",// withSources() withJavadoc(),
  "org.jsoup" % "jsoup" % "1.8.2",// withSources() withJavadoc(),
  "info.bliki.wiki" % "bliki-core" % "3.0.19"// withSources() withJavadoc()
)