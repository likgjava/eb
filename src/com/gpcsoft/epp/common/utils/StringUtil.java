package com.gpcsoft.epp.common.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import org.springframework.util.Assert;

/**
 * @Description: String 工具类
 * 
 * @version V1.0
 * @Create Date 2011-3-15 下午02:34:06 By wanghz
 */
public class StringUtil {

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}

	/**
	 * 用Base64算法对字符串进行编码.
	 * 
	 * @param str
	 * @return String
	 * */
	public static String encodeString(String str) {
		sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		return encoder.encodeBuffer(str.getBytes()).trim();
	}

	/**
	 * 用Base64算法对字符串进行解码.
	 * 
	 * @param str
	 * @return String
	 * */
	public static String decodeString(String str) {
		sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
		try {
			return new String(dec.decodeBuffer(str));
		} catch (IOException io) {
			throw new RuntimeException(io.getMessage(), io.getCause());
		}
	}

	/**
	 * 
	 * Description :判断对象是否为空,或者为长度为0 Create Date: 2011-10-17下午06:26:34 by
	 * chenhongjun Modified Date: 2011-10-17下午06:26:34 by chenhongjun
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	public static boolean empty(Object obj) {
		boolean result = true;
		if (null == obj) {
			result = true;
		} else if (obj instanceof String) {
			result = ((String) obj).length() == 0 ? true : false;
		} else if (obj instanceof List) {
			result = ((List) obj).size() == 0 ? true : false;
		} else if (obj instanceof String[]) {
			result = ((String[]) obj).length == 0 ? true : false;
		}
		return result;
	}

	public static String[] toArray(String parseString) {
		return toArray(parseString, " \t\n\r\f", false);
	}

	public static String[] toArray(String parseString, String splitString) {
		return toArray(parseString, splitString, false);
	}

	/**
	 * 分隔一个字符串
	 * 
	 * @param parseString
	 *            String 原始字符串
	 * @param splitString
	 *            String 分隔串
	 * @param returnDelims
	 *            boolean 返回值是否包含分隔串
	 * @return String[] 分隔后的字符串
	 */
	public static String[] toArray(String parseString, String splitString,
			boolean returnDelims) {
		StringTokenizer tokens = new StringTokenizer(parseString, splitString,
				returnDelims);
		String[] values = new String[tokens.countTokens()];
		int i = 0;
		while (tokens.hasMoreTokens()) {
			values[i++] = tokens.nextToken();
		}
		return values;
	}

	/**
	 * 
	 * Description : 两个字段串相除,并保存6位小数 Create Date: 2011-11-6下午05:13:03 by
	 * chenhongjun Modified Date: 2011-11-6下午05:13:03 by chenhongjun
	 * 
	 * @param objOne
	 *@param objTwo
	 *@return 下午05:13:03 String
	 */
	public static String divide(String objOne, String objTwo) {
		return divide(objOne, objTwo, 6);
	}

	/**
	 * 
	 * Description : 两字段串相除,保留小数位数可又传入 Create Date: 2011-11-6下午05:13:38 by
	 * chenhongjun Modified Date: 2011-11-6下午05:13:38 by chenhongjun
	 * 
	 * @param objOne
	 *            除数
	 *@param objTwo
	 *            被除数
	 *@param scale
	 *            保存小数位数
	 *@return 下午 20111-11-06 05:13:38 String
	 */
	public static String divide(String objOne, String objTwo, int scale) {

		BigDecimal one = new BigDecimal(objOne);
		BigDecimal two = new BigDecimal(objTwo);
		return one.divide(two, scale, RoundingMode.HALF_UP).toString();

	}

	/**
	 * 
	 * Description : 清除字段串有点的数据最后带字符. Create Date: 2011-11-6下午05:15:10 by
	 * chenhongjun Modified Date: 2011-11-6下午05:15:10 by chenhongjun
	 * 
	 * @param inpuStr
	 *@return 下午05:15:10 String
	 */
	public static String clearNumZero(String inpuStr) {
		String pointEnd = inpuStr.substring(inpuStr.lastIndexOf(".") + 1);
		char[] c = pointEnd.toCharArray();
		int ind = inpuStr.indexOf(".");
		if (ind == -1)
			return inpuStr;
		StringBuffer str = new StringBuffer();
		str.append(inpuStr.substring(0, ind));
		int n = 0;
		for (int i = c.length - 1; i > 0; i--) {
			if (c[i] != '0' && c[i] != '.') {
				n = i;
				break;
			}
		}
		if (n == 0 && c[0] == '0') {
		} else {
			str.append(".");
			for (int i = 0; i <= n; i++) {
				str.append(c[i]);
			}
		}
		return str.toString();
	}

	
	
	/**
	 * 
	 * Description :  生成随机数
	 * Create Date: 2011-12-15上午09:41:56 by chenhongjun  
	 * Modified Date: 2011-12-15上午09:41:56 by chenhongjun
	 *@param length  生成字符串长度
	 *@return 
	 *上午09:41:56 
	 *String
	 */
	public static final String randomString(int length) {
		return randomString("0123456789abcdefghijklmnopqrstuvwxyz", length);
	}
	
	/**
	 * 
	 * Description : 从某个字段中随机生成几位字符串 
	 * Create Date: 2011-12-15上午10:15:24 by chenhongjun  
	 * Modified Date: 2011-12-15上午10:15:24 by chenhongjun
	 *@param str  字符串
	 *@param length  得到随机串的长度
	 *@return
	 *上午10:15:24 
	 *String
	 */
	public static final String randomString(String str,int length) {
		if (length < 1) {
			return null;
		}
		if(str==null||str.length()==0){
			return null;
		}
		Random randGen = new Random();
		char[] numbersAndLetters =str.toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
		}
		return new String(randBuffer);//generatorSequence
	}
	
	public static char toUpperCase(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            ch -= 32;
        }
        return ch;
    }

    /**
     * 将给定字符串指定位置上的字母大写
     *
     * @param source String
     * @param pos    int
     * @return String
     */
    public static String upperCharAt(final String source, int pos) {
        if (source == null) {
            return null;
        }
        int len = source.length();
        if (pos < 0 || pos >= len) {
            return "";
        }
        char[] chars = source.toCharArray();
        chars[pos] = toUpperCase(chars[pos]);
        return new String(chars);
    }
    
    /**
     * 将给定字符串所有字母大写
     *
     * @param source String
     * @param pos    int
     * @return String
     */
    public static String upperStrintAt(final String source) {
        if (source == null) {
            return null;
        }
        char[] chars = source.toCharArray();
        StringBuffer returnString = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
        	chars[i] = toUpperCase(chars[i]);
		}
        return new String(chars);
    }
    public static void main(String[] args) {
		
	}
}
