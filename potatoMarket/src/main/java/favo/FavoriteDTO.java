package favo;

public class FavoriteDTO {
	private int favoCode, itemCode, userCode;

	public FavoriteDTO(int favoCode, int itemCode, int userCode) {
		super();
		this.favoCode = favoCode;
		this.itemCode = itemCode;
		this.userCode = userCode;
	}

	public int getFavoCode() {
		return favoCode;
	}

	public int getItemCode() {
		return itemCode;
	}

	public int getUserCode() {
		return userCode;
	}
}
