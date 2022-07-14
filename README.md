

# ArcUdoc - the desktop application to rock your doc

[TOC]



## Was ist ArcUdoc?

ArcUDoc soll es erleichtern eine strukturierte Architektur-/Software-/Projektdokumentation zu erstellen. Vor allem neuen Team-Mitglieder:innen soll es dadurch leichter fallen, sich in ein neues Projekt einzuarbeiten. Auch die Zusammenarbeit zwischen Designern, Entwicklern und Architekten soll damit vereinfacht werden. Richtlinien, Konzepte und allgemein im Team geltende Vorgehensweisen werden dort festgehalten und leicht zugänglich für alle Teammitglieder. 

### Login

Mithilfe von GitLab kann sich der User hier anmelden. Bei erfolgreicher Anmeldung wird er direkt zur Desktop Application von ArcUdoc weitergeleitet und alle Projekte erscheinen, wo er Mitglied ist. (weitere Informationen zum Login sind in der Dokumentation in Kapitel 8 "Querschnittliche Konzepte" unter [8.2 "User Experience"](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/08_Querschnittliche-Konzepte/User-Experience.md) zu finden.)

### Workspace 

Hier werden alle Projekte angezeigt, mit zugehöriger Rolle und Job - Label des Users. Dazugehörig, wann das letzte mal dieses Projekt bearbeitet wurde. Ebenfalls wird der User hier informiert, wenn er eine neue Einladung erhalten hat. Diese kann er dann annehmen und hat dann Zugriff auf die Dokumentation des Projektes. Das anlegen von Projekten und einladen von Mitgliedern ist über "Projekt anlegen" möglich. Dargestellt werden diese dann nach dem Tap auf den "refresh"-Button.

### Projekt Dokumentation

Keine Ahnung wie eine Dokumentation strukturiert werden soll? Durch das von ArcUdoc vorkonfigurierte arc42 Template wird der User und sein Team an die Hand genommen step-by-step die Software Dokumentation in Angriff zu nehmen. Bei jedem Kapitel wird durch den default Text nochmals erläutert, welche Inhalte in diesem Kapitel beschrieben werden. Eingefügt wird dieser Content über Markdown im ArcUdoc-eigenen Editor. Gespeichert werden diese Inhalte bei verlassen des Editors.


## Installation und Verwendung

Aktuell gibt es aufgrund von Problemen mit den Java 9 Modulen kein .jar- file. Daher ist die Applikation nur über IntelliJ nutzbar. Die Main Methode ist im Modul `de.hdm_stuttgart.ui` im root Package zu finden. Zur Nutzung der Anwendung ist ein GitLab-Account obligatorisch. (**gitlab.com** nicht gitlab.mi.hdm-stuttgart.de). Der Login über Google ist derzeit noch nicht implementiert.


## Dokumentation
Zu finden ist unsere Software- Dokumentation auf GitLab unter [arc](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/tree/main/arc). Zur Dokumentation wurde das Softwarearchitekturdokumentations-Template [arc42](https://www.arc42.de/overview/) verwendet. Nicht relevante Kapitel des Templates wurden dabei entfernt. 


## Technologien

Nähere Erläuterungen zu den verwendeten Technologien in diesem Projekt sind [hier](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/04_L%C3%B6sungsstrategie/L%C3%B6sungsstrategie.md) zu finden. Unter ["Entwurfsentscheidungen"](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/09_Entwurfsentscheidungen/Entwurfsentscheidungen.md) wird näher erläutert, weshalb wir uns für diese Technologien entschieden haben.

## Anmerkungen in Bezug auf den Bewertungsbogen

### Kategorien

Gewählt haben wir als Kategorien **UI** und **Schnittstellen**. 

Zudem haben wir durch die Verwendung von Supabase "Persistenz" und "Threading". Es wurde ein komplexes Datenbankmodell, sowie Datenbank-Functions, Trigger und Zugriffskontrolle über RLS implementiert. Dokumentation dafür ist in Kapitel 5 "Bausteinsicht" unter [5.1 "Backend"](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/05_Bausteinsicht/Backend.md) zu finden. Gerne gewähren wir auch Zugriff auf Supabase damit dies mit in die Projekt-Bewertung einfließen kann. 
Der Zugriff auf Supabase erfolgt über asynchrone REST Api calls (background thread) mittels Retrofit.

### Anforderungsanalyse/Projektdokumentation

Wie bereits im Abschnitt "Dokumentation" erwähnt, wurde zur Dokumentation des Projektes das arc42 template verwendet. Die Anfoderungsanalyse ist in [Kapitel 1 "Einführung und Ziele"](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/tree/main/arc/01_Einf%C3%BChrung-und-Ziele) zu finden. 

### Testing

Die Module stehen unabhängig voneinander und werden nur im UI-Modul vereint. Deshalb wurden die Module getrennt voneinander durch Unit-Tests getestet. Auf Integrationstests wurde aus obigen Grund verzichtet. Konsistentes Testen gegen das Backend gestaltet sich aufgrund der strengen Zugriffskontrolle ebenfalls schwierig. Zudem handelt es sich um sehr user spezifische sowie dynamsiche Daten, weshalb wir im Rahmen des SE3-Projektes das Einbeziehen des Backends in die Tests als wenig sinnvoll erachtet haben. 

### Schnittstellen

Fast allle Schnittstellen laufen asynchron um langlaufende Prozesse in den Hintergrund zu verlegen. Die einzige Schnittstelle welche synchron läuft ist im `de.hdm_stuttgart.api`-Modul im `Profilerespository`zu finden. Da die ganze Anwendung auf der Verfügbarkeit des Nutzerdaten basiert, ist diese Schnittstelle synchron. Dies ist auch mittels Javadoc im Code dokumentiert. 

### Softwarearchitektur

Wir haben uns sehr intensiv mit der Architektur des Projektes auseinandergesetzt. Ziel war es ein sehr modulares System zu entwerfen, welches durch lose Kopplung zwischen den Bausteinen offen für Erweiterung ist. Weitere Informationen zur Architektur und Modulaufbau sind [hier](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/05_Bausteinsicht/Bausteinsicht.md) zu finden.


## Known Issues / Project Status

Wie unter [01.1 Einführungen und Ziele](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/01_Einf%C3%BChrung-und-Ziele/Aufgabenstellung.md) beschrieben ist das Projekt noch nicht vollständig in seiner Funktion implementiert. Trotzdem ist unser definierter MVP erreicht. 

Bekannte Issues sind:

- Login über GitLab funktioniert nur über den Browser "Firefox" (weitere Informationen dazu [hier](https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/blob/main/arc/08_Querschnittliche-Konzepte/User-Experience.md))
- Login über Google nicht implementiert
- Offline Nutzung nicht möglich
- Löschen von Projekten sind aktuell nicht für den User über das UI möglich


## Reflexion
Im Folgenden sind stichpunktartig Anmerkungen zusammengefasst, was wir nächstes Mal anders angehen würden:
- Zeitplan manifestieren
- nur ein .css file für schnellere Änderungen
- Phase zur Ansteckung des Scopes zeitlich kürzer halten (sich nicht in der Konzeption der kleinen Features verlieren)
- Die Verwendung von Java 9 Modulen bringt einige Schwierigkeiten, vor allem in Verbindung mit Maven, mit sich. Zudem gibt es wenige zuverlässige und gute Quellen zu den Java 9 Modulen. Eine erneute Verwendung der Module sollte also genau überlegt werden auch wenn diese zu einer sehr sauberen Projektstruktur und Architektur verholfen haben.


## Authors
Pia Schilling (ps149), Vivien Volpert (vv014), Paul Sendelbach (ps152), Sara Tietze (st093)



