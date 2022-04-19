package en.example.simple

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class SimpleSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
		given:
		log.info(".'test something'(): enter.")
		String concatenation = "dummy"
		String prefix = "dumm"
		String postfix = "y"
		expect:"a very simple test, which is true"
		concatenation == prefix + postfix
    }
}
