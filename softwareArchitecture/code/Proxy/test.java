package proxy;

import junit.framework.TestCase;

public class test extends TestCase{
	public void testOrderPrice() {
		Order o = new Order("Bob");
		Product toothpaste = new Product("ToothPaste",129);
		o.addItem(toothpaste, 1);
		assertEquals(129, o.total());
		Product mouthwash = new Product("MouthWash",342);
		o.addItem(mouthwash, 2);
		assertEquals(813,o.total());
	}
}
