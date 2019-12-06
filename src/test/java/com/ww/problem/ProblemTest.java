package com.ww.problem;

import org.junit.Test;

import java.util.Random;

/**
 * @author: Sun
 * @create: 2019-12-04 12:05
 * @version: v1.0
 */
public class ProblemTest {

    private volatile Filter filter;

    /**
     * 这么写为什么不会抛出空指针异常？
     * 因为&&：左边为false后面直接就不会继续判断了
     */
    @Test
    public void whyNotWhrowNPE() {
        System.out.println(filter != null && filter.filter("event") == "DENY");
    }

    class Filter {
        private String filter(String event) {
            System.out.println(event);
            return "DENY";
        }
    }

    // --------------------------------------------------------------------------------------------- //

    /**
     * String.indexOf() API 方法：从指定的索引开始，返回指定子字符串首次出现在该字符串内的索引。
     * {@link java.lang.String#indexOf(String, int)}
     */
    @Test
    public void studyStringIndexOfApi() {
        String string = "[v1:{}] [v2:{}]";
        int indexOf1 = string.indexOf("{}", 0);
        int indexOf2 = string.indexOf("{}", 5);

        System.out.println(indexOf1);
        System.out.println(indexOf2);
    }

    // --------------------------------------------------------------------------------------------- //

    /**
     * Random中的seed参数到底是用来做什么的?
     * 结论：seed是用来计算随机数的一部分。
     * <p>
     * 伪随机（preundorandom）：通过算法产生的随机数都是伪随机！！只有通过真实的随机事件产生的随机数才是真随机！！比如，通过机器的硬件噪声产生
     * 随机数、通过大气噪声产生随机数。Random生成的随机数都是伪随机数！！！是由可确定的函数（常用线性同余），通过一个种子（常用时钟），产生的伪随机数。
     * 这意味着：如果知道了种子，或者已经产生的随机数，都可能获得接下来随机数序列的信息（可预测性）。
     * <p>
     * 观察下列两个单元测试的运行结果可以发现：
     * 第一个单元测试每次运行最后输出的结果都是不一样的，
     * 第二个单元测试每次运行最后输出的结果都是一样的。
     * <p>
     * 无参的构造方法是以当前时间作为种子，每次的种子都不一样，随机性更强。而有参的构造方法是以固定值作为种子，每次输出的值都是一样的。
     * 虽然二者都是伪随机，但是，无参数构造方法（不设置种子）具有更强的随机性，能够满足一般统计上的随机数要求。
     * 使用有参的构造方法（设置种子）无论你生成多少次，每次生成的随机序列都相同，名副其实的伪随机！
     */
    @Test
    public void randomNotHaveSeed() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            for (int j = 0; j < 5; j++) {
                System.out.print(" " + random.nextInt(10) + ", ");
            }
            System.out.println("");
        }
    }

    @Test
    public void test() {
        for (int i = 0; i < 5; i++) {
            Random random = new Random(47);
            for (int j = 0; j < 5; j++) {
                System.out.print(+random.nextInt(10) + " ");
            }
            System.out.println("");
        }
    }
}
