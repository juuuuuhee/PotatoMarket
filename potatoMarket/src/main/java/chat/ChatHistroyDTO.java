package chat;

import java.sql.Date;

public class ChatHistroyDTO {
	private int chatRoom_code;
	private int addUser;
	private String chat_contents;
	private Date created_at;
	private Date modified_at;
	private int readChat; // 0 : 읽은상태, 1 : 안읽은상태

	public ChatHistroyDTO(int chatRoom_code, int addUser, String chat_contents, int readChat) {
		this.chatRoom_code = chatRoom_code;
		this.addUser = addUser;
		this.chat_contents = chat_contents;
		this.readChat = readChat;
	}

	public int getChatRoom_code() {
		return chatRoom_code;
	}

	public void setChatRoom_code(int chat_code) {
		this.chatRoom_code = chat_code;
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

	public int getReadChat() {
		return readChat;
	}

	public void setReadChat(int readChat) {
		this.readChat = readChat;
	}

	public void setModified_at(Date modified_at) {
		this.modified_at = modified_at;
	}

}
