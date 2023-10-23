package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IClassroomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private IClassroomService iClassroomService;

    /**
     * Create a new classroom - 10-22 jimmy改了username的加入添加方法
     * @param newClassroom
     * @param principal
     * @return
     */
    @PostMapping("/createClassroom")
    @ApiOperation("Create a new classroom")
    public GeneralReturn CreateClass(@RequestBody Classroom newClassroom, Principal principal){
        if (principal == null) {
            return GeneralReturn.error("Please login first");
        }
        String username = principal.getName();
        return iClassroomService.CreateClass(newClassroom, username);
    }

//    @GetMapping("/getAllOrSpecificClassroom")
//    @ApiOperation("Get all or specific classroom")
//    public GeneralReturn GetAllOrSpecificClassroom(@RequestParam(value = "pageNo") int pageNo,
//                                                   @RequestParam(value = "pageSize") int pageSize,
//                                                   @RequestParam(value = "classname", required = false) String classname){
//        return iClassroomService.GetAllOrSpecificClassroom(pageNo,pageSize,classname);
//    }

    @GetMapping("/getAllOrSpecificClassroom")
    @ApiOperation("Get all or specific classroom")
    public GeneralReturn GetAllOrSpecificClassroom(@RequestParam(value = "pageNo") int pageNo,
                                                   @RequestParam(value = "pageSize") int pageSize,
                                                   @RequestParam(value = "classname", required = false) String classname,
                                                   @RequestParam(value = "Teacherusername", required = false) String username){
        return iClassroomService.GetAllOrSpecificClassroom(pageNo,pageSize,classname,username);
    }

    @DeleteMapping("/deleteClassroom")
    @ApiOperation(value = "Delete a classroom")
    public GeneralReturn DeleteClassroom(@RequestParam(value = "classroomId") Integer classroomId){
        return iClassroomService.DeleteClassroom(classroomId);
    }
}
