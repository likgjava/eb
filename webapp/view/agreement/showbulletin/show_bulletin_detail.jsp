<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon" />
<title>${bulletin.bullTitle } - 阳光易购电子采购与招标平台</title>
<meta name="keywords" content="${buyer.orgInfo.orgName}，招标预告，招标公告，采购，竞价采购，政府采购，企业采购，求购信息" />
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/three.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin01/css/bulletinDetail.css"/>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/agreement/showbulletin/show_bulletin_detail.js"></script>
</head>
<body>
<div id="container">
<!-- 头部开始 -->
<div class="header">
	<%@ include file="/view/srplatform/portal/include/main_header_index.jsp"%>
</div>
<!-- 头部结束-->
<!--主要内容 开始-->
<div class="page" id="sysContent">
	<input type="hidden" id="bulletinType" value="${bulletin.bullType }" />
	<input type="hidden" id="currentOrgId" value="${currentOrgId }" />
	<input type="hidden" id="buyerOrgInfoId" value="${buyer.orgInfo.objId }" />
	<input type="hidden" id="projectCreator" value="${projectCreator}" />
	<input type="hidden" id="loginSuccess" value="0" />
	<input type="hidden" id="signUpSuccess" value="0" />
	<c:set var="evalName" value="报价" scope="page" />
	<c:if test="${bulletin.bullType=='01'}">
		<c:set var="evalName" value="投标" />
	</c:if>
	
	<div class="base_t3" id="base_bd">
		<h2 class="package_headline layoutfix">
			${bulletin.bullTitle }
			<c:if test="${bulletin.bullType=='01'}">
				<p class="package_fontcolor">我是采购单位，也要发布招标项目，请注册登录后点击<a href="javascript:void(0);" onclick="ShowBulletinDetail.createBidProject();">&nbsp;发布招标项目</a>。</p>
			</c:if>
			<c:if test="${bulletin.bullType!='01'}">
				<p class="package_fontcolor">我是采购单位，也要发布采购需求，请注册登录后点击<a href="javascript:void(0);" onclick="ShowBulletinDetail.createRequirement();">&nbsp;发布采购需求</a>。</p>
			</c:if>
		</h2>
		
		<!-- 项目进度 -->
		<ul class="base_step base_step${processStatus }">
			<li class="view">发布公告</li>
			<li class="select">供应商报名</li>
			<c:if test="${bulletin.bullType=='01'}">
			<li class="book">投标评标</li>
			<li class="check">定标</li>
			</c:if>
			<c:if test="${bulletin.bullType!='01'}">
			<li class="book">供应商报价</li>
			<li class="check">确定成交供应商</li>
			</c:if>
			<li class="submit">项目结束</li>
		</ul>

		<div class="base_box package_details_intro">
			<div class="b_bd layoutfix">
				<div class="package_details_info">
					<div class="package_name">
						<a onclick="common.geToBuyerDetail('${buyer.objId}');" href="javascript:void(0);">${buyer.orgInfo.orgName}</a>
					</div>
					<div class="package_details_date">
						<p>发标次数：${bulletinNum} &nbsp;&nbsp;评价总分：<fmt:formatNumber type="number" value="${buyer.evalSum }" pattern="#0.0"/>分</p>
					</div>
					<!-- JiaThis Button BEGIN -->
					<div id="ckepop" style="float:right;padding-right:10px;">
						<span class="jiathis_txt">分享到：</span>
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_renren"></a>
						<a class="jiathis_button_kaixin001"></a>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						<a class="jiathis_counter_style"></a>
					</div>
					<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>
					<!-- JiaThis Button END -->
				</div>

				<div class="pripackage_date_main">
					<h5 style="font-size: 14px; width: 300px; float: left;">
						<c:choose>
							<c:when test="${processStatus=='1'}">
								报名剩余时间：<span class="base_txtgray">报名未开始</span>
							</c:when>
							<c:when test="${hasSignUp && remainEvalTime>0}">
								${evalName}剩余时间：<span class="base_txtgray" id="remainSignUpTime" name="${remainEvalTime}" tipname="${evalName}"></span>
							</c:when>
							<c:when test="${hasSignUp && remainEvalTime==-2}">
								${evalName}剩余时间：<span class="base_txtgray">${evalName}未开始</span>
							</c:when>
							<c:when test="${hasSignUp}">
								${evalName}剩余时间：<span class="base_txtgray">${evalName}已结束</span>
							</c:when>
							<c:when test="${remainSignUpTime>0}">
								报名剩余时间：<span class="base_txtgray" id="remainSignUpTime" name="${remainSignUpTime}" tipname="报名"></span>
							</c:when>
							<c:otherwise>
								报名剩余时间：<span class="base_txtgray">报名已结束</span>
							</c:otherwise>
						</c:choose>
					</h5>
					<c:choose>
						<c:when test="${userType=='visitor'}">
							<div style="width: 100px; float: left;">
								<c:if var="canSignUp" test="${remainSignUpTime>0}">
									<input type="button" class="base_btns1" value="登录后报名" onclick="ShowBulletinDetail.login();"/>
								</c:if>
								<c:if test="${!canSignUp}">
									<input type="button" class="base_btns1_disabled" value="我要报名" disabled="disabled"/>
								</c:if>
							</div>
							<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
								★我还没有注册：请点击这里进行 <a href="javascript:void(0);" onclick="ShowBulletinDetail.register();">注册</a>，注册后提交审核通过后，即可进行在线报名。<br/>
								★我已是供应商：请点击这里进行 <a href="javascript:void(0);" onclick="ShowBulletinDetail.login();">登录</a>，登录后即可进行在线报名。
							</p>
						</c:when>
						<c:when test="${userType=='personal'}">
							<div style="width: 100px; float: left;">
								<input type="button" class="base_btns1_disabled" value="我要报名" disabled="disabled"/>
							</div>
							<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
								<c:if test="${bulletin.bullType=='01'}">
								★我还不是采购人：申请成为采购人后，即可发布招标公告。<br/>
								</c:if>
								<c:if test="${bulletin.bullType!='01'}">
								★我还不是采购人：申请成为采购人后，即可发布采购需求。<br/>
								</c:if>
								★我还不是供应商：申请成为供应商后，即可进行在线报名。
							</p>
						</c:when>
						<c:when test="${userType=='supplier' && hasSignUp}">
							<div style="width: 100px; float: left;">
								<input type="button" onclick="ShowBulletinDetail.showProject('${bulletin.project.objId}','${userType}');" class="base_btns4" value="进入项目" />
							</div>
							<c:choose>
							<%--招标项目--%>
							<c:when test="${bulletin.bullType=='01'}">
								<c:if test="${!isDividePack}">
								<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
									★招标文件每套费用(元/人民币)：￥<fmt:formatNumber value="${bulletin.project.purDocPrice}" pattern="#,##0.00#" />&nbsp;&nbsp;
									<!-- 已支付，查看支付记录 -->
									<c:if test="${hasDocPay}">
										已支付完成，<a href="javascript:void(0);" onclick="ShowBulletinDetail.showDocPayRecord('${doc_pay_business_id}');">查看支付记录</a>&nbsp;&nbsp;
										<a href="javascript:void(0);" onclick="ShowBulletinDetail.downloadPurchaseDocFile('${bulletin.project.objId}');return false;"><span class="sysicon siDownBtn" style="padding-left: 17px;">下载招标文件</span></a>
									</c:if>
									<!-- 未支付，显示购买链接 -->
									<c:if test="${!hasDocPay}">
										<a href="javascript:void(0);" onclick="ShowBulletinDetail.toDocPay('${bulletin.project.objId}','${bulletin.project.purDocPrice}');">购买</a>
									</c:if>
									
									<br />
									
									★保证金费用(元/人民币)：￥<fmt:formatNumber value="${bulletin.project.bail}" pattern="#,##0.00#" />&nbsp;&nbsp;
									<!-- 已支付，查看支付记录 -->
									<c:if test="${hasBailPay}">
										已支付完成，<a href="javascript:void(0);" onclick="ShowBulletinDetail.showBailPayRecord('${bail_pay_business_id}');">查看支付记录</a>
									</c:if>
									<!-- 未支付，显示购买链接 -->
									<c:if test="${!hasBailPay}">
										<a href="javascript:void(0);" onclick="ShowBulletinDetail.toBailPay('${bulletin.project.objId}','${bulletin.project.bail}');">缴纳</a>
									</c:if>
								</p>
								</c:if>
								<c:if test="${isDividePack}">
								<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
									★查看标书费支付情况请点击：<a href="javascript:void(0);" onclick="ShowBulletinDetail.showPacksDocPay('${bulletin.project.objId}','${bulletin.project.projName}');">标书费支付记录</a><br/>
									★查看保证金支付情况请点击：<a href="javascript:void(0);" onclick="ShowBulletinDetail.showPacksBailPay('${bulletin.project.objId}','${bulletin.project.projName}');">保证金支付记录</a>
								</p>
								</c:if>
							</c:when>
							<%--竞价项目--%>
							<c:when test="${bulletin.bullType=='12'}">
								<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
									★我已参与该项目：点击进入项目查看更多项目信息。
								</p>
							</c:when>
							</c:choose>
						</c:when>
						<c:when test="${userType=='supplier' && !hasSignUp && remainSignUpTime>0}">
							<div style="width: 100px; float: left;">
								<input type="button" id="signUpBut" onclick="ShowBulletinDetail.signUp('${bulletin.project.objId}','${bulletin.bullType}')" class="base_btns1" value="我要报名" />
							</div>
							<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
								<c:if test="${bulletin.bullType=='01'}">
								★我还没有参与该项目：请先下载 <a href="AttachmentController.do?method=downLoadFile&objId=ff8080812fe93117012ff81a6d5e1111">购买标书登记表.doc</a>文件，然后填写表格。<br/>
								★报名的时候上传填写后的报名表。
								</c:if>
								<c:if test="${bulletin.bullType!='01'}">
								★我还没有参与该项目：点击我要报名，即可进行在线报名。
								</c:if>
							</p>
						</c:when>
						<c:when test="${userType=='supplier' && !hasSignUp}">
							<div style="width: 100px; float: left;">
								<input type="button" class="base_btns1_disabled" value="我要报名" disabled="disabled"/>
							</div>
							<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
								★您现在不能参与该项目：点击 <c:choose><c:when test="${bulletin.bullType=='01'}"><a target="_blank" href="<%=request.getContextPath()%>/BulletinShowController.do?method=toShowBiddingIndexView">招标项目</a></c:when><c:otherwise><a target="_blank" href="<%=request.getContextPath()%>/BulletinShowController.do?method=toShowBargainIndexView">采购项目</a></c:otherwise></c:choose>，寻找我可参与的项目。
							</p>
						</c:when>
						<c:when test="${userType=='publisher' || userType=='agency'}">
							<div style="width: 100px; float: left;">
								<input type="button" onclick="ShowBulletinDetail.showProject('${bulletin.project.objId}','${userType}');" class="base_btns4" value="进入项目" />
							</div>
							<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
								★该公告是我发布的：点击进入项目，可以查看项目详细信息。
							</p>
						</c:when>
						<c:otherwise>
							<div style="width: 100px; float: left;">
								<input type="button" class="base_btns1_disabled" value="我要报名" disabled="disabled"/>
							</div>
							<p class="package_fontcolor" style="padding-top: 10px; clear: both;">
								★我还不是供应商：申请成为供应商后，即可进行在线报名。
							</p>
						</c:otherwise>
					</c:choose>
					<div class="mailtofrd" style="float: right; margin-top: 10px;">
						<!--<a title="将此公告推荐给好友" href="javascriptvascript:void(0);" onclick="ShowBulletinDetail.shareBulletin('${bulletin.objId}','${bulletin.bullTitle}');return false;"><img src="<%=request.getContextPath() %>/view/resource/skin/skin07/img/map.gif" /> 推荐</a>--> &nbsp;
						<a title="收藏此公告" href="javascript:void(0);" onclick="ShowBulletinDetail.favoritesBulletin('${bulletin.objId}','${bulletin.bullTitle}','07');return false;"><img src="<%=request.getContextPath() %>/view/resource/skin/skin07/img/registration.png" /> 收藏</a> &nbsp;
						<a title="打印此公告内容" href="javascript:void(0);" onclick="ShowBulletinDetail.printBulletin();return false;"><img src="<%=request.getContextPath() %>/view/resource/skin/skin07/img/printer.png" /> 打印</a> &nbsp;
						<a title="关闭本页面" href="javascript:self.close();"><img src="<%=request.getContextPath() %>/view/resource/skin/sysicon/16/cross.png" /> 关闭本页</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 公告内容 -->
		<div class="package_lines layoutfix" id="lines">
			${bullContents }
		</div>
		
		<!-- 已报名供应商  开始  -->
		<c:if test="${bulletin.bullType=='12'}">
		<div class="package_pribg">
			<h2><span>已报名供应商</span></h2>
			<ul class="package_recommend layoutfix">
				<c:forEach var="signUprecord" items="${signUprecordList}">
					<li style="height: 25px;">
						<p class="item" style="display: ">
							<a href="javascript:void(0);" onclick="common.goToOrgShop('${signUprecord.supplier.objId}');return false;">${signUprecord.supplierName}</a>
						</p>
					</li>
				</c:forEach>
			</ul>
		</div>
		</c:if>
		<!-- 已报名供应商  结束 -->

		<!-- 相关公告  -->
		<div class="package_pribg">
			<h2><span>相关公告</span></h2>
			<ul class="package_recommend layoutfix">
				<c:forEach var="relatedBulletin" items="${relatedBulletinList}">
					<c:if test="${relatedBulletin.objId != bulletin.objId}">
					<li>
						<p class="item">
							<span class="base_price02">${relatedBulletin.bullTypeCN }</span>
							<a href="javascript:void(0);" onclick="common.goToBulletinDetail('${relatedBulletin.project.objId }','${relatedBulletin.bullType}');">${relatedBulletin.bullTitle }</a>
						</p>
						<p>${relatedBulletin.project.buyersName }</p>
					</li>
					</c:if>
				</c:forEach>
			</ul>
		</div>
		
		<!-- 项目成交信息  开始 -->
		<c:if test="${bulletin.bullType=='13'}">
		<div class="package_pribg">
			<h2><span>项目成交信息</span></h2>
			<table class="package_itinerary_table">
				<tbody>
					<tr>
						<th style="width: 40%;">中标供应商名称</th>
						<th style="width: 20%;">项目预算（元）</th>
						<th style="width: 20%;">供应商报价（元）</th>
						<th style="width: 20%;">节省金额（元）</th>
					</tr>
					<c:forEach var="resultMap" items="${resultList}">
					<tr>
						<td><a href="javascript:void(0);" onclick="common.goToOrgShop('${resultMap['winner'].selllerId}'); return false;">${resultMap['winner'].sellerName}</a></td>
						<td class="price"><fmt:formatNumber value="${bulletin.project.budgetTotalMoney}" pattern="#,##0.00#" /></td>
						<td class="price"><fmt:formatNumber value="${resultMap['record'].goodsTotal}" pattern="#,##0.00#" /></td>
						<td class="price"><fmt:formatNumber value="${bulletin.project.budgetTotalMoney-resultMap['record'].goodsTotal}" pattern="#,##0.00#" /></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</c:if>
		<!-- 项目成交信息  结束 -->
		
	</div>
</div>
<!--主要内容 结束-->
<!-- 脚开始 -->
<div class="footer">
	<%@ include file="/view/srplatform/portal/include/foot.jsp"%>
</div>
<!-- 脚结束 -->
<!--在线客服开始-->
  <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
<!--在线客服结束-->
</div>
</body>
</html>