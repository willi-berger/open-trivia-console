import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class Library {
	private static final Logger LOG = LoggerFactory.getLogger(Library.class);

    public boolean someLibraryMethod() {
    	LOG.info("someLibraryMethod");
        return true;
    }
    
    public int add(int a, int b) {
    	LOG.info("add(" + a + "," + b + ")");
    	return a+b;
    }
}
