package evgenyt.springboot.springboot_jms_obj;

import java.util.Scanner;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

/**
 * Spring boot demo: sending POJO using JMS
 * @author EUTyrin
 *
 */

@SpringBootApplication
@EnableJms
public class SpringbootJmsObjApplication {

	// App starter
	public static void main(String[] args) {
		ConfigurableApplicationContext context = 
				SpringApplication.run(SpringbootJmsObjApplication.class, args);
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		Scanner in = new Scanner(System.in);
		System.out.println("Enter new person name to send or exit to stop app");
		do {
			String name = in.next();
			if (name.equals("exit"))
				System.exit(0);
			System.out.println("Sending person:" + name);
		    jmsTemplate.convertAndSend("personQueue", new Person(name));
		} while(true);
	}
	
	
	@Bean // Factory to connect a broker (listener needs it)
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) 
	{
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		configurer.configure(factory, connectionFactory);
		return factory;
	}
	
	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
	    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	    converter.setTargetType(MessageType.TEXT);
	    converter.setTypeIdPropertyName("_type");
	    return converter;
	}	
	
}


