# Findings

Here we will document all the findings related to the project.

Finding will cover from bugs, best practices, etc.

## No such file or directory - ./gradlew

While using fastlane you must be aware that `bundle exec fastlane` will execute all lanes taking in context the current directory. Adapt the paths to the gradlew file accordingly.