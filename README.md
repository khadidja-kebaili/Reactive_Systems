![Build Status](https://img.shields.io/github/actions/workflow/status/khadidja-kebaili/Reactive_Systems/scalaWithCoveralls.yml)
![Test Coverage from Coveralls](https://img.shields.io/coverallsCoverage/github/khadidja-kebaili/Reactive_Systems)

# Airport Data

Diese Applikation ermöglicht das einsehen von interessanten Informationen von Flughäfen in Deutschland.

## Features

Alle bisher implementierten Features:

1. ...

## Build / run

```bash
# run tests
sbt coverage test

# get test coverage (run tests before report)
sbt coverageReport

# run applications -> have to choose which one
sbt run

# clean up genereated files
sbt clean
```

## Spark installieren und configurieren

Um Spark nutzen zu können, müssen folgende Dinge sichergesellt werden:

- Create the following folders: 'C:\spark' 'C:\hadoop\bin'
- Download Spark
  - https://spark.apache.org/downloads.html
  - Version: 3.5.0
  - Package type: Pre-built for Apache Hadoop 3.3 and later
  - Extract into 'C:\spark'<br/><br/>

- Download Hadoop
  - https://github.com/kontext-tech/winutils/tree/master/hadoop-3.3.0/bin
  - Download file "winutils.exe"
  - Save to 'C:\hadoop\bin'<br/><br/>

- Download Java 17 (if not installed)
  - https://www.oracle.com/de/java/technologies/downloads/
  - Download and execute the MSI-Installer<br/><br/>

- Systemvariablen setzen
  - Gehe zum Fenster Umgebungsvariablen und füge bei den Benutzervariablen folgendes hinzu:
    - SPARK_HOME mit Wert C:\spark
    - HADOOP_HOME mit Wert C:\hadoop
    - JAVA_HOME mit Wert C:\Program Files\Java\jdk-17
  - Bearbeite dann die PATH Variable und Füge folgendes hinzu:
    - %JAVA_HOME%\bin
    - %SPARK_HOME%\bin
    - %HADOOP_HOME%\bin <br/><br/> 

- Configure Project 
  - Goto File -> Project Structure -> Project -> SDK -> 17
  - Goto Edit Configuration -> Edit configuration templates -> Select Application -> Modify options -> Add VM options -> Einfügen: --add-exports java.base/sun.nio.ch=ALL-UNNAMED -> Save/Apply<br/>
Lösche dann alle existierenden Run Configurations, sodass beim nächsten mal die Option automatisch gesetzt wird.