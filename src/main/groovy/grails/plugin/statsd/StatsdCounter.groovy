package grails.plugin.statsd

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass(['grails.plugin.statsd.ast.CounterASTTransformation'])
public @interface StatsdCounter {
    String key() default '';
    int magnitude() default 1;
    double sampleRate() default 1.0d;
}