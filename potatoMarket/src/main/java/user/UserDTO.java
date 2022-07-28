package user;

public class UserDTO {
	private int code;
	private String id,pw,name,add;
	private int year,month,day;
	private String phone;
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getCode() {
		return code;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
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
		this.add = add;
		this.phone = phone;
	}
	
	
	
}
