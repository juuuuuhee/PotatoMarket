package item;

public class ItemDTO {

	private int itemCode,bookingCode,userCode;
	private String title,contents;
	private int price,sellchk;
	private String pic;
	public ItemDTO(int itemCode, int bookingCode, int userCode, String title, String contents, int price, int sellchk,
			String pic, int cateCode) {
		super();
		this.itemCode = itemCode;
		this.bookingCode = bookingCode;
		this.userCode = userCode;
		this.title = title;
		this.contents = contents;
		this.price = price;
		this.sellchk = sellchk;
		this.pic = pic;
		this.cateCode = cateCode;
	}
	private int cateCode;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSellchk() {
		return sellchk;
	}
	public void setSellchk(int sellchk) {
		this.sellchk = sellchk;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public int getItemCode() {
		return itemCode;
	}
	public int getBookingCode() {
		return bookingCode;
	}
	public int getUserCode() {
		return userCode;
	}
	public int getCateCode() {
		return cateCode;
	}
	
}
