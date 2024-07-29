plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

val plugin = "Alies"
val author = "Diamondy01"
val desc = "Duel plugin"
val main = "com.diamondy.alies.Alies"
val version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.purpurmc.org/snapshots")
}

dependencies {
    compileOnly("org.purpurmc.purpur:purpur-api:1.20.4-R0.1-SNAPSHOT")
}

tasks {
    named<ProcessResources>("processResources") {
        expand(
            "id" to plugin.lowercase(),
            "name" to plugin,
            "author" to author,
            "main" to main,
            "description" to desc,
            "version" to version
        )
    }

    named<JavaCompile>("compileJava") {
        options.encoding = "UTF-8"
    }

    register<Copy>("copy") {
        from(named("shadowJar"))
        rename("(.*)-all.jar", "$plugin-$version.jar")
        into(file("jars"))
    }

    register("delete") {
        doLast { file("jars").deleteRecursively() }
    }
}