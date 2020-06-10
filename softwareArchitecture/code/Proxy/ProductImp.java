package proxytwo;

public class ProductImp implements Product{
	private int itsPrice;
	public String itsName;
	public String itsSku;
	
	public ProductImp(String sku, String name, int price) {
		itsPrice = price;
		itsName = name;
		itsSku = sku;
	}
	public int getPrice() {
		return itsPrice;
	}
	public String getName() {
		return itsName;
	}
	public String getSku() {
		return itsSku;
	}
}
