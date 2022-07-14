# 8.2 User Experience

[TOC]



## Authentifizierung 

Um eine nutzerfreundliche Authentifizierung zu bieten und zudem nicht selbst für das Verwalten von Passwörtern und Userdaten verantwortlich zu sein, wurde Authentifizierung über OAuth implementiert. Derzeit ist nur GitLab als Auth Provider implementiert. 

Im Projekt ist der Login-Flow im Modul ` de.hdm_stuttgart.login` zu finden. Im folgenden wird der Auth Flow sowie damit verbundenen technische Details beschrieben.

### Auth flow

![Auth flow diagram](./AuthFlow.png)

### Technische Details

#### Redirect URL

Da keine Webseite zum Redirect nach erfolgreicher Authentifizierung zur verfügung steht, wird der User auf folgende URL umgeleitet: 

`http://localhost:8000/arcudoc/login/`

Diese URL wurde in Supabase hinterlegt. Client-Seitig startet ein Portlistener im Hintergrund auf obige URL sobald der Login-Flow beginnt. Dieser hört auf eingehende Verbindungen und filtert dann die notwendigen Informationen (Access tokens) aus dem eingehenden Request heraus.

#### Token-Parsing

Nach erfolgreicher Authentifizierung bei einem Auth Provider wie Gitlab wird der User auf obige URL umgeleitet. An die obige URL wird der erhaltene JWT angehängt. Die URL sieht dann wie folgt aus: (access_token wurde aufgrund der lesbarkeit verkürzt)

`http://localhost:8000/arcudoc/login/#access_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNjUCJ9.yC4kdXa6SQ9idG6llmZ0bySHo0ehgYINI9W7Yfflyok&expires_in=604800&provider_token=df66d284cc8497a917ca55c72a504842fd420f2be18f1557c31410d8564a019b&refresh_token=roTYsbm6c0voHWiZQqM7WQ&token_type=bearer` 

Da die Tokens sich in nach einem *ragment identifier* (#) befinden, ist es nicht möglich sie ohne weiteres auszulesen, da aus Sicherheitsgründen alles nach dem # herausgefiltert wird. Es wurde folgender Workaround implementiert. Durch ein Java-Script Skript, welches vom Portlistener in den Browser geladen wird, sobald eine eingehende Verbindung erkannt wird, wir der # durch ein ? ausgetauscht. Nun ist es problemlos möglich die Tokens zu parsen. Es ist zu beachten, dass diese Lösung nicht funktioniert, sollte der Browser Java-Script blockieren, wie es beispielsweise der Safar-Browser tut. In Firefox wurde die Lösung ausführlich getestet und funktioniert ohne Probleme. 

```java
//loaded into browser and executed once incoming connection is made 
<h1>loading</h1>
 <script>
   let url = window.location.href
   let result = url.replace("#", "?")
   location = result
</script>
```

#### Refresh Token

Damit sich der User nicht bei jedem Programmstart neu einloggen muss, wird der Refresh-Token persistent in die [Preferences](https://docs.oracle.com/javase/8/docs/api/java/util/prefs/Preferences.html) gespeichert. Bei Programmstart wird geprüft, ob dieser Refresh-Token abrufbar ist. Wenn ja dann wird mit dem Refresh-Token ein neuer Accesstoken gezogen, da dieser nach einer Stunde abläuft. Anschließend wird der Workspace Screen gezeigt. Wenn kein Refresh-Token in den Preferences gefunden wird, wird der oben beschriebene Login-Flow eingeleitet. 