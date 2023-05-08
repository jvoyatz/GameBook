
## Technologies
- Multiple modules
- Clean Architecture
- MVVM+ (mix of MVVM with MVI)
- Single Activity
- Navigation Component
- Material UI
- Kotlin 
  - Coroutines
  - Flows
- Dependency Injection **(Dagger Hilt)**
- Networking **(Retrofit)**
- Unit Testing & UI Testing
  - JUnit4
  - Truth
  - Turbine
  - **MockK**/Mockito
  - Espresso
- Gradle
  - using Kotlin (.kts) instead of Groovy (autocompletion)
  - Version Catalog (single source of truth for dependencies and plugins versions)
    - with a drawaback (unfortunately), cannot use precompiled script plugins for the several modules.
    - The solution is the **Convention Plugins**

  
**This app has been designed, built and implemented having in the multi-module (by layer and feature) and clean approach in mind**


## Dependency Graph

![graph](./reports/project.dot.png)

### Modules

**App Module**
> App module is the start point of this project, it is aware of the other features modules provided. It contains the one Activity used in this app and it contains the navigation graph, which is needed to navigate through the features screens of this app. It also depends on the **:data** modules as well as other modules.

**Features Modules**
> These modules are essentially representing the User Interface of this app. They are indendent of each other and they are not aware of the :app module.
> > **GameBook** :
> > Display the current open bets and the main headlines

**Component Module**
> Contains the use cases, the exposed repositories and their implementation needed by the app to fetch data 

**Libaries Module**
> Contains code and implementations that they are used throughout the app
> 
> > **Shared** Shared classes and utilities used throughout out the app. -- **Java/Kotlin library**
> 
> > **Utils-Android** Same as above, however this module is dependent to Android, since it provides methods on top of that. -- **Android Library**
> 
> > **Testing** Utilities and classes needed for testing -- **Android Library**
> 
> > **Network** Defines the contract that gives us the capability to fetch from the remote service. -- **Android Library/Retrofit**
> > **mvvmI** Defines an implementation of MVVM with MVI architecture approaches.
> > Using UiState to wrap data that they will be shown in the screens
> > Using Intents **(not Android Intents)** to define the actions that can take place by the user.
> > Using Events to show a dialog/toast/snackbar as a result of an action triggered by the user and it does not affect the data displayed.
> 
> **UI** Defines custom views, extensions functions and other common method/functions/utilities that can used in a particular feature module of this app
