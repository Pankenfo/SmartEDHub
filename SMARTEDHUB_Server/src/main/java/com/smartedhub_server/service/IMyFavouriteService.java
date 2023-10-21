package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.GeneralReturn;
import com.smartedhub_server.pojo.MyFavourite;

/**
 * <p>
 * The notebook that saves the question that student favourite 服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface IMyFavouriteService extends IService<MyFavourite> {

    /**
     * 新建用户自动生成一个收藏夹
     * @param studentId 学生ID
     * @return 返回为0 插入失败 不为0 成功
     */
    public int CreateMyFavourite(String username);

    GeneralReturn AddToFvourite(int questionId, String username);

    GeneralReturn CancelFavourite(int questionId, String username);
}
