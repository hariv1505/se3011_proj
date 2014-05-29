
public class MSMPoint {
	private String date;
	private Double price;

	public MSMPoint(String date, Double price) {
		this.setDate(date);
		this.setPrice(price);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
