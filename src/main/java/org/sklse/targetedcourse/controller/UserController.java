package org.sklse.targetedcourse.controller;

import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.sklse.targetedcourse.bean.Guardian;
import org.sklse.targetedcourse.bean.ResultModel;
import org.sklse.targetedcourse.bean.Teacher;
import org.sklse.targetedcourse.repository.GuardianRepository;
import org.sklse.targetedcourse.repository.StuClassRepository;
import org.sklse.targetedcourse.repository.StudentRepository;
import org.sklse.targetedcourse.repository.TeacherRepository;
import org.sklse.targetedcourse.service.StuClassService;
import org.sklse.targetedcourse.service.TeacherService;
import org.sklse.targetedcourse.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;


@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StuClassService stuClassService;

    @Autowired
    private StuClassRepository stuClassRepository;

    @ApiOperation(value = "登录")
    @PostMapping(value = "/teacher/login")
    public ResponseEntity<ResultModel> Login(@ModelAttribute Teacher teacher) throws ServletException {

        String phoneNumber = teacher.getPhoneNumber();
        String password = teacher.getPassword();
        if (phoneNumber == null || password == null) {

            throw new ServletException("Please fill in username and password");
        }
        String jwtToken = "";
        Teacher user = teacherRepository.findByPhoneNumber(phoneNumber);

        if (user == null) {
            throw new ServletException("User phoneNumber not found.");
        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "Invalid login. Please check your name and password."), HttpStatus.FORBIDDEN);
        }

        jwtToken = Jwts.builder().setSubject(phoneNumber).claim("roles", "teacher").setIssuedAt(new Date())
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, jwtToken), HttpStatus.OK);
    }


    @ApiOperation(value = "注册")
    @PostMapping(value = "/teacher/register")
    public ResponseEntity<ResultModel> register(Teacher teacher) throws ServletException {
        String phoneNumber = teacher.getPhoneNumber();
        String password = teacher.getPassword();

        String message = Validator.isPhoneNumberAndPassword(phoneNumber, password);
        if (!message.equals("true")) {


            return new ResponseEntity<>(ResultModel.error(HttpStatus.BAD_REQUEST, message), HttpStatus.BAD_REQUEST);
        }


        if (teacherRepository.findByPhoneNumber(phoneNumber) != null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "Account already exists."), HttpStatus.FORBIDDEN);

        }
        teacherRepository.save(teacher);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, "注册成功"), HttpStatus.OK);

    }


    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "电话号码", required = true, dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "string")
    })
    @RequestMapping(value = "/guardian/login", method = RequestMethod.POST)
    public ResponseEntity<ResultModel> Login(Guardian login) throws ServletException {
        String jwtToken = "";

        if (login.getPhoneNumber() == null || login.getPassword() == null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.NOT_FOUND, "Please fill in username and password"), HttpStatus.NOT_FOUND);

        }

        String phoneNumber = login.getPhoneNumber();
        String password = login.getPassword();

        Guardian user = guardianRepository.findByPhoneNumber(phoneNumber);

        if (user == null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.NOT_FOUND, "User phoneNumber not found."), HttpStatus.NOT_FOUND);

        }

        String pwd = user.getPassword();

        if (!password.equals(pwd)) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "Invalid login. Please check your name and password."), HttpStatus.FORBIDDEN);

        }

        jwtToken = Jwts.builder().setSubject(phoneNumber).claim("roles", "guardian").setIssuedAt(new Date())
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, "secretkey").compact();

        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, jwtToken), HttpStatus.OK);
    }


    @ApiOperation(value = "注册")
    @PostMapping(value = "/guardian/register")
    public ResponseEntity<ResultModel> register(Guardian guardian) throws ServletException {
        String phoneNumber = guardian.getPhoneNumber();
        String password = guardian.getPassword();


        String message = Validator.isPhoneNumberAndPassword(phoneNumber, password);
        if (!message.equals("true")) {


            return new ResponseEntity<>(ResultModel.error(HttpStatus.BAD_REQUEST, message), HttpStatus.BAD_REQUEST);
        }
        if (guardianRepository.findByPhoneNumber(phoneNumber) != null) {
            return new ResponseEntity<>(ResultModel.error(HttpStatus.FORBIDDEN, "Account already exists."), HttpStatus.FORBIDDEN);

        }

        guardianRepository.save(guardian);
        return new ResponseEntity<>(ResultModel.ok(HttpStatus.OK, "注册成功"), HttpStatus.OK);

    }

}
