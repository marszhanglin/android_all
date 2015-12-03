/*
 * Copyright (c) 2005, 2014, EVECOM Technology Co.,Ltd. All rights reserved.
 * EVECOM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 */
package mars.all.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Title: Regex.java
 * @Package net.evecom.newplat.utils
 * @ClassName: Regex
 * @Description: 后台验证输入值是否合法
 * @author Fandy Liu
 * @date 2014年6月29日 下午2:47:42
 * @version V1.0
 */
public class Regex {

    /**
     * 检查 email输入是否正确 正确的书写格 式为 username@domain
     * 
     * @param value
     * @return
     */
    public boolean checkEmail(String value, int length) {
        return value.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*") && value.length() <= length;
    }

    /**
     * 检查电话输入 是否正确 正确格 式 012-87654321、0123-87654321、0123－7654321
     * 
     * @param value
     * @return
     */
    public boolean checkTel(String value) {
        return value.matches("(^(\\d{4})?\\d{7,8}$)|" + "(^(\\d{4}-)*\\d{7,8}$)|(^(\\d{3})?"
                + "\\d{8}$)|(^(\\d{3}-)*\\d{8}$)");
    }

    /**
     * 检查手机输入 是否正确
     * 
     * @param value
     * @return
     */
    public boolean checkMobile(String value) {
        return value.matches("^1[3|5|7|8][0-9]\\d{4,8}$");
    }

    /**
     * @Title: checkMobileAndTel
     * @Description: 检查手机号码
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:42:52
     */
    public boolean checkMobileAndTel(String value) {
        return checkTel(value) || value.matches("^1[3|5|7|8][0-9]\\d{4,8}$");
    }

    /**
     * 
     * @Title: checkChineseName
     * @Description: 检查中文输 入是否正确
     * @param value
     * @param length
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:42:33
     */
    public boolean checkChinese(String value) {
        return value.matches("^[\u4e00-\u9fa5]+$");

    }

    /**
     * 
     * @Title: checkBlank
     * @Description: 中首尾空行或空格
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:44:02
     */
    public boolean checkBlank(String value) {
        return value.matches("^\\s*|\\s*$");
    }

    /**
     * 
     * @Title: checkHtmlTag
     * @Description: 中首尾空行或空格
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:44:16
     */

    public boolean checkHtmlTag(String value) {
        return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
    }

    /**
     * 
     * @Title: checkURL
     * @Description: 检查URL是 否合法
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:44:44
     */
    public boolean checkURL(String value) {
        return value.matches("[a-zA-z]+://[^\\s]*");
    }

    /**
     * 
     * @Title: checkIP
     * @Description: 检查IP是否 合法
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:45:11
     */
    public boolean checkIP(String value) {
        return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
    }

    /**
     * 
     * @Title: checkQQ
     * @Description: 检查QQ是否 合法，必须是数字，且首位不能为0，最长15位
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:45:36
     */
    public boolean checkQQ(String value) {
        return value.matches("[1-9][0-9]{4,13}");
    }

    /**
     * 
     * @Title: checkPostCode
     * @Description: 检查邮编是否 合法
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:45:53
     */
    public boolean checkPostCode(String value) {
        return value.matches("[1-9]\\d{5}(?!\\d)");
    }

    /**
     * 
     * @Title: checkIDCard
     * @Description: 检查身份证是 否合法,15位或18位
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:46:07
     */
    @SuppressWarnings("unused")
    public boolean checkIDCard(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (checkDate(strYear + "-" + strMonth + "-" + strDay, "yyyy-MM-dd") == false) {
            errorInfo = "身份证生日无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
            errorInfo = "身份证生日不在有效范围。";
            return false;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码是否有效 ================
        Hashtable<String, String> h = getAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================

        /*
         * int TotalmulAiWi = 0; for (int i = 0; i < 17; i++) { TotalmulAiWi =
         * TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i)))
         * Integer.parseInt(Wi[i]); } int modValue = TotalmulAiWi % 11; String
         * strVerifyCode = ValCodeArr[modValue]; Ai = Ai + strVerifyCode;
         * 
         * if (IDStr.length() == 18) { if (Ai.equals(IDStr) == false) {
         * errorInfo = "身份证无效，不是合法的身份证号码"; return false; } } else { return true;
         * }
         */

        if (IDStr.length() == 18) {

            String last = IDStr.substring(17);
            if (isNumeric(last)) {
                return true;
            } else {
                return "X".equals(last);
            }

        }

        // =====================(end)=====================
        return true;
    }

    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 检查身份证最后一位是否是数字
     * 
     * @discription TODO
     * @author Fandy Liu
     * @created 2014年8月19日 上午11:18:39
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @Title: checkLength
     * @Description: 检查输入是否 超出规定长度
     * @param value
     * @param length
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:46:35
     */
    public boolean checkLength(String value, String[] length) {
        return value.length() >= new Integer(length[0]) && value.length() <= new Integer(length[1]);
    }

    /**
     * 
     * @Title: checkNull
     * @Description: 检查是否为空 字符串,空：true,不空:false
     * @param value
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午2:47:11
     */
    public boolean checkNull(String value) {
        return value == null || "".equals(value.trim());
    }

    /**
     * @Title: intValid
     * @Description: 整数判断
     * @param value
     * @param parms
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午3:02:51
     */
    public boolean checkIntValue(String value) {
        return value.matches("^-?\\d+$");
    }

    /**
     * 
     * @Title: checkDate
     * @Description:验证输入的日期是否合法
     * @param value
     * @param formater
     * @return
     * @author Fandy Liu
     * @date 2014年6月29日 下午4:53:09
     */
    public boolean checkDate(String value, String formater) {
        SimpleDateFormat formatter = new SimpleDateFormat(formater);
        formatter.setLenient(false);
        try {
            formatter.parse(value);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}