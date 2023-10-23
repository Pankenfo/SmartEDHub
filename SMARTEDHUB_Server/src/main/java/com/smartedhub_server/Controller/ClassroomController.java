package com.smartedhub_server.Controller;


import com.fasterxml.jackson.core.base.GeneratorBase;
import com.smartedhub_server.pojo.Classroom;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IClassroomService;
import com.smartedhub_server.service.IStudentClassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private IStudentClassService iStudentClassService;

    @PostMapping("/createClassroom")
    @ApiOperation("Create a new classroom")
    public GeneralReturn CreateClass(@RequestBody Classroom newClassroom){
        return iClassroomService.CreateClass(newClassroom);
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

    @PostMapping("/addToClassroom")
    @ApiOperation("Add a student to classroom ")
    public GeneralReturn AddToClassroom(@RequestParam(value = "studentId") Integer studentId,
                                        @RequestParam(value = "classId") Integer classId) {
        return iStudentClassService.AddToClassroom(studentId, classId);
    }

    @GetMapping("/showClassDetail")
    @ApiOperation("Shwo the detail of the classroon")
    public GeneralReturn ShowClassDetail(@RequestParam(value = "classId") Integer classId){
        return iStudentClassService.ShowClassDetail(classId);
    }

    @GetMapping("/showTeacherClassList")
    @ApiOperation("Show the list of class")
    public GeneralReturn ShowTeacherClassList(@RequestParam(value = "teacherUsername") String teaUsername){
        return iStudentClassService.ShowTeacherClassList(teaUsername);
    }

    @GetMapping("/countStudent")
    @ApiOperation("Count the number of students")
    public Long CountStudent(@RequestParam(value = "classId") Integer classId){
        return iStudentClassService.CountStudent(classId);
    }
}
