package proxy;

public class ProductDBProxy implements IProduct{
	ProductImplementation pi = new ProductImplementation();
	public void retrieveProduct(String sku) {
		pi.retrieveProduct(sku);
	}
}
class ProductImplementation implements IProduct{
	public void retrieveProduct(String sku) {}
}