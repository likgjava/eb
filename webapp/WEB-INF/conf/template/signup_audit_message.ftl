<p class="abstract">
<font color="blue">
尊敬的客户您好：<br/>
&nbsp;&nbsp;&nbsp;&nbsp;${content!}
</font> 
</p>
<script type="text/javascript">
viewSignUpDetail = function(objId){
	 //弹出报名详情页面
	 $.epsDialog({
	        title:'报名信息：',
			url:$('#initPath').val()+'/SignUprecordController.do?method=toViewSignupPageByAgent&objId='+objId
	  }); 
}
</script>
