package proxytwo;

import junit.framework.*;
import junit.textui.TestRunner;

public class ProxyTest extends TestCase{
	public static void main(String[] args) {
		TestRunner.main(new String[] {"ProxyTest"});
	}
	public ProxyTest(String name) {
		super(name);
	}
	public void setUp() throws Exception{
		DB.init();
		ProductData pd = new ProductData();
		pd.sku = "ProxyTest1"; 
		pd.name = "ProxyTestName1";
		pd.price = 456;
		DB.store(pd);
	}
	public void tearDown() throws Exception{
		DB.deleteProductData("ProxyTest1");
	}
	public void testProductProxy() throws Exception{
		Product p =new ProductProxy("ProxyTest1");
		assertEquals(456,p.getPrice());
		assertEquals("ProxyTestName1",p.getName());
		assertEquals("ProxyTest1",p.getSku());
	}
}
