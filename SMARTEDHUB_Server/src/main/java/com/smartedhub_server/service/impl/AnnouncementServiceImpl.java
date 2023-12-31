package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.AnnouncementMapper;
import com.smartedhub_server.pojo.Announcement;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.StudentClass;
import com.smartedhub_server.pojo.Teacher;
import com.smartedhub_server.service.IAnnouncementService;
import com.smartedhub_server.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
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
    @Autowired
    private ITeacherService teacherService;

    @Override
    public GeneralReturn CreateAnnouncement(Announcement announcement, Integer classId, String username) {
        //TODO:改一下，将时间，aid，tid都由后端来设置好，只需要写title和detail和classId

        Teacher teacherByUserName = teacherService.getTeacherByUserName(username);
        Integer teacherId = teacherByUserName.getTeacherId();
        announcement.setTeacherId(teacherId);
        announcement.setAnnouncementDate(new Date());
        announcement.setClassId(classId);
        announcement.setValidity(1);
        int result = announcementMapper.insert(announcement);
        if (result == 1) {
            return GeneralReturn.success("Create new announcement successfully.");
        }
        return GeneralReturn.error("Create new announcement failed, please try again.");
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

    @Override
    public GeneralReturn ShowAllOrSpecificAnnouncementNoPage(String announcementTitle, String announcementDetail) {
        LambdaQueryWrapper<Announcement> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(announcementTitle), Announcement::getAnnouncementTitle,announcementTitle)
                .or()
                .like(StringUtils.hasLength(announcementDetail), Announcement::getAnnouncementDetail,announcementDetail);
        return GeneralReturn.success(announcementMapper.selectList(wrapper));

    }

    @Override
    public GeneralReturn ShowAnnouncementByStudentId(Integer studentId, String announcementTitle) {
        return GeneralReturn.success(announcementMapper.ShowAnnouncementByStudentId(studentId,announcementTitle));
    }

    @Override
    public GeneralReturn DeleteAnnouncement(Integer announcementId) {
        announcementMapper.deleteById(announcementId);
        return GeneralReturn.success("Detele successfully");
    }

    @Override
    public GeneralReturn ShowAnnouncementByTeacherId(Integer teacherId, String announcementTitle) {
        return GeneralReturn.success(announcementMapper.ShowAnnouncementByTeacherId(teacherId,announcementTitle));
    }
}
