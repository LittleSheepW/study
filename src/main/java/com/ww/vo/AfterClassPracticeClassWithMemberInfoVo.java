package com.ww.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级+班级成员信息vo.
 *
 * @author: Sun
 * @create: 2019-11-08 10:36
 * @version: v1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfterClassPracticeClassWithMemberInfoVo {

    /**
     * 班级头像url
     */
    private String classPicUrl;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 老师名称
     */
    private String teacherName;

    /**
     * 班级成员信息
     */
    private List<ClassMemberInfo> classMemberInfoList = new ArrayList<>();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ClassMemberInfo {
        /**
         * 用户ID
         */
        private Integer userId;
        /**
         * 用户姓名
         */
        private String userName;
        /**
         * 用户头像url
         */
        private String userPicUrl;
        /**
         * 用户总积分
         */
        private Integer userTotalPoints;
    }
}
