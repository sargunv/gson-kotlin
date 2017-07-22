import com.beust.kobalt.plugin.packaging.assemble
import com.beust.kobalt.plugin.publish.bintray
import com.beust.kobalt.project
import com.beust.kobalt.test

object Versions {
    val thisProject = "0.1.0"
    val kotlin = "1.1.3-2"
    val gson = "2.7"
    val testng = "6.9.10"
}

@Suppress("unused")
val p = project {

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
        compile("org.testng:testng:${Versions.testng}")
    }

    assemble {
        jar {}
        mavenJars {}
    }

    test {
    }

    bintray {
        publish = true
    }
}