
package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Converters {
    
    
    public static String dateToString(Date date) {
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String string = dateFormatter.format(date);
        
        return string;
    }
    
    public static Date stringToDate(String string) throws ParseException {
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormatter.parse(string);
        
        return date;
    }
    
    public static String intToString(int number) {
        
        String string = Integer.toString(number);
        
        return string;
    }
    
    public static int stringToInt(String string) {
        
        int number = Integer.parseInt(string);
                
        return number;
    }
    
    public static String booleanToString(Boolean bool) {
        if (bool) {
            return "1";
        }
        else {
            return "0";
        }
    }
    
    public static Boolean stringToBoolean(String string) {
        if (string.equals("1")) {
            return true;
        }
        else {
            return false;
        }
    }
}
