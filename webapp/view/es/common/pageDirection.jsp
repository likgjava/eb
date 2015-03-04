<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<div class="flexigrid">
	<div class="pDiv">
		<div class="pDiv2">
			<div class="btnseparator"></div>
			<div class="pGroup">
				<div class="pFirst pButton"><span onClick="pageDirection.changePage('first');"></span></div>
				<div class="pPrev pButton"><span onClick="pageDirection.changePage('prev');"></span></div> 
			</div>
			<div class="btnseparator"></div>
			<div class="pGroup">
				<span class="pcontrol">第 <input type="text" id="pageNumC" value="${PAGERESULT.pageNum }" size="4">页 共<span>${PAGERESULT.totalPageNum }</span>页</span>
			</div> 
			<div class="pGroup">
				<div class="pButton"><span class="pcontrol"><a href="#" id="go_page">GO</a></span></div>
			</div> 
			<div class="pGroup"> 
				<div class="pNext pButton"><span onClick="pageDirection.changePage('next');"></span></div>
				<div class="pLast pButton" onClick="pageDirection.changePage('last');"><span ></span></div> 
			</div> 
			<div class="btnseparator"></div> 
			<!--<div class="pGroup"> 
				<div class="pReload pButton"><span onClick="pageDirection.jump();"></span></div> 
			</div> 
			--><div class="btnseparator"></div> 
			<div class="pGroup">
				<span class="pPageStat"> 共&nbsp;${PAGERESULT.totalRowNum }&nbsp;条记录</span>
			</div>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
<script>
var pageDirection = {};
//跳转
pageDirection.jump = function(){
	//将需要跳转的页数据赋给page，以方便完成form封装
	$("#page").val($("#newPage").val());
	var url=$('#initPath').val()+'/'+$("#pageSearchUrl").val();
	$('#consignListView').loadPage(url,formToJsonObject("pageSearchZone"));
}
$("#go_page").click(function(){
	var pageNumC = $("#pageNumC").val()*1;			// 需要跳转的页数
	var totalPageNum = $("#totalPageNum").val()*1;	// 总页数
	if(pageNumC >= totalPageNum){
		$("#newPage").val(totalPageNum);
	}else if(pageNumC <=1){
		$("#newPage").val(1);
	}else if(pageNumC <=totalPageNum){
		$("#newPage").val(pageNumC);
	}
	pageDirection.changePage('go_page');
})

pageDirection.changePage=function(ctype){ //change page
		if(ctype=='first'){$("#newPage").val(1); };
		if(ctype=='prev'){
			var previousPage = $("#page").val()-1;
			if(previousPage <= 0){
				previousPage = 1;//控制是否有上一页
			}
			$("#newPage").val(previousPage); 
		};
		if(ctype=='next'){$("#newPage").val($("#nextPage").val()); };
		if(ctype=='last'){$("#newPage").val($("#totalPageNum").val()); };
		if(ctype=='input'){
				var nv = $("#pageNumC").val();
				if (isNaN(nv)) nv = 1;
				if (nv<1) 
					nv = 1;
				else if (nv > $("#totalPageNum").val()) 
					nv = $("#totalPageNum").val();
				//$('.pcontrol input',this.pDiv).val(nv);
				$("#newPage").val(nv);
				};
	if ($("#newPage").val()==$("#page").val()) return false;//若需要跳转的页和当前页是一样，则不需要跳转
	pageDirection.jump();
}
/* 往分页中加入参数 (不考虑参数值中已有？的情况)*/
pageDirection.addParameters = function(params){
	var thisUrl = $("#pageSearchUrl").val();
	var dot = thisUrl.lastIndexOf("?");
	if( dot >0 ){//有参数
		thisUrl += '&'+params;
	}else{ //还没有参数
		thisUrl += "?" + params;
  	}
}
$(document).ready(function(){
	$('.pcontrol input').keydown(function(e){if(e.keyCode==13) pageDirection.changePage('input');});
});
</script>