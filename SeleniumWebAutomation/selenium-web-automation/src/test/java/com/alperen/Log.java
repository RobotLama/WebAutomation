package com.alperen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log {

    static Logger logger = (Logger)LogManager.getLogger(Log.class);

    public static void info(String info)
    {
        logger.info(info);
    }
    
}
