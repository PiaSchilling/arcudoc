# 2.1 Konventionen 

[TOC]

------

## Git

### Commit message naming convention

Format: 
```
`<type>(<scope>): <subject>`
<BLANK LINE>
<body>
<BLANK LINE>
<footer>
```

`<scope>` is optional

Types:

- `feat`: (new feature for the user, not a new feature for build script)
- `fix`: (bug fix for the user, not a fix to a build script)
- `docs`: (changes to the documentation)
- `style`: (formatting, missing semi colons, etc; no production code change)
- `refactor`: (refactoring production code, eg. renaming a variable)
- `test`: (adding missing tests, refactoring tests; no production code change)
- `chore`: (updating grunt tasks etc; no production code change)

Subject:

`The subject contains succinct description of the change:`
`- use the imperative, present tense: “change” not “changed” nor “changes”`
`- don’t capitalize first letter`
`- no dot (.) at the end`

Body: 

`Just as in the subject, use the imperative, present tense: “change” not “changed” nor “changes”. The body should include the motivation for the change and contrast this with previous behavior.`

Footer: 

`Place to reference GitHub issues that this commit Closes`

Example : 

```
feat(ui): add register button 

Implement button in the login screen to provide registration action to the user

closes #34
```

source: https://gist.github.com/joshbuchea/6f47e86d2510bce28f8e7f42ae84c716, https://ec.europa.eu/component-library/v1.15.0/eu/docs/conventions/git/ 

----

### Git-Flow 

In unserem Projekt arbeiten wir durchgängig mit dem Git-Flow. Dabei unterscheiden wir in   `feature` , `fix` und `test`  Branches. Diese basieren auf vorher erstellten Issues und deren Nummerierung. Beispielsweise: `feat/12_implement-login-scene`. Dabei steht die 12 für die id des zugehörigen Issues. Der Titel des Issues spiegelt sich dann im Name der Branch wieder. 

Sobald das Issue erledigt war, wurde die Branch in den Develop gemergt und dann gelöscht. So hatten wir so gut wie keine Merge-Konflikte, das war aber schon durch unseren modularen Projektaufbau vorhersehbar. 

[Hier]( https://gitlab.mi.hdm-stuttgart.de/ps149/se3/-/network/develop?extended_sha1=) ist die Umsetzung auch nochmal schön sichtbar



------
