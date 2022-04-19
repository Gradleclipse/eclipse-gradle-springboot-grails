package en.example

class HelloWorldController {
	
	def index() {
		def model = [ greeting: 'Hello World!' ]
		render view: "/helloworld/helloWorld", model: model
	}
	
}