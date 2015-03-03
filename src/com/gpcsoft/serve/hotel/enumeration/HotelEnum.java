package com.gpcsoft.serve.hotel.enumeration;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

public class HotelEnum {
	/**
	 * 商品审核状态[草稿:00,待审核:01,审核通过:02,审核不通过:03]
	 */
	public final static String HOTEL_AUDIT_STATUS = "auditStatus";
    public static final String DRAFT_EXAM  = "00";
    public static final String AWAIT_EXAM  = "01";
    public static final String PASS_EXAM  = "02";
    public static final String NO_PASS_EXAM  = "03";
    public static final String getAuditStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.HOTEL_AUDIT_STATUS, str);
    }
    
    /**
     * 使用状态[临时:00,有效:01,报废:02]
     */
    public final static String HOTEL_USE_STATUS = "useStatus";
	public final static String USE_TEMP = "00";
	public final static String USE_VALID = "01";
	public final static String USE_INVALID = "02";
    public static final String getUseStatusCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.HOTEL_USE_STATUS, str);
    }
    
    /**
     * 使用状态[启用:1,禁用:2]
     */
    public final static String HOTEL_ISOFF = "isOff";
    public final static String DISABLE = "2";
    public final static String ENABLE = "1";
    /**
     * 禁用启用中文
     * @param   
     * @return  
     */
    public static String getIsOffCN(String str){
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(OrganizationEnum.ORG_INFO_ISOFF, str);
	}
    
    /**
     * 客房类型[01:标准房,02:商务房,03：高级房,04:豪华房]
     */
    public final static String GUEST_ROOM_TYPE = "serve.hotel.type";
    public static final String getGuestRoomTypeCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.GUEST_ROOM_TYPE, str);
    }
    
    /**
     * 床型[01:单人床,02:双人床,03:大床]
     */
    public final static String BED_TYPE = "serve.hotel.bedType";
    public static final String getBedTypeCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.BED_TYPE, str);
    }
    
    /**
     * 早餐类型[00:无早,01:单早,02：双早]
     */
    public final static String BREAKFAST_TYPE = "serve.hotel.breakfastType";
    public static final String getBreakfastTypeCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.BREAKFAST_TYPE, str);
    }
    
    /**
     * 餐饮设施[00:咖啡厅,01:宴会厅]
     */
    public final static String FOOD_FACILITIES = "serve.hotel.foodFacilities";
    public static final String getFoodFacilitiesCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.FOOD_FACILITIES, str);
    }
    
    /**
     * 酒店服务项目[00:预订火车票,01:预订飞机票]
     */
    public final static String SERVICE_ITEMS = "serve.hotel.serviceItems";
    public static final String getServiceItemsCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.SERVICE_ITEMS, str);
    }
    
    /**
     * 娱乐设施[00:健身馆,01:桑拿]
     */
    public final static String FUN_FACILITIES = "serve.hotel.funFacilities";
    public static final String getFunFacilitiesCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.FUN_FACILITIES, str);
    }
    
    /**
     * 客房设施和服务[00:国内长途电话,01:国际长途电话,02:中央空调,03:24小时热水]
     */
    public final static String GUEST_ROOM_FACILITIES = "serve.hotel.guestRoomFacilities";
    public static final String getGuestRoomFacilitiesCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.GUEST_ROOM_FACILITIES, str);
    }
    
    /**
     * 可接受信用卡类型[00:银联(UnionPay),01:万事达(Master),02:威士(VISA),03:运通(AMEX),04:大来(Diners Club)]
     */
    public final static String CREDIT_CARDTYPE = "serve.hotel.creditCardType";
    public static final String getCreditCardTypeCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.CREDIT_CARDTYPE, str);
    }
    
    /**
     * 星级[00:无星级,01:一星级,02:二星级,03:三星级,04:四星级,05:五星级]
     */
    public final static String STAR = "serve.hotel.star";
    public static final String getStarCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.STAR, str);
    }
    
    /**
     * 会议室类型[00:U型,01:董事局会议室,02:小组讨论]
     */
    public final static String MEETINGROOMTYPE = "serve.hotel.meetingRoomType";
    public static final String getMeetingRoomTypeCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.MEETINGROOMTYPE, str);
    }
    
    /**
     * 会议室设施[00:投影仪,01:视频,02:会议桌,03:沙发]
     */
    public final static String MEETINGROOMFACILITIES = "serve.hotel.meetingRoomFacilities";
    public static final String getMeetingRoomFacilitiesName(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.MEETINGROOMFACILITIES, str);
    }
    
    /**
     * 价格单位[00:半天,01:一天,02:一周,03:一月]
     */
    public final static String PRICE_UNIT = "serve.hotel.priceUnit";
    public static final String getUnitCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.PRICE_UNIT, str);
    }
    
    /**
     * 会议室人数范围[0_50:超小型50人以下,51_100:小型51-100人,101_200:中小型101-200人,201_500:中型201-500人,501_750:大中型501-750人,751_1000:大型751-1000人,1001_:超大型1000人以上]
     */
    public final static String MEETING_NUM_RANG = "erve.hotel.meetingNumRang";
    public final static String MINI = "0_50";
    public final static String SMALL = "51_100";
    public final static String SMALL_MEDIUM = "101_200";
    public final static String MEDIUM = "201_500";
    public final static String MEDIUM_LARGE = "501_750";
    public final static String LARGE = "751_1000";
    public final static String SUPER_LARGE = "1001_";
    public static final String getMeetingNumRangCN(String str) {
    	return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(HotelEnum.MEETING_NUM_RANG, str);
    }
}
