package com.ww.pvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 获取学生所有选择题pvo.
 *
 * @author: Sun
 * @create: 2019-11-08 11:37
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterClassPracticeCourseSectionChoiceQuestionPvo {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 班级ID
     */
    private Integer classId;

    /**
     * 课节ID
     */
    private Integer courseSectionId;
}
