package org.launchcode.healthylivingtracker;

import org.launchcode.healthylivingtracker.controllers.AuthenticationController;
import org.launchcode.healthylivingtracker.data.UserRepository;
import org.launchcode.healthylivingtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.ArrayUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    private String[] whitelist = new String[]{"/login", "/register"};

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private boolean containedInWhitelist(String path) {
        return ArrayUtils.contains(whitelist, path);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (containedInWhitelist(request.getRequestURI())) {
//            response.sendRedirect("home");
            return true;
        } else {
            User theUser = userRepository.findByUsername(request.getRemoteUser());
            if (theUser == null) {
                response.sendRedirect("login");
                return false;
            } else {
//                response.sendRedirect("home");
                return true;
            }
        }
    }

}
