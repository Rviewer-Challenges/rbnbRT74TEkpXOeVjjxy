# Twitter Mirroring

Simple app that represents a fake Twitter timeline using Compose.
I've used Android Studio Electric Eel for this project in order to use the live edit feature, 
as it's not something to be published I thought there should be no problem.

## APK

The apk of this app can be downloaded from:
https://drive.google.com/file/d/1dYobyo4ttoL7mMV6_-SFAoMcRXsXYvh2/view?usp=sharing

## Modules
I tried to create different modules so that the app is about twitter-mirroring and feature-timeline
is just a feature of it so there could be extra features done.

These modules are:
- feature-timeline: Contains the composable to show a twitter timeline, the data is fake and 
provided by the repo itself.
- library-components: it's idea is to have components that can be reused in different features of 
the app, they are used in feature-timeline for now but they could be used elsewhere.

## Resources

I used the following site for the mentions regex 
https://stackoverflow.com/questions/40129180/find-a-twitter-mention-using-regex-in-java-with-a-twist 
with some changes cause it didn't work for some cases.
An answer of here https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url 
is used for the url matches.

## Testing

As the purpose of this pet project is about UI I've done UI tests in the components of library-components
and in feature-timeline.

To run the unit tests: `./gradlew lint testDebug --continue`

To run the UI tests: `./gradlew connectedDebugAndroidTest`

To record the screenshots test: `./gradlew executeScreenshotTests -Precord`

To execute the screenshots test: `./gradlew executeScreenshotTests`
