<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<%@ include file="/view/resource/plug-in/syntaxhighlighter/highlight.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/wflow/toDeployProcessDefinition.js"></script>
<div class="codeTab">
	<div class="formLayout">      
		<form id="attachmentForm" enctype="multipart/form-data">
			<h4><span>上传组件(文件)</span></h4>
		    <ul>
		     	<li>
		       		<label>附件</label>
		       		<input type="file" name="attachFile" id="attachFile" value="" />
		       		<a id="attachmentHref" href="#1"></a>
		        	<button id="attachmentBtn" type="button" tabindex="17"><span>发布流程</span></button>
				</li>
		    	
		    </ul>
		</form>
		<ul>
			<li id="proDefLi" style="display:none">
				<button id="getLatestPro" type="button" tabindex="17"><span>同步流程数据</span></button>	
			</li>
			<div>	
				<table id="proDefTable"  align="left" border="1" width="70%">
					<tr>
			    		<td width="45%" align="center">流程定义ID
			   			</td>
						<td width="45%" align="center">名称
						</td>
						<td width="10%" align="center">操作
						</td>
		    		</tr>
		  		</table>
			</div>
		</ul>
    </div>
</div>
