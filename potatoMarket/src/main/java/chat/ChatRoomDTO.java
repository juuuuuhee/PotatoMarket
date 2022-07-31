package chat;

import java.sql.Date;

public class ChatRoomDTO {
	private int chat_code;
	private int seller_code;
	private int buyer_code;
	private int item_code;
	private Date created_at;
	private Date modified_at;

	public ChatRoomDTO(int chat_code, int seller_code, int buyer_code, int item_code) {
		super();
		this.chat_code = chat_code;
		this.seller_code = seller_code;
		this.buyer_code = buyer_code;
		this.item_code = item_code;
	}

	public int getChat_code() {
		return chat_code;
	}

	public void setChat_code(int chat_code) {
		this.chat_code = chat_code;
	}

	public int getSeller_code() {
		return seller_code;
	}

	public void setSeller_code(int seller_code) {
		this.seller_code = seller_code;
	}

	public int getBuyer_code() {
		return buyer_code;
	}

	public void setBuyer_code(int buyer_code) {
		this.buyer_code = buyer_code;
	}

	public int getItem_code() {
		return item_code;
	}

	public void setItem_code(int item_code) {
		this.item_code = item_code;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getModified_at() {
		return modified_at;
	}

	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}

}
