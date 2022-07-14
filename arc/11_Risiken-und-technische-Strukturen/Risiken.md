# 11.0 Risiken 

## Datensicherheit und Zugriffsteuerung

### Risiko eines unautorisierten Datenzugriffs 

In unserem Programm besteht die Möglichkeit, dass die eingespeisten Daten ggf. vertraulich zu behandeln sind
oder sensible Informationen beinhalten. Durch z.B. eine Modifikation des Sourcecode besteht ggf. für Angreifer die Möglichkeit Zugriff auf die Inhalte eines fremden Projekts zu bekommen. 

### Zugriffssteuerung 

Wir bieten aus oben genannten Gründen in unserem Programm die Möglichkeit über eine Row-Level-Security in unserem SupaBase-Backend, einen Projektzugriff nur bei hinterlegtem bzw. freigegebenem Access Token freizugeben. Durch dieses Vorgehen garantieren wir die Datensicherheit. 


## Datenpersistenz und Offlinenutzung

Durch unsere Backend-Lösung ist die Datenpersistenz nur bei bestehendem Internetzugriff und funktionierenden SupaBase-Servern gewährleistet. Liegen beim Nutzer Probleme mit der Internetverbindung vor oder gibt es Probleme bei Supabase, hat der Nutzer in diesen Fällen keinen Zugriff auf den aktuellen Stand seiner Daten. Zum momentanen Stand unseres Projekts gibt es noch keine lokal gespeicherten Versionen der Inhalte.

## Authentifizierungsprobleme

Die Anmeldung in unserem Programm funktioniert momentan nur über den Firefox-Browser. Wir konnten das Problem nachvollziehen und feststellen, dass alle angestoßenen Prozesse zwar funktionieren, allerdings blockiert der Safari-Browser das Java-Script. Den Grund hierfür konnten wir zum jetzigen Stand noch nicht klären. Einen detaillierter Einblick zum Ablauf der Authentifizierung
ist in den Querschnittlichen Konzepten unter User Experience zu finden.  
