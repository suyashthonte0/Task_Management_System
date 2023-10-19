package com.project.tasks.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = null;
        try {
            final HttpServletRequest request = (HttpServletRequest) servletRequest;
            response = (HttpServletResponse) servletResponse;
            final String authHeader = request.getHeader("authorization");
            if ("OPTIONS".equals(request.getMethod())) {
                response.setStatus(HttpServletResponse.SC_OK);
                filterChain.doFilter(request, response);
            } else {
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new ServletException("An exception occurred");
                }
            }
            final String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            String role = (String) claims.get("role");
            request.setAttribute("role", role);
            request.setAttribute("claims", claims);
            request.setAttribute("blog", servletRequest.getParameter("id"));
            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: " + ex.getMessage());
        }
    }
}