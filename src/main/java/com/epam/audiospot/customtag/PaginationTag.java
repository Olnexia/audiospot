package com.epam.audiospot.customtag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class PaginationTag extends SimpleTagSupport {
    private static final String A_TAG_START = "<a class=";
    private static final String A_TAG_HREF = " href=\"controller?command=";
    private static final String PAGE_PARAMETER_START = "&page=";
    private static final String PAGE_PARAM_END ="\">";
    private static final String A_TAG_END = "</a>";
    private static final String CONTROLLER_CONTEXT="/controller?command=";
    private int entryAmount;
    private int amountByPage;
    private String prevText;
    private String nextText;
    private String styleClass;

    public void setEntryAmount(int entryAmount) {
        this.entryAmount = entryAmount;
    }

    public void setAmountByPage(int amountByPage) {
        this.amountByPage = amountByPage;
    }

    public void setPrevText(String prevText) {
        this.prevText = prevText;
    }

    public void setNextText(String nextText) {
        this.nextText = nextText;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        int pagesAmount = (entryAmount/amountByPage)+1;

        PageContext pageContext = (PageContext) getJspContext();
        ServletRequest request = pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

        request.setAttribute("pagesAmount",pagesAmount);
        String pageParameter = request.getParameter("page");
        String command = request.getParameter("command");

        int page;
        if(pageParameter==null){
            page=1;
            String target = buildRedirect(command,page);
            response.sendRedirect(target);
        }else{
            page= Integer.parseInt(pageParameter);
        }

        if(page>pagesAmount){
            String target = buildRedirect(command,pagesAmount);
            response.sendRedirect(target);
        }else if(page<1){
            String target = buildRedirect(command,1);
            response.sendRedirect(target);
        }

        if(page>1){
            int prevPage = page-1;
            out.print(buildATag(command,prevText,prevPage));
        }
        if(page<pagesAmount){
            int nextPage = page+1;
            out.print(buildATag(command,nextText,nextPage));
        }
    }

    private String buildATag(String command,String content,int pageNumber){
        return A_TAG_START
                +styleClass
                +A_TAG_HREF
                +command
                +PAGE_PARAMETER_START
                +pageNumber
                +PAGE_PARAM_END
                +content
                +A_TAG_END;
    }

    private String buildRedirect(String command,int pageNumber){
        return CONTROLLER_CONTEXT
                +command
                +PAGE_PARAMETER_START
                +pageNumber;
    }
}