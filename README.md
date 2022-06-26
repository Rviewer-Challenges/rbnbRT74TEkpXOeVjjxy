# Twitter Mirroring

Simple app that represents a fake Twitter timeline using Compose.

I've used Android Studio Electric Eel for this project in order to use the live edit feature, 
as it's not something to be published I thought there should be no problem.

The data is hardcoded in TweetsRepositoryImpl in the feature-timeline module.

It accepts tweets with just text as well as one of the following media:
- 1-4 images (if more than 4 images are passed the rest are ignored).
- 1 gif image.
- 1 video.

The images are zoomable and the gif and video playable.
There is also the possibility to see the tweet details with some fake comments.

## APK

The apk of this app can be downloaded from:
https://drive.google.com/file/d/1Xts0ELdy7KHo-rvtYdEaUPfGke34ntHK/view?usp=sharing

## Modules
I tried to create different modules so that the app is about twitter-mirroring and feature-timeline
is just a feature of it so there could be extra features done.

These modules are:
- feature-timeline: Contains the composables to show the twitter timeline, the image details 
and the tweet details, the data is fake and provided by the repo itself.
- library-components: it's idea is to have components that can be reused in different features of 
the app, they are used in feature-timeline for now but they could be used in other future features 
(search...).

## Screenshots & Youtube video

![Youtube Video](https://youtu.be/mh86Nrg7JLg)

![Timeline 1](/screenshots/timeline1.png "Timeline 1")
![Timeline 2](/screenshots/timeline2.png "Timeline 2")
![Timeline 3](/screenshots/timeline3.png "Timeline 3")
![Timeline 4](/screenshots/timeline4.png "Timeline 4")
![Picture Details](/screenshots/pictureDetails.png "Picture Details")

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
