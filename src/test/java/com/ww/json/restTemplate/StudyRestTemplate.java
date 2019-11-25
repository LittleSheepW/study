package com.ww.json.restTemplate;

import com.ww.api.ApiResult;
import com.ww.pvo.AfterClassPracticeCourseSectionChoiceQuestionPvo;
import com.ww.vo.AfterClassPracticeCourseSectionChoiceQuestionVo;
import jdk.nashorn.internal.runtime.JSONFunctions;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.awt.print.Book;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试RestTemplate发送请求的几种方式：
 * <p>
 * restTemplate.getForEntity()
 * restTemplate.getForObject()      对getForEntity()方法的进一步封装，如果只关注返回的消息体的内容，对其他信息都不关注，此时可以使用 getForObject
 * restTemplate.postForEntity()
 * restTemplate.postForObject()
 * restTemplate.put()
 * restTemplate.delete()
 * restTemplate.exchange()  使用LinkedMultiValueMap或HttpEntity传递数据
 * <p>
 * ResponseEntity<T>是 Spring 对 HTTP 请求响应的封装，包括了几个重要的元素，如Status code、ContentType、ContentLength、ResponseBody等。
 *
 * @author: Sun
 * @create: 2019-11-25 16:35
 * @version: v1.0
 */
@Slf4j
public class StudyRestTemplate {

    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    /**
     * restTemplate.getForEntity()
     */
    @Test
    public void getForEntity() {
        // getForEntity()最简单的调用形式【不带参数】 getForEntity(String url, Class<T> responseType, Object... uriVariables)
        ResponseEntity<String> responseEntity1 = REST_TEMPLATE.getForEntity("http://localhost:8999/rest/getForEntity", String.class);
        String body1 = responseEntity1.getBody();
        HttpStatus statusCode = responseEntity1.getStatusCode();
        int statusCodeValue = responseEntity1.getStatusCodeValue();
        HttpHeaders headers = responseEntity1.getHeaders();

        StringBuffer response1 = new StringBuffer();
        response1.append("responseEntity1.getBody()：").append(body1).append("\n")
                .append("responseEntity1.getStatusCode()：").append(statusCode).append("\n")
                .append("responseEntity1.getStatusCodeValue()：").append(statusCodeValue).append("\n")
                .append("responseEntity1.getHeaders()：").append(headers).append("\n");
        log.info("[getForEntity] [response1:{}]", response1);

        // getForEntity()带参数形式第一种调用方式  getForEntity(String url, Class<T> responseType, Object... uriVariables)
        ResponseEntity<String> responseEntity2 = REST_TEMPLATE.getForEntity("http://localhost:8999/rest/getForEntityWithParam?name={1}&hobby={2}", String.class, "sunshine", "ranran");
        String body2 = responseEntity2.getBody();
        StringBuffer response2 = new StringBuffer();
        response2.append("responseEntity2.getBody()：").append(body2).append("\n");
        log.info("[getForEntity] [response1=2:{}]", response2);


        // getForEntity()带参数形式第二种调用方式  getForEntity(String url, Class<T> responseType, Map<String, ?> uriVariables)
        Map<String, String> map = new HashMap<>();
        map.put("name", "ranran");
        map.put("hobby", "sunshine");
        ResponseEntity<String> responseEntity3 = REST_TEMPLATE.getForEntity("http://localhost:8999/rest/getForEntityWithParam?name={name}&hobby={hobby}", String.class, map);
        String body3 = responseEntity3.getBody();
        StringBuffer response3 = new StringBuffer();
        response3.append("responseEntity3.getBody()：").append(body3).append("\n");
        log.info("[getForEntity] [response3:{}]", response3);

        // 使用getForEntity()时第一个调用地址也可以是一个URI而不是字符串  getForEntity(URI url, Class<T> responseType)
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://localhost:8999/rest/getForEntityWithParam?name={name}&hobby={hobby}").build().expand(map).encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity4 = REST_TEMPLATE.getForEntity(uri, String.class);
        String body4 = responseEntity4.getBody();
        StringBuffer response4 = new StringBuffer();
        response4.append("responseEntity4.getBody()：").append(body4).append("\n");
        log.info("[getForEntity] [response4:{}]", response4);

        // 使用getForEntity访问Restful API
        String name = "sunshine";
        ResponseEntity<String> responseEntity5 = REST_TEMPLATE.getForEntity("http://localhost:8999/rest/getForEntityWithParamRestful/" + name, String.class);
        String body5 = responseEntity5.getBody();
        StringBuffer response5 = new StringBuffer();
        response5.append("responseEntity5.getBody()：").append(body5).append("\n");
        log.info("[getForEntity] [response5:{}]", response5);
    }

    /**
     * restTemplate.getForObject()，getForObject函数实际上是对getForEntity函数的进一步封装，如果你只关注返回的消息体的内容，
     * 对其他信息都不关注，此时可以使用该方法。getForObject中的几个重载方法调用形式和getForEntity中的方法一致。
     */
    @Test
    public void getForObject() {
        // 不带参
        String stringResult1 = REST_TEMPLATE.getForObject("http://localhost:8999/rest/getForEntity", String.class);
        log.info("[getForObject] [stringResult1:{}]", stringResult1);

        // 代餐
        Map<String, String> map = new HashMap<>();
        map.put("name", "ranran");
        map.put("hobby", "sunshine");
        String stringResult2 = REST_TEMPLATE.getForObject("http://localhost:8999/rest/getForObject?name={name}&hobby={hobby}", String.class, map);
        log.info("[getForObject] [stringResult2:{}]", stringResult2);
    }

    /**
     * restTemplate.postForEntity()大致和getForEntity()调用形式一致
     */
    @Test
    public void postForEntity() {
        AfterClassPracticeCourseSectionChoiceQuestionPvo pvo = new AfterClassPracticeCourseSectionChoiceQuestionPvo(1, 1, 1);
        ResponseEntity<AfterClassPracticeCourseSectionChoiceQuestionVo> responseEntity1 = REST_TEMPLATE.postForEntity("http://localhost:8999/rest/postForEntity", pvo, AfterClassPracticeCourseSectionChoiceQuestionVo.class);
        StringBuffer response1 = new StringBuffer();
        AfterClassPracticeCourseSectionChoiceQuestionVo body1 = responseEntity1.getBody();
        HttpStatus statusCode = responseEntity1.getStatusCode();
        int statusCodeValue = responseEntity1.getStatusCodeValue();
        HttpHeaders headers = responseEntity1.getHeaders();
        response1.append("responseEntity1.getBody()：").append(body1).append("\n")
                .append("responseEntity1.getStatusCode()：").append(statusCode).append("\n")
                .append("responseEntity1.getStatusCodeValue()：").append(statusCodeValue).append("\n")
                .append("responseEntity1.getHeaders()：").append(headers).append("\n");
        log.info("[postForEntity] [response1:{}]", response1);
    }

    /**
     * 如果只关注返回的消息体，可以直接使用postForObject。用法和getForObject一致。
     */
    @Test
    public void postForObject() {
        AfterClassPracticeCourseSectionChoiceQuestionPvo pvo = new AfterClassPracticeCourseSectionChoiceQuestionPvo(1, 1, 1);
        AfterClassPracticeCourseSectionChoiceQuestionVo vo = REST_TEMPLATE.postForObject("http://localhost:8999/rest/postForEntity", pvo, AfterClassPracticeCourseSectionChoiceQuestionVo.class);
        log.info("[postForObject] [vo:{}]", vo);
    }

    @Test
    public void put() {
        AfterClassPracticeCourseSectionChoiceQuestionPvo pvo = new AfterClassPracticeCourseSectionChoiceQuestionPvo(1, 1, 1);
        REST_TEMPLATE.put("http://localhost:8999/rest/put", pvo);
    }

    @Test
    public void delete() {
        REST_TEMPLATE.delete("http://localhost:8999/rest/delete/{1}", 100);
    }

    /**
     * RestTemplate.exchange()传参时使用LinkedMultiValueMap进行包装传递，接收时使用@RequestParam进行接收，非JSON形式传递
     */
    @Test
    public void exchangeUseLinkedMultiValueMap() {
        LinkedMultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("userId", 1);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(paramMap, headers);

        ResponseEntity<String> exchangeResult = REST_TEMPLATE.exchange("http://localhost:8999/rest/exchangeUseLinkedMultiValueMap", HttpMethod.POST,
                requestEntity, String.class);
        log.info("[exchange] [exchangeResult:{}]", exchangeResult);
    }

    /**
     * RestTemplate.exchange()传参时使用HttpEntity进行包装传递，接收时需要使用@RequestBody注解接收，JSON形式传递
     */
    @Test
    public void exchangeUseHttpEntity() {
        AfterClassPracticeCourseSectionChoiceQuestionPvo pvo = new AfterClassPracticeCourseSectionChoiceQuestionPvo();
        pvo.setClassId(1);
        pvo.setCourseSectionId(2);
        pvo.setUserId(3);

        HttpHeaders headers = new HttpHeaders();
        System.out.println(MediaType.APPLICATION_JSON_UTF8);
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<AfterClassPracticeCourseSectionChoiceQuestionPvo> requestEntity = new HttpEntity<>(pvo, headers);

        ResponseEntity<String> exchangeResult = REST_TEMPLATE.exchange("http://localhost:8999/rest/exchangeUseHttpEntity", HttpMethod.POST,
                requestEntity, String.class);
        log.info("[exchange] [exchangeResult:{}]", exchangeResult);
    }

    /**
     * RestTemplate发送请求接收泛型响应数据 (该方法在kkclass项目中使用TestRestTemplate测试过)
     */
    @Test
    public void restTemplateResultToGenericType() {
        AfterClassPracticeCourseSectionChoiceQuestionPvo questionPvo = new AfterClassPracticeCourseSectionChoiceQuestionPvo();
        questionPvo.setClassId(1);
        questionPvo.setUserId(1);
        questionPvo.setCourseSectionId(1);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApiResult<List<AfterClassPracticeCourseSectionChoiceQuestionVo>>> exchange = restTemplate.exchange("/after/class/practice/course-section-choice-question", HttpMethod.POST,
                new HttpEntity<>(questionPvo),
                new ParameterizedTypeReference<ApiResult<List<AfterClassPracticeCourseSectionChoiceQuestionVo>>>() {
                });

        ApiResult<List<AfterClassPracticeCourseSectionChoiceQuestionVo>> body = exchange.getBody();
        System.out.println("body:" + body);
        List<AfterClassPracticeCourseSectionChoiceQuestionVo> data = body.getData();
        System.out.println("data:" + data);
    }
}
