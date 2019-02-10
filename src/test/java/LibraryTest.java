/*
 * This Java source file was generated by the Gradle 'init' task.
 */
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryTest {
    @Test 
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
        
        assertEquals("add should return 5", 5, classUnderTest.add(2, 3) );
    }
    
    @Test
    public void testRandomNumbers() {
    	
    	List<Integer>  list = new ArrayList<Integer>();
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for (int i = 0; i < 100; i++) {
    		Integer rand = Double.valueOf( Math.floor(Math.random()*4)).intValue();
    		list.add(rand);
    		
    		if (map.containsKey(rand)) {
    			map.put(rand, map.get(rand)+1);
    		} else {
    			map.put(rand, 1);
    		}
    	}
    	System.out.println(list.toString());
    	System.out.println(map.toString());
    }
}
