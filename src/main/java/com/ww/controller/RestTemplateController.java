package com.ww.controller;

import com.ww.constant.ChoiceQuestionFinishState;
import com.ww.constant.ChoiceQuestionType;
import com.ww.pvo.AfterClassPracticeCourseSectionChoiceQuestionPvo;
import com.ww.vo.AfterClassPracticeCourseSectionChoiceQuestionVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Sun
 * @create: 2019-11-25 16:47
 * @version: v1.0
 */
@RestController
@RequestMapping(value = "/rest")
@Slf4j
public class RestTemplateController {

    /**
     * RestTemplate.getForEntity()  不带参数调用接口
     *
     * @return
     */
    @RequestMapping(value = "/getForEntity", method = RequestMethod.GET)
    public String getForEntity() {
        return "GetForEntity success.";
    }

    /**
     * RestTemplate.getForEntity()  带参数调用接口
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/getForEntityWithParam", method = RequestMethod.GET)
    public String getForEntityWithParam(@RequestParam("name") String name, @RequestParam("hobby") String hobby) {
        return "GetForEntityWithParam success. Name is: " + name + ", Hobby is: " + hobby;
    }

    /**
     * RestTemplate.getForEntity()  带参数调用Restful风格接口
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/getForEntityWithParamRestful/{name}", method = RequestMethod.GET)
    public String getForEntityWithParam(@PathVariable("name") String name) {
        return "getForEntityWithParamRestful success. Name is: " + name;
    }

    /**
     * RestTemplate.getForObject()  带参数调用接口
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/getForObject", method = RequestMethod.GET)
    public String getForObject(@RequestParam("name") String name, @RequestParam("hobby") String hobby) {
        return "GetForEntityWithParam success. Name is: " + name + ", Hobby is: " + hobby;
    }


    // --------------------------------------------------------------------------------------------------------------------- //

    /**
     * RestTemplate.postForEntity()  带参数调用接口
     *
     * @param pvo
     * @return
     */
    @RequestMapping(value = "/postForEntity", method = RequestMethod.POST)
    public AfterClassPracticeCourseSectionChoiceQuestionVo postForEntity(@RequestBody AfterClassPracticeCourseSectionChoiceQuestionPvo pvo) {
        log.info("[postForEntity] [pvo:{}]", pvo);
        return new AfterClassPracticeCourseSectionChoiceQuestionVo(1, "选择题", ChoiceQuestionFinishState.FINSH, "A", 1, 1, ChoiceQuestionType.IMAGE_MATCHING_QUESTION);
    }

    /**
     * RestTemplate.postForObject()  带参数调用接口
     *
     * @param pvo
     * @return
     */
    @RequestMapping(value = "/postForObject", method = RequestMethod.POST)
    public AfterClassPracticeCourseSectionChoiceQuestionVo postForObject(@RequestBody AfterClassPracticeCourseSectionChoiceQuestionPvo pvo) {
        log.info("[postForEntity] [pvo:{}]", pvo);
        return new AfterClassPracticeCourseSectionChoiceQuestionVo(1, "选择题", ChoiceQuestionFinishState.FINSH, "A", 1, 1, ChoiceQuestionType.IMAGE_MATCHING_QUESTION);
    }

    /**
     * RestTemplate.put()  带参数调用接口
     *
     * @param pvo
     * @return
     */
    @RequestMapping(value = "/put", method = RequestMethod.PUT)
    public void put(@RequestBody AfterClassPracticeCourseSectionChoiceQuestionPvo pvo) {
        log.info("[put] [pvo:{}]", pvo);
    }

    /**
     * RestTemplate.delete()  带参数调用接口
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        log.info("[delete] [id:{}]", id);
    }

    /**
     * RestTemplate.exchange()  非Json数据
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/exchangeUseLinkedMultiValueMap")
    public AfterClassPracticeCourseSectionChoiceQuestionVo exchangeUseLinkedMultiValueMap(@RequestParam Integer userId) {
        log.info("[exchangeUseLinkedMultiValueMap] [userId:{}]", userId);
        return new AfterClassPracticeCourseSectionChoiceQuestionVo(1, "选择题", ChoiceQuestionFinishState.FINSH, "A", 1, 1, ChoiceQuestionType.IMAGE_MATCHING_QUESTION);
    }

    /**
     * RestTemplate.exchange()  Json数据
     *
     * @param pvo
     * @return
     */
    @RequestMapping(value = "/exchangeUseHttpEntity")
    public AfterClassPracticeCourseSectionChoiceQuestionVo exchangeUseHttpEntity(@RequestBody AfterClassPracticeCourseSectionChoiceQuestionPvo pvo) {
        log.info("[exchangeUseHttpEntity] [pvo:{}]", pvo);
        return new AfterClassPracticeCourseSectionChoiceQuestionVo(1, "选择题", ChoiceQuestionFinishState.FINSH, "A", 1, 1, ChoiceQuestionType.IMAGE_MATCHING_QUESTION);
    }
}
