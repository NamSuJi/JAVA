package proxytwo;

import junit.framework.*;
import junit.textui.TestRunner;

public class DBTest extends TestCase{
	public static void main(String[] args) {
		TestRunner.main(new String[] {"DBTest"});
	}
	public DBTest(String name) {
		super(name);
	}
	public void setUp() throws Exception{
		DB.init();
	}
	public void tearDown() throws Exception{
		DB.close();
	}
	public void testStoreProduct() throws Exception{
		ProductData storedProduct = new ProductData();
		storedProduct.name = "MyProduct";
		storedProduct.price = 1234;
		storedProduct.sku = "999";
		DB.store(storedProduct);
		ProductData retrievedProduct = DB.getProductData("999");
		DB.deleteProductData("999");
		assertEquals(storedProduct, retrievedProduct);
	}
}
