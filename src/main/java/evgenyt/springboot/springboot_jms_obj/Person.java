package evgenyt.springboot.springboot_jms_obj;

/**
 * Class we want to send using JMS
 * @author EUTyrin
 *
 */

public class Person {
	private String name;

	// Empty constructor needed by jackson
	public Person() {}
	
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + "]";
	}	
	
}
