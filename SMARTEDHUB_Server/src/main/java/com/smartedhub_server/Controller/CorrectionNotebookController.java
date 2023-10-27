package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.ICorrectionNotebookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Saving the correction notebook detail 前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/correction-notebook")
public class CorrectionNotebookController {

    @Autowired
    private ICorrectionNotebookService iCorrectionNotebookService;

    @PutMapping("/addToCorrection")
    @ApiOperation("add a question to Correction notebook")
    public GeneralReturn AddToCorrection(@RequestParam(value = "questionId") int questionId,
                                       @RequestParam(value = "username") String username){
        return iCorrectionNotebookService.AddToCorrection(questionId,username);

    }

    @DeleteMapping("/deleteFromCorrection")
    @ApiOperation("Delete a correction question")
    public GeneralReturn CancelFromCorrection(@RequestParam(value = "questionId") int questionId,
                                         @RequestParam(value = "username") String username){
        return iCorrectionNotebookService.CancelFromCorrection(questionId,username);

    }

    @GetMapping("/listCorrectionNote")
    @ApiOperation("Show the list of correction notebook")
    public GeneralReturn ListCorrectionNote(@RequestParam(value = "username") String username){
        return iCorrectionNotebookService.ListCorrectionNote(username);
    }
}
