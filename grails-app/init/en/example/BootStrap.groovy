package en.example

import groovy.util.logging.Slf4j

@Slf4j
class BootStrap {

    def init = { servletContext ->
		log.info(".init()")
    }
    def destroy = {
		log.info(".destroy()")
    }
}
