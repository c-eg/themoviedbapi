plugins {
    `java-library`
    checkstyle
    `maven-publish`
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("org.jreleaser") version "1.24.0"
}

group = "uk.co.conoregan"
version = "2.6.0"

repositories {
    mavenCentral()
}

dependencies {
    // logging
    implementation(platform("org.slf4j:slf4j-bom:2.0.17"))
    implementation("org.slf4j:slf4j-api")

    // testing
    testImplementation(platform("org.junit:junit-bom:6.0.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")  // pin launcher to junit-bom; Gradle's bundled one lags the JUnit version

    testImplementation(platform("org.mockito:mockito-bom:5.23.0"))
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")

    testImplementation("org.wiremock:wiremock:3.13.2")

    // util
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")
    testCompileOnly("org.projectlombok:lombok:1.18.46")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.46")

    implementation(platform("com.fasterxml.jackson:jackson-bom:2.21.3"))
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    implementation("org.apache.commons:commons-lang3:3.20.0")

    testImplementation("commons-io:commons-io:2.21.0")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
    withJavadocJar()
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform {
        excludeTags("integration")
    }
}

tasks.register<Test>("integrationTest") {
    description = "Runs integration tests (tagged with \"integration\")."
    group = "verification"
    useJUnitPlatform {
        includeTags("integration")
    }
    testClassesDirs = sourceSets["test"].output.classesDirs
    classpath = sourceSets["test"].runtimeClasspath
    shouldRunAfter(tasks.test)
}

tasks.check {
    dependsOn("integrationTest")
}

checkstyle {
    toolVersion = "10.17.0"
    configFile = file("config/checkstyle/checkstyle.xml")
}
tasks.checkstyleMain {
    source = fileTree("src/main/java")
}
tasks.checkstyleTest {
    source = fileTree("src/test/java")
}
tasks.withType<Checkstyle>().configureEach {
    reports {
        html.required = true
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name = "themoviedbapi"
                description = "A Java-wrapper around the JSON API provided by TMdB, which is an open database for movie and tv content."
                url = "https://github.com/c-eg/themoviedbapi"

                licenses {
                    license {
                        name = "BSD-2-Clause"
                        url = "https://github.com/c-eg/themoviedbapi/blob/master/LICENCE.txt"
                    }
                }

                scm {
                    connection = "scm:git:https://github.com/c-eg/themoviedbapi.git"
                    developerConnection = "scm:git:ssh://git@github.com/c-eg/themoviedbapi.git"
                    url = "https://github.com/c-eg/themoviedbapi"
                }

                developers {
                    developer {
                        id = "holgerbrandl"
                        name = "Holger Brandl"
                        email = "holgerbrandl@gmail.com"
                    }

                    developer {
                        id = "c-eg"
                        name = "Conor Egan"
                        email = "17conoregan@gmail.com"
                    }
                }
            }
        }
    }
    repositories {
        // Local staging dir that JReleaser reads from for the signed release deploy.
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-deploy"))
        }

        // Central Portal snapshot repository.
        maven {
            name = "centralSnapshots"
            url = uri("https://central.sonatype.com/repository/maven-snapshots/")
            credentials {
                username = providers.environmentVariable("MAVENCENTRAL_USERNAME").orNull
                password = providers.environmentVariable("MAVENCENTRAL_PASSWORD").orNull
            }
        }
    }
}

jreleaser {
    signing {
        pgp {
            active = org.jreleaser.model.Active.ALWAYS
            armored = true
            mode = org.jreleaser.model.Signing.Mode.MEMORY
        }
    }
    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active = org.jreleaser.model.Active.ALWAYS
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    stagingRepository("build/staging-deploy")
                }
            }
        }
    }
}

tasks.javadoc {
    options {
        (this as StandardJavadocDocletOptions).apply {
            addBooleanOption("Xdoclint:none", true)
            addBooleanOption("html5", true)
        }
    }
}
