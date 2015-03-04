<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
	
	
<script>
/*
 * 执行平台，需求条目列表
 * author: 梁潇静
 * mail: liangxj@gpcsoft.com
 */
var preqEntryList={};
 
//修改
preqEntryList.update=function(name,grid){
	if(!preqEntryList.validation(name,grid))return;
	//跳转到修改页面
	var objId = $(grid).getSelect();
	
	$.epsDialog({
        title:'需求条目',
        url:$('#initPath').val()+'/view/es/planform/requirement/preqEntryForm.jsp?objId='+$(grid).getSelect(),
        width: '800',
        height: '530',
        onClose: function(){ 
        	$(grid).reload();//刷新
        }
	});
}   

//查看详细
preqEntryList.showDetail=function(name,grid)
{
	if(!preqEntryList.validation(name,grid))return;
	$.epsDialog({
        title:'需求条目',
        url:$('#initPath').val()+'/view/es/planform/requirement/preqEntryDetail.jsp?objId='+$(grid).getSelect(),
        width: '800',
        height: '500'
	});
}

//列表操作验证
preqEntryList.validation=function(name,grid){
	if($(grid).isSelectEmpty()){alert('请选择一个需求条目');return false;}//是否选中
	if(!$(grid).isSelectOne()){alert('请只选择一个需求条目');return false;}//是否只选中一个
	return true;
}

//查询条件过滤
preqEntryList.before=function(){
	var option={"purRequirement.objId":$("#objId").val()}
	$('#preqEntryGrid').flexOptions({params:option});
	return true;
}
</script>
<flex:flexgrid checkbox="true"
	id="preqEntryGrid" url="PreqEntryController.do?method=list" queryColumns=""  
		rp="10" title="采购需求条目"  
		onSubmit="preqEntryList.before">
				<flex:flexCol name="name" display="preqEntryForm.name" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="purchaseName" display="preqEntryForm.purchase" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="spec" display="preqEntryForm.spec" sortable="true" width="100"align="left"></flex:flexCol>
				<flex:flexCol name="unit" display="preqEntryForm.unit" sortable="true" width="60"align="left"></flex:flexCol>
				<flex:flexCol name="quantity" format="amount" display="preqEntryForm.quantity" sortable="true" width="60"align="right"></flex:flexCol>
				<flex:flexCol name="totalPrice" format="money" display="preqEntryForm.totalPrice" sortable="true" width="100"align="right"></flex:flexCol>
	<flex:flexBtn name="globe.detail" bclass="look" onpress="preqEntryList.showDetail"></flex:flexBtn>
	<flex:flexBtn name="globe.modify" bclass="modify" onpress="preqEntryList.update"></flex:flexBtn>
</flex:flexgrid>
