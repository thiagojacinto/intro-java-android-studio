# Introduction to Android Studio App Development
Introduction (basically first steps) to Android Studio App development.

## Worth take a look:

Besides its an introdutory repository of mobile development, I would recommmend take a look at:

- [StopWatch App](/StopWatch/app/src/main): After an tutorial to make a simple stopwatch, I decided to upgrade it by adding a STOP button (reset animations + stops time couting); a [variety of methods](/StopWatch/app/src/main/java/com/example/myapplication/StopWatch.java) to each action when Stop Button is pressed (stops couting, resets animation, convert time from Chronometer class into String, saves that into a Queue and then pass that Queue info content [into a ScrollView](https://gist.github.com/thiagojacinto/06ac72c9239162432c4ab8a083d855b8) (with HorizontalLayout) of Buttons, each containing the last 5 laps (time between clicks on START & STOP). 
> Reference used as start point: [Build a Stopwatch App from scratch](https://youtu.be/gqn7HqTnOPA)

- [OpenWeather Consumer App](/OpenWeatherConsumerApp/app/src/main): From another tutorial, this simple Weather App was created with all logic done in Kotlin, so it's my first encounter with that language - already with lite version of Ansynchronos Tasks. With the first version with only [one Activity](/OpenWeatherConsumerApp/app/src/main/java/com/example/openweatherconsumerapp/MainActivity.kt), this app gather information on weather of a selected city using [Open Weather's API](https://api.openweathermap.org), showing on screen some part of its possibilities.
> Reference uses as start point: [Creating an Android Weather App using Kotlin](https://www.androdocs.com/tutorials/creating-an-android-weather-app-using-kotlin.html)
