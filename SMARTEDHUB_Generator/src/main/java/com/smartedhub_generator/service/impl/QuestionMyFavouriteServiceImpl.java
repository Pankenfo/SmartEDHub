package com.smartedhub_generator.service.impl;

import com.smartedhub_generator.pojo.QuestionMyFavourite;
import com.smartedhub_generator.mapper.QuestionMyFavouriteMapper;
import com.smartedhub_generator.service.IQuestionMyFavouriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * relationship between question and myfavourite (save question to myfavourite) 服务实现类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
@Service
public class QuestionMyFavouriteServiceImpl extends ServiceImpl<QuestionMyFavouriteMapper, QuestionMyFavourite> implements IQuestionMyFavouriteService {

}
