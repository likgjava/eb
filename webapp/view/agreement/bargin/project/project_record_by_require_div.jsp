<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<style>
.selectGoods{
color:red;
font-weight:bold;
}

.unselectGoods{
color:green;
font-weight:bold;
}
</style>


<script type="text/javascript">

var record_by_require_div = {};

record_by_require_div.compareIds = "";
show_list = {};

//比较方法
record_by_require_div.comPare = function(e){

	//比较复选框点击事件
	var goodsId = $(e).attr("gid");
	var goodsName = $(e).attr("gname");
	var goodsCode = $(e).attr("gcode");

	//选中交替
	$(e).hasClass("selectGoods")?$(e).removeClass("selectGoods").addClass("unselectGoods"):$(e).removeClass("unselectGoods").addClass("selectGoods");

	//选中
	if($(e).hasClass("selectGoods")){
		$("#goodsCompare").removeClass("hidden").show();  //显示比较层
		if($("#C_"+goodsId).length == 0) {
			if($('#goodsCompare ul').find('li').length == 4) {  //最多可以比较四个
				alert("最多可以比较四个！")
				$(e).removeClass("selectGoods").addClass("unselectGoods");
				return;
			}else {
				$('#goodsCompare ul').append('<li class=center id=C_'+goodsId+'>'+goodsName+' ('+goodsCode+')&nbsp;<a href="javascript:void(0);" onclick="show_list.removeCompare(\''+goodsId+'\');return false;"><span class="sysicon siExit" >&nbsp;</span></a></li>'); //追加比较数据
			}
		}
	}else { 
		record_by_require_div.removeCompare(goodsId);
	}
}


//删除比较数据
show_list.removeCompare=function(goodsId){
	if(goodsId) { //单条删除
		$("#C_"+goodsId).remove();
		//将对应的复选框选中状态去掉
		$(":a[gid="+goodsId+"]").removeClass("selectGoods").addClass("unselectGoods");
		
		if($('#goodsCompare ul').text() == "") {    //隐藏比较层
			$("#goodsCompare").hide();
		}
	}else {  //全部清除
		$('#goodsCompare ul').empty();
		$(":a[name=compareGoods]").removeClass("selectGoods").addClass("unselectGoods");
		$("#goodsCompare").hide();  ////隐藏比较层
	}
}

//商品比较
record_by_require_div.startCompare = function(){
	var objIds = "";
	$.each($('#goodsCompare ul li'),function(i,n){
		objIds += $(n).attr("id").split("_")[1] + ",";
	})

	if(objIds.length == 0) {
		return;
	}else {
		objIds = objIds.substring(0,objIds.length-1);
	}
	$.epsDialog({
	 	id:"compareDialog",
	    title:'商品对比',
	    width:920,
	    height:500,
		url:$('#initPath').val()+'/GoodsShowController.do?method=getCompareGoodsInfo&objIds='+objIds+"&divId=compareDialog"
	}); 
}

</script>


<c:forEach var="biddingRecordObject" items="${biddingRecordObjectList}" varStatus="status">

	<c:set var ="index" value="0"/>
	
	<div class="pdetail itinerary_content layoutfix showMoreInfo hi_60" id="div${biddingRecordObject[0]}" style="width:100%;" ondblclick="project_detail_require.showMoreInfo(this)">
		<table class="line_content" style="width:100%;">
		
			<!-- 递增 -->
			<c:set var ="index" value="${index+1}"/>
		
			<tr>
				<th title="${biddingRecordObject[2]}" style="width:240px;">${biddingRecordObject[2]}</th>
				<td style="width: 10px;">
					<input type="checkbox" price="${biddingRecordObject[3]}"
					onchange="project_detail_require.checkDetail('${biddingRecordObject[0]}','${biddingRecordObject[1]}');" id="${biddingRecordObject[0]}" name="${biddingRecordObject[1]}" isChoose="${biddingRecordObject[4]}" <c:if test="${biddingRecordObject[4]==49}">checked="checked"</c:if> />
				
				</td>
				<td title="${biddingRecordObject[6]}">
						<c:if test="${biddingRecordObject[9]!=null && requireItem.goods==null }">
							<c:if test="${requireItem.goods==null}">
								<a name="compareGoods" href="javascript:void(0);" gid="${biddingRecordObject[9]}" gname="${biddingRecordObject[10]}" gcode="${biddingRecordObject[11]}" onclick="record_by_require_div.comPare(this);" class="unselectGoods">[比较]</a>
							</c:if>
							<a onclick="common.geToGoodsDetail('${biddingRecordObject[9]}');" href="javascript:void(0);">
							${biddingRecordObject[6]}
							</a>
							<br/>${biddingRecordObject[8]}
						</c:if>
						<c:if test="${biddingRecordObject[9]==null && requireItem.goods==null }">${biddingRecordObject[6]}</c:if>
						<c:if test="${requireItem.goods!=null }">报价商品同需求商品</c:if>
						<c:if test="${biddingRecordObject[13]!=null}"><br>【备注】：${biddingRecordObject[13] }</c:if>
				</td>
				<td style="width: 250px;">
					<!-- 排序 -->
					<c:choose>
						<c:when test="${status.index==0}">
							<a href="javascript:void(0);" name="moveUp" onclick="project_detail_require.moveUp('${biddingRecordObject[0]}');" title="上移"><span class="sysicon siUpGray">&nbsp;</span></a>
							<a href="javascript:void(0);" name="moveDown" onclick="project_detail_require.moveDown('${biddingRecordObject[0]}');" title="下移"><span class="sysicon siDown">&nbsp;</span></a>
						</c:when>
						<c:when test="${status.index == fn:length(biddingRecordObjectList)-1}">
							<a href="javascript:void(0);" name="moveUp" onclick="project_detail_require.moveUp('${biddingRecordObject[0]}');" title="上移"><span class="sysicon siUp">&nbsp;</span></a>
							<a href="javascript:void(0);" name="moveDown" onclick="project_detail_require.moveDown('${biddingRecordObject[0]}');" title="下移"><span class="sysicon siDownGray">&nbsp;</span></a>
						</c:when>
						<c:otherwise>
							<a href="javascript:void(0);" name="moveUp" onclick="project_detail_require.moveUp('${biddingRecordObject[0]}');" title="上移"><span class="sysicon siUp">&nbsp;</span></a>
							<a href="javascript:void(0);" name="moveDown"  onclick="project_detail_require.moveDown('${biddingRecordObject[0]}');" title="下移"><span class="sysicon siDown">&nbsp;</span></a>
						</c:otherwise>
					</c:choose>
				
					<span id="${biddingRecordObject[0]}" name="detailPrice">￥<fmt:formatNumber value="${biddingRecordObject[3]}" pattern="#,##0.00#" /></span>
					&nbsp;
					
					<i class="save_total">节省：<fmt:formatNumber value="${requireItem.goodsPrice*requireItem.goodsQty - biddingRecordObject[3]}" pattern="#,##0.00#" /></i>
				</td>
				<td style="width: 50px;" ><a href="javascript:void(0);" onclick="project_detail_require.downLoadFile('${biddingRecordObject[5]}');">报价文件</a></td>
			
				<c:set var ="supplierId"  value="${biddingRecordObject[1]}"/>
			</tr>
		
		</table>
	</div>
</c:forEach>

	
<!-- 商品比较 --> 
<div id="goodsCompare" class="compareGoodsTips hidden">
  	<h4><span>商品对比</span></h4>
  	<ul></ul>
	<div class="conOperation center"><button id="startGoodsCompare" onclick="record_by_require_div.startCompare();"><span>开始比较</span></button><button onclick="show_list.removeCompare()"><span>取消对比</span></button></div>
</div>	
	
	
<script type="text/javascript">
</script>
