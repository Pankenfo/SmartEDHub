package com.smartedhub_server.Controller;


import com.smartedhub_server.mapper.CommentMapper;
import com.smartedhub_server.pojo.Comment;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.ICommentService;
import com.smartedhub_server.service.IStudentService;
import com.smartedhub_server.util.FastDFSUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;

    //TODO: 头像
    @ApiOperation(value = "updateAvatar")
    @PostMapping("/updateAvatar")
    public GeneralReturn updateAvatar(@RequestBody MultipartFile multipartFile, Principal principal, Authentication authentication) {

        if (principal == null){
            return GeneralReturn.error("Please login first");
        } else {
            String currentUsername = principal.getName();
            //Upload file by FastDFS
            String[] uploadPath = FastDFSUtils.upload(multipartFile);
            //get the url
            String url = FastDFSUtils.getTrackerUrl() + uploadPath[0] + "/" + uploadPath[1];
            return studentService.updateStudentAvatar(url, currentUsername, authentication);
        }

    }





}
