package com.gpcsoft.epp.common.utils;

import java.util.Date;
import java.util.Hashtable;

import junit.framework.Assert;

/** 
 *  Comments: <strong>generatorSequence</strong>            		
 *	 <br/>		        																							
 *  <br/>CopyRright (c)2011-xxxx:   珠海政采软件技术有限公司    		
 *  <br/>Project:   zh-epp                    					          
 *  <br/>Module ID:     		
 *  <br/>Create Date：2011-12-15 下午01:01:22 by chenhongjun    					                            
 *  <br/>Modified Date:  2011-12-15 下午01:01:22 by chenhongjun                                   
 *<p>@since 0.5
 *   @version: 0.5 
 */
public class GeneratorSequence {
	private static int SIZE=10000;
	private static Hashtable<String, String> seqMap=new Hashtable<String, String>(SIZE);
	
	public static String generatorSeqyyyyMMdd(){
		String prefix=DateUtil.format(new Date(), "yyyyMMdd");
 		return generatorSeqPreFix(prefix,5);
	}
	
	/**
	 * 根据给出的序列号前缀和要求的随机号长度，生成唯一的编号
	 * @param prefix   序列号前缀
	 * @param lenght   序列号长度
	 * @return  序列号
	 */
	public static String generatorSeqPreFix(String prefix,int lenght){
		Assert.assertNotNull("序列号前缀不能为空", prefix);
		Assert.assertTrue("序列号长度不能为0",lenght>1);
		String seq=StringUtil.randomString(lenght);
		String key=prefix+seq;
		while(contains(key)){
			seq=StringUtil.randomString(5);
			key=prefix+seq;
		}
		putKey(key, key);
 		return key;
	}
	
	private static boolean contains(String key){
		return seqMap.containsKey(key);
	}
	
	private static void putKey(String key,String value){
		seqMap.put(key, value);
	}
	 
	public static void main(String[] args) throws Exception{
		BarCodeUtil.generateBarCode(generatorSeqPreFix("20121215",5), "D://tets.gif");
	}
	
}
