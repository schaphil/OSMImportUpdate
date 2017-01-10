package util;

/**
 *
 * @author thsc
 */
public class Util {
    private static final int MAX_DECIMAL_PLACES = 3;
    private static final int HIGHEST_NUMBER_PLUS_ONE = (int) Math.pow(10, MAX_DECIMAL_PLACES);
    
    private static  void appendStringWithLength(StringBuilder target, String s) {
        if(s == null || s.length() == 0) {
            target.append("0000");
            return;
        }
        
        int length = s.length();
        if(length >= HIGHEST_NUMBER_PLUS_ONE) {
            target.append("0000");
            return;
        }

        int hugeNumber = HIGHEST_NUMBER_PLUS_ONE / 10;
        
        while(hugeNumber > 1) {
            if(length >= hugeNumber) {
                break;
            } else {
                target.append("0");
            }
            
            hugeNumber /= 10;
        }
        
        target.append(Integer.toString(length));

        target.append(s);
    }
    
  
    public static void serializeAttributes(StringBuilder target, String key, String value) {
        if(target == null) return;
        
        /* strip forbidden signs (without additional function call and string copy operation!
        accept code duplicates. in most cases, key and value are not copied
        */
        int i = key.indexOf("'");
        while(i != -1) {
            // just throw it away
            StringBuilder sb = new StringBuilder();
            sb.append(key.substring(0, i));
            if(i < key.length()-1) {
                sb.append(key.substring(i+1));
            }
            key = sb.toString();
            i = key.indexOf("'");
        }

        if(value == null) { i = -1; }
        else { i = value.indexOf("'");}
        
        while(i != -1) {
            // just throw it away
            StringBuilder sb = new StringBuilder();
            sb.append(value.substring(0, i));
            if(i < value.length()-1) {
                sb.append(value.substring(i+1));
            }
            value = sb.toString();
            i = value.indexOf("'");
        }
        
        appendStringWithLength(target, key);
        appendStringWithLength(target, value);
    }
}