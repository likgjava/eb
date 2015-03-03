package com.gpcsoft.bizplatform.base.message;

public class CustomerMessage {

	public static String APP_NAME = "bizplatform.appName";
	//用户邮箱有空
	public static String USER_NO_EMAILADDRESS = "eps.orginfo.noEmailAddress";
	
	//注册完毕
	public static String ORGINFO_REGISTER_COMPLETE="bizplatform.orginfo.register.complete";
	
	//注册完毕模板文件
	public static String ORGINFO_REGISTER_TEMPLATE="bizplatform.orginfo.register.template";	
	
	//推广人注册完毕
	public static String PROMOTER_REGISTER_COMPLETE="smallscale.promoter.register.complete";
	
	//推广人注册完毕模板文件
	public static String PROMOTER_REGISTER_TEMPLATE="smallscale.promoter.register.template";
	
	//咨询专家注册完毕
	public static String EXPERT_REGISTER_COMPLETE="smallscale.expert.register.complete";
	
	//咨询专家注册完毕模板文件
	public static String EXPERT_REGISTER_TEMPLATE="smallscale.expert.register.template";
	
	//注册信息审核完毕
	public static String ORGINFO_AUDIT_COMPLETE="bizplatform.orginfo.audit.complete";
	
	//机构变更审核完毕
	public static String ORGINFO_MODIFY_AUDIT_COMPLETE="bizplatform.orginfo.modify.complete";
	
	//机构申请审核完毕
	public static String ORGINFO_APPLY_AUDIT_COMPLETE="bizplatform.orginfo.apply.complete";
	
	//审核通过的模板文件
	public static String ORGINFO_APPLY_PASS_TEMPLATE = "bizplatform.orginfo.apply.pass";
	
	//审核不通过的模板文件
	public static String ORGINFO_APPLY_NOT_PASS_TEMPLATE = "bizplatform.orginfo.apply.notpass";
	
	//审核通过的模板文件
	public static String ORGINFO_MODIFY_PASS_TEMPLATE = "bizplatform.orginfo.modify.pass";
	
	//审核不通过的模板文件
	public static String ORGINFO_MODIFY_NOT_PASS_TEMPLATE = "bizplatform.orginfo.modify.notpass";
	
	//审核通过的模板文件
	public static String ORGINFO_AUDIT_PASS_TEMPLATE = "bizplatform.orginfo.audit.pass";
	
	//审核不通过的模板文件
	public static String ORGINFO_AUDIT_NOT_PASS_TEMPLATE = "bizplatform.orginfo.audit.notpass";
	
	//保存成功
	public static String ORG_SAVE_SUCCESS = "eps.bizplatform.saveSuccess";
	//商品禁卖成功
	public static String GOODS_FORBIDDEN_SUCCESS = "eps.goods.forbiddenSuccess";
	//商品启卖成功
	public static String GOODS_START_SUCCESS = "eps.goods.startSuccess";
	//商品报废成功
	public static String GOODS_SCRAPPED_SUCCESS = "eps.goods.scrappedSuccess";
	
	// 邀请函
	public static String INVITATION_EMAIL_SUBJECT = "agreement.bargin.invitation.email.subject";
	
	// 邀请函模板
	public static String INVITATION_EMAIL_TEMPLATE = "agreement.bargin.invitation.email.template";
	
	//结果模板
	public static String INVITATION_BUYRESULT_TEMPLATE = "agreement.bargin.buyresult.template";
	
	// 邀请函站内信
	public static String INVITATION_MESSAGE_TEMPLATE = "agreement.bargin.invitation.message.template";
	
	// 竞价邀请函模板
	public static String INVITATION_TEMPLATE = "agreement.bargin.invitation.template";
	
	// 议价邀请函模板
	public static String INVITATION_BARGAIN_TEMPLATE = "agreement.bargin.invitationBargain.template";
	
	// 预告模板模板
	public static String BULLETIN_NOTICE_TEMPLATE = "agreement.bargin.bulletin.notice.template";
	
	// 项目公告模板
	public static String BULLETIN_TEMPLATE = "agreement.bargin.bulletin.template";
	
	//黑名单客户报名项目时站内信提醒的内容模板
	public static String BLACKLIST_SIGNUP_MESSAGE_TEMPLATE = "agreement.bargin.blacklistSignupMessage.template";
	public static String BLACKLIST_SIGNUP_MESSAGE_REMIND_TITLE = "agreement.bargin.blacklistSignupMessage.remindTitle";
	
	//订单作废模板
	public static String ORDER_DESTORY_TEMPLATE = "agreement.bargin.orderdestroy.template";
	
	//供应商成功案例模板
	public static String SUCCESSCASE_SUPPLIER_TEMPLATE = "bizplatform.organization.successcase.supplier.template";
	
	//采购人成功案例模板
	public static String SUCCESSCASE_BUYER_TEMPLATE = "bizplatform.organization.successcase.buyer.template";
	
	//广告图片模板
	public static String ADVERTISING_PICTURE_TEMPLATE = "pubservice.message.adverPicture.template";
	
	//广告flash模板
	public static String ADVERTISING_FLASH_TEMPLATE = "pubservice.message.adverFlash.template";
	
	//广告跑马灯模板
	public static String ADVERTISING_POMADENG_TEMPLATE = "pubservice.message.adverPaomadeng.template";
	
	//广告默认图片模板
	public static String ADVERTISING_DEFAULT_TEMPLATE = "pubservice.message.adverDefault.template";
	
	//供应商报价排名FusionChart XML模板
	public static String BIDDING_RANK_TEMPLATE = "agreement.bargin.bidding.biddingRank.template";
	
	//客户竞价项目邀请函模板
	public static String INVITATION_CONCERN_CLIENT_PROJECT_TEMPLATE = "pubservice.application.concern.project_template";
	
	
	/************************短信模板*****************************/
	
	//中标短信模板
	public static String INVITATION_BUYRESULT_DEAL_MESSAGE = "agreement.bargin.buyresult.deal.message";
	
	//剔除短信模板
	public static String PROJECT_ELIMINATE_MESSAGE = "agreement.bargin.eliminate.message";
	
	//邮件推荐公告的邮件标题模板
	public static String MAIL_RECOMMEND_BULLETIN_SUBJECT = "agreement.bargin.bulletin.recommend.mailSubject";
	
	//邀请供应商参与项目短信通知的内容模板
	public static String INVITATION_SUPPLIER_MOBILE_MESSAGE_CONTENT = "agreement.bargin.invitationSupplier.mobileMessageContent";
	
	/************推荐采购需求********************/
	//邮件推荐采购需求的邮件标题模板
	public static String MAIL_RECOMMEND_REQUIREMENT_SUBJECT = "agreement.bargin.requirement.recommend.mailSubject";
	//邮件推荐采购需求的邮件内容模板
	public static String MAIL_RECOMMEND_REQUIREMENT_CONTENT = "agreement.bargin.requirement.recommend.mailContent";
	
	/************发布公告通知********************/
	//发布公告时，邮件、站内信、短信通知供应商的标题
	public static String RELEASE_BULLETIN_REMIND_TITLE = "agreement.bargin.bulletin.releaseBulletin.remindTitle";
	//发布公告时，邮件、站内信、短信通知供应商的内容
	public static String RELEASE_BULLETIN_REMIND_CONTENT = "agreement.bargin.bulletin.releaseBulletin.remindContent";
	
	/***********提交合同*************/
	//采购人提交合同邮件、站内信提醒的标题
	public static String BUYER_SUBMINT_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.buyerSubmit.remindTitle";
	//采购人提交合同邮件、站内信、短信提醒的内容
	public static String BUYER_SUBMINT_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.buyerSubmit.remindContent";
	
	//供应商提交合同时邮件、站内信提醒的标题
	public static String SUPPLIER_SUBMINT_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.supplierSubmit.remindTitle";
	//供应商提交合同时邮件、站内信、短信提醒的内容
	public static String SUPPLIER_SUBMINT_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.supplierSubmit.remindContent";
	
	/***********确认合同*************/
	//采购人确认合同时邮件、站内信提醒的标题
	public static String BUYER_CONFIRM_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.buyerConfirm.remindTitle";
	//采购人确认合同时邮件、站内信、短信提醒的内容
	public static String BUYER_CONFIRM_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.buyerConfirm.remindContent";
	
	//供应商确认合同时邮件、站内信提醒的标题
	public static String SUPPLIER_CONFIRM_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.supplierConfirm.remindTitle";
	//供应商确认合同时邮件、站内信、短信提醒的内容
	public static String SUPPLIER_CONFIRM_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.supplierConfirm.remindContent";
	
	/***********确认作废合同*************/
	//采购人确认作废合同时邮件、站内信提醒的标题
	public static String BUYER_CONFIRM_INVALID_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.buyerConfirmInvalid.remindTitle";
	//采购人确认作废合同时邮件、站内信、短信提醒的内容
	public static String BUYER_CONFIRM_INVALID_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.buyerConfirmInvalid.remindContent";
	
	//供应商确认作废合同时邮件、站内信提醒的标题
	public static String SUPPLIER_CONFIRM_INVALID_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.supplierConfirmInvalid.remindTitle";
	//供应商确认作废合同时邮件、站内信、短信提醒的内容
	public static String SUPPLIER_CONFIRM_INVALID_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.supplierConfirmInvalid.remindContent";
	
	/***********申请作废合同*************/
	//采购人申请作废合同时邮件、站内信提醒的标题
	public static String BUYER_APPLY_INVALID_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.buyerApplyInvalid.remindTitle";
	//采购人申请作废合同时邮件、站内信、短信提醒的内容
	public static String BUYER_APPLY_INVALID_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.buyerApplyInvalid.remindContent";
	
	//供应商申请作废合同时邮件、站内信提醒的标题
	public static String SUPPLIER_APPLY_INVALID_AGCONTRACT_REMIND_TITLE = "agreement.bargin.agContract.supplierApplyInvalid.remindTitle";
	//供应商申请作废合同时邮件、站内信、短信提醒的内容
	public static String SUPPLIER_APPLY_INVALID_AGCONTRACT_REMIND_CONTENT = "agreement.bargin.agContract.supplierApplyInvalid.remindContent";
	
	/***********提交订单*************/
	//采购人提交订单时，短信、邮件、站内信提醒的标题
	public static String BUYER_SUBMINT_ORDER_REMIND_TITLE = "agreement.bargin.order.buyerSubmit.remindTitle";
	//采购人提交订单时，短信、邮件、站内信、短信提醒的内容
	public static String BUYER_SUBMINT_ORDER_REMIND_CONTENT = "agreement.bargin.order.buyerSubmit.remindContent";
	
	/***********确认订单*************/
	//供应商确认订单时，短信、邮件、站内信提醒的标题
	public static String SUPPLIER_CONFIRM_ORDER_REMIND_TITLE = "agreement.bargin.order.supplierConfirm.remindTitle";
	//供应商确认订单时，短信、邮件、站内信、短信提醒的内容
	public static String SUPPLIER_CONFIRM_ORDER_REMIND_CONTENT = "agreement.bargin.order.supplierConfirm.remindContent";
	
	/***********申请调整订单*************/
	//供应商申请调整订单时，短信、邮件、站内信提醒的标题
	public static String SUPPLIER_APPLY_ADJUST_ORDER_REMIND_TITLE = "agreement.bargin.order.supplierApplyAdjust.remindTitle";
	//供应商申请调整订单时，短信、邮件、站内信、短信提醒的内容
	public static String SUPPLIER_APPLY_ADJUST_ORDER_REMIND_CONTENT = "agreement.bargin.order.supplierApplyAdjust.remindContent";
	
	/***********机构注册审核通过*************/
	//机构注册审核通过时，短信、站内信提醒的标题
	public static String ORG_REG_AUDIT_PASS_REMIND_TITLE = "bizplatform.organization.orgRegAuditPass.remindTitle";
	//机构注册审核通过时，短信、站内信、短信提醒的内容
	public static String ORG_REG_AUDIT_PASS_REMIND_CONTENT = "bizplatform.organization.orgRegAuditPass.remindContent";
	
	
}
