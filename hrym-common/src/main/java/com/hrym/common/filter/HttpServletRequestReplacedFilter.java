package com.hrym.common.filter;

import com.hrym.common.request.MyHttpServletRequestWrapper;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartRequest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * 创建一个实现Filter的类，重写doFilter方法，将ServletRequest替换为自定义的request类
 * Created by hong on 2017/6/27.
 */
public class HttpServletRequestReplacedFilter implements Filter {

    private String[] excludedUrls;
    @Override
    public void destroy() {
        excludedUrls = null;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        // 判断是否文件上传
        boolean isMultipart = ServletFileUpload.isMultipartContent((HttpServletRequest)request);

        if(isMultipart)
            chain.doFilter(request, response);
        else {

            // 不是文件上传时，将request转换为自定义MyHttpServletRequestWrapper
            ServletRequest requestWrapper = null;

            if(request instanceof HttpServletRequest) {
                requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);
            }
            if(requestWrapper == null) {
                chain.doFilter(request, response);
            } else {
                chain.doFilter(requestWrapper, response);
            }
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        String urlStr = filterConfig.getInitParameter("excludedUrls");
        if(!urlStr.isEmpty()) {
            this.excludedUrls = urlStr.split(",");
        }

    }
}
