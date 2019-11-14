package com.ww.jdk8.sort;

import com.ww.entity.Apple;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 学习对集合排序的API.
 *
 * @author: Sun
 * @create: 2019-11-14 10:35
 * @version: v1.0
 */
@Slf4j
public class StudyCollectionSort {

    private static List<Apple> appleList = new ArrayList<>();

    @Before
    public void setUp() {
        log.info("[setUp] [Start initialization data...]");

        appleList.add(new Apple(5, "black", 1000L));
        appleList.add(new Apple(2, "red", 980L));
        appleList.add(new Apple(3, "yellow", 990L));
        appleList.add(new Apple(1, "green", 960L));
        appleList.add(new Apple(4, "blue", 970L));
        appleList.add(new Apple(5, "purple", 1010L));

        log.info("[setUp] [appleList:{}]", appleList);
    }


    /**
     * 匿名内部类方式对集合进行排序(该方式是创建一个类实现Comparator接口的优化方式)
     */
    @Test
    public void sortMethod1() {
        // 按照对象中的某个int类型字段进行排序
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getId() - o2.getId();
            }
        });
        log.info("[sortMethod1] [byIdAfterSortingAppleList:{}]", appleList);

        // 按照对象中的某个long类型字段进行排序
        appleList.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                long result = o1.getBeginTime() - o2.getBeginTime();
                if (result > 0) {
                    return 1;
                } else if (result == 0) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        log.info("[sortMethod1] [byBeginTimeAfterSortingAppleList:{}]", appleList);

        // 使用lambda表达式形式按照对象中某个long类型字段进行排序
        appleList.sort((o1, o2) -> {
            long result = o2.getBeginTime() - o1.getBeginTime();
            if (result > 0) {
                return 1;
            } else if (result == 0) {
                return 0;
            } else {
                return -1;
            }
        });
        log.info("[sortMethod1] [byBeginTimeDescAfterSortingAppleList:{}]", appleList);
    }

    /**
     * 使用Comparator的comparing()方法对集合进行排序
     */
    @Test
    public void sortMethod2() {
        // 使用Comparator的comparing()方法对集合进行排序，对象中的字段为int类型
        Comparator<Apple> comparing = Comparator.comparing((Apple apple) -> apple.getId());
        appleList.sort(comparing);
        log.info("[sortMethod2] [comparingBeforeAppleList:{}]", appleList);

        // 上述方法简写形式  简写形式
        appleList.sort(Comparator.comparing(Apple::getId));

        // 逆序排序
        appleList.sort(Comparator.comparing(Apple::getId).reversed());
        log.info("[sortMethod2] [comparingDescBeforeAppleList:{}]", appleList);
    }

    /**
     * 如果排序的条件中有相等的，此时想让该条件相等的数据再进行优先级排序怎么办？
     * 可以使用在sort()方法之后再使用thenComparing()方法
     */
    @Test
    public void sortAfterAgainSort() {
        appleList.sort(Comparator.comparing(Apple::getId).thenComparingLong(Apple::getBeginTime));
        log.info("[sortAfterAgainSort] [comparingBeforeAppleList:{}]", appleList);
    }

    @Test
    public void test() {
        // TODO: 2019-11-14 Comparable接口和Comparator接口的区别？

    }
}
