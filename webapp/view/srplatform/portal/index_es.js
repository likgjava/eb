/*
++++++++
+ 
+ 皮肤主题数据
+ 
++++++++*/


$(document).ready(function() {
	$(window).resize(function(){//zhanghuihua 20111105
		$("[layoutH]").layoutH();
	});
    /*
	++++++++
	+ 
	+ 定义框架
	+ 
	++++++++*/
    //结构
    var $sysContainer = $("#sysContainer"); //页面容器
    var $sysBranding = $('#sysBranding'); //系统标识
    var $sysContent = $("#sysContent"); //系统容器
    var $contentSub = $("#contentSub"); //辅容器
    var $navMain = $("#navMain"); //主菜单
    var $contentMain = $("#contentMain"); //主容器
    var $contentSupp = $("#contentSupp"); //补充容器
    var $sysInfo = $("#sysInfo"); //系统信息

    /*++++++++
	+ 
	+ 窗口工具条
	+ 
	++++++++*/
    /*返回桌面*/
    $('#myDesktop').click(function() {
    	if(PlatForm.user.usrIsAdmin!='2'){
    		$.getJSON($('#initPath').val()+'/UserInfoController.do?method=getObject&includedProperties=orgInfo&objId='+PlatForm.user.objId,function(userInfo){
  			  PlatForm.userInfo = userInfo.list[0];   
  			  $.getJSON($('#initPath').val()+'/IndexViewController.do?method=setOrgInfoToUser',function(userInfo){
  					//这里通过请求去检查用户角色，根据角色完成首页桌面的跳转
  					$('#conBody').loadPage($('#initPath').val()+'/IndexViewController.do?method=toMyDesktop');
  			  })
  		  });
    	}
    });
    
    //跳转到首页	
	$('#goToIndex').click(function(){
		window.location.href = $('#initPath').val()+"/IndexViewController.do?method=index";
		return false;
	});
	
	//点击小额采购室
	$("#goToXYCG").click(function(){		
		window.location.href = $('#initPath').val()+"/ModelIndexController.do?method=toDeskTopIndex&viewName=deskTopIndexView";
		return false;
	})
	
    
    /*退出系统*/
    $('#exitBtn').click(function() {
    	if(confirm('确定要退出吗？')){
    		$.ajax({
    			url : $("#initPath").val()+"/logout.do",
    			type :"POST",
    			success:function(msg){
    				window.location.href=$('#initPath').val()+"/IndexViewController.do?method=index";
    			}
    		});
    	}
    });
    /*修改密码*/
    $('#updateMyPsw').click(function() {
         $.epsDialog({
	        title:'修改密码',
	        url:$('#initPath').val()+'/UserController.do?method=toUpdateMyPsw',
	        width: '500',
	        height: '200',
	        onOpen: function(){ },
	        afterLoad: function(){ },
	        onClose: function(){ }
	    }); 
    });
    /*维护个人信息*/
    $('#updateMyInfo').click(function() {
    	if(PlatForm.user.emp&&PlatForm.user.emp.objId){
			$.epsDialog({
		        title:'维护个人信息',
		        url:$('#initPath').val()+'/EmployeeController.do?method=toCreateOrUpdate&isSelf=true&objId='+PlatForm.user.emp.objId,
		        width: '500',
		        height: '400',
		        onOpen: function(){ },
		        afterLoad: function(){ },
		        onClose: function(){ }
		    }); 
		}
		else{
			alert("本账号没有关联的任何员工！");
		}
        
    });
    
    $("#dhtmlScheduler").click(function(){
    	window.open($('#initPath').val()+'/view/common/dhtmlxScheduler.jsp');
    });
    
    /*窗口快捷键*/
        $.sysTools({
            hideSubID: 'navSubControl',
            //隐藏左栏ID
            fullScreenID: 'screenControl' //全屏ID
        });
    /*刷新*/
    $('#refreshBtn').click(function() {
        $('#conBody').loadPage(current_menu_href);
    });

    /*++++++++
	+ 
	+ 时间日期控件
	+ 
	++++++++*/
   		h = gpcsoftDate.getHours();   
		m = gpcsoftDate.getMinutes();   
		s = gpcsoftDate.getSeconds();
		window.setInterval(function(){
				$('#nowTime').text(date_nowTime(gpcsoftDate));	
		}, 1000);


    /*++++++++
	+ 
	+ 第一次加载登陆加载样式处理
	+ 
	++++++++*/
        var c = readCookie('epsStyle');
        if (c) {
            changeSkin(c)
        }
        else {
			$.getJSON($('#initPath').val()+'/view/resource/skin/skin.txt', function(jsonSource){
				$.each(jsonSource.themes,
				function(i, j) {
					if (j.defaultTheme == 'true') changeSkin(j.name);
				})
			});
        }

	/*++++++++
	+ 
	+ html菜单处理
	+ 
	++++++++*/
		$.addMenu({
			url:$('#initPath').val()+'/UserMenuController.do?method=loadMenu',//菜单url
			target:'#conBody',//菜单target
			callback:function(){ //加载完成后执行
			 //$('#myDesktop').click();
			}
		});
	/*++++++++
	+ 
	+ 设置全局返回事件
	+ 
	++++++++*/
	$('button[name=historyBackBtn]').live('click',function(){
		if($("#returnUrl").val() != '')
			$('#conBody').loadPage($("#returnUrl").val());
	});
	
	/*++++++++
	+ 
	+ 加载系统信息
	+ 
	++++++++*/
		$.sysInfo_load();
	/*++++++++
	+ 
	+ 设定链接跳转目标，记录操作历史，对应loadPage功能，增加iframe用法
	+ 
	++++++++*/
	$("a,button.buttonLink").live("click",function(ev){	
		
		var targetID = this.getAttribute("target");//取目标链接target值
		var href = this.getAttribute("href");//取目标链接地址
		var thisId = this.id;
		if(targetID&&targetID.indexOf("#")!=-1) {
			if(thisId&&$('#contentSub > a #'+thisId)){
				var f = $('#navMain>ul>li.selected>a').clone(false).removeAttr('target');;
				var s = $('#contentSub >ul>li.selected>a').clone(false);
				var t = $('#'+thisId).clone(false);
				$('#conTitle .navCurrent').empty().append(f).append(s).append(t);
			}
			$(targetID).loadPage(href,null,function(){
				if('#conBody'==targetID){
					current_menu_href = href;
				}
			});
			
		}
		return false;
	});
	// 绑定验证触发事件
	$('.maxInput').live('keyup',function(){
		validateElementInputMax($(this));
	})
	$('.maxInput').live('click',function(){
		validateElementInputMax($(this));
	})

  /*
	++++++++
	+ 
	+ 改变浏览器大小时改变高度
	+ 
	++++++++*/
    $(window).wresize(fitHeight);
});


/*++++++++
+ 
+ 关闭系统
+ 
++++++++*/
function closeSystem() {
    window.opener = null;
    window.open("", "_self");
    window.close();
}
//右边内容高度调整
function conbodyHeight(){
	$.autoHeight({
		 top:'#sysBranding',
		 bottom:'#sysInfo',
		 center:'#sysContent',
		 callback:function(i){ 
		 	 $('.frameMainSub').height(i-exHeight($('#contentMain'))-$('#conTitle').height()-exHeight($('#conTitle'))-10);//设置树容器高度。
			 $('.frameMain').height(i-exHeight($('#contentMain'))-$('#conTitle').height()-exHeight($('#conTitle'))-10);//设置树容器高度。
	         $('.frameSub').height(i-exHeight($('#contentMain'))-$('#conTitle').height()-exHeight($('#conTitle'))-20);//设置树右边高度。
            return false;}
		 });
}

