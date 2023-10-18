package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IClassroomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private IClassroomService iClassroomService;

    @PostMapping("/createClass")
    @ApiOperation("Create a new class")
    public GeneralReturn CreateClass(@RequestBody Classroom newClassroom){
        return iClassroomService.CreateClass(newClassroom);
    }
}
