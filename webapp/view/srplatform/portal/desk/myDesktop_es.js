/*
 * 平台开发demo
 * author: wangsw
 * mail: sinlff@163.com
 */
var myDesktop={};
var projectList={};
myDesktop.EASettings={};
myDesktop.userDeskTopGadgetList=null;
myDesktop.columnNum=2;
myDesktop.timer = 60000;   //定时器的时间

myDesktop.EASettings={
		i18n : { 
		    editText : '<img src='+$('#initPath').val()+'/view/resource/plug-in/jquery/easywidgets/edit.png alt="Edit" width="16" height="16" />',
		    closeText : '<img src='+$('#initPath').val()+'/view/resource/plug-in/jquery/easywidgets/close.png alt="Close" width="16" height="16" />',
		    collapseText : '<img src='+$('#initPath').val()+'/view/resource/plug-in/jquery/easywidgets/collapse.png alt="Collapse" width="16" height="16" />',
		    cancelEditText : '<img src='+$('#initPath').val()+'/view/resource/plug-in/jquery/easywidgets/edit.png alt="Edit" width="16" height="16" />',
		    extendText : '<img src='+$('#initPath').val()+'/view/resource/plug-in/jquery/easywidgets/extend.png alt="Extend" width="16" height="16" />'
		},
	    callbacks : {
		   onEdit : function(link, ui){	//显示
	    	    $(myDesktop.userDeskTopGadgetList).each(function(i,n){//显示个性化桌面collapsable
	   		        if($(ui).attr('id')==n.objId && myDesktop.userDeskTopGadgetList[i].openable=='0'){//没有打开才去请求
	   		        	myDesktop.userDeskTopGadgetList[i].openable='1';
	   		        	$('#'+$(ui).attr('id')+' .widget-editlink').remove();
	   				    $.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=updateOpenAllGadGetByUser',{ids:$(ui).attr('id')},function(json){
	   						if(json.failure){alert(json.result);return;}
	   				    });
	   		        }
	   		    });
		   },
		   onCancelEdit : function(link, ui){ //隐藏
		   },
		   onCollapse : function(link, ui){	//收缩
			   var status=$('.widget-place #'+$(ui).attr('id')).find('.collapse')!=0?'0':'1';
		       $.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=updateCollapseOrExpand',{objId:$(ui).attr('id'),status:status},function(json){
		   	   });
	       }, 
		   onExtend : function(link, ui){ //展开	$(ui).attr('class').indexOf('collapsable')
	    	   var status=$('.widget-place #'+$(ui).attr('id')).find('.collapse')==0?'0':'1';
		       $.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=updateCollapseOrExpand',{objId:$(ui).attr('id'),status:status},function(json){
		   	   });
	       }, 
	       onClose : function(link, ui){ //关闭	
	    	   $('#'+$(ui).attr('id')).remove();
		       $.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=updateCloseGadget',{objId:$(ui).attr('id'),status:'0'},function(json){
		    	   if(json.failure){alert(json.result);return;}
		    	   $(myDesktop.userDeskTopGadgetList).each(function(i,n){//显示个性化桌面collapsable
		    		    if($(ui).attr('id')==n.objId)
		    		    	myDesktop.userDeskTopGadgetList[i].openable='0';
		    		});
		       });
	       },
	       onChangePositions : function(){//拖拽完毕之后改变行列坐标
	    	   myDesktop.changePosition();
		   }
		}
	};

myDesktop.changePosition=function(){
   var ids=[];//桌面ID数组
   var rows=[];//行数组
   var columns=[];//列数组
   var openable=[];//展开数组
   $('.widget-place').each(function(i,n){
   $(this).find('.widget').each(function(ii,nn){
	   if($(this).attr('class').indexOf('collapsable')>-1)	openable.push(1);//展开
	   else	openable.push(0);//收缩
		   ids.push(this.id);
		   columns.push(i);     
		   rows.push(ii);
	   });
   });
   $.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=sortPosition',{ids:ids,rows:rows,columns:columns},function(json){
   });
}

myDesktop.getUserGadgetAndShowIt=function(){
	$.getJSON($('#initPath').val()+'/UserDeskTopGadgetController.do?method=getObject',{'user.objId':PlatForm.user.objId,includedProperties:'gadget,gadget.resource'},function(json){
		if(json.failure){alert(json.result);return;}
		myDesktop.userDeskTopGadgetList=json.list;
		myDesktop.userDeskTopGadgetList.sort(function(a,b){return (a.rowIndex>b.rowIndex)?1:-1});//使用js排序数组根据行顺序排序
		myDesktop.showGadget(2);//渲染桌面显示    2列布局
	});
}

myDesktop.showHiddenGadget=function(columnNum){
	myDesktop.columnNum=columnNum;
	$(myDesktop.userDeskTopGadgetList).each(function(i,n){//显示个性化桌面collapsable
		if(n.openable=='0' && $('#'+n.objId).length==0){//显示隐藏了的小工具
			var clazz='widget movable collapsable removable editable';
			if(n.maxable=='0')	 clazz+=' collapse';
			var html='<div id='+n.objId+' class="'+clazz+'">';
			html+='<div class=widget-header><strong>'+n.gadget.name+'</strong></div>';
			html+='<div class=widget-editbox></div>';
			html+='<div class=widget-content></div>';
			html+='</div>';
			
			var place;
			if(columnNum==2){//判断该添加到哪行列   为了美观布局
				var column0=$('#widget-place0 .widget').length;
				var column1=$('#widget-place1 .widget').length;		
				if(column0>column1)	place='widget-place1';
				else place='widget-place0';
			}
			myDesktop.userDeskTopGadgetList[i].openable=='1';//设置为已打开状态
			$.fn.AddEasyWidget(html, place, myDesktop.EASettings);		
			if(n.gadget.resource)
			$('#'+n.objId).find('.widget-content').load($('#initPath').val()+'/'+n.gadget.resource.url);
		}
	});
}

myDesktop.showGadget=function(columnNum){
	myDesktop.columnNum=columnNum;
	$('#myDesktopDiv').empty();
	$('#myDesktopDiv').append('<div class=widget-place style=width:65% id=widget-place'+0+'>');
	$('#myDesktopDiv').append('<div class=widget-place style=width:35% id=widget-place'+1+'>');
	
	//如果没有一个工具,	默认加入天气预报等
	if(myDesktop.userDeskTopGadgetList.length==0){
	    return;
	}
	$(myDesktop.userDeskTopGadgetList).each(function(i,n){//显示个性化桌面collapsable
		if(n.openable=='1'){//是否打开 collapse   或者点击了全部显示
				var clazz='widget movable collapsable removable editable';
			if(n.maxable=='0')	 clazz+=' collapse';
			var html='<div id='+n.objId+' class="'+clazz+'">';
			html+='<div class=widget-header><strong>'+n.gadget.name+'</strong></div>';
			html+='<div class=widget-editbox></div>';
			html+='<div class=widget-content></div>';
			html+='</div>';
			$('.widget-place').eq(n.columnIndex).append(html);	
			if(n.gadget.resource)
			$('#'+n.objId).find('.widget-content').load($('#initPath').val()+'/'+n.gadget.resource.url);
		}
	});

	$.fn.EasyWidgets(myDesktop.EASettings);
	$('.widget-editlink').remove();
}

//跳转到站内信列表页面
myDesktop.showStationMessage=function(){
	$('#conBody').loadPage($('#initPath').val()+"/MessageController.do?method=toMessageListView");
}

//改变未读的站内信数量
myDesktop.changeNotReadMessageNum=function(num){
	if(num > 0) {
		$("#notReadMessageNum").text("("+num+")");
		$("#notReadMessageNum").attr('num',num);
	}else{
		$("#notReadMessageNum").text("");
		$("#notReadMessageNum").attr('num','0');
	}
}

//获得当前用户的【未读和未提示】的站内信数目
myDesktop.processMessage=function(){
	$.getJSON($('#initPath').val()+"/MessageController.do?method=getMessageNum", function(json){
		//修改未读站内信的数目
		myDesktop.changeNotReadMessageNum(json.notReadMessageNum);
		
		//显示未提示的站内信内容
		if(json.notTipMessageNum > 0) {
			var url = $('#initPath').val()+'/MessageController.do?method=getNotTipMessage&dialogId=messageTip';
			$.epsDialog({
				id:"messageTip",
		        title:'系统消息',
		        url:url,
		        width: '380',
		        height: '200',
		        mutiWin:false,//不允许多个
		        timeOut: 0,
		        isTips:true //是否右下角提示,默认否
		    });
		}
	});
}

$(document).ready(function(){
	$('#addGadget').click(function(){
		$.epsDialog({
	        title:'自定义桌面',
	        url:$('#initPath').val()+'/view/srplatform/auth/gadgetCustomList.jsp',
	        width: '730',
	        height: '380',
	        hasBg:true,//背景
	        fadeTo:80//透明度
	    });
	});
	$('#myDesktopDiv').empty();
	myDesktop.getUserGadgetAndShowIt();//两列布局
	$('#openAllGadget').click(function(){
		myDesktop.showHiddenGadget(2);//两列布局
		myDesktop.changePosition();//重新调整行列坐标
	});
	
	//设定机构信息 排除管理员
	if(PlatForm.user.usrIsAdmin!='2')
	$.getJSON($('#initPath').val()+'/UserInfoController.do?method=getObject&includedProperties=orgInfo&objId='+PlatForm.user.objId,function(userInfo){
		  PlatForm.userInfo = userInfo.list[0];   
		  $.getJSON($('#initPath').val()+'/IndexViewController.do?method=setOrgInfoToUser',function(userInfo){
				//这里通过请求去检查用户角色，根据角色完成首页桌面的跳转
				$('#conBody').loadPage($('#initPath').val()+'/IndexViewController.do?method=toMyDesktop');
		  })
	  });
	
	//定时获取未读信息
	setInterval('myDesktop.processMessage()', myDesktop.timer);
	
	//处理当前用户的站内信
	myDesktop.processMessage();
});

$(function(){
	$('#conTitle .navCurrent').empty().append("<a href='#' class='loadingPageTitle'>我的桌面</a>");
});
		
