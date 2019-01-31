package com.epam.audiospot.customtag;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.*;

public class PaginationTag extends SimpleTagSupport {
    private static final String PAGE_PARAM = "page";
    private static final String PREV_HREF_ATTR = "prevHref";
    private static final String NEXT_HREF_ATTR = "nextHref";
    private static final String HREF_START = "controller?";
    private static final String PAGE_PARAMETER_START = "&page=";
    private static final String CONTROLLER_CONTEXT = "/controller?";
    private Iterator iterator;
    private Collection items;
    private String var;
    private int perPage;

    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        ServletRequest request = pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

        String pageParameter = request.getParameter(PAGE_PARAM);
        Map <String, String[]> requestParameters = request.getParameterMap();
        int page;

        if (pageParameter == null) {
            page = 1;
            if (!(items.size() <= perPage)) {
                String target = buildRedirect(requestParameters, page);
                response.sendRedirect(target);
            }
        } else {
            page = Integer.parseInt(pageParameter);
        }

        int pagesAmount = (items.size() / perPage) + 1;

        if (page > pagesAmount) {
            String target = buildRedirect(requestParameters, pagesAmount);
            response.sendRedirect(target);
        } else if (page < 1) {
            String target = buildRedirect(requestParameters, 1);
            response.sendRedirect(target);
        }

        if (iterator == null) {
            return;
        }
        for (int i = 0; i < items.size(); i++) {
            if (iterator.hasNext() && i >= (page * perPage - perPage) && i < (page * perPage)) {
                getJspContext().setAttribute(var, iterator.next());
                getJspBody().invoke(null);
            } else {
                iterator.next();
            }
        }

        if (page > 1) {
            int prevPage = page - 1;
            pageContext.setAttribute(PREV_HREF_ATTR, buildHef(requestParameters, prevPage));
        }
        if (page < pagesAmount) {
            int nextPage = page + 1;
            pageContext.setAttribute(NEXT_HREF_ATTR, buildHef(requestParameters, nextPage));
        }
    }

    public void setVar(String var) {
        this.var = var;
    }

    public void setItems(Collection items) {
        this.items = items;
        if (items.size() > 0) {
            iterator = items.iterator();
        }
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    private String buildHef(Map <String, String[]> params, int pageNumber) {
        return HREF_START
                + buildParamString(params)
                + PAGE_PARAMETER_START
                + pageNumber;
    }

    private String buildRedirect(Map <String, String[]> params, int pageNumber) {
        return CONTROLLER_CONTEXT
                + buildParamString(params)
                + PAGE_PARAMETER_START
                + pageNumber;
    }

    private String buildParamString(Map <String, String[]> params) {
        StringBuilder paramString = new StringBuilder();
        List <Map.Entry <String, String[]>> entries = new ArrayList <>(params.entrySet());

        for (int i = 0; i < params.size(); i++) {
            if (entries.get(i).getKey().equals(PAGE_PARAM)) {
                continue;
            }
            if (i != 0) {
                paramString.append("&");
            }
            paramString.append(entries.get(i).getKey())
                    .append("=")
                    .append(entries.get(i).getValue()[0]);
        }
        return paramString.toString();
    }
}