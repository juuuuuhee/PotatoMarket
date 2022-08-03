package user;

import java.sql.Timestamp;

public class UserDTO {


	private UserDAO dao = UserDAO.getInstance();
	private int code;
	private String id, pw, name, address;
	private Timestamp createdAt, modifiedA;

	private String add;
	private int year, month, day;

	private String phone;

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
	
	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}



	public UserDTO(int code, String id, String pw, String name, String add, int year, int month, int day,
			String phone) {
		this.code = code;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.add = add;
		this.year = year;
		this.month = month;
		this.day = day;
		this.phone = phone;
	}


	public UserDTO(int code, String id, String name, String add, String phone) {
		super();
		this.code = code;
		this.id = id;
		this.name = name;
		this.address = add;
		this.phone = phone;
	}

//	// join user 생성자
//	public UserDTO(String id, String pw, String name, String address, String phone) {
//		this.code = dao.userCode();
//		this.id = id;
//		this.pw = pw;
//		this.name = name;
//		this.address = address;
//		this.phone = phone;
//	}
//
//	// login user 생성자
//	public UserDTO(int code, String id, String pw, String name, String address, String phone) {
//		this.code = code;
//		this.id = id;
//		this.pw = pw;
//		this.name = name;
//		this.address = address;
//		this.phone = phone;
//	}
//
//	public int getCode() {
//		return code;
//	}
//
//	public void setCode(int code) {
//		this.code = code;
//	}
//
//	public String getId() {
//		return id;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public String getPw() {
//		return pw;
//	}
//
//	public void setPw(String pw) {
//		this.pw = pw;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getAddress() {
//		return address;
//	}
//
//	public void setAddress(String address) {
//		this.address = address;
//	}
//
//	public Timestamp getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Timestamp createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Timestamp getModifiedA() {
//		return modifiedA;
//	}
//
//	public void setModifiedA(Timestamp modifiedA) {
//		this.modifiedA = modifiedA;
//	}
//
//	public String getPhone() {
//		return phone;
//	}
//
//	public void setPhone(String phone) {
//		this.phone = phone;
//	}

}
