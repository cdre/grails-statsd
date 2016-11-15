package grails.plugin.statsd

import grails.plugins.*
import org.apache.commons.pool2.impl.GenericObjectPool
import org.apache.commons.pool2.impl.GenericObjectPoolConfig

class GrailsStatsdGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.1.12 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Grails Statsd" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''
    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-statsd"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    Closure doWithSpring() { {->
            // TODO Implement runtime spring config (optional)
            def statsdConfigMap = grailsApplication.config.grails.statsd ?: [:]
            statsdPoolConfig(GenericObjectPoolConfig) {
                // used to set arbitrary config values without calling all of them out here or requiring any of them
                // any property that can be set on GenericObjectPoolConfig can be set here
                statsdConfigMap.poolConfig.each { key, value ->
                    delegate.setProperty(key, value)
                }
            }

            def host = statsdConfigMap.host ?: 'localhost'
            def port = statsdConfigMap.port ?: 8125
            def prefix = statsdConfigMap.prefix ?: ''

            log.info("Setting up statsd for ${host}:${port} with ${prefix} prefix")
            println "Setting up statsd for ${host}:${port} with ${prefix} prefix"


            statsdPoolFactory(StatsdPoolFactory, host, port, prefix)
            statsdPool(GenericObjectPool, ref('statsdPoolFactory'), ref('statsdPoolConfig')) {
                //bean.destroyMethod = 'close'
            }
        }
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
