<script>
	function viewProject(){
		window.open($("#initPath").val()+"/BargainProjectController.do?method=toProjectView&userType=supplier&projectId="+${project.objId});
	}
</script>
<p class="abstract">
<font color="blue">
尊敬的供应商您好：<br/>
&nbsp;&nbsp;&nbsp;&nbsp;
&nbsp;&nbsp;&nbsp;&nbsp;您的客户${project.buyersName}创建了竞价项目’${project.projName}‘，报名开始时间为${signUpSTime}，
特邀请您参与竞价，祝您愉快！
</font> 
</p>
