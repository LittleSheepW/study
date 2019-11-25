package com.ww.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生本课节下课后选择题vo.
 *
 * @author: Sun
 * @create: 2019-11-08 11:37
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterClassPracticeCourseSectionChoiceQuestionVo {

    /**
     * 选择题ID
     */
    private Integer choiceQuestionId;

    /**
     * 选择题内容
     */
    private String choiceQuestionContent;

    /**
     * 选择题完成状态 {@link com.ww.constant.ChoiceQuestionFinishState}
     */
    private Integer finishedState;

    /**
     * 学生选择的答案
     */
    private String answer;

    /**
     * 课程ID
     */
    private Integer courseId;

    /**
     * 课节id
     */
    private Integer courseSectionId;

    /**
     * 选择题类型  (1文本选择题 2图片选择题 3文本匹配题 4图片匹配题)
      */
    private Integer questionType;

}
