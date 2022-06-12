// Use application.groovy for configuration that doesn’t depend on application classes

println("[application.groovy]: Reading configuration...")

// if property is not in messages_xy.properties look for messages.properties!
spring.messages.'fallback-to-system-locale' = false


environments {	
	development {
		// E.g. trigger Restart via: curl -X POST http://localhost:{PORT}{CONTEXTPATH}/actuator/restart
		management {
			endpoints {
				// class WebEndpointProperties comes from dependency 'org.springframework.boot:spring-boot-starter-actuator'
				web {
					exposure {
						include = "restart,health"
					}
				}
			}
			// https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/reference/html/appendix-application-properties.html#actuator-properties
			// class RestartEndpoint comes from dependency 'org.springframework.cloud:spring-cloud-starter'
			endpoint {
				restart {
					enabled = true
				}
			}
			
		}
		
		init {
			// example properties
			example {
				foo = 'fooDev'
				bar = 'barDev'
			}
		}
	}	
	production {
		init {
			example {
				foo = 'fooProd'
				bar = 'barProd'
			}
		}
	}
}

println("[application.groovy]: Done.")