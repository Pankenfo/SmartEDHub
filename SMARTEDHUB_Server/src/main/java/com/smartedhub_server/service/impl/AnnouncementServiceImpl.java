package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.AnnouncementMapper;
import com.smartedhub_server.pojo.Announcement;
import com.smartedhub_server.service.IAnnouncementService;
import org.springframework.stereotype.Service;

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

}
