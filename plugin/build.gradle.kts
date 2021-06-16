plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
}

repositories {
    google()
    mavenCentral()
}

/* TODO INIT REGION Extract this to a separete file */
group = "com.backbase.plugin"
version = "1.0.0-alpha04"

publishing {
    publications {
        repositories {
            mavenLocal()
        }

        create<MavenPublication>("maven") {
            artifactId = "backbase-dependencies-manager"

            from(components["kotlin"])

            pom {
                name.set("Backbase Dependencies Manager Plugin")
                description.set("Check if the project is using the latest dependencies defined by the team")
            }
        }
    }
}
/* TODO END REGION */

dependencies {
    implementation("com.android.tools.build:gradle:4.2.1")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

gradlePlugin {
    plugins.register(Plugin.NAME) {
        id = Plugin.NAME
        implementationClass = Plugin.IMPLEMENTATION_CLASS
    }
}

private object Plugin {
    const val NAME = "backbase-dependencies-manager"
    const val IMPLEMENTATION_CLASS = "com.backbase.plugin.DependencyManagementPlugin"
}
