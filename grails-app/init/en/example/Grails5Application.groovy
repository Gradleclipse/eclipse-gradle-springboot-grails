package en.example

import org.springframework.boot.autoconfigure.SpringBootApplication

import en.example.util.StringsUtils
import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@CompileStatic
@Slf4j
@SpringBootApplication
class Grails5Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
		String lArgs = StringsUtils.toString(args);
		log.info("[main]: starting SpringBoot Application via run-method: args="+lArgs);
        GrailsApp.run(Grails5Application, args)
    }
}