package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.config.security.JwtTokenUtil;
import com.smartedhub_server.mapper.StudentMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.pojo.UserLoginInfo;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Student getStudentByUserName(String userName) {
        return studentMapper.selectOne(new QueryWrapper<Student>().eq("username", userName).eq("validity", true));
    }

    /**
     * For student register
     * @param student
     * @return
     */
    @Override
    public GeneralReturn studentRegister(Student student) {

        Student studentByUserName = getStudentByUserName(student.getUsername());
        Teacher teacherByUserName = teacherService.getTeacherByUserName(student.getUsername());

        if (studentByUserName == null && student.getUsername() != null && teacherByUserName == null) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
            student.setValidity(true);
            student.setRightToComment(1);

            int result = studentMapper.insert(student);
            if (result == 1) {
                return GeneralReturn.success("Register successfully");
            } else {
                return GeneralReturn.error("Register failed");
            }
        } else {
            return GeneralReturn.error("Username already exists, please try another one");
        }
    }

    @Override
    public GeneralReturn studentLogin(String username, String password, HttpServletRequest request) {

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

}
