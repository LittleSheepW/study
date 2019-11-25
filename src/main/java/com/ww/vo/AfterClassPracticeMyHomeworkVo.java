package com.ww.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 我的作业信息vo.
 *
 * @author: Sun
 * @create: 2019-11-08 14:58
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterClassPracticeMyHomeworkVo {


    /**
     * 课节id
     */
    private Integer courseSectionId;

    /**
     * 课节序号
     */
    private Integer courseSectionOrder;

    /**
     * 课节名称
     */
    private String courseSectionName;

    /**
     * 课节开始上课时间
     */
    private Long beginTime;

    /**
     * 课节选择题完成度
     */
    private String degreeOfCompletion;

    /**
     * 是否完成 {@link com.ww.constant.HomeworkFinishState}
     */
    private Integer whetherItIsCompleted;

    /**
     * 课节下选择题信息
     */
    private List<AfterClassPracticeCourseSectionChoiceQuestionVo> choiceQuestionVoList;

}
