package com.smartedhub_server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.smartedhub_server.pojo.CorrectionNotebook;
import com.smartedhub_server.pojo.GeneralReturn;

/**
 * <p>
 * Saving the correction notebook detail 服务类
 * </p>
 *
 * @author Junxian Cai
 * @since 2023-10-15
 */
public interface ICorrectionNotebookService extends IService<CorrectionNotebook> {

    public void CreateCorrection(String username);

    GeneralReturn AddToCorrection(int questionId, String username);

    GeneralReturn ListCorrectionNote(String username);

    GeneralReturn CancelFromCorrection(int questionId, String username);
}
