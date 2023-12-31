package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.exception.ParamsException;
import com.smartedhub_server.pojo.Comment;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.Student;
import com.smartedhub_server.pojo.UserLoginInfo;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IStudentService extends IService<Student> {

    /**
     * Return student by username
     * @param userName
     * @return
     */
    Student getStudentByUserName(String userName);

    Student getStudentByUserNameIgnoreValidity(String userName);

    int getCurrentStudentId() throws ParamsException;

    /**
     * For student studentRegister
     * @return
     */
    GeneralReturn studentRegister(Student student);

    /**
     * For student login
     * @param username
     * @param password
     * @param request
     * @return
     */
    GeneralReturn studentOrTeacherLogin(String username, String password, HttpServletRequest request);

    /**
     * For student update student avatar
     * @param url
     * @param currentUsername
     * @param authentication
     * @return
     */
    GeneralReturn updateStudentAvatar(String url, String currentUsername, Authentication authentication);

    /**
     * For Admin get all comments
     * @return
     */
    List<Student> getAllStudent();

    /**
     * For admin enable student
     * @param username
     */
    GeneralReturn enableStudent(String username);
}
