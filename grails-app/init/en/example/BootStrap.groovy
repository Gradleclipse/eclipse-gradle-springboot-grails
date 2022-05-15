package en.example

import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {
	
	def grailsApplication
	
	InitService initService

    def init = { servletContext ->
		log.info(".init()")		
		initService.logInfo()
    }
    def destroy = {
		log.info(".destroy()")
    }
}
