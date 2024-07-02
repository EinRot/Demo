package com.thread.countdown.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.joda.cfg.FormatConfig;
import com.fasterxml.jackson.datatype.joda.cfg.JacksonJodaDateFormat;
import com.fasterxml.jackson.datatype.joda.deser.JodaDateDeserializerBase;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;

/**
 * @Author: shua
 * @Date: 2019/8/27 16:11
 * @Description: No Description
 */
public class IocDateTimeDeserializer extends JodaDateDeserializerBase<DateTime> {
    private static final long serialVersionUID = 1L;

    private final static DateTimeFormatter FROMAT_yyyyMMdd = DateTimeFormat.forPattern("yyyyMMdd");
    private final static DateTimeFormatter FROMAT_yyyyMMddHH = DateTimeFormat.forPattern("yyyyMMddHH");
    private final static DateTimeFormatter FROMAT_yyyyMMddHHmmss = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private final static DateTimeFormatter FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ = DateTimeFormat.forPattern("yyyyMMdd'T'HHmmssZZ");
    private final static DateTimeFormatter FROMAT_1 = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss");
    private final static DateTimeFormatter FROMAT_2 = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private final static DateTimeFormatter FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_S= DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.S");
    private final static DateTimeFormatter FROMAT_3 = DateTimeFormat.forPattern("yyyy年MM月dd日 HH:mm");
    private final static DateTimeFormatter FROMAT_DATETIME_yyyy_MM = DateTimeFormat.forPattern("yyyyMM");
    
    public IocDateTimeDeserializer() {
        this(DateTime.class, FormatConfig.DEFAULT_LOCAL_DATEONLY_FORMAT);
    }

    protected IocDateTimeDeserializer(Class<?> type, JacksonJodaDateFormat format) {
        super(type, format);
    }

    @Override
    public JodaDateDeserializerBase<?> withFormat(JacksonJodaDateFormat jacksonJodaDateFormat) {
        return new com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer(DateTime.class, jacksonJodaDateFormat);
    }

    @Override
    public DateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        switch (p.getCurrentTokenId()) {
            case 4:
            case 5:
            case 6:
                String str = p.getText().trim();
                if (0 == str.length()) {
                    return null;
                }
                if (StringUtils.equals(str, "null")) {
                    return null;
                }

                switch (str.length()) {
                	case 6:
                		return DateTime.parse(str, FROMAT_DATETIME_yyyy_MM);
                    case 8:
                        return DateTime.parse(str, FROMAT_yyyyMMdd);
                    case 10:
                        return DateTime.parse(str, FROMAT_yyyyMMddHH);
                    case 14:
                        return DateTime.parse(str, FROMAT_yyyyMMddHHmmss);
                    case 17:
                    	return DateTime.parse(str,FROMAT_3);
                    case 18:
                        return  DateTime.parse(str, FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ);
                    case 19:
                        if (str.contains("T")) {
                            return DateTime.parse(str, FROMAT_1);
                        } else {
                            return DateTime.parse(str, FROMAT_2);
                        }
                    case 21:
                    	return  DateTime.parse(str, FROMAT_DATETIME_yyyy_MM_dd_HH_mm_ss_S);
                    default:
                        return DateTime.parse(str, JacksonMapper.instance.FROMAT_DATETIME_YYYYMMDDTHHMMSSZZ);
                }
            default:
                return (DateTime) ctxt.handleUnexpectedToken(this.handledType(), p.getCurrentToken(), p, "expected String, Number or JSON Array", new Object[0]);

        }
    }
}
