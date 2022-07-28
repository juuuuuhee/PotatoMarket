package chat;

import java.sql.Date;

public class ChatHistroyDTO {
	private int chat_code;
	private int addUser;
	private String chat_contents;
	private Date created_at;
	private Date modified_at;

	public ChatHistroyDTO(int chat_code, int addUser, String chat_contents) {
		super();
		this.chat_code = chat_code;
		this.addUser = addUser;
		this.chat_contents = chat_contents;
	}

	public int getChat_code() {
		return chat_code;
	}

	public void setChat_code(int chat_code) {
		this.chat_code = chat_code;
	}

	public int getAddUser() {
		return addUser;
	}

	public void setAddUser(int addUser) {
		this.addUser = addUser;
	}

	public String getChat_contents() {
		return chat_contents;
	}

	public void setChat_contents(String chat_contents) {
		this.chat_contents = chat_contents;
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
