package com.smartedhub_server.Controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smartedhub_server.mapper.StudentMapper;
import com.smartedhub_server.mapper.TeacherMapper;
import com.smartedhub_server.pojo.*;
import com.smartedhub_server.service.IAdminService;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import com.smartedhub_server.util.FastDFSUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "AdminController")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    @ApiOperation(value = "registerAdmin")
    @PostMapping("/registerAdmin")
    public GeneralReturn registerAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/adminLogin")
    @ApiOperation(value = "user login")
    public GeneralReturn login(@RequestBody UserLoginInfo userLoginInfo, HttpServletRequest request) {
        return adminService.adminLogin(userLoginInfo.getUsername(), userLoginInfo.getPassword(), request);
    }

    @GetMapping("/getCurrentAdminInfo")
    @ApiOperation(value = "get current admin info")
    public Admin getCurrentAdminInfo(Principal principal) {
        if (principal == null) {
            return null;
        } else {
            String name = principal.getName();
            Admin adminByUserName = adminService.getAdminByUserName(name);
            adminByUserName.setPassword(null);
            return adminByUserName;
        }
    }

    @ApiOperation(value = "updateAvatar")
    @PostMapping("/updateAvatar")
    public GeneralReturn updateAvatar(@RequestBody MultipartFile multipartFile, @RequestParam Integer userId, Authentication authentication) {
        //Upload file by FastDFS
        String[] uploadPath = FastDFSUtils.upload(multipartFile);
        //get the url
        String url = FastDFSUtils.getTrackerUrl() + uploadPath[0] + "/" + uploadPath[1];
        return adminService.updateAdminAvatar(url, userId, authentication);
    }

    /**
     * 1: student 2: teacher 3: admin
     */
    @ApiOperation(value = "invalid user")
    @PostMapping("/invalidUser")
    public GeneralReturn invalidUser(@RequestParam Integer userType, @RequestParam String username) {

        if (userType == 2) {
            int result = teacherMapper.delete(new QueryWrapper<Teacher>().eq("username", username));
            if (result == 1) {
                return GeneralReturn.success("Teacher set to invalid");
            } else {
                return GeneralReturn.error("Please try again.");
            }
        } else if (userType == 1) {
            int result = studentMapper.delete(new QueryWrapper<Student>().eq("username", username));
            if (result == 1) {
                return GeneralReturn.success("Student set to invalid");
            } else {
                return GeneralReturn.error("Please try again.");
            }
        }
        return GeneralReturn.error("Invalid user type");
    }

    @ApiOperation(value = "enableUser")
    @PostMapping("/enableUser")
    public GeneralReturn enableUser(@RequestParam Integer userType, @RequestParam String username) {
        if (userType == 1) {
            return studentService.enableStudent(username);
        } else if (userType == 2) {
            return teacherService.enableTeacher(username);
        }
        return GeneralReturn.error("Invalid user type");
    }


    @ApiOperation(value = "get all student")
    @GetMapping("/getAllStudent")
    public List<Student> getAllStudent() {
        List<Student> studentList = studentService.getAllStudent();
        return studentList;
    }

    @ApiOperation(value = "get all teacher")
    @GetMapping("/getAllTeacher")
    public List<Teacher> getAllTeacher() {
        List<Teacher> teacherList = teacherService.getAllTeacher();
        return teacherList;
    }



}
