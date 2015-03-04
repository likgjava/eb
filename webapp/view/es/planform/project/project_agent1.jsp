<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/es/planform/project/project_agent.js"></script>
<style>
.chapter {
	width: auto;
	margin-bottom: 10px;
	border:1px solid #EEEEEE;
}
.sec-tab {
	background:
		url("view/resource/skin/xygh/img/mytb_v4_bg.png")
		repeat-x scroll center -299px #FFFFFF;
	border-top: 1px solid #C4D5E0;
	height: 22px;
}
.sec-tab li a span {
color:#404040;
font-size:12px;
padding:0 5px;
font-weight: bold;
}
#alipay-notice .alipay-manage li,#alipay-notice .alipay-manage {
	background: url("view/resource/skin/xygh/img/alipay_bg.png")
		no-repeat scroll 1000px 1000px transparent;
}
#alipay-notice .alipay-manage {
	background-position: -135px 5px;
	height: 80px;
	padding: 0 0 0 10px;
}

#alipay-notice .alipay-manage h4 {
	font-weight: normal;
	margin: 10px 5px;
}
.alipay-manage h4 b{
	font-size:15px;
}
.alipay-manage h4 a{
	padding-right:10px;
}
.alipay-manage h4 .login{
	font-size:11px;
	color:#999999;
}

#alipay-notice .alipay-manage ul {
	overflow: hidden;
}


#alipay-notice .alipay-manage li {
	background-position: 0 0;
	float: left;
	height: 30px;
	line-height: 30px;
	margin: 0 5px;
	text-align: center;
}


.sec {
	border-top: 0 none;
	margin: 0;
	padding: 2px 0 2px 70px;
}

.mytaobao-notice {
	overflow: hidden;
	position: relative;
}

.mytaobao-notice .sec {
	border-top: 1px dotted #CCCCCC;
	margin: -1px 0 0;
	padding: 5px 0 7px 80px;
	position: relative;
}

.mytaobao-notice .sec h4 {
	color: #404040;
	font-size: 12px;
	font-weight: normal;
	left: 0;
	position: absolute;
	text-align: right;
	width: 70px;
}

.mytaobao-notice .sec ul {
	margin: 0 0 2px;
}

.mytaobao-notice .sec li {
	float: left;
	line-height: 18px;
	margin: 0 0 3px;
	width: 135px;
}

.mytaobao-notice .sec li.long-text {
	width: 170px !important;
}

.mytaobao-notice .sec li em {
	color: #FF6600;
	font-weight: 700;
	padding: 0;
}

.mytaobao-notice .sec li em.none {
	color: #999999;
}
.tabHead {
	background:url("view/resource/skin/skin03/img/navMain_bg3.gif") repeat-x scroll 0 0 transparent;
	font:1.2em "宋体";
	height:20px;
	left:0;
	overflow:hidden;
	right:0;
	z-index:1
}
.tabHead li {
float:left;
line-height:20px
}

.tabHead a {
background:url("view/resource/skin/skin03/img/navMain_a.gif") no-repeat scroll 0 0 transparent;
color:#023D69;
display:block;
outline:medium none;
text-align:center;
text-decoration:none;
width:100px;
}
.tabHead ul li a.select {
background:url("view/resource/skin/skin03/img/homePage_bg.png") no-repeat scroll 0 0 transparent;
color:#FFFFFF;
display:block;
font-size:14px;
font-weight:bold;
height:18px;
line-height:40px;
margin-left:5px;
text-align:center;
width:120px;
}

</style>
<div id="alipay-notice" class="chapter">
	<div class="formLayout form2Pa">
		<h5><span class="B EN"><b>${project.projCode }</b></span>[${project.projName }]</h5>
		<!-- 隐藏数据 -->
		<!-- 项目 全局使用 -->
		<input type="hidden" value="${project.objId }" id="projectId" name="projectId"/>
	</div>
	<div class="alipay-manage">
		<span>
			采购方式：<a href="#" >${project.ebuyMethodCN }</a>&nbsp;&nbsp;&nbsp;&nbsp;
			项目负责人：<span id="xmfzr"></span>&lt;<span id="xmfzr_fp">待分配</span>&gt;&nbsp;&nbsp;&nbsp;&nbsp;
			项目经办人：<span id="xmjbr"></span>&lt;<span id="xmjbr_fp">待分配</span>&gt;&nbsp;&nbsp;&nbsp;&nbsp;
		</span>
		<h4>
			<a lzlinkno="11" href="" class="action h">变更公告(<em>2</em>)</a>
			<a lzlinkno="11" href="" class="action h">已报名的投标单位(<em>5</em>)</a>
			<a lzlinkno="11" href="" class="action h">质疑(<em>8</em>)</a>
		</h4>
		<ul>
			<li><a lzlinkno="13" onclick="javascript:$('#conBody').loadPage('view/goods/goods/brandmng/brand_list_manage.jsp')" href="javascript:void(0)"><dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out></a></li>
			<li><a lzlinkno="14" onclick="javascript:$('#conBody').loadPage('view/goods/goods/goodsmng/goods_manage.jsp')" href="javascript:void(0)"><dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out></a></li>
			<li><a lzlinkno="15" onclick="javascript:$('#conBody').loadPage('view/goods/goods/fittingmng/accessory_manage.jsp')" href="javascript:void(0)"><dm:out value="local__RESULTBULLETIN" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">中标公告</dm:out></a></li>
			<li><a lzlinkno="16" onclick="javascript:$('#conBody').loadPage('view/ebargaing/my_bargain_supplier_list.jsp')" href="javascript:void(0)">成交/结果<dm:out value="local__OpenTendering__notice_zh">通知书</dm:out></a></li>
		</ul>
	</div>
</div>
<div>
	<div id="tabInfo">
	    <ul>
	    	<li><a href="#tabform" id="tabform1" class=""><span>标前</span></a></li>
	    	<li><a href="#tabform" id="tabform2"><span>标中</span></a></li>
	    	<li><a href="#tabform" id="tabform3"><span>标后</span></a></li>
	    </ul>
	    <div id="tabform" class="formLayout form2Pa">
			<h5>
				<span onClick="javascript:checkProjectMenu('menu_bidtype');">设置采购方式</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<span onClick="javascript:checkProjectMenu('menu_project_number');">设置招标编号</span>&nbsp;&nbsp;&nbsp;&nbsp;
				<dm:out value="local__PURBULLETIN"  tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">公告</dm:out>&nbsp;&nbsp;&nbsp;<dm:out value="local__PURDOC" tenderType="${project.tenderType}" ebuyMethod="${project.ebuyMethod}">招标文件</dm:out>&nbsp;&nbsp;&nbsp;9 家投标单位参与&nbsp;&nbsp;&nbsp;</h5>
		</div>
	</div>
</div>
<div id="projDoDiv">
</div>

<script>

$(document).ready(function(){	
	//分配项目经办人
	$("#xmjbr_fp").click(function(){
		$.epsDialog({
	        title:'任务书条目',
	        url:$("#initPath").val()+"/ProjectController.do?method=toProjectLinkGovMan&projectId="+$("#projectId").val(),
	        width: '800',
	        height: '300',
	        onClose: function(){ 
	        	
	        }
		});	
	})
	
})
function checkProjectMenu(id){
	if(id == 'menu_bidtype'){
		//设置采购方式
		$("#projDoDiv").empty().loadPage($("#initPath").val()+"/ProjectController.do?method=toProjectComWork&projectId="+$("#projectId").val());
	}
	if(id == 'menu_project_number'){
		//设置招标编号
		$("#projDoDiv").empty().loadPage($("#initPath").val()+"/view/es/planform/project/numberSet.jsp?projectId="+$("#projectId").val());
	}
	
}
</script>
