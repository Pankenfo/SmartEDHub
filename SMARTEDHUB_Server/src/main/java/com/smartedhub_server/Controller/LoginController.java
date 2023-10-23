package com.smartedhub_server.Controller;

import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.pojo.UserLoginInfo;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @program: SMARTEDHUB
 * @description: For login model
 * @author: Junxian Cai
 **/

@RestController
@Api(tags = "LoginController")
public class LoginController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private ITeacherService teacherService;

    /**
     * Login
     * @param student
     * @return
     */
    @PostMapping("/studentRegister")
    @ApiOperation(value = "student register")
    public GeneralReturn studentRegister(@RequestBody Student student) {
        return studentService.studentRegister(student);
    }

    @PostMapping("/login")
    @ApiOperation(value = "user login")
    public GeneralReturn login(@RequestBody UserLoginInfo userLoginInfo, HttpServletRequest  request) {
        return studentService.studentOrTeacherLogin(userLoginInfo.getUsername(), userLoginInfo.getPassword(), request);
    }

    @PostMapping("/getCurrentStudentDetails")
    @ApiModelProperty(value = "get current student details")
    public Student getCurrentStudentDetails(Principal principal) {
        //如果是空证明spring security里面没有这个用户
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Student student = studentService.getStudentByUserName(username);
        //Do not return the password
        student.setPassword(null);
        return student;
    }

    @PostMapping("/getCurrentTeacherDetails")
    @ApiModelProperty(value = "get current teacher details")
    public Teacher getCurrentTeacherDetails(Principal principal) {
        //如果是空证明spring security里面没有这个用户
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Teacher teacher = teacherService.getTeacherByUserName(username);
        //Do not return the password
        teacher.setPassword(null);
        return teacher;
    }

    @PostMapping("/teacherRegister")
    @ApiOperation(value = "teacher register")
    public GeneralReturn teacherRegister(@RequestBody Teacher teacher) {
        return teacherService.teacherRegister(teacher);
    }
}
