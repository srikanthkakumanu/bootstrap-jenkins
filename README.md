# bootstrap-jenkins
This Docker image will install Jenkins on Alpine and setup a Seed Job to bootstrap Jenkins, install recommended Plugins and skips login wizard.


## Instructions

1. Build Gradle project and create a docker image for jenkins: `./gradlew docker`
2. Run the docker image via Gradle task: `./gradlew dockerRun`
