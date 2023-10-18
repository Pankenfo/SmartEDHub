package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.Announcement;
import com.smartedhub_server.pojo.GeneralReturn;

/**
 * <p>
 * For storage the announcement  服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IAnnouncementService extends IService<Announcement> {

    GeneralReturn CreateAnnouncement(Announcement newAnnouncement);

    GeneralReturn ShowAllOrSpecificAnnouncement(int pageNo, int pageSize, String announcementTitle, String announcementDetail);
}
