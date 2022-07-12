

# ArcUdoc - the desktop application to rock your doc

## Was ist ArcUdoc?

ArcUDoc soll es erleichtern eine strukturierte Architektur-/Software-/Projektdokumentation zu erstellen. Vor allem neuen Team-Mitglieder:innen soll es dadurch leichter fallen, sich in ein neues Projekt einzuarbeiten. Auch die Zusammenarbeit zwischen Designern, Entwicklern und Architekten soll damit vereinfacht werden. Richtlinien, Konzepte und allgemein im Team geltende Vorgehensweisen werden dort festgehalten und leicht zugänglich für alle Teammitglieder. 

### Login

Mithilfe von GitLab kann sich der User hier anmelden. Bei erfolgreicher Anmeldung wird er direkt zur Desktop Application von ArcUdoc weitergeleitet und alle Projekte erscheinen, wo er Mitglied ist.

### Workspace 

Hier werden alle Projekte angezeigt, mit zugehöriger Rolle und Job - Label des Users. Dazugehörig, wann das letzte mal dieses Projekt bearbeitet wurde. Ebenfalls wird der User hier informiert, wenn er eine neue Einladung erhalten hat. Diese kann er dann annehmen und hat dann Zugriff auf die Dokumentation des Projektes. Das anlegen von Projekten und einladen von Mitgliedern ist über "Projekt anlegen" möglich. Dargestellt werden diese dann nach dem Tap auf den "refresh"-Button.

### Projekt Dokumentation

Keine Ahnung wie eine Dokumentation strukturiert werden soll? Durch das von ArcUdoc vorkonfigurierte arc42 Template wird der User und sein Team an die Hand genommen step-by-step die Software Dokumentation in Angriff zu nehmen. Bei jedem Kapitel wird durch den default Text nochmals erläutert, welche Inhalte in diesem Kapitel beschrieben werden. Eingefügt wird dieser Content über Markdown im ArcUdoc-eigenen Editor. Gespeichert werden diese Inhalte bei verlassen des Editors.


## Installation

Aktuell gibt es aufgrund von Issues mit den Java 9 Modulen kein .jar- file. Daher ist die Applikation nur über IntelliJ nutzbar.


## Dokumentation
Zu finden ist unsere Software- Dokumentation auf GitLab unter "arc".


## Technologien

Nähere Erläuterungen zu den verwendeten Technologien in diesem Projekt sind [hier ](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/09_Entwurfsentscheidungen/Entwurfsentscheidungen.md) zu finden. 


## Anmerkungen in Bezug auf den Bewertungsbogen

Gewählt haben wir als Kategorien UI und Schnittstellen. 
Zudem haben wir durch die Verwendung von Supabase "Persistenz" und "Threading". Zudem ist CI/CD trotz Unterstützung der Tutorin für uns nicht möglich, dadurch dass man Java 9 Module verwendet und die Module nicht erkannt werden. Die Integrationstests gestalteten sich auch schwierig zu implementieren, da man es vermeiden wollte den Access Token hart zu coden.


## Known Issues / Project Status

Wie unter [01.1 Einführungen und Ziele](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/01_Einf%C3%BChrung-und-Ziele/Aufgabenstellung.md) beschrieben ist das Projekt noch nicht vollständig in seiner Funktion implementiert. Trotzdem ist unser definierter MVP erreicht. 
Bekannte Issues sind:
- Login über GitLab funktioniert nur über den Browser "Firefox"
- offline use nicht möglich
- Löschen von Projekten sind aktuell nicht für den User über das UI möglich
- wie erwähnt CI/CD nicht möglich


## Reflexion
Im Folgenden sind stichpunktartig Anmerkungen zusammengefasst, was wir nächstes Mal anders angehen würden:
- Zeitplan manifestieren
- nur ein .css file für schnellere Änderungen
- Phase zur Ansteckung des Scopes zeitlich kürzer halten (sich nicht in der Konzeption der kleinen Features verlieren)


## Authors
Pia Schilling (ps149), Vivien Volpert (vv014), Paul Sendelbach (ps152), Sara Tietze (st093)




