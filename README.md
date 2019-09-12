# Project Title

NY Times Most Popular Articles

## Getting Started

This app import NY Times Most Popular Articles into an Android application.

### Prerequisites

Android Studio

```
NY Times Most Popular Articles Account & Api Key
```

### Installing

Importing into Android Studio
Choose Import Project... and navigate to the NYTimesMostPopularArticles directory.

## Built With
Building with Gradle
Build the sample app using:
./gradlew clean build`
To see a list of available tasks, execute:
./gradlew tasks

## Running the tests

Clean
gradle clean
Debug
This compiles a debugging apk in build/outputs/apk/ signed with a debug key, ready to be installed for testing purposes.

gradle assembleDebug
You can also install it on your attached device:

gradle installDebug
Release
This compiles an unsigned release (non-debugging) apk in build/outputs/apk/. It's not signed, you must sign it before it can be installed by any users.

gradle assembleRelease
Test
Were you to add automated java tests, you could configure them in your build.gradle file and run them within gradle as well.

gradle test
Lint
This analyses the code and produces reports containing warnings about your application in build/outputs/lint/.

gradle lint

### Break down into end to end tests

Unit Tests
./gradlew :unit-test:test
Coverage
After unit tests have been executed
./gradlew jacocoTestReport

## Deployment

UI approach: Using MVP

## Versioning

We use [GITHUP](http://githup.com/) for versioning. For the versions available, see the [tags on this repository](https://github.com/yazan1984/NYTimesMostPopularArticles). 

## Authors

* **Yazan Aljayoussi** - *Initial work* - [Yazan1984](https://github.com/yazan1984)

