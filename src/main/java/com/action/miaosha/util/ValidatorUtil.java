package com.action.miaosha.util;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Dell
 * @create 2019-07-18 18:37
 */
public class ValidatorUtil {

    private static final Pattern mobile_pattern = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if(StringUtils.isEmpty(src)) {
            return false;
        }
        Matcher m = mobile_pattern.matcher(src);
        return m.matches();
    }

	public static void main(String[] args) {
			System.out.println(isMobile("18912341234"));
			System.out.println(isMobile("1891234123"));
	}
}