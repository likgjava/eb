package com.gpcsoft.pubservice.common.enumeration;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class PictureWHRuleEnum {
	
	/**
	 * 商品图片宽、高大小规则
	 */
	public final static String GOODS_PIC_WIDTH_HEIGHT_RULE = "goods_pic_width_height_rule";
	//商品图片规则1
	public final static String GOODS_PIC_WH_RULE_80_80= "01";
	//商品图片规则2
	public final static String GOODS_PIC_WH_RULE_160_160 = "02";
	//商品图片规则3
	public final static String GOODS_PIC_WH_RULE_320_320 = "03";
	
	/**
	 * 获取商品图片的宽高值
	 */
	public static final String getGoodsPicWidthAndHeight(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PictureWHRuleEnum.GOODS_PIC_WIDTH_HEIGHT_RULE, str);
	}
	
	
	/**
	 * 品牌图片宽、高大小规则
	 */
	public final static String BRANDS_PIC_WIDTH_HEIGHT_RULE = "brands_pic_width_height_rule";
	//品牌图片规则1
	public final static String BRANDS_PIC_WH_RULE_100_75= "01";
	//品牌图片规则2
	public final static String BRANDS_PIC_WH_RULE_200_150 = "02";
	//品牌图片规则3
	public final static String BRANDS_PIC_WH_RULE_300_225 = "03";
	
	/**
	 * 获取品牌图片的宽高值
	 */
	public static final String getBrandsPicWidthAndHeight(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PictureWHRuleEnum.BRANDS_PIC_WIDTH_HEIGHT_RULE, str);
	}
    
	/**
	 * 机构logo图片宽、高大小规则
	 */
	public final static String ORG_PIC_WIDTH_HEIGHT_RULE = "org_pic_width_height_rule";
	//机构logo图片规则1
	public final static String ORG_PIC_WH_RULE_60_60= "01";
	//机构logo图片规则2
	public final static String ORG_PIC_WH_RULE_130_130 = "02";
	//机构logo图片规则3
	public final static String ORG_PIC_WH_RULE_180_180 = "03";
	
	/**
	 * 获取机构logo图片的宽高值
	 */
	public static final String getOrgPicWidthAndHeight(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PictureWHRuleEnum.ORG_PIC_WIDTH_HEIGHT_RULE, str);
	}
	
	/**
	 * 专家头像图片宽、高大小规则
	 */
	public final static String EXPERT_PIC_WIDTH_HEIGHT_RULE = "expert_pic_width_height_rule";
	//专家头像图片规则1
	public final static String EXPERT_PIC_WH_RULE_60_80= "01";
	//专家头像图片规则2
	public final static String EXPERT_PIC_WH_RULE_120_160 = "02";
	//专家头像图片规则3
	public final static String EXPERT_PIC_WH_RULE_180_240 = "03";
	
	/**
	 * 获取专家头像图片的宽高值
	 */
	public static final String getExpertPicWidthAndHeight(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PictureWHRuleEnum.EXPERT_PIC_WIDTH_HEIGHT_RULE, str);
	}
}