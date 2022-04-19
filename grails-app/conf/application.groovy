// Use application.groovy for configuration that doesn’t depend on application classes

println("[application.groovy]: Reading configuration...")

// if property is not in messages_de.properties look for messages.properties!
spring.messages.'fallback-to-system-locale' = false

println("[application.groovy]: Done.")