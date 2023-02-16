
import org.apache.log4j.Logger;


public class HtmlLogger  
{  
    static Logger log = Logger.getLogger(HtmlLogger.class);  
   
    public static void main(String[] args)  
    {  
   
        log.debug("Sample debug message");  
        
        log.info("Sample info message");  
        log.error("Sample error message");  
        log.fatal("Sample fatal message");  
    }  
}  