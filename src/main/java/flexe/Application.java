package flexe;



public class Application {

	public static void main(String[] args)  {

		 User user = new User("alice",  "people",  "mydomain", "local","AlicePassword") ;

			Server server = new Server("192.168.0.104", 389, "ldap") ;
			
			server.authentication(user);
			for (String i : server.getAllConnectedUsers().keySet()) {
			      System.out.println("key: " + i + " value: " + server.getAllConnectedUsers().get(i));
			    }
			

	}

}
