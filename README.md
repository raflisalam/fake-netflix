# fake-netflix

<!-- About the Project -->
## :star2: About the Project

A simple project using [The Movie DB](https://www.themoviedb.org) based on Kotlin MVVM architecture and material designs.<br>

<!-- Screenshots -->
### :camera: Screenshoots & Preview

-User Interfaces

<div align="center"> 
  <img src="preview/user-interfaces.svg" alt="screenshot" />
</div>

-Preview <br>

![preview1](preview/preview-1.gif)
![preview2](preview/preview-2.gif)
<br>
![preview3](preview/preview-3.gif)
<img src="preview/preview-4.gif" width="180" height="320" alt="preview4">

[Full Preview with good quality](https://youtu.be/LllustXSqcQ)

<!-- Features -->
### :dart: Features

- Feature 1 : carousel now playing & upcoming moview
- Feature 2 : searching movies, tv shows, actors by the name
- Feature 3 : filter movies & tv shows by genres
- Feature 4 : detail movies, tv shows & actors
- Feature 5 : watch trailer movies & tv shows

<!-- Env Variables -->
### :key: How to build on your environment

Add your [The Movie DB](https://www.themoviedb.org)'s API key and Authorization Key in your `gradle.properties` file.
```xml
movie_api_key=YOUR_API_KEY
authorization_key=YOUR_AUTH_KEY
```

<!-- Installation -->
### :gear: Installation

Install my-project with Clone the project

```bash
  git clone https://github.com/raflisalam/fake-netflix.git
```
### :space_invader: Tech Stack & Open-source libraries

- Minimum SDK level 16
- 100% [Kotlin](https://kotlinlang.org/) based + [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- JetPack
  - Lifecycle - dispose observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct database.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Hilt - dependency injection
- Material Design
- [Retrofit2 & Gson](https://github.com/square/retrofit) - constructing the REST API
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server
- [Swipe Decorator]( https://github.com/xabaras/RecyclerViewSwipeDecorator) - swipe delete items from watchlist  
- [Glide](https://github.com/bumptech/glide) - loading images
- [Blurry](https://github.com/wasabeef/Blurry) - blurry images
- [Rounded Images](https://github.com/vinc3m1/RoundedImageView) - rounded images


