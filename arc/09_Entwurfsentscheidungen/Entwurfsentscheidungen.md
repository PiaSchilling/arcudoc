# Entwurfsentscheidungen 

[TOC]

-----

## Feature support

### Markdown support

There are several options to implement markdown support

1. [commonmark-java](https://github.com/commonmark/commonmark-java)

   Java library for parsing and rendering Markdown text according to the CommonMark specification.

   | pros            | cons                                |
   | --------------- | ----------------------------------- |
   | works offline   | needs to be implemented in the code |
   | well documented | check license                       |

   

2. [GitLab Markdown API](https://docs.gitlab.com/ee/api/markdown.html)

   Render arbitrary Markdown documents via api calls 

   | pros                                          | cons                                      |
   | --------------------------------------------- | ----------------------------------------- |
   | easy to use/ no implementation in code needed | works only while connected to the network |

------

### UML support

There are possibilities to implement UML support. Check how complicated it is or if it's needed at all. 

1. [draw.io / diagramms.net](https://drawio-app.com/integrations-ecosystem/)

> With the source code available on Github, you (anyone!) can develop your own integrations. Right now, we have posted few examples in [JGraph’s draw.io open source repository on GitHub](https://github.com/jgraph) including WebDav, HTML5, and GitHub that developers can use to create their own integrations. Many integrations already exist!

2. [plantuml](https://plantuml.com/de/api)

   Integrate PlantUML with own code by adding plantuml.jar in the classpath.

-----

## UI Frameworks

We have to decide if we want to focus on functionality or on learning. Based on that we should choose the ui framework. 

### [JavaFx](https://openjfx.io/)

| pros                                     | cons                                       |
| ---------------------------------------- | ------------------------------------------ |
| we are familiar                          | its a pain to implement own components     |
| graphical ui building with scene builder | styling via css                            |
| needs no/not much time to figure it out  | xml                                        |
|                                          | no big learning effect/ we already know it |



### [Compose UI/ Compose Desktop](https://www.jetbrains.com/de-de/lp/compose-mpp/) 

| pros                                                         | cons                            |
| ------------------------------------------------------------ | ------------------------------- |
| very easy declarative ui building                            | mix kotlin and java             |
| easy to create own components                                | we are not familiar with kotlin |
| easy to style                                                | needs time to figure out        |
| multiplattform                                               |                                 |
| great learning effect/ good to know for upcoming app projects |                                 |

