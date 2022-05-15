package en.example

import org.springframework.beans.factory.annotation.Value

import groovy.util.logging.Slf4j

@Slf4j
class InitService {
	
	@Value('${init.example.foo:fooDefault}')
	final String initExampleFoo
	
	@Value('${init.example.bar:barDefault}')
	final String initExampleBar
	
	void logInfo() {
		log.info("[init.example.foo]: ${initExampleFoo}")
		log.info("[init.example.bar]: ${initExampleBar}")
	}
	
}