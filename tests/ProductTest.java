import org.junit.Before;
import org.junit.Test;
import simple_shops_and_customers.*;

import static org.junit.Assert.assertEquals;

public class ProductTest {

    Product lotr;

    @Before
    public void before(){
        lotr = new Product(
                "LordOfTheRings11234",
                8.99,
                12.99,
                "Unabridged copy of The Lord of the Rings by J.R.R. Tolkien"
        );
    }

    @Test
    public void testGetIdentifier(){
        assertEquals( "LordOfTheRings11234", lotr.getIdentifier() );
    }

    @Test
    public void testGetWholesalePrice(){
        assertEquals( 8.99, lotr.getWholesalePrice(), 0.001 );
    }

    @Test
    public void testGetRetailPrice(){
        assertEquals( 12.99, lotr.getRetailPrice(), 0.001 );
    }

    @Test
    public void testGetDescription(){
        assertEquals("Unabridged copy of The Lord of the Rings by J.R.R. Tolkien", lotr.getDescription() );
    }
}
