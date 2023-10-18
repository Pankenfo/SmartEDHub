package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.AnnouncementMapper;
import com.smartedhub_server.pojo.Announcement;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * For storage the announcement  服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public GeneralReturn CreateAnnouncement(Announcement newAnnouncement) {
        newAnnouncement.setValidity(1);
        announcementMapper.insert(newAnnouncement);
        return GeneralReturn.success("Create new announcement successfully");
    }

    @Override
    public GeneralReturn ShowAllOrSpecificAnnouncement(int pageNo, int pageSize, String announcementTitle, String announcementDetail) {
        Page<Announcement> page = new Page<Announcement>(pageNo,pageSize);
        LambdaQueryWrapper<Announcement> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(announcementTitle), Announcement::getAnnouncementTitle,announcementTitle)
                .or()
                .like(StringUtils.hasLength(announcementDetail), Announcement::getAnnouncementDetail,announcementDetail);
        announcementMapper.selectPage(page,wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("total", page.getTotal());//查询的总数
        data.put("rows",page.getRecords());//查询的数据
        return GeneralReturn.success(data);
    }
}
