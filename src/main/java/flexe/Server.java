package flexe;

import java.util.HashMap;
import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Server {
	private String IP_Adress;
	private int port;
	private String service;
	
private HashMap<String,User> users;

public Server (HashMap<String,User> users) {
	this.users=users;
}

	
	int authentication(User user) {
		try {
		
			Hashtable<String, String> environment = new Hashtable<String, String>();

			environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			environment.put(Context.PROVIDER_URL, this.service+"://"+this.IP_Adress+":"+this.port);
			environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			environment.put(Context.SECURITY_PRINCIPAL, "cn=alice,ou=people,dc=mydomain,dc=local");
			environment.put(Context.SECURITY_PRINCIPAL, "cn="+user.getCommonName()+","+"ou="+user.getOrganizationalUnit()+","+"dc="+user.getDomaineComponent()+","+"dc="+user.getEndDomaineComponent());


			environment.put(Context.SECURITY_CREDENTIALS,user.getPassword());
			
			addConnectedUsers(user); // add a new user as long as the authentication worked successfully.

			DirContext context;
				
				context = new InitialDirContext(environment);
				System.out.println(context.getEnvironment());

				context.close();
				return 0;

			} 
			 catch (AuthenticationException ex) {
				    ex.printStackTrace();

				    }
			catch (NamingException e) {
				e.printStackTrace();
			}
			return -1;
			
		}
	
	HashMap<String,User> addConnectedUsers(User user){
		
		this.users= new HashMap<String,User>();
		this.users.put(user.getDomaineComponent(),user);
		
		return this.users;
		}
	
	HashMap<String, User> getAllConnectedUsers(){
		return this.users;
	}


	public Server(String iP_Adress, int port, String service) {
		super();
		IP_Adress = iP_Adress;
		this.port = port;
		this.service = service;
	}



	
	
	
	
	
	
	
	
	
	
	
	

}
