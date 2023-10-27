package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.Admin;
import com.smartedhub_server.pojo.GeneralReturn;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IAdminService extends IService<Admin> {

    /**
     * For admin login
     * @param admin
     * @return
     */
    GeneralReturn registerAdmin(Admin admin);

    Admin getAdminByUserName(String username);

    /**
     * For admin login
     * @param username
     * @param password
     * @param request
     * @return
     */
    GeneralReturn adminLogin(String username, String password, HttpServletRequest request);

    /**
     * For admin upload avatar
     * @param url
     * @param userId
     * @param authentication
     * @return
     */
    GeneralReturn updateAdminAvatar(String url, Integer userId, Authentication authentication);

    /**
     * For admin modify teacher validity
     * @param username
     * @return
     */
    GeneralReturn modifyTeacherValidity(String username);

    /**
     * For admin modify student validity
     * @param username
     * @return
     */
    GeneralReturn modifyStudentValidity(String username);
}
