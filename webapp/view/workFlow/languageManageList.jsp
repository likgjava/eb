<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<script>
var languageManageList={};
	languageManageList.add=function(name,grid){
		$('#conBody').loadPage($('#initPath').val()+"/LanguageManageController.do?method=toLanguageManageFormView");
	}   
	languageManageList.remove=function(name,grid){
		if($(grid).getSelects().length ==0){
			alert("请选择要删除的审批语");
			return;
		};
		if(window.confirm('确定'+name+'?')){
			$.getJSON($('#initPath').val()+'/LanguageManageController.do?method=deleteLanguageManage',{'objId':$(grid).getSelects()},function(json){
				if(json.result)alert(json.result);if(json.failure)return;
				$(grid).reload();
			});
		}
	}
	languageManageList.success=function(){
	$("#languageManageGrid").flexAddOptionStr({
			'<span><a href="#" class="abtn">&nbsp;&nbsp;修改</a>&nbsp;&nbsp;&nbsp</span>' : function(btn,rowId,obj){
			btn.click(function(){
			  $('#conBody').loadPage($('#initPath').val()+'/LanguageManageController.do?method=toLanguageManageFormView&objId='+rowId+"&isShowButon=true");
			}).appendTo(obj);
		},'<span><a href="#" class="abtn">&nbsp;&nbsp;删除</a>&nbsp;&nbsp;&nbsp;&nbsp;</span>' : function(btn,rowId,obj){
				btn.click(function(){
					var obj = $(this);
					if(window.confirm('确定要删除吗?')){
						$.getJSON($('#initPath').val()+'/LanguageManageController.do?method=deleteLanguageManage',{'objId':rowId},function(json){
							if(json.failure)return;
							obj.parent().parent().parent().remove();
						});
					}
				}).appendTo(obj);
			}
		});
	}
	$("#returnUrl").val($('#initPath').val()+"/LanguageManageController.do");
</script>
<div class="partContainers">
	<form id="languageManageSearchZone" >
		<div class="conSearch">
			<h4><span><spring:message code="globe.query"/></span></h4>
			<ul>
			    <li class="fullLine">常用语：<input type="text" name="content" class="long" /><input type="hidden" name="content_op" value="like"/></li>
				<li class="operationBtnDiv"><button type="submit"><span><spring:message code="globe.query"/></span></button></li>
			</ul>
		</div>
	</form>
	<flex:flexgrid checkbox="true"
		id="languageManageGrid" url="LanguageManageController.do?method=list&createUser.objId=${userId}&order=createTime&order_flag=true" queryColumns=""  
			searchZone="languageManageSearchZone" rp="100" title="常用审批语"  
			onSuccess="languageManageList.success" usepager="false">
		<flex:flexCol name="content" display="内容" sortable="true" width="840" align="left"></flex:flexCol>	
		<flex:flexBtn name="新增常用批语" bclass="add" onpress="languageManageList.add"></flex:flexBtn>	
		<flex:flexBtn name="批量删除" bclass="delete" onpress="languageManageList.remove"></flex:flexBtn>	
	</flex:flexgrid>
</div>