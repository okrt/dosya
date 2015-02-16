package dosya;
import javax.mail.internet.*;
public class FormControls {
	public static boolean notempty(String a){
		if(a!=null && a.isEmpty()!=true) return true;
		else{return false;}
	}
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
}
