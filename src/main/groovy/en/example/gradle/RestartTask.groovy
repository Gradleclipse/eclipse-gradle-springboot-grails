package en.example.gradle

import java.net.URI
import java.net.URISyntaxException
import java.util.ArrayList
import java.util.Collection

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

public class RestartTask {
	
	private static final Logger log = LoggerFactory.getLogger(RestartTask.class)
	
	private String baseUrl = "http://localhost:8080/actuator/restart"
	
	public RestartTask(String baseUrl) {
		if (baseUrl != null && !baseUrl.trim().isEmpty()) {
			this.baseUrl = baseUrl
		}
		log.info("Constructor: enter with baseUrl=${this.baseUrl}")
	}
	
	public RestartTask() {
		log.info("Constructor: enter with baseUrl=${this.baseUrl}")
	}
	
	public HttpStatus postRestart() {
		RestTemplate restTemplate = new RestTemplate()

	    URI uri = null
		try {
			uri = new URI(baseUrl)
		} catch (URISyntaxException e) {
			log.error(e.getMessage(), e)
			return null // game over
		}
	   
	    MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>()
	    headers.add("X-COM-PLANET", "JUPITER")
	    Collection<MediaType> mediaTypes = new ArrayList<MediaType>()
	    mediaTypes.add(MediaType.APPLICATION_JSON)
	    headers.add("content-type", MediaType.toString(mediaTypes))
	    
	    String jsonBody = "{}"
	    HttpEntity<String> httpEntity = new HttpEntity<String>(jsonBody, headers)    	 
	    ResponseEntity<String> result = restTemplate.postForEntity(uri, httpEntity, String.class)
	    if ( result == null ) {
	    	log.debug(".postRestart(): result is null")
	    	return HttpStatus.SERVICE_UNAVAILABLE
	    }
	    HttpStatus httpStatus = result.getStatusCode()
	    if ( httpStatus == null ) {
	    	log.debug(".postRestart(): httpStatus is null")
	    	return HttpStatus.SERVICE_UNAVAILABLE
	    }	    
	    int httpStatusValue = httpStatus.value()
	    log.info(".postRestart(): httpStatusValue = " + httpStatusValue)
	    return result.getStatusCode()
	}
	
}