package com.gpcsoft.epp.common.utils;

/**
 * 处理数据工具类
 * @author zhouzhanghe
 * @created at 2011-8-19 14:51
 */
public class NumberUtil {
	
	/**
	 * 将整数转换为汉字
	 * @param str
	 * @return
	 * @author zhouzhanghe
	 * @created at 2011-8-19 14:51
	 */
	public static String cnvtIntgStrgToChnsStrg(String str) {
		StringBuffer result = new StringBuffer();
		int length = str.length();
		boolean bZero = false;
		char[] digits = new char[] { ' ', '十', '百', '千', '万', '十', '百', '千' };
		char[] chs = new char[] { '零', '一', '二', '三', '四', '五', '六', '七', '八',
				'九' };
		
		if(Integer.parseInt(str) == 0){
			return "零";
		}
		
		for (int i = 0; i < length; i++) {
			char ch = chs[Integer.parseInt(str.charAt(i) + "")];
			char digit = digits[length - 1 - i];
			// 处理零和连续零的情况
			if (ch == '零') {
				bZero = true;
				if (digit == '万') {
					result.append('万');
				}
				continue;
			}
			if (bZero) {
				result.append('零');
				bZero = false;
			}
			// 处理一十的情况
			if (digit == '十' && ch == '一') {
				result.append("十");
				continue;
			}
			result.append(ch).append(digit);
		}
		return result.toString().trim();
	}
}
