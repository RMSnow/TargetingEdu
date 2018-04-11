package org.sklse.targetedcourse.service;


import io.jsonwebtoken.Claims;
import org.sklse.targetedcourse.bean.*;
import org.sklse.targetedcourse.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


@Service
public class UserService {


    @Autowired
    private  TeacherRepository teacherRepository;
    @Autowired
    private   GuardianRepository guardianRepository;

    public  Teacher currentTeacher(HttpServletRequest request) throws ServletException {
        Claims claims = (Claims) request.getAttribute("claims");
        String phoneNumber = (String) claims.get("sub");
        String role = (String) claims.get("roles");
        if (role.equals("teacher")) {
            return teacherRepository.findByPhoneNumber(phoneNumber);
        } else {
            throw new ServletException("Current user not found.");

        }
    }


    public   Guardian currentGuardian(HttpServletRequest request) throws ServletException {
        Claims claims = (Claims) request.getAttribute("claims");
        String phoneNumber = (String) claims.get("sub");
        System.out.println("phoneNumber: "+phoneNumber);
        String role = (String) claims.get("roles");
        if (role.equals("guardian")) {
            return guardianRepository.findByPhoneNumber("22");
        } else {
            throw new ServletException("Current user not found.");
        }
    }

}
