package com.epam.audiospot.customtag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PaginationTag extends SimpleTagSupport {
    private static final String A_TAG_START = "<a class=";
    private static final String A_TAG_HREF = " href=\"controller?";
    private static final String PAGE_PARAMETER_START = "&page=";
    private static final String PAGE_PARAM_END ="\">";
    private static final String A_TAG_END = "</a>";
    private static final String CONTROLLER_CONTEXT="/controller?";
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
    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        int pagesAmount = (entryAmount/amountByPage)+1;

        PageContext pageContext = (PageContext) getJspContext();
        ServletRequest request = pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

        request.setAttribute("pagesAmount",pagesAmount);
        String pageParameter = request.getParameter("page");

        Map<String,String[]> requestParameters = request.getParameterMap();
        int page;
        if(pageParameter==null){
            page=1;
            String target = buildRedirect(requestParameters,page);
            response.sendRedirect(target);
        }else{
            page= Integer.parseInt(pageParameter);
        }

        if(page>pagesAmount){
            String target = buildRedirect(requestParameters,pagesAmount);
            response.sendRedirect(target);
        }else if(page<1){
            String target = buildRedirect(requestParameters,1);
            response.sendRedirect(target);
        }

        if(page>1){
            int prevPage = page-1;
            out.print(buildATag(prevText,requestParameters,prevPage));
        }
        if(page<pagesAmount){
            int nextPage = page+1;
            out.print(buildATag(nextText,requestParameters,nextPage));
        }
    }

    private String buildATag(String content,Map<String,String[]> params,int pageNumber){
        return A_TAG_START
                +styleClass
                +A_TAG_HREF
                +buildParamString(params)
                +PAGE_PARAMETER_START
                +pageNumber
                +PAGE_PARAM_END
                +content
                +A_TAG_END;
    }

    private String buildRedirect(Map<String,String[]> params, int pageNumber){
        return CONTROLLER_CONTEXT
                +buildParamString(params)
                +PAGE_PARAMETER_START
                +pageNumber;
    }

    private String buildParamString(Map<String,String[]> params){
        StringBuilder paramString= new StringBuilder();
        List<Map.Entry<String, String[]>> entries = new ArrayList <>(params.entrySet());
        for(int i=0;i<params.size();i++){
            if(entries.get(i).getKey().equals("page")){
                continue;
            }
            if(i!=0){
                paramString.append("&");
            }
            paramString.append(entries.get(i).getKey())
                        .append("=")
                        .append(entries.get(i).getValue()[0]);
        }
        return paramString.toString();
    }
}