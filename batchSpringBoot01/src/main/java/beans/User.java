package beans;

public class User {
	private String lastName;
	private String firstName;
	private String login;
	private String passeWord;
	
	public User() {

	}
	public User(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}


	public User(String lastName, String firstName, String login,
			String passeWord) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.login = login;
		this.passeWord = passeWord;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}





	@Override
	public String toString() {
		return "lastName=" + lastName + ", firstName=" + firstName
				+ ", login=" + login + ", passeWord=" + passeWord;
	}


	public String getPasseWord() {
		return passeWord;
	}


	public void setPasseWord(String passeWord) {
		this.passeWord = passeWord;
	}
}
