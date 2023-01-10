val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val h2_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.4.32"
}


tasks.jar {
    manifest.attributes["Main-Class"] = "com/example/ApplicationKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) } +
            sourcesMain.output
    from(contents)

    baseName = "base-service"
    exclude("com/example/services/*")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com/example/services/authService/AuthAppKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) } +
            sourcesMain.output
    from(contents)

    baseName = "auth-service"
    exclude("com/example/services/hallService/*")
    exclude("com/example/services/reservationService/*")
    exclude("com/example/services/seanceService/*")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com/example/services/hallService/HallAppKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) } +
            sourcesMain.output
    from(contents)

    baseName = "hall-service"
    exclude("com/example/services/authService/*")
    exclude("com/example/services/reservationService/*")
    exclude("com/example/services/seanceService/*")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com/example/services/reservationService/ReservationAppKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) } +
            sourcesMain.output
    from(contents)

    baseName = "reservation-service"
    exclude("com/example/services/authService/*")
    exclude("com/example/services/hallService/*")
    exclude("com/example/services/seanceService/*")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "com/example/services/seanceService/SeanceAppKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath
        .get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    val sourcesMain = sourceSets.main.get()
    val contents = configurations.runtimeClasspath.get()
        .map { if (it.isDirectory) it else zipTree(it) } +
            sourcesMain.output
    from(contents)

    baseName = "seance-service"
    exclude("com/example/services/authService/*")
    exclude("com/example/services/hallService/*")
    exclude("com/example/services/reservationService/*")
}





repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version")
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation ("com.h2database:h2:$h2_version")
    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    implementation("commons-codec:commons-codec:1.14")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-server-sessions:$ktor_version")
    //exposed
    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-dao:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")
    implementation("org.jetbrains.exposed:exposed-java-time:0.37.3")

    implementation("io.ktor:ktor-server-auth:$ktor_version")
    implementation("org.postgresql:postgresql:42.3.3")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    //email sender
    implementation("javax.activation:activation:1.1.1")
    implementation("com.sun.mail:javax.mail:1.6.2")
    implementation("org.apache.commons:commons-email:1.5")

    //pdf
    implementation("com.github.librepdf:openpdf:1.3.30")

    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

}