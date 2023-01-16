package flexe;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class JunitTest {
	 User user = new User("alice",  "people",  "mydomain", "local","AlicePassword") ;

		Server server = new Server("192.168.0.104", 389, "ldap") ;
		@Test
		public void authenticationTest() {
			assertEquals(0, server.authentication(user));
			
		}
	
	
	
}
