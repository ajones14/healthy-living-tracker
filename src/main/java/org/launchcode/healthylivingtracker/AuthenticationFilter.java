package org.launchcode.healthylivingtracker;

import org.launchcode.healthylivingtracker.controllers.AuthenticationController;
import org.launchcode.healthylivingtracker.data.UserRepository;
import org.launchcode.healthylivingtracker.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter extends HandlerInterceptorAdapter {

    //private String[] whitelist = new String[]{"/login", "/register"};
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/images", "/styles");

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;

    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }

//    private boolean containedInWhitelist(String path) {
//        return ArrayUtils.contains(whitelist, path);
//    }

//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//
//        if (containedInWhitelist(request.getRequestURI())) {
//            System.out.println("first block");
//            System.out.println(request.getRequestURI());
//            return true;
//        } else {
//            try {
//                System.out.println("try block");
//                System.out.println(request.toString());
//                System.out.println(request.getAttribute("userSessionKey"));
//                Object user = request.getSession().getAttribute("user");
//                userRepository.findByUsername(user.g);
//            } catch (NullPointerException e) {
//                System.out.println("catch block");
//                response.sendRedirect("login");
//                return false;
//            }
//            return true;
//        }
//
//    }

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Don't require sign-in for whitelisted pages
        if (isWhitelisted(request.getRequestURI())) {
            // returning true indicates that the request may proceed
            return true;
        }

        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in
        if (user != null) {
            return true;
        }

        // The user is NOT logged in
        response.sendRedirect("/login");
        return false;
    }

}
