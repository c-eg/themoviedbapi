plugins {
    `java-library`
    checkstyle
    `maven-publish`
    signing
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

group = "uk.co.conoregan"
version = "2.2.0"

repositories {
    mavenCentral()
}

dependencies {
    // logging
    implementation(platform("org.slf4j:slf4j-bom:2.0.16"))
    implementation("org.slf4j:slf4j-api")

    // testing
    testImplementation(platform("org.junit:junit-bom:5.11.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation(platform("org.mockito:mockito-bom:5.14.1"))
    testImplementation("org.mockito:mockito-core")

    // util
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation(platform("com.fasterxml.jackson:jackson-bom:2.18.0"))
    implementation("com.fasterxml.jackson.core:jackson-annotations")
    implementation("com.fasterxml.jackson.core:jackson-core")
    implementation("com.fasterxml.jackson.core:jackson-databind")

    implementation("org.apache.commons:commons-lang3:3.17.0")
    testImplementation("commons-io:commons-io:2.17.0")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
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
                        name = "BSD"
                        url = "https://github.com/c-eg/themoviedbapi/blob/master/LICENCE.txt"
                    }
                }

                scm {
                    connection = "scm:git:github.com/c-eg/themoviedbapi.git"
                    url = "https://github.com/c-eg/themoviedbapi.git"
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
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}

if (project.hasProperty("signing.keyId") && project.hasProperty("signing.password") && project.hasProperty("signing.secretKeyRingFile")) signing {
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

tasks {
    javadoc {
        options {
            (this as CoreJavadocOptions).addBooleanOption("Xdoclint:none", true)
        }
    }
}
