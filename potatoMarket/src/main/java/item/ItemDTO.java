
package item;

import java.sql.Timestamp;

public class ItemDTO {

	private int item_code, booking_code, cate_code, item_price, item_seiling, user_code;
	private String item_tilte, item_contents, item_pic;
	private Timestamp created_At, modified_At;
	private int orderuser_code;

	public int getOrderuser_code() {
		return orderuser_code;
	}

	public ItemDTO(int item_code, int booking_code, int user_code, String item_tilte, String item_contents,
			int item_price, int item_seiling, String item_pic, int cate_code, int orderuser_code) {
		super();
		this.item_code = item_code;
		this.booking_code = booking_code;
		this.user_code = user_code;
		this.item_tilte = item_tilte;
		this.item_contents = item_contents;
		this.item_price = item_price;
		this.item_seiling = item_seiling;
		this.item_pic = item_pic;
		this.cate_code = cate_code;
		this.orderuser_code = orderuser_code;
	}

	public void setOrderuser_code(int orderuser_code) {
		this.orderuser_code = orderuser_code;
	}

	public ItemDTO(int item_code, int booking_code, int user_code, String item_tilte, String item_contents,
			int item_price, int item_seiling, String item_pic, int cate_code) {
		super();
		this.item_code = item_code;
		this.booking_code = booking_code;
		this.user_code = user_code;
		this.item_tilte = item_tilte;
		this.item_contents = item_contents;
		this.item_price = item_price;
		this.item_seiling = item_seiling;
		this.item_pic = item_pic;
		this.cate_code = cate_code;
	}

	public ItemDTO(int item_code, int booking_code, int cate_code, int item_price, int item_seiling, int user_code,
			String item_tilte, String item_contents, String item_pic, Timestamp created_At, Timestamp modified_At) {
		this.item_code = item_code;
		this.booking_code = booking_code;
		this.cate_code = cate_code;
		this.item_price = item_price;
		this.item_seiling = item_seiling;
		this.user_code = user_code;
		this.item_tilte = item_tilte;
		this.item_contents = item_contents;
		this.item_pic = item_pic;
		this.created_At = created_At;
		this.modified_At = modified_At;
	}

	public ItemDTO(int item_code, int user_code, String item_tilte, String item_contents, String item_pic,
			int item_price) {
		this.item_code = item_code;
		this.item_price = item_price;
		this.user_code = user_code;
		this.item_tilte = item_tilte;
		this.item_contents = item_contents;
		this.item_pic = item_pic;
	}

	public int getItem_code() {
		return item_code;
	}

	public int getBooking_code() {
		return booking_code;
	}

	public int getCate_code() {
		return cate_code;
	}

	public int getItem_price() {
		return item_price;
	}

	public int getItem_seiling() {
		return item_seiling;
	}

	public int getUser_code() {
		return user_code;
	}

	public String getItem_tilte() {
		return item_tilte;
	}

	public String getItem_contents() {
		return item_contents;
	}

	public String getItem_pic() {
		return item_pic;
	}

	public Timestamp getCreated_At() {
		return created_At;
	}

	public Timestamp getModified_At() {
		return modified_At;
	}

}
