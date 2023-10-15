package com.smartedhub_server.config.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: SMARTEDHUB_Server
 * @description: Blocking when accessing interfaces without permissions(无权限时拦截)
 * @author: Junxian Cai
 **/

@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        PrintWriter output  = httpServletResponse.getWriter();
        GeneralReturn generalReturn = GeneralReturn.error("Sorry, insufficient authority.");
        generalReturn.setCode(403);
        output.write(new ObjectMapper().writeValueAsString(generalReturn));
        output.flush();
        output.close();
    }
}
