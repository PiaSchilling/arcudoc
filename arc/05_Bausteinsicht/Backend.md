# 5.1_Backend-Aufbau 

Das Backend wurde mit Supabase umgesetzt. Es handelt sich um eine [PostgrestSQL](https://www.postgresql.org/docs/current/) Datenbank, welche über eine [PostgREST](https://postgrest.org/en/stable/api.html) API erreichbar ist. Die Kommunikation zwischen Client und Backend erfolgt somit über die Abfrage von REST-Endpoints mithilfe von Retrofit. 

## ER-Diagramm

![Untitled Diagram.drawio](/Users/piaschilling/Desktop/Untitled Diagram.drawio.png)

## Entitäten 

Im folgenden werden die einzelnen Entitäten der Datenbank beschrieben. Um Filterung von Daten in das Backend zu verlagern wird bei einigen Tabellen mit Row Level Security Policies gearbeitet. Client-Seitig kann somit komplett auf das Filtern der Daten verzichtet werden. Zudem wird durch die RLS Datensicherheit gewährleistet. 

Die DB-Trigger, welche auf die jeweiligen Tabellen gesetzt sind, werden im Abschnitt "Trigger und Functions" genauer erläutert. 

| profiles           |                                                              |
| ------------------ | ------------------------------------------------------------ |
| Beschreibung       | Hält die Profilinformationen der authentifizierten user      |
| Beziehungen        | `id` ist FK Beziehung zu `auth.users.uuid`                   |
| Trigger            | `create_profile_for_user_trigger`                            |
| Row level security | Enable select for users based on user_id(uid() = id) -> user können nur ihr eigenes Profil selecten |
| Default-Werte      | -                                                            |
| Anmerkungen        | Profilinformationen werden aus der aus der `auth.users` Tabelle gewonnen, welche von Supabase bereitgestellt wird. Hier wird jeder user erfasst der sich durch das oAuth verfahren authentifiziert hat. Aus der Spalte `raw_user_metadata` werden zusätzliche user-Informationen wie der Name und der Avatar des users gewonnen. (für weitere Informationen zur Authentifizierung siehe anderer Abschnitt) |

| project_invitations |                                                              |
| ------------------- | ------------------------------------------------------------ |
| Beschreibung        | Hält die Projekteinladungen (Projekteinladungen basieren auf der E-Mail Adresse des einzuladenden users und zugehöriger ProjektId) |
| Beziehungen         | - `project_id` ist FK Beziehung zu `projects`<br />- `invited_by` ist FK Beziehung zu `profiles` |
| Trigger             | -                                                            |
| Row level security  | - Enable delete for users based on user_mail(email() = member_mail)<br />- Enable insert access to all users <br />- Enable select for users based on member_mail (email() = member_mail)<br />- Enable update for users based on member_mail(email() = member_mail) |
| Default-Werte       | `invited_by` Spalte -> `auth.uid()` (jwt user id)            |
| Anmerkungen         | Die invited_by Spalte wird derzeit nicht genutzt. Dies ist nicht möglich, da die invited_by Spalte auf die durch RLS geschützte Tabelle profiles verweist. Es ist für user dadurch nur möglich das eigene Profil zu selecten. Ein select das invited_by users wäre somit nicht möglich. |

| project_members    |                                                              |
| ------------------ | ------------------------------------------------------------ |
| Beschreibung       | Modliert die many-to-many relationship zwischen Projektmitgliedern und Projekten |
| Beziehungen        | - `project_id`ist FK Beziehung zu `projects`<br />- `profile_id`ist FK Beziehung zu `profiles` |
| Trigger            | -                                                            |
| Row level security | - Enable insert for authenticated users only<br />- Enable select for users based on user_id(uid() = profile_id) |
| Default-Werte      | -                                                            |
| Anmerkungen        | -                                                            |

| project_owners     |                                                              |
| ------------------ | ------------------------------------------------------------ |
| Beschreibung       | Hält alle user, welche owner einer Projektes sind            |
| Beziehungen        | `id` ist FK Beziehung zu `profiles`                          |
| Trigger            | -                                                            |
| Row level security | not enabled                                                  |
| Default-Werte      | -                                                            |
| Anmerkungen        | Da der Zugriff auf die Profiles Tabelle durch RLS gesichert ist, war es nicht möglich eine direkte Verknüpfung zwsichen `project_owner` der `projects` Tabelle und dem entsprechenden User-Profil der `profiles` Tabelle zu machen. Jedoch sollten Profilinfomtationen des Projectowners Client-Seitig verfügbar sein. Deshalb wurde eine die Tabelle `project_owners` mit identischen Daten aber ohne RLS erstellt. Dies ist eine nicht optimale aber einfachste Lösung für das Problem. |

| projects           |                                                              |
| ------------------ | ------------------------------------------------------------ |
| Beschreibung       | Hält Informationen zu den Projekten (Projektdokumentationen) |
| Beziehungen        | `owner_id`ist FK Beziehung zu `project_owners`<br />`owner`ist FK Beziehung zu `profiles` -> wird jedoch nicht genutzt (wurde ersetzt durch owner_id) |
| Trigger            | - `add_owner_to_project_members_trigger`<br />- `create_content_trigger`<br />- `create_project_owner_trigger`<br />- `update_last_updated` |
| Row level security | not enabled                                                  |
| Default-Werte      | -                                                            |
| Anmerkungen        | Durch Umstrukturierung nahe dem Ende des Projektes wurde neue Spalte `owner_id`eingeführt, welche die alte owner-Spalte ersetzt.<br />Der Inhalt der Projektdokumentationen wurde in eine separate Tabelle (content) ausgelagert. Siehe content-Tabelle für weitere Informationen. |

| content            |                                                              |
| ------------------ | ------------------------------------------------------------ |
| Beschreibung       | Hält den eigentlichen Projektdokumentations-Inhalt           |
| Beziehungen        | `id`ist FK Beziehung zu `projects`                           |
| Trigger            | -                                                            |
| Row level security | not enabled                                                  |
| Default-Werte      | -                                                            |
| Anmerkungen        | Für eine bessere Übersicht wird der content getrennt von den Projekt-Informationen gespeichert. So kann das arc42 template schön in der Datenbanktabelle abgebildet und ggf. angepasst werden. |

----



## Trigger und Functions 

Im folgenden werden die erstellten Trigger dargestellt. Jeder Trigger macht von einer Function gebrauch, welche ebenfalls dokumentiert sind.

### Functions

#### `add_project_owner_to_project_members`

```sql
begin
  insert into public.project_members(project_id,profile_id,project_role,job_label)
  values(new.id, new.owner,'owner','team lead');
  return new;
end;
```

#### `create_content_when_project_creating`

```sql
begin
  insert into public.content(id)
  values(new.id);
  return new; 
end; 
```

#### `create_profile_for_user`

```sql
begin
  insert into public.profiles(id,mail,display_name,avatar)
  values(new.id, 
  new.email,
  new.raw_user_meta_data ->> 'name',
  new.raw_user_meta_data ->> 'picture');
  return new;
end;
```

#### `create_project_member`

```sql
begin
  insert into project_members(project_id,project_role,job_label)
  values (new.project_id,new.project_role,new.job_label);
  return null;
end;
```

#### `create_project_owner`

```sql
begin
  insert into public.project_owners(id,mail,display_name,avatar)
  select profiles.id,profiles.mail,profiles.display_name,profiles.avatar 
  from profiles where auth.uid() = id 
  on conflict (id) do nothing;
  return new;
end;
```

#### `set_last_updated`

```sql
begin
  new.last_updated = now();
  return new;
end;
```

----

### Trigger

#### `add_owner_to_project_members_trigger`

- Fügt den user, welcher ein neues Projekt erstellt, als Projekt-Mitglied des neu erstellten Projekts hinzu
- on insert 
- after
- function: `add_project_owner_to_project_members`

#### `create_content_trigger`

- Erstellt ein neues Projekt-content-template, für das neu erstellte Projekt
- on insert 
- after
- function: `create_content_when_project_creating`

#### `create_project_owner_trigger`

- Legt den user, welcher das Projekt erstellt, als Projekt-Owner an 
- on insert 
- before
- function: `create_project_owner`

#### `update_last_updated` (not used/broken)

- Setzt den "zuletzt-akutalisiert" Zeitstempel neu, wenn das Projekt geupdated wird (müsste auf die content tabelle nicht die projects tabelle gesetzt werden)
- on update 
- after
- function: `set_last_updated`

#### `create_profile_for_user_trigger`

- Legt ein neues Profil für den neu authentifizierten user an 
- on insert 
- before
- function: `create_profile_for_user`