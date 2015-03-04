<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>

<script language="javascript" type="text/javascript">
var BulletinDetailView = {};

//点击导航
BulletinDetailView.searchByTitle = function() {
	$('#sysContent').loadPage($('#initPath').val()+'/BulletinShowController.do?method=toBulletinList&rp=21&page=1&districtLevel=1');
}

$(document).ready(function(){
	$(".conbody-beer-three tr:even").css("background-Color","#f9f7e5"); //公告模板table奇偶行显示不同背景色
})
</script>

<div id="conTitle">
	<div class="navCurrent">
		<a id="a" href="javascript:void(0);" onclick="BulletinDetailView.searchByTitle();return false;" >采购项目展示</a>
		采购项目详情
	</div>
</div>
 
<div id="conBody">
	<div>
		${bullContents}
	</div>
</div>
