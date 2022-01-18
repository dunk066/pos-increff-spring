
package com.increff.pos.spring;

import static org.junit.Assert.assertNotNull;
import java.io.InputStream;
import org.junit.Test;

public class SampleTest extends AbstractUnitTest {
    // test Sample files
    @Test
    public void testFiles() {
        // test different files exists or not
        InputStream s = null;
        s = SampleTest.class.getResourceAsStream("/com/increff/pos/brand.tsv");
        assertNotNull(s);
        s = SampleTest.class.getResourceAsStream("/com/increff/pos/inventory.tsv");
        assertNotNull(s);
        s = SampleTest.class.getResourceAsStream("/com/increff/pos/product.tsv");
        assertNotNull(s);
    }

}
