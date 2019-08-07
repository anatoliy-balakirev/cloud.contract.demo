import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.springframework.cloud.contract.verifier.config.TestFramework
import org.springframework.cloud.contract.verifier.config.TestMode
import org.springframework.cloud.contract.verifier.plugin.ContractVerifierExtension


buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        // This is needed for `org.springframework.cloud.contract` plugin to pick up pact jsons as an input:
        classpath("org.springframework.cloud:spring-cloud-contract-pact:2.1.2.RELEASE")
        classpath ("org.springframework.cloud:spring-cloud-contract-gradle-plugin:2.1.2.RELEASE")
//        classpath ("org.springframework.cloud:spring-cloud-contract-gradle-plugin:2.2.0.BUILD-SNAPSHOT")
    }
}

apply(plugin = "org.springframework.cloud.contract")

plugins {
    id("org.springframework.boot") version "2.1.6.RELEASE"
    id("java")
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}
configure<DependencyManagementExtension> {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-contract-dependencies:2.1.2.RELEASE")
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.rest-assured:spring-web-test-client")
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.0")
}

afterEvaluate {
    the<ContractVerifierExtension>().packageWithBaseClasses = "com.example.demo.contract"
    the<ContractVerifierExtension>().basePackageForTests = "com.example.demo.contract"
    the<ContractVerifierExtension>().testMode = TestMode.WEBTESTCLIENT
    the<ContractVerifierExtension>().testFramework = TestFramework.JUNIT5
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    test {
        useJUnitPlatform()
    }
}
