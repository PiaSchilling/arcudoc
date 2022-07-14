# 4.1 Lösungsstrategie

[TOC]




## Backend
→ [Supabase](https://supabase.com/docs)

Für das Backend verwenden wir Supabase. Hiermit wird unsere Datenbank verwaltet und die Authentication geregelt.

## Authentifizierung
→ [OAuth](https://supabase.com/docs/guides/auth/auth-gitlab)

## Markdown Support
→ [GitLab Markdown API](https://docs.gitlab.com/ee/api/markdown.html)

Um mit Markdown in ArcUdoc die Inhalte der Dokumentation einfügen zu können, haben wir uns für die GitLab Markdown App entschieden.


## UI Frameworks
→ [JavaFx](https://openjfx.io/)

Zudem haben wir für das UI Java FX verwendet, um im Rahmen der Möglichkeiten ein ansprechendes und benutzerfreundliches UI dem User bereitzustellen. 

## Strukturierung
Aus Interesse und in Hinblick der Modularität wurden die Java 9 Module verwendet. Dies stellt uns die Möglichkeit bereit, den Code klar zu strukturieren und sicherte uns eine reibungslose Zusammenarbeit. 

## Dependency Injection

Um lose Kopplung zwischen Klassen sowie Modulen zu erreichen wurde Dependency Injection eingesetzt. Implementiert wurde dies mittels dem DI Framework [Google Guice](https://github.com/google/guice). 

## API Communication

API calls wurden mittels dem HTTP-Client [Retrofit](https://square.github.io/retrofit/) implementiert. 

## Testing

Unit-Tests wurden mithilfe von [JUnit](https://junit.org/junit5/) implementiert. 

Für das Mocken der Repositories (um die Logik unabhängig vom Backend testen zu können) wurde [Mockito](https://site.mockito.org/) eingesetzt. 

## Logging

Logging wurde mittels [Log4j](https://logging.apache.org/log4j/2.x/) implementiert. 

