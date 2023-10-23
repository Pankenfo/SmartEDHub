package com.smartedhub_server.Controller;


import com.smartedhub_server.pojo.Announcement;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IAnnouncementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * <p>
 * For storage the announcement  前端控制器
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Autowired
    private IAnnouncementService iAnnouncementService;

    @PostMapping("/createAnnouncement")
    @ApiOperation("Create a new announcement")
    public GeneralReturn CreateAnnouncement(@RequestBody Announcement announcement, @RequestParam Integer classId, Principal principal){

        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        return iAnnouncementService.CreateAnnouncement(announcement, classId, username);
    }

    @GetMapping("/showAllOrSpecificAnnouncement")
    @ApiOperation("Show All Or Specific Announcement")
    public GeneralReturn ShowAllOrSpecificAnnouncement(@RequestParam(value = "pageNo") int pageNo,
                                                       @RequestParam(value = "pageSize") int pageSize,
                                                       @RequestParam(value = "announcementTitle", required = false) String announcementTitle,
                                                       @RequestParam(value = "announcementDetail", required = false) String announcementDetail){
        return iAnnouncementService.ShowAllOrSpecificAnnouncement(pageNo, pageSize, announcementTitle, announcementDetail);
    }


}
