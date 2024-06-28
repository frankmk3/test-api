
plugins {
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
	id("com.diffplug.spotless") version "6.25.0"

	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
}


allprojects {
	group = "com.frankmk3"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}

}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "com.diffplug.spotless")

	spotless {
		kotlin {
			target("**/**/*.kt")
			ktfmt().googleStyle()
			indentWithTabs(2)
			indentWithSpaces(4)
		}
		kotlinGradle {
			target("*.kts", "**/*.kts")
			ktfmt().googleStyle()
			indentWithTabs(2)
			indentWithSpaces(4)
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		dependsOn("spotlessKotlinApply")
		dependsOn("spotlessKotlinGradleApply")
	}
}
