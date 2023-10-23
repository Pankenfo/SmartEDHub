package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.MyFavourite;
import com.smartedhub_server.service.IMyFavouriteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * The notebook that saves the question that student favourite 前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/my-favourite")
public class MyFavouriteController {
    @Autowired
    private IMyFavouriteService iMyFavouriteService;

    @PutMapping("/addToFavourite")
    @ApiOperation("add a question to myFavourite")
    public GeneralReturn AddToFvourite(@RequestParam(value = "questionId") int questionId,
                                       @RequestParam(value = "username") String username){
        return iMyFavouriteService.AddToFvourite(questionId,username);

    }

    @DeleteMapping("/cancelFavourite")
    @ApiOperation("Cancel a favourite question")
    public GeneralReturn CancelFavourite(@RequestParam(value = "questionId") int questionId,
                                         @RequestParam(value = "username") String username){
        return iMyFavouriteService.CancelFavourite(questionId,username);

    }

    @GetMapping("/listMyFavourite")
    @ApiOperation("Show the list of Myfavourite")
    public GeneralReturn ListMyFavourite(@RequestParam(value = "username") String username){
        return iMyFavouriteService.ListMyFavourite(username);
    }
}
