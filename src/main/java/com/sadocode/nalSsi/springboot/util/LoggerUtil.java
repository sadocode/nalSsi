package com.sadocode.nalSsi.springboot.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;

public class LoggerUtil implements Runnable{

    /** log level */
    public static final int TRACE = 0;
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARNING = 3;
    public static final int ERROR = 4;
    public static final int FATAL = 5;

    public static final String ILLEGAL_LOGLEVEL_MSG = "로그 레벨이 잘못 입력되었습니다.";
    public static final String ILLEGAL_TIME_MSG = "시간이 잘못 입력되었습니다.";

    /** 싱글톤 */
    private static LoggerUtil loggerUtil;

    /** LogData를 담을 Queue */
    private static Queue<LogData> logQueue = new LinkedList<LogData>();

    /** 로그설정파일(properties)에서 key-value 를 가지고 있는 map */
    private static Map<String, String> dataMap;

    /**
     * LoggerUtil 생성자.<br>
     * 외부에서 선언이 불가능하다.
     */
    private LoggerUtil(String configFileName)
    {
        this.init(configFileName);
        this.run();
    }

    private void init(String configFileName)
    {
        dataMap = new HashMap<String, String>();

        // 설정 정보 읽기.
        try(FileInputStream fis = new FileInputStream(configFileName))
        {
            Properties properties = new Properties();
            properties.load(fis);

            for(String key : properties.stringPropertyNames())
            {
                dataMap.put(key, properties.getProperty(key));
            }
        }
        catch(IOException ioe)
        {
            ioe.getStackTrace();
        }
    }

    public static LoggerUtil getInstance(String properties)
    {
        if(loggerUtil == null)
            loggerUtil = new LoggerUtil(properties);

        return loggerUtil;
    }

    public synchronized void log(LogData logData)
    {
        logQueue.add(logData);
    }

    public void run()
    {
        while(true)
        {
            // 특정 시간이 되면, sleep 걸기.
        }
    }
    private void printLog()
    {
        // log를 실제로 파일에 찍어주는 메소드.
        LogData logData = logQueue.poll();

    }
    private void _printLog()
    {

    }
    private void sleep()
    {
        // 스레드 재우기.
    }

    /**
     * DataMap 을 반환한다.<br>
     * 다른 map에 복사 후, 리턴한다.
     * @return dataMap과 같은 정보를 담은 map
     */
    public Map<String, String> getDataMap()
    {
        Map<String, String> resultMap = new HashMap<String, String>(dataMap);

        return resultMap;
    }
}