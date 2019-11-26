package com.ww.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * @author: Sun
 * @create: 2019-11-26 10:43
 * @version: v1.0
 */
@Slf4j
public class StudyString {

    /**
     * LOCAL_CHARSET = "UTF-8/GBK";
     * SERVER_CHARSET = "ISO-8859-1";
     * 问题：在往FTP服务器上传文件时，为什么需要以这种形式将new String(originalFilename.getBytes(LOCAL_CHARSET), SERVER_CHARSET)将文件编码？感觉怪怪的。
     * 因为在FTP协议里面，规定文件名编码为ISO-8859-1，所以目录名或文件名需要转码。所以如果是中文需要用户手动设置。(再看看下面代码就清楚了)
     *
     * @throws UnsupportedEncodingException
     */
    @Test
    public void newString() throws UnsupportedEncodingException {
        // String.getBytes(String charsetName)方法会根据指定的编码返回某字符串在该编码下的byte数组表示
        byte[] b_gbk = "中".getBytes("GBK");
        byte[] b_utf8 = "中".getBytes("UTF-8");
        byte[] b_iso88591 = "中".getBytes("ISO8859-1");
        log.info("[newString] [b_gbk:{}] [b_utf8:{}] [b_iso88591:{}]", b_gbk.length, b_utf8.length, b_iso88591.length);

        // 而与getBytes相对的，可以通过new String(byte[] byte, String charsetName)的方式来还原这个“中”字时，实际是通过使用指定的字符集解码指定的字节数组来构造新的String。
        String s_gbk = new String(b_gbk, "GBK");
        String s_utf8 = new String(b_utf8, "UTF-8");
        String s_iso88591 = new String(b_iso88591, "ISO8859-1");
        /**
         * 打印s_gbk、s_utf8和s_iso88591发现s_gbk和s_utf8都是“中”，而s_iso88591是一个不认识的字符，为什么使用ISO8859-1编码再组合之后，
         * 无法还原“中”字呢，其实原因很简单，因为ISO8859-1编码的编码表中，根本就没有包含汉字字符，当然也就无法通过"中".getBytes("ISO8859-1")来
         * 得到正确的“中”字在ISO8859-1中的编码值了，所以再通过new String()来还原就无从谈起了。
         *
         * 因此通过String.getBytes(String charsetName)方法来得到byte[]时，一定要确定charsetName的编码表中确实存在String表示的码值，这样得到的byte[]数组才能正确被还原。
         */
        log.info("[newString] [s_gbk:{}] [s_utf8:{}] [s_iso88591:{}]", s_gbk, s_utf8, s_iso88591);

        /**
         * 有时候，为了让中文字符适应某些特殊要求（如http header头要求其内容必须为iso8859-1编码），可能会通过将中文字符按照字节方式来编码的情况，如：
         * String s_iso88591 = new String("中".getBytes("UTF-8"), "ISO8859-1")，这样得到的s_iso88591字符串实际是三个在 ISO8859-1中的字符，
         * 在将这些字符传递到目的地后，目的地程序再通过相反的方式来得到正确的中文汉字“中”：
         * String s_utf8 = new String(s_iso88591.getBytes("ISO8859-1"), "UTF-8")
         *
         * 这样就既保证了遵守协议规定、也支持中文。
         */
    }
}
