package com.epam.audiospot.customtag;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class QuoteEscapeTag extends SimpleTagSupport {
    private String content;

    @Override
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        String escapedContent = content.replaceAll("'", "\\\\'").trim();
        out.print(escapedContent);
    }

    public void setContent(String content) {
        this.content = content;
    }
}
