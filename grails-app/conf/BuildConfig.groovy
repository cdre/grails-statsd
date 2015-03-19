grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.repos.default = "rumble"
grails.project.repos.rumble.url = "http://artifactory.aws.cdrentertainment.com:8081/artifactory/plugins-release-local"
grails.project.repos.rumble.username = "jenkins"
grails.project.repos.rumble.password = "cdre1300"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenCentral()
        mavenLocal()
        //mavenRepo "http://repository.codehaus.org"
    }
    dependencies {
        test 'org.gmock:gmock:0.8.0'
        test 'org.hamcrest:hamcrest-library:1.1' // Optionally, you can use hamcrest matchers
        compile "org.apache.commons:commons-pool2:2.0"
    }

    plugins {
        build ":release:3.1.0"
        build(":tomcat:7.0.55") {
            export = false
        }
    }
}
