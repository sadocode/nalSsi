package com.sadocode.nalSsi.springboot.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class LogData implements Serializable{
    static final long serialVersionUID = 123123123L;

    /** 로그 레벨 */
    private int logLevel;

    /** 로그가 찍힌 시간 */
    private String time;

    /** 로그 메세지 */
    private String message;

    /** 선언 불가 */
    private LogData()
    {
        throw new AssertionError();
    }

    /**
     * LogData 생성자
     * @param logLevel 로그 레벨
     * @param time 로그 찍는 시간
     * @param message 로그 메세지
     */
    public LogData(String logLevel, long time, String message)
    {
        this(logLevel, time, message, null);
    }

    /**
     * LogData 생성자
     * @param logLevel 로그 레벨
     * @param time 로그 찍는 시간
     * @param message 로그 메세지
     * @param objects 추가 정보 인자
     */
    public LogData(String logLevel, long time, String message, Object... objects)
    {
        this.setLogLevel(logLevel);
        this.setTime(time);
        this.setMessage(message, objects);
    }

    /**
     * 로그 레벨을 설정해주는 메소드
     * @param logLevel 로그 레벨
     * @throws IllegalArgumentException 잘못된 로그레벨이 입력되었을 때 발생
     */
    private void setLogLevel(String logLevel) throws IllegalArgumentException
    {
        if(logLevel == null || logLevel.length() == 0)
            throw new IllegalArgumentException(LoggerUtil.ILLEGAL_LOGLEVEL_MSG);

        logLevel = logLevel.trim().toUpperCase();
        switch(logLevel)
        {
            case "TRACE" :
                this.logLevel = LoggerUtil.TRACE;
            case "DEBUG" :
                this.logLevel = LoggerUtil.DEBUG;
            case "INFO" :
                this.logLevel = LoggerUtil.INFO;
            case "WARNING" :
                this.logLevel = LoggerUtil.WARNING;
            case "ERROR" :
                this.logLevel = LoggerUtil.ERROR;
            case "FATAL" :
                this.logLevel = LoggerUtil.FATAL;
            default :
                throw new IllegalArgumentException(LoggerUtil.ILLEGAL_LOGLEVEL_MSG);
        }
    }

    /**
     * 로그 시간을 설정해주는 메소드
     * @param time 로그 찍는 시간
     * @throws NumberFormatException 잘못된 숫자가 입력되었을 때 발생
     */
    private void setTime(long time) throws NumberFormatException
    {
        try
        {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[YYYY_MM_dd_HH:mm:ss.SSS]");
            this.time = simpleDateFormat.format(time);
        }
        catch(Exception e)
        {
            throw new NumberFormatException(LoggerUtil.ILLEGAL_TIME_MSG);
        }
    }

    /**
     * 로그 메세지를 설정해주는 메소드<br>
     * message 외에 Object가 추가로 입력되었을 경우, toString으로 message에 덧붙힌다.
     * @param message 로그 메세지
     * @param objects 로그 추가 정보
     */
    private void setMessage(String message, Object...objects)
    {
        if(objects != null)
        {
            StringBuilder tempMessage = new StringBuilder(message);
            int objectLength = objects.length;

            for(int i = 0; i < objectLength; i++)
            {
                tempMessage.append(objects[i].toString());
            }
            this.message = tempMessage.toString();
        }
        else
        {
            this.message = message;
        }
    }
}