package com.gpcsoft.bizplatform.base.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;


/**
 * 对字符串进行操作的工具类
 * 
 * @author <a href="mailto:xiaojf@cntmi.com">xiaojf</a>
 */
public class StringUtils extends com.gpcsoft.core.utils.StringUtils {
	public static String CHAR_DOUBLE_WELLS = "##"; 
	public static String CHAR_VERTICAL = "|";
	public static String CHAR_PERCENT = "%"; 
	public static String CHAR_SPACE = " "; 
	private static String PREFIX = "\\u";

	/** 
	 * Description :  ascii2转中文
	 * Create Date: 2010-11-18下午09:13:28 by liangxj  Modified Date: 2010-11-18下午09:13:28 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String ascii2Native(String str) {
		if (str == null) {
			str = "";
		}
		StringBuilder sb = new StringBuilder();
		int begin = 0;
		int index = str.indexOf(PREFIX);
		while (index != -1) {

			sb.append(str.substring(begin, index));

			sb.append(ascii2Char(str.substring(index, index + 6)));

			begin = index + 6;

			index = str.indexOf(PREFIX, begin);
		}

		sb.append(str.substring(begin));
		return sb.toString();
	}
	
	/** 
	 * Description :  
	 * Create Date: 2010-11-18下午09:13:48 by liangxj  Modified Date: 2010-11-18下午09:13:48 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private static char ascii2Char(String str) {
		if (str.length() != 6) {
			throw new IllegalArgumentException(
			"Ascii string of a native character must be 6 character.");
		}

		if (!PREFIX.equals(str.substring(0, 2))) {
			throw new IllegalArgumentException(
			"Ascii string of a native character must start with \" \\u\".");
		}
		String tmp = str.substring(2, 4);
		int code = Integer.parseInt(tmp, 16) << 8;
		tmp = str.substring(4, 6);
		code += Integer.parseInt(tmp, 16);
		return (char) code;
	}  

	
	/** 
	 * Description :  将字符串进行转码，由iso转jbk
	 * Create Date: 2010-9-2下午01:52:56 by liangxj  Modified Date: 2010-9-2下午01:52:56 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String codingStr(String str) throws Exception{
		String encode =  getEncoding(str);
		if (str == null)
			return str;
		return new String(str.getBytes(encode),"GBK");
	}
	
	/** 
	 * Description :  判断字符串的编码 
	 * Create Date: 2010-11-12下午04:34:13 by yucy  Modified Date: 2010-11-12下午04:34:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getEncoding(String str) {    
       String encode = "GB2312";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s = encode;    
               return s;    
            }    
       } catch (Exception exception) {    
       }    
       encode = "ISO-8859-1";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s1 = encode;    
               return s1;    
            }    
        } catch (Exception exception1) {    
        }    
       encode = "UTF-8";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s2 = encode;    
               return s2;    
            }    
       } catch (Exception exception2) {    
       }    
       encode = "GBK";    
       try {    
           if (str.equals(new String(str.getBytes(encode), encode))) {    
                String s3 = encode;    
               return s3;    
            }    
       } catch (Exception exception3) {    
       }    
       return "";    
    }    
	
	
	/** 
	 * Description :  将特殊字符进行替换
	 * Create Date: 2010-9-2下午02:10:58 by liangxj  Modified Date: 2010-9-2下午02:10:58 by liangxj
	 * @param   将"##"替换成"%"
	 * @return  
	 * @Exception   
	 */
	public static String replaceIngoreStr(String str) throws Exception{
		if (str == null)
			return str;
		return str.replace(CHAR_DOUBLE_WELLS, CHAR_PERCENT);
	}
	
	/** 
	 * Description :  还原特殊字符
	 * Create Date: 2010-9-2下午02:10:58 by liangxj  Modified Date: 2010-9-2下午02:10:58 by liangxj
	 * @param   将"##"替换成" "
	 * @return  
	 * @Exception   
	 */
	public static String returnIngoreStr(String str) throws Exception{
		if (str == null)
			return str;
		return str.replace(CHAR_DOUBLE_WELLS, CHAR_SPACE);
	}
	
	/** 
	 * Description :  将空格转换成百分号
	 * Create Date: 2010-9-3下午06:57:39 by liangxj  Modified Date: 2010-9-3下午06:57:39 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String spaceToPercent(String str) throws Exception{
		if (str == null)
			return str;
		return str.replace(CHAR_SPACE, CHAR_PERCENT);
	}
	
	
	/** 
	 * Description :  生成指定长度为的字符串
	 * Create Date: 2010-11-16下午06:35:05 by dongcl  Modified Date: 2010-11-16下午06:35:05 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String getRandomString(int lenth) { 
	    StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
	    StringBuffer sb = new StringBuffer(); 
	    Random r = new Random(); 
	    int range = buffer.length(); 
	    for (int i = 0; i < lenth; i ++) { 
	        sb.append(buffer.charAt(r.nextInt(range))); 
	    } 
	    return sb.toString(); 
	}
	
	/** 
	 * Description :  解码base64code
	 * Create Date: 2011-10-6上午11:42:14 by yucy  Modified Date: 2011-10-6上午11:42:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static String Base64Code(String base64String){
		String base64StringOut = null;
	    try {
	    	base64StringOut =  StringUtils.returnIngoreStr ( URLDecoder.decode( new String( Base64.decodeBase64(base64String.getBytes()) ) , "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64StringOut;
	}
}