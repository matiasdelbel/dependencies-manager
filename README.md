# Dependencies manager
A plugin that checks the version of specific libraries to see if they are updated or not. 

### Development
This project contains 2 key elements: a test app (app) and the plugin (plugin).

In order to release a new local version of the plugin you need to run the following command:
```shell script
cd plugin
./gradlew publishToMavenLocal
```
To specify a new version of the plugin you need to update the version in the `plugin/build.gradle` file.

### How to use it?
1- Add the dependency in the top level `build.gradle`. NOTE: We want to pull always the latest version, 
to keep all the project in sync.

```
dependencies {
        classpath "com.backbase.plugin:backbase-dependencies-manager:+"
    }
```
2- Apply the plugin
  ```
plugins {
    id('backbase-dependencies-manager')
}
```

3- In case of an outdated library version, the compilation of the project will fail:

```
FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':app:checkDependencies'.
> Unvalid dependency version Dependency(name=DependencyName(group=androidx.appcompat, name=appcompat), version=1.2.0)
```

### Know Issue
1- It is not handling the cases when the dependency version contains a (+)
