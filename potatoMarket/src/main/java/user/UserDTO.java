package user;

import java.sql.Timestamp;

public class UserDTO {

	


	private UserDAO dao = UserDAO.getInstance();
	private int code;
	private String id, pw, name, address;
	private Timestamp createdAt, modifiedA;
	private String phone;

	public UserDTO(int code, String id, String name, String add, String phone) {
		super();
		this.code = code;
		this.id = id;
		this.name = name;
		this.address = add;
		this.phone = phone;
	}
	// join user 생성자
	public UserDTO(String id, String pw, String name, String address, String phone) {
		this.code = dao.userCode();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	// login user 생성자
	public UserDTO(int code, String id, String pw, String name, String address, String phone) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedA() {
		return modifiedA;
	}

	public void setModifiedA(Timestamp modifiedA) {
		this.modifiedA = modifiedA;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
