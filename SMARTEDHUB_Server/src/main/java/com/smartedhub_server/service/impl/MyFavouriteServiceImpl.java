package com.smartedhub_server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smartedhub_server.mapper.MyFavouriteMapper;
import com.smartedhub_server.mapper.QuestionMyFavouriteMapper;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.MyFavourite;
import com.smartedhub_server.pojo.Question;
import com.smartedhub_server.pojo.QuestionMyFavourite;
import com.smartedhub_server.service.IMyFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private MyFavouriteMapper myFavouriteMapper;
    @Autowired
    private QuestionMyFavouriteMapper questionMyFavouriteMapper;
    public int CreateMyFavourite(int studentId){
        MyFavourite myFavourite = new MyFavourite();
        myFavourite.setValidity(1);
        myFavourite.setStudentId(studentId);
        return myFavouriteMapper.insert(myFavourite);
    }

    @Override
    public GeneralReturn AddToFvourite(int questionId, int studentId) {
        QuestionMyFavourite questionMyFavourite = new QuestionMyFavourite();
        LambdaQueryWrapper<MyFavourite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MyFavourite::getStudentId,studentId);
        questionMyFavourite.setQuestionId(questionId);
        questionMyFavourite.setMyfavouriteId(myFavouriteMapper.selectOne(wrapper).getMyfavouriteId());
        questionMyFavourite.setValidity(1);
        questionMyFavouriteMapper.insert(questionMyFavourite);
        return GeneralReturn.success("Add to MyFavourite successfully");
    }

}
