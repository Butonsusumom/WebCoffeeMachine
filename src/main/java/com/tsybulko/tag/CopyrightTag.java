package com.tsybulko.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.*;
import java.io.IOException;

@SuppressWarnings("serial")
public class CopyrightTag extends TagSupport {
    private static final String COPYRIGHT_TEXT = "Copyright \u00a9 2020 Online Coffee Machine";

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(COPYRIGHT_TEXT);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}

