import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.bintray
import com.beust.kobalt.project

object Versions {
    val thisProject = "0.1.0"
    val kotlin = "1.0.3"
    val gson = "2.7"
    val junit = "4.12"
}

@Suppress("unused")
val core = project {

    name = "gson-kotlin"
    group = "me.sargunvohra.lib"
    artifactId = name
    version = Versions.thisProject

    dependencies {
        compile("org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}")
        compile("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
        compile("com.google.code.gson:gson:${Versions.gson}")
    }

    dependenciesTest {
        compile("org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}")
        compile("junit:junit:${Versions.junit}")
    }

    assemble {
        jar {}
        mavenJars {}
    }

    bintray {
        publish = true
    }
}