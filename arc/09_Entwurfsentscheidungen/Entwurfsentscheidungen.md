# 9.0 Entwurfsentscheidungen 

[TOC]

-----

## Feature support

Es muss entschieden werden, welche Features unterstützt werden sollen und wie diese implementiert werden. Im folgenden werden verschiedene Features, mögliche Umsetzungsarten und deren Vor- und Nachteile, vorgestellt. 

------

### Versionierung (Feature wurde nicht implementiert)

Die Versionierung/ Version control der im Tool erstellten Dateien ist ein wünschenswertes/ wichtiges Feature. 

Zwei mögliche Ziele der Versionierung

- Versionierung um versehentliche Änderungen rückgängig machen zu können
  - es sind nur die letzten 5 Stände abrufbar um Speicher zu sparen (alte Änderungen werden dann überschrieben)
- Versionierung um den Verlauf/die Entwicklung von Dateien verfolgen zu können 
  - nimmt viel/mehr Speicher in Angriff

Arten der Implementierung:

#### 1. [JGit](https://git-scm.com/book/de/v2/Anhang-B%3A-Git-in-Ihre-Anwendungen-einbetten-JGit) (Wurde verworfen, macht keinen Sinn im Zusammenhang mit dem Backend/Supabase)

> Wenn Sie Git aus einem Java-Programm heraus verwenden möchten, gibt es  eine voll funktionsfähige Git-Bibliothek mit der Bezeichnung JGit.

#### 2. Versionierung ohne git 

Datei nicht überschreiben sondern Kopieren, alte Versionen bleiben damit erhalten -> Speicherverschwendung aber einfach zu implementieren 

------

### Markdown support

Es gibt verschiedene Möglichkeiten Markdown support zu implementieren

#### 1. [commonmark-java](https://github.com/commonmark/commonmark-java)

Java library for parsing and rendering Markdown text according to the CommonMark specification.

| pros            | cons                                |
| --------------- | ----------------------------------- |
| works offline   | needs to be implemented in the code |
| well documented | check license                       |

#### 2. [GitLab Markdown API](https://docs.gitlab.com/ee/api/markdown.html) (verwendet)

Render arbitrary Markdown documents via api calls 

| pros                                          | cons                                      |
| --------------------------------------------- | ----------------------------------------- |
| easy to use/ no implementation in code needed | works only while connected to the network |

------

### UML support (Feature wurde nicht implementiert)

Es gibt Möglichkeiten externe UML Tools einzubetten. Es sollte geprüft werden wie aufwendig und ob es überhaupt notwendig ist. 

#### 1. [draw.io / diagramms.net](https://drawio-app.com/integrations-ecosystem/)

> With the source code available on Github, you (anyone!) can develop your own integrations. Right now, we have posted few examples in [JGraph’s draw.io open source repository on GitHub](https://github.com/jgraph) including WebDav, HTML5, and GitHub that developers can use to create their own integrations. Many integrations already exist!

#### 2. [plantuml](https://plantuml.com/de/api)

Integrate PlantUML with own code by adding plantuml.jar in the classpath.

------

## UI Frameworks

Die Wahl des UI Frameworks hängt davon ab, ob wir bereit sind Zeit in das Lernen neuer Technologien zu investieren. 

#### 1. [JavaFx](https://openjfx.io/) (verwendet)

| pros                                     | cons                                       |
| ---------------------------------------- | ------------------------------------------ |
| we are familiar                          | its a pain to implement own components     |
| graphical ui building with scene builder | styling via css                            |
| needs no/not much time to figure it out  | xml                                        |
|                                          | no big learning effect/ we already know it |

------

#### 2. [Compose UI/ Compose Desktop](https://www.jetbrains.com/de-de/lp/compose-mpp/) 

| pros                                                         | cons                            |
| ------------------------------------------------------------ | ------------------------------- |
| very easy declarative ui building                            | mix kotlin and java             |
| easy to create own components                                | we are not familiar with kotlin |
| easy to style                                                | needs time to figure out        |
| multiplattform                                               |                                 |
| great learning effect/ good to know for upcoming app projects |                                 |

------

## Backend 

#### 1. Firebase

#### 2. Supabase (verwendet)

Supabase ist eine open source Alternative zu Firebase. Es handelt sich um eine Postgres Datenbank. REST Api-Endpoint werden automatisch generiert. Zudem gibt es verschiedene Möglichkeiten zur Userauthentifizierung. 
Durch sogenannte Row Level Security Policies können die Zugriffsrechte auf Tabellen einfach gesteuert werden. 

Eine Ausführliche Dokumentation des Datenbankschemas sowie Datenbank-Functions und RLS ist im Kapitel 5 "Bausteinsicht" unter [5.1 "Backend"](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/05_Bausteinsicht/Backend.md) zu finden. 

## Authentifizierung 

#### 1. OAuth (verwendet)

Um nicht selbst für Passwörter und Userdaten verantwortlich zu sein, bietet es sich an, Authentifizierung über OAuth zu implementieren. Supabase bietet die Möglichkeit dies für die verschiedensten OAuth Provider einfach umzusetzen. Eine detaillierte Dokumentation des Auth-Flows ist im Kapitel 8 "Querschnittliche Konzepte" im Abschnitt [8.1 "User Experience"](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/08_Querschnittliche-Konzepte/User-Experience.md) zu finden. 

