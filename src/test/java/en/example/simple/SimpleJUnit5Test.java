package en.example.simple;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SimpleJUnit5Test {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleJUnit5Test.class);

	@Test
	@DisplayName("test Something")
	void testSomething() {
		
		log.info(".testSomething(): enter.");
		
		String concatenation = "dummy";
		String prefix = "dumm";
		String postfix = "y";		

		assertEquals(concatenation, prefix + postfix);
	}

	
}
