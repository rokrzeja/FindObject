package FindPackage;

import java.util.HashSet;

public interface Find {

    public HashSet returnMatchingWords(String regex);

    public String returnContext(String txt);
}
