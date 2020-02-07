package evgenyt.springboot.springboot_jms_obj;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Listen to person queue here
 * @author EUTyrin
 *
 */

@Component
public class Receiver {

	@JmsListener(destination = "personQueue", containerFactory = "myFactory")
	public void receiveMessage(Person person) {
		System.out.println("Received: " + person);
	}

}