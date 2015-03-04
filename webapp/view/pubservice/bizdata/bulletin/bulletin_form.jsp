<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script type="text/javascript" >
$(document).ready(function(){
	setTabClick('province',4,'mouseover');

	// 点击公告、公示进入内容页面
	$("#provinceNotice li").find("a").live("click",function() {
		fnShowSub();
		$("#contentSub").loadPage($('#initPath').val()+"/view/staticpags/load/left.html");
		$("#contentMain").loadPage($('#initPath').val()+"/BulletinController.do?method=toShowView&objId="+$(this).attr("id"));
	})
})
</script>

<h4><span>公告公示</span></h4><span class="more"><a href="javascript:void(0);" id="noticeMore_index">更多…</a></span>
        <div class="tabList">
          <ul>
            <li class="selected" id="province1">采购公告</li>
            <li class="tag" id="province2">结果公告</li>
            <li class="tag" id="province3">资格预审</li>
            <li class="tag" id="province4">更正公告</li>
          </ul>
        </div>
        <ul class="show" id="tab_province1">
          <h5>省级信息</h5>
          <c:forEach items="${provincePurchaseBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
          <h5>市县信息</h5>
          <c:forEach items="${cityPurchaseBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
        </ul>
        <ul class=" hidden" id="tab_province2">
          <h5>省级信息</h5>
         <c:forEach items="${provinceResultBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
          <h5>市县信息</h5>
          <c:forEach items="${cityResultBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
        </ul>
        <ul class="hidden" id="tab_province3">
          <h5>省级信息</h5>
          <c:forEach items="${provinceQualificationBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
          <h5>市县信息</h5>
          <c:forEach items="${cityQualificationBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
        </ul>
        <ul class="hidden" id="tab_province4">
          <h5>省级信息</h5>
          <c:forEach items="${provinceVariationBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
          <h5>市县信息</h5>
          <c:forEach items="${cityVariationBulletin}" var="bulletin">
            <li><a href="javascript:void(0);" id="${bulletin.objId }">${bulletin.bullTitle}</a><span class="right"><fmt:formatDate value="${bulletin.createTime}" pattern="yyyy-MM-dd"/></span></li>
          </c:forEach>
        </ul>