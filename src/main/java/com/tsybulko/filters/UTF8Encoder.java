package com.tsybulko.filters;

import com.tsybulko.dao.impl.IngredientDAOImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class UTF8Encoder {
    private static final String UTF8 = "UTF-8";
    private static UTF8Encoder utf8EncoderInstance;
    private static final org.apache.log4j.Logger logger = Logger.getLogger(IngredientDAOImpl.class);

    private UTF8Encoder() {
    }

    public static UTF8Encoder getUtf8EncoderInstance() {
        if (utf8EncoderInstance == null)
            synchronized (UTF8Encoder.class) {
                if (utf8EncoderInstance == null)
                    utf8EncoderInstance = new UTF8Encoder();
            }
        return utf8EncoderInstance;
    }

    public void encode(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding(UTF8);
            response.setCharacterEncoding(UTF8);
        } catch (UnsupportedEncodingException e) {
            logger.log(Level.ERROR, "Exception", e);
            e.printStackTrace();
        }
    }
}
