// Use application.groovy for configuration that doesn’t depend on application classes

println("[application.groovy]: Reading configuration...")

// if property is not in messages_xy.properties look for messages.properties!
spring.messages.'fallback-to-system-locale' = false


environments {	
	development {
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