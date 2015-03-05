var mainContentHeight;
var RoleAuthorizeResource={};
RoleAuthorizeResource.rows=null;//列表查询的结果集
//加载资源树
RoleAuthorizeResource.resourceTree=function(id){
	var openId=[];
	$('.frameSub').height( $('#contentMain').height()-exHeight($('#contentMain')) - exHeight($('.frameSub'))+2);//设置树右边高度。。
	RoleAuthorizeResource.resourcetree=new dhtmlXTreeObject("resourceTree","85%","100%",0);
	RoleAuthorizeResource.resourcetree.setImagePath($('#initPath').val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	RoleAuthorizeResource.resourcetree.enableCheckBoxes(1);
	//RoleAuthorizeResource.resourcetree.enableDragAndDrop(1);
	RoleAuthorizeResource.resourcetree.enableThreeStateCheckboxes(true);
	//RoleAuthorizeResource.resourcetree.setOnClickHandler(RoleAuthorizeResource.resclick);
	RoleAuthorizeResource.resourcetree.setXMLAutoLoading("ResourceController.do?method=getResourceTree&action=listById&roleId="+roleList.roleId,function(){
		//获取子节点选中状态
		$.getJSON("RoleController.do?method=getResourceByRoleTop",{'roleId':id,"id":'all'},function(json){
			if(json.failure)return;
			$.each(json.result,function(i,n){
				if(n.checked==1){
					RoleAuthorizeResource.resourcetree.setCheck(n.objId,1);//设置全部选中
				}
				if(n.checked==2){
					RoleAuthorizeResource.resourcetree.setCheck(n.objId,'unsure');//设置部分选中
				}
			})
		})
	});

	RoleAuthorizeResource.resourcetree.loadXML($("#initPath").val()+"/ResourceController.do?method=getResourceTree&action=openChecked&id=0&roleId="+roleList.roleId,function(){
		//获取所有节点选中状态
		$.getJSON("RoleController.do?method=getResourceByRoleTop",{'roleId':roleList.roleId, 'id':'all'},function(json){
			if(json.failure)return;
			$.each(json.result,function(i,n){
				if(n.checked==1){
					RoleAuthorizeResource.resourcetree.setCheck(n.objId,1);//设置全部选中
				}
				if(n.checked==2){
					RoleAuthorizeResource.resourcetree.setCheck(n.objId,'unsure');//设置部分选中
					openId.push(n.objId);
				}
			})
		})
	});
}
//对角色分配资源
$("#saveRoleRes").click(function(){
	var josnObj = {};
	//取得选中的资源树的值,去掉顶级树的编号
	var resourceArr=RoleAuthorizeResource.resourcetree.getAllCheckedBranches().replace("-1","");
	//没有选中资源的时候，不执行SET值操作
	if(resourceArr != ""){
		for(var i = 0 ; i<resourceArr.split(",").length ; i++){
			//处理修改时 默认去除空id值
			if(resourceArr.split(",")[i]!=""&&resourceArr.split(",")[i]!=null){
				var keyname = 'sets['+i+'&com.gpcsoft.srplatform.auth.domain.RoleResource]';
				josnObj[keyname+'.objId']='';
				josnObj[keyname+'.resource.objId']=resourceArr.split(",")[i];
				josnObj[keyname+'.checked']=RoleAuthorizeResource.resourcetree.isItemChecked(resourceArr.split(",")[i])
			}
		}
	}
	josnObj['roleId']=roleList.roleId;
	//对角色所授资源进行保存
	$.getJSON("RoleController.do?method=saveRoleResource",josnObj,function(json){
		if(json.failure){ 
			alert("分配资源失败") 
			return;
		}else if(json.success){
			if(json.result=='true'){
				roleList.showRole(roleList.div);;//加载系统角色div
			}
		}
	})
})
	
//返回
$('#roleReturn').click(function(){
	roleList.showRole(roleList.div);;
});
$(document).ready(function(){
	RoleAuthorizeResource.resourceTree(roleList.roleId);//加载资源树
	
});
function conbodyHeight(){
	$.autoHeight({
		 top:'#sysBranding',
		 bottom:'#sysInfo',
		 center:'#sysContent',
		 callback:function(i){
			 $('.selectionTree').height( $('#contentMain').height()-exHeight($('#contentMain')) - exHeight($('.frameMain'))-40);//设置树容器高度。
			 }
		 });
}
