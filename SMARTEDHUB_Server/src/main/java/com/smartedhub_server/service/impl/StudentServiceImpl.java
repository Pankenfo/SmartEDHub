package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.config.security.JwtTokenUtil;
import com.smartedhub_server.exception.ParamsException;
import com.smartedhub_server.mapper.StudentMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.pojo.UserLoginInfo;
import com.smartedhub_server.service.ICorrectionNotebookService;
import com.smartedhub_server.service.IMyFavouriteService;
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

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private IMyFavouriteService iMyFavouriteService; //新用户注册自动创建收藏夹 kevin
    @Autowired
    private ICorrectionNotebookService iCorrectionNotebookService; //新用户注册自动创建错题本 kevin
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public Student getStudentByUserName(String userName) {
        return studentMapper.selectOne(new QueryWrapper<Student>().eq("username", userName).eq("validity", true));
    }

    /**
     * For admin get student by username
     * @param userName
     * @return
     */
    @Override
    public Student getStudentByUserNameIgnoreValidity(String userName) {

        return studentMapper.getStudentByUserNameIgnoreValidity(userName);
    }

    /**
     * Get current studentId
     * @return
     */
    @Override
    public int getCurrentStudentId() throws ParamsException {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() != null) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    String username = ((UserDetails) principal).getUsername();
                    Student student = getStudentByUserName(username);
                    if (student != null) {
                        return student.getStudentId();
                    }
                }
            }
            throw new ParamsException();
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
                iMyFavouriteService.CreateMyFavourite(student.getUsername());
                iCorrectionNotebookService.CreateCorrection(student.getUsername());
                return GeneralReturn.success("Register successfully");
            } else {
                return GeneralReturn.error("Register failed");
            }
        } else {
            return GeneralReturn.error("Username already exists, please try another one");
        }
    }

    @Override
    public GeneralReturn studentOrTeacherLogin(String username, String password, HttpServletRequest request) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return GeneralReturn.error("Username or password is wrong");
        }
        if (!userDetails.isEnabled()) {
            return GeneralReturn.error("Current account is disabled, please contact our service team");
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
     * Update student avatar
     * @param url
     * @param userId
     * @param authentication
     * @return
     */
    @Override
    public GeneralReturn updateStudentAvatar(String url, Integer userId, Authentication authentication) {

        Student student = studentMapper.selectById(userId);
        student.setAvatar(url);
        int result = studentMapper.updateById(student);
        if (result == 1) {
            Student principal = (Student) authentication.getPrincipal();
            principal.setAvatar(url);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(student, null, authentication.getAuthorities()));
            return GeneralReturn.success("Update avatar successfully", url);
        }
        return GeneralReturn.error("Update avatar failed");
    }


    /**
     * For admin get all students
     * @return
     */
    @Override
    public List<Student> getAllStudent() {
        List<Student> allStudent = studentMapper.getAllStudent();
        return allStudent;

    }

    /**
     * For admin enable student
     * @param username
     * @return
     */
    @Override
    public GeneralReturn enableStudent(String username) {
        int result = studentMapper.enableStudent(username);
        if (result == 1) {
            return GeneralReturn.success("Enable student");
        }
        return GeneralReturn.error("Enable student failed");
    }

}
