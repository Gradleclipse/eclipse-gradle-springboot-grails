package en.example

import groovy.util.logging.Slf4j

@Slf4j
class HelloWorldController {
	
	def index() {
		log.debug(".index(): enter.")
		def model = [ greeting: 'Hello World!' ]
		log.debug(".index(): render view with model.")
		render view: "/helloworld/helloWorld", model: model
	}
	
}