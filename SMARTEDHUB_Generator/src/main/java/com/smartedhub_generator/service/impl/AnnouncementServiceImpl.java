package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.Announcement;
import com.smartedhub_generator.mapper.AnnouncementMapper;
import com.smartedhub_generator.service.IAnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
