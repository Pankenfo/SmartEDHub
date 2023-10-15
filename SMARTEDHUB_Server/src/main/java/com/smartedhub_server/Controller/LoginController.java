package com.smartedhub_server.Controller;

import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.pojo.UserLoginInfo;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping("/studentLogin")
    @ApiOperation(value = "student login")
    public GeneralReturn studentLogin(@RequestBody UserLoginInfo userLoginInfo, HttpServletRequest  request) {
        return studentService.studentLogin(userLoginInfo.getUsername(), userLoginInfo.getPassword(), request);
    }

    @PostMapping("/teacherRegister")
    @ApiOperation(value = "teacher register")
    public GeneralReturn teacherRegister(@RequestBody Teacher teacher) {
        return teacherService.teacherRegister(teacher);
    }
}
