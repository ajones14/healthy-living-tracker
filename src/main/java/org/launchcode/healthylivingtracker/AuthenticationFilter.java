package org.launchcode.healthylivingtracker;

import org.launchcode.healthylivingtracker.controllers.AuthenticationController;
import org.launchcode.healthylivingtracker.data.UserRepository;
import org.launchcode.healthylivingtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends HandlerInterceptorAdapter {



    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        User theUser = userRepository.findByUsername(request.getSession().getId());
        if (theUser == null) {
            return true;
        } else {
            try {
                response.sendRedirect("/login");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

}
