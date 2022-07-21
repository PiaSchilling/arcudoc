

# ArcUdoc - "the desktop application to rock your doc"

![Workspace](https://user-images.githubusercontent.com/96486990/180218432-6894c242-e0a9-44ef-90ac-57b06d4d7d02.png)
![View](https://user-images.githubusercontent.com/96486990/180218462-2fed0e7a-7cc5-4992-a328-6927083de183.png)
![Edit](https://user-images.githubusercontent.com/96486990/180218474-512f6ab5-aebb-40d6-ad3b-486830db0261.png)
![CreateProject](https://user-images.githubusercontent.com/96486990/180218498-1359ed1b-4b26-4343-b6bd-f52a41b5ae38.png)
<img width="400" alt="Login" src="https://user-images.githubusercontent.com/96486990/180219191-362b61aa-c29a-42bb-95ae-dd0f994a9fab.png">


## What is ArcUdoc?

ArcUDoc is intended to make it easier to create structured software architecture documentation based on the [arc42](https://arc42.org/) architecture documentation template. Especially new team members should find it easier to familiarize themselves with a new project. Also the cooperation between developers and architects should be simplified. Guidelines, concepts and generally applicable procedures in the team are recorded there and easily accessible for all team members. 

## Features

- Create projects and invite team members 
- See project invitations and accept them to be part of the project
- View and edit the contents of the project documentation using an integrated Markdown editor

## Usage

Currently only usable via IntelliJ or comparable development environment. The `Main` method can be found in the UI module in the root package. 


## Documentation

Detailed project documentation can be found [here](https://github.com/PiaSchilling/arcudoc/tree/main/arc)

## Technologies

More detailed explanations of the technologies used in this project can be found [here](https://github.com/PiaSchilling/arcudoc/tree/main/arc/04_L%C3%B6sungsstrategie). See ["Design Decisions"](https://github.com/PiaSchilling/arcudoc/tree/main/arc/09_Entwurfsentscheidungen) for a more detailed explanation of why we chose these technologies.

## Known Issues 

- Login was only tested extensively in Firefox. Login via other browsers could cause problems (especially in Safari). (see [AuthFlow](https://github.com/PiaSchilling/arcudoc/blob/main/arc/08_Querschnittliche-Konzepte/User-Experience.md) for furhter details)
- Login via Google not yet implemented 
- Missing implementation of Searchbar and Join-Project-Button in Workspace-Screen, Team and Design Buttons in Project-Screen

## Project status / future plans

The following features are planned to be implemented in the future:

- version control or at least the possbibility to view past versions of the documentation 
- offline usage 
- implementation of the "design" and "team" part in the project screen. In the design part it will be possible to record design guidelines etc. for the project. In the team part it will be possible to see all members of the projects with theier project roles, job labels and further information. 
- Access control based on the defined project roles (edit, view, ...)


## Authors
Pia Schilling, Vivien Volpert, Paul Sendelbach, Sara Tietze



