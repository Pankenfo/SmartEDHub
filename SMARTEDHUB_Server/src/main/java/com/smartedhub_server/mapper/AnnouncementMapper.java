package com.smartedhub_server.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smartedhub_server.pojo.Announcement;

import java.util.List;

/**
 * <p>
 * For storage the announcement  Mapper 接口
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface AnnouncementMapper extends BaseMapper<Announcement> {
    List<Announcement> ShowAnnouncementByStudentId(Integer studentId, String announcementTitle);
}
