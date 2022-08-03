package booking;

import java.sql.Timestamp;

public class BookingDTO {
	private int booking_code,item_code;
	private int bookingX, bookingY;
	private int user_codeA, user_codeB;
	private Timestamp created_At, modified_At;
	public int getBookingX() {
		return bookingX;
	}
	public void setBookingX(int bookingX) {
		this.bookingX = bookingX;
	}
	public int getBookingY() {
		return bookingY;
	}
	public void setBookingY(int bookingY) {
		this.bookingY = bookingY;
	}
	public Timestamp getCreated_At() {
		return created_At;
	}
	public void setCreated_At(Timestamp created_At) {
		this.created_At = created_At;
	}
	public Timestamp getModified_At() {
		return modified_At;
	}
	public void setModified_At(Timestamp modified_At) {
		this.modified_At = modified_At;
	}
	public int getBooking_code() {
		return booking_code;
	}
	public int getItem_code() {
		return item_code;
	}
	public int getUser_codeA() {
		return user_codeA;
	}
	public int getUser_codeB() {
		return user_codeB;
	}
	public BookingDTO(int booking_code, int item_code, int bookingX, int bookingY, int user_codeA, int user_codeB,
			Timestamp created_At, Timestamp modified_At) {
		super();
		this.booking_code = booking_code;
		this.item_code = item_code;
		this.bookingX = bookingX;
		this.bookingY = bookingY;
		this.user_codeA = user_codeA;
		this.user_codeB = user_codeB;
		this.created_At = created_At;
		this.modified_At = modified_At;
	}
	

}
