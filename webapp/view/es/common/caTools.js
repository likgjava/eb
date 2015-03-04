function checkUser(){
//alert("屏蔽掉校验用户是否插入KEY");
//if(true)
//return true;
	var castr=HBActKeyCOM.ZJReadKeySerial();
	if(castr!=""){
		if(castr == document.getElementById("caStrId").value )
			return true;
		else{
			alert("您的证书绑定信息与当前插入密钥的信息不匹配!");
			return false;
		}
	}else{
  		alert("请检查您的证书是否已插好!");
  		return false;
	}
	return false;
}