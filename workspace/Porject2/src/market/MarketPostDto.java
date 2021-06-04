package market;

public class MarketPostDto {
	private String pk_user;
	private String title;
	private int price;
	private String content;
	private String filename;
	
	public MarketPostDto(String pk_user, String title, int price, String content, String filename) {
		this.pk_user= pk_user;
		this.title= title;
		this.price= price;
		this.content= content;
		this.filename= filename;
	}
	
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
