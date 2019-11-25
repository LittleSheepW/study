package com.ww.json.jsonNode;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ww.vo.AfterClassPracticeMyHomeworkVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author: Sun
 * @create: 2019-11-13 14:36
 * @version: v1.0
 */
@Slf4j
public class StudyJsonNode {

    private ObjectMapper objectMapper = new ObjectMapper();

    private static String stringJsonData = "{\"code\":0,\"message\":\"成功\",\"data\":{\"classPicUrl\":null,\"" +
            "className\":\"这是一个好听的班级名称\",\"teacherName\":\"陈老师\",\"classMemberInfoList\":[{\"userId\"" +
            ":10001,\"userName\":\"小一\",\"userPicUrl\":null,\"userTotalPoints\":0},{\"userId\":10002,\"userName\"" +
            ":\"小二\",\"userPicUrl\":null,\"userTotalPoints\":0},{\"userId\":10003,\"userName\":\"小三\",\"userPicUrl" +
            "\":null,\"userTotalPoints\":0},{\"userId\":10004,\"userName\":\"小四\",\"userPicUrl\":null,\"" +
            "userTotalPoints\":0},{\"userId\":10005,\"userName\":\"小五\",\"userPicUrl\":null,\"userTotalPoints\":0}]}}";


    /**
     * JsonNode对象转Json字符串
     *
     * @throws IOException
     */
    @Test
    public void JsonNodeToJsonString() throws IOException {
        JsonNode outsideJsonNode = objectMapper.readTree(stringJsonData);

        // asText()，如果该节点是一个值节点（方法isValueNode返回true），则该方法将返回容器值的有效String表示形式，否则为空String。
        JsonNode codeJsonNode = outsideJsonNode.path("code");
        System.out.println(codeJsonNode.isValueNode());
        System.out.println(codeJsonNode.asText());

        JsonNode dataJsonNode = outsideJsonNode.path("data");
        System.out.println(dataJsonNode.isValueNode());
        // 此方法输出为空字符串
        System.out.println(dataJsonNode.asText());
        // JsonNode对象转Json字符串
        System.out.println(dataJsonNode.toString());
    }

    /**
     * fieldNames() 获取JsonNode对象中所有key值
     *
     * @throws IOException
     */
    @Test
    public void getJsonNodeObjectAllKey() throws IOException {
        JsonNode outsideJsonNode = objectMapper.readTree(stringJsonData);
        log.info("[getJsonNodeObjectAllKey] [outsideJsonNode:{}]", outsideJsonNode);

        Iterator<String> outsideStringIterator = outsideJsonNode.fieldNames();
        while (outsideStringIterator.hasNext()) {
            String outsideKey = outsideStringIterator.next();
            log.info("[getJsonNodeObjectAllKey] [outsideKey:{}]", outsideKey);
            if ("data".equals(outsideKey)) {
                JsonNode insideJsonNode = outsideJsonNode.path("data");
                log.info("[getJsonNodeObjectAllKey] [insideJsonNode:{}]", insideJsonNode);
                Iterator<String> insideStringIterator = insideJsonNode.fieldNames();
                while (insideStringIterator.hasNext()) {
                    String insideKey = insideStringIterator.next();
                    log.info("[getJsonNodeObjectAllKey] [insideKey:{}]", insideKey);
                }
            }
        }
    }

    /**
     * get()、path()、findValue()、findPath() 从JsonNode对象中通过key再获取对应的JsonNode对象
     *
     * @throws IOException
     */
    @Test
    public void byKeyGetJsonNodeObject() throws IOException {
        JsonNode completeJsonNode = objectMapper.readTree(stringJsonData);
        log.info("[byKeyGetJsonNodeObject] [completeJsonNode:{}]", completeJsonNode);

        /*
         * 一种访问对象节点指定字段值的方法。如果此节点不是对象（或者它没有指定字段名称的值）、或者没有该名称的字段，则返回null。
         * (此方法不会到子节点中进行搜索) 注意：如果属性值已显式设置为null（这与删除操作不同！），
         *  则将返回com.fasterxml.jackson.databind.node.NullNode，而不是null。
         */
        JsonNode getJsonNode = completeJsonNode.get("userName");
        log.info("[byKeyGetJsonNodeObject] [getJsonNode:{}]", getJsonNode);
        /* 此方法类似于get（String），不同之处在于，如果不存在这样的值（由于此节点不是对象，或者对象没有指定字段的值），
         * 则返回MissingNode对象实例。这样可以通过路径调用方便，安全地链接访问。
         */
        JsonNode pathJsonNode = completeJsonNode.path("userName");
        log.info("[byKeyGetJsonNodeObject] [pathJsonNode:{}]", pathJsonNode);

        // 在此节点或其子节点中查找具有指定名称的JSON Object字段并返回其值的方法。如果在此节点或其后代中找不到匹配的字段，则返回null。
        JsonNode findValueJsonNode = completeJsonNode.findValue("userName");
        log.info("[byKeyGetJsonNodeObject] [findValueJsonNode:{}]", findValueJsonNode);
        // 与findValue相似的方法，但是如果未找到任何字段，它将返回MissingNode实例而不是null。
        JsonNode findPathJsonNode = completeJsonNode.findPath("userName");
        log.info("[byKeyGetJsonNodeObject] [findPathJsonNode:{}]", findPathJsonNode);
    }

    /**
     * JsonNode.elements()
     * 如果是一个JsonNode数组，返回数组中每个node，
     * 如果不是JsonNode数组，则返回JsonNode的values
     *
     * @throws IOException
     */
    @Test
    public void jsonNodeGetElements() throws IOException {
        // 返回JsonNode中的values
        JsonNode completeJsonNode = objectMapper.readTree(stringJsonData);
        log.info("[jsonNodeGetElements] [completeJsonNode:{}]", completeJsonNode);
        Iterator<JsonNode> nonArrayJsonNodeIterator = completeJsonNode.elements();
        while (nonArrayJsonNodeIterator.hasNext()) {
            log.info("[jsonNodeGetElements] [nonArrayElements:{}]", nonArrayJsonNodeIterator.next());
        }

        // 返回JsonNode中的每个node
        JsonNode arrayJsonNode = completeJsonNode.findPath("classMemberInfoList");
        log.info("[jsonNodeGetElements] [arrayJsonNode:{}]", arrayJsonNode);
        Iterator<JsonNode> arrayJsonNodeIterator = arrayJsonNode.elements();
        while (arrayJsonNodeIterator.hasNext()) {
            log.info("[jsonNodeGetElements] [arrayElements:{}]", arrayJsonNodeIterator.next());
        }
    }

    /**
     * findParents()
     * 在此节点或其后代中查找包含指定字段的JSON对象的方法。如果在此节点或其后代中找不到匹配的字段，则返回null
     */
    @Test
    public void jsonNodeFindParents() throws IOException {
        JsonNode completeJsonNode = objectMapper.readTree(stringJsonData);
        log.info("[jsonNodeFindParents] [completeJsonNode:{}]", completeJsonNode);
        List<JsonNode> jsonNodeList = completeJsonNode.findParents("userId");
        for (JsonNode jsonNode : jsonNodeList) {
            log.info("[jsonNodeFindParents] [jsonNode:{}]", jsonNode);
        }
    }

    /**
     * findValues()
     * 取出所有key值为指定字符串对应的value(如果value中包含子JsonNode并且子JsonNode的key值也为指定字符串，是无法捕获到并加入list的)
     *
     * @throws IOException
     */
    @Test
    public void jsonNodeFindValues() throws IOException {
        JsonNode completeJsonNode = objectMapper.readTree(stringJsonData);
        log.info("[jsonNodeFindValues] [completeJsonNode:{}]", completeJsonNode);

        List<JsonNode> jsonNodeList1 = completeJsonNode.findValues("userName");
        for (JsonNode jsonNode : jsonNodeList1) {
            log.info("[jsonNodeFindValues] [jsonNode1:{}]", jsonNode);
        }

        List<JsonNode> jsonNodeList2 = completeJsonNode.findValues("classMemberInfoList");
        for (JsonNode jsonNode : jsonNodeList2) {
            log.info("[jsonNodeFindValues] [jsonNode2:{}]", jsonNode);
        }
    }

    /**
     * fields() 迭代器，可用于遍历对象节点的所有键/值对
     *
     * @throws IOException
     */
    @Test
    public void jsonNodeFields() throws IOException {
        JsonNode completeJsonNode = objectMapper.readTree(stringJsonData);
        log.info("[jsonNodeFields] [completeJsonNode:{}]", completeJsonNode);

        Iterator<Map.Entry<String, JsonNode>> jsonNodes = completeJsonNode.fields();
        while (jsonNodes.hasNext()) {
            Map.Entry<String, JsonNode> node = jsonNodes.next();
            log.info("[jsonNodeFields] [遍历获取key:{}]", node.getKey());
            log.info("[jsonNodeFields] [遍历获取值:{}]", node.getValue());
        }
    }

    /**
     * 使用ObjectMapper将JSON数据转为相应的泛型数组
     *
     * @throws IOException
     */
    @Test
    public void jsonToGenericList() throws IOException {
        String jsonResult = "{\"code\":0,\"message\":\"成功\",\"data\":{\"totalSize\":2,\"list\":[{\"courseSectionId\":1,\"courseSectionOrder\":1,\"courseSectionName\":\"Java-第一讲\",\"beginTime\":1574654827732,\"degreeOfCompletion\":\"0/6\",\"whetherItIsCompleted\":0,\"choiceQuestionVoList\":[{\"choiceQuestionId\":1,\"choiceQuestionContent\":\"这是第1道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":1,\"questionType\":1},{\"choiceQuestionId\":2,\"choiceQuestionContent\":\"这是第2道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":1,\"questionType\":1},{\"choiceQuestionId\":3,\"choiceQuestionContent\":\"这是第3道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":1,\"questionType\":1},{\"choiceQuestionId\":4,\"choiceQuestionContent\":\"这是第4道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":1,\"questionType\":1},{\"choiceQuestionId\":5,\"choiceQuestionContent\":\"这是第5道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":1,\"questionType\":1},{\"choiceQuestionId\":6,\"choiceQuestionContent\":\"这是第6道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":1,\"questionType\":1}]},{\"courseSectionId\":2,\"courseSectionOrder\":2,\"courseSectionName\":\"Java-第二讲\",\"beginTime\":1574683627732,\"degreeOfCompletion\":\"0/4\",\"whetherItIsCompleted\":0,\"choiceQuestionVoList\":[{\"choiceQuestionId\":7,\"choiceQuestionContent\":\"这是第7道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":2,\"questionType\":1},{\"choiceQuestionId\":8,\"choiceQuestionContent\":\"这是第8道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":2,\"questionType\":1},{\"choiceQuestionId\":9,\"choiceQuestionContent\":\"这是第9道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":2,\"questionType\":1},{\"choiceQuestionId\":10,\"choiceQuestionContent\":\"这是第10道选择题\",\"finishedState\":0,\"answer\":null,\"courseId\":1,\"courseSectionId\":2,\"questionType\":1}]}]}}";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode listJsonNode = objectMapper.readTree(jsonResult).findPath("list");
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, AfterClassPracticeMyHomeworkVo.class);
        List<AfterClassPracticeMyHomeworkVo> afterClassPracticeMyHomeworkVoList = objectMapper.readValue(listJsonNode.toString(), javaType);
        System.out.println(afterClassPracticeMyHomeworkVoList);

    }
}
