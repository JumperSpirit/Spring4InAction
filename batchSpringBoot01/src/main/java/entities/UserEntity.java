package entities;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "APP_USER")
public class UserEntity {

	@Id
	@Column(name = "user_id")
	private Long id;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "login")
	private String login;

	@Column(name = "pass")
	private String passeWord;

	public UserEntity() {

	}

	public UserEntity(String lastName, String firstName) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public UserEntity(String lastName, String firstName, String login,
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "lastName=" + lastName + ", firstName=" + firstName + ", login="
				+ login + ", passeWord=" + passeWord;
	}

	public String getPasseWord() {
		return passeWord;
	}

	public void setPasseWord(String passeWord) {
		this.passeWord = passeWord;
	}
}
