package com.smartedhub_server.service.impl;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.config.security.JwtTokenUtil;
import com.smartedhub_server.mapper.AdminMapper;
import com.smartedhub_server.mapper.StudentMapper;
import com.smartedhub_server.mapper.TeacherMapper;
import com.smartedhub_server.pojo.Admin;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.service.IAdminService;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    /**
     * For admin login
     * @param admin
     * @return
     */
    @Override
    public GeneralReturn registerAdmin(Admin admin) {

        Admin adminByUserName = getAdminByUserName(admin.getUsername());
        Student studentByUserName = studentService.getStudentByUserName(admin.getUsername());
        Teacher teacherByUserName = teacherService.getTeacherByUserName(admin.getUsername());


        if (adminByUserName == null && studentByUserName == null && teacherByUserName == null && admin.getUsername() != null) {

            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            admin.setValidity(true);
            admin.setUsername(admin.getUsername());

            int result = adminMapper.insert(admin);
            if (result == 1) {
                return GeneralReturn.success("Register successfully", admin);
            }
            return GeneralReturn.error("Register failed, please try again");
        }
        return GeneralReturn.error("Username already exists");
    }

    /**
     * Get admin by username
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        Admin admin = adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("validity", true));
        return admin;
    }

    /**
     * For admin login
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public GeneralReturn adminLogin(String username, String password, HttpServletRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return GeneralReturn.error("Username or password is wrong");
        }
        if (!userDetails.isEnabled()) {
            return GeneralReturn.error("Current account is disabled");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails
                ,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //Generate token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return GeneralReturn.success("Login successfully", tokenMap);

    }

    /**
     * For admin avatar
     * @param url
     * @param userId
     * @param authentication
     * @return
     */
    @Override
    public GeneralReturn updateAdminAvatar(String url, Integer userId, Authentication authentication) {
        Admin admin = adminMapper.selectById(userId);
        admin.setAvatar(url);
        int result = adminMapper.updateById(admin);
        if (result == 1) {
            Student principal = (Student) authentication.getPrincipal();
            principal.setAvatar(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin, null, authentication.getAuthorities()));
            return GeneralReturn.success("Update avatar successfully", url);
        }
        return GeneralReturn.error("Update avatar failed");
    }

    /**
     * For admin modify teacher validity
     * @param username
     * @return
     */
    @Override
    public GeneralReturn modifyTeacherValidity(String username) {
        Teacher teacherByUserName = teacherService.getTeacherByUserName(String.valueOf(username));
        if (teacherByUserName == null) {
            return GeneralReturn.error("Teacher not found");
        }else {
            teacherByUserName.setValidity(false);
            int result = teacherMapper.updateById(teacherByUserName);
            if (result == 1) {
                return GeneralReturn.success("Modify teacher validity successfully");
            }
        }
        return GeneralReturn.error("Modify teacher validity failed");
    }

    /**
     * For admin modify student validity
     * @param username
     * @return
     */
    @Override
    public GeneralReturn modifyStudentValidity(String username) {
        Student studentByUserName = studentService.getStudentByUserName(String.valueOf(username));
        if (studentByUserName == null) {
            return GeneralReturn.error("Student not found");
        }else {
            studentByUserName.setValidity(false);
            int result = studentMapper.updateById(studentByUserName);
            if (result == 1) {
                return GeneralReturn.success("Modify student validity successfully");
            }
        }
        return GeneralReturn.error("Modify student validity failed");
    }
}
