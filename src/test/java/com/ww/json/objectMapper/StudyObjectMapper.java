package com.ww.json.objectMapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ww.api.ApiResult;
import com.ww.vo.AfterClassPracticeClassWithMemberInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学习ObjectMapper API.
 *
 * @author: Sun
 * @create: 2019-11-13 14:14
 * @version: v1.0
 */
@Slf4j
public class StudyObjectMapper {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static String stringJsonData = "{\"code\":0,\"message\":\"成功\",\"data\":{\"classPicUrl\":null,\"" +
            "className\":\"这是一个好听的班级名称\",\"teacherName\":\"陈老师\",\"classMemberInfoList\":[{\"userId\"" +
            ":10001,\"userName\":\"小一\",\"userPicUrl\":null,\"userTotalPoints\":0},{\"userId\":10002,\"userName\"" +
            ":\"小二\",\"userPicUrl\":null,\"userTotalPoints\":0},{\"userId\":10003,\"userName\":\"小三\",\"userPicUrl" +
            "\":null,\"userTotalPoints\":0},{\"userId\":10004,\"userName\":\"小四\",\"userPicUrl\":null,\"" +
            "userTotalPoints\":0},{\"userId\":10005,\"userName\":\"小五\",\"userPicUrl\":null,\"userTotalPoints\":0}]}}";


    /**
     * JSON字符串转对象 - readValue()
     *
     * @throws IOException
     */
    @Test
    public void jsonToObject() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ApiResult apiResult = objectMapper.readValue(stringJsonData, ApiResult.class);
        log.info("[jsonToObject] [apiResult:{}]", apiResult);
    }

    /**
     * 对象转JSON字符串 - writeValueAsString()
     *
     * @throws IOException
     */
    @Test
    public void objectToJson() throws IOException {
        AfterClassPracticeClassWithMemberInfoVo vo = new AfterClassPracticeClassWithMemberInfoVo();
        vo.setTeacherName("sunshine");
        vo.setClassPicUrl("http://localhost:8080");
        vo.setClassName("class name");
        List<AfterClassPracticeClassWithMemberInfoVo.ClassMemberInfo> classMemberInfoList = new ArrayList<>();
        classMemberInfoList.add(new AfterClassPracticeClassWithMemberInfoVo.ClassMemberInfo(1, "小一",
                "http://localhost:9090", 200));
        classMemberInfoList.add(new AfterClassPracticeClassWithMemberInfoVo.ClassMemberInfo(2, "小二",
                "http://localhost:9091", 400));
        vo.setClassMemberInfoList(classMemberInfoList);
        String stringJson = objectMapper.writeValueAsString(vo);
        log.info("[objectToJson] [stringJson:{}]", stringJson);
    }

    /**
     * Json字符串转JsonNode对象
     *
     * @throws IOException
     */
    @Test
    public void jsonToJsonNode() throws IOException {
        JsonNode jsonNode = objectMapper.readTree(stringJsonData);
        log.info("[jsonToJsonNode] [jsonNode:{}]", jsonNode);
    }
}
