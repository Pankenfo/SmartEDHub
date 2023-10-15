package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.MyFavouriteMapper;
import com.smartedhub_server.pojo.MyFavourite;
import com.smartedhub_server.service.IMyFavouriteService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * The notebook that saves the question that student favourite 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class MyFavouriteServiceImpl extends ServiceImpl<MyFavouriteMapper, MyFavourite> implements IMyFavouriteService {

}
