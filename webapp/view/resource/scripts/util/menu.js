//加载菜单
//by anson chents@gpcsoft.com
//target为三级以上的的菜单target
//callback为加载完菜单后回调函数，一般用于设置默认点击的菜单
//2010-1-17

//20010130 wangsw
//这个是平台登录到首页之后需要在首页显示用户名的代码, 正式系统运行需要取消注释
var PlatForm = {}
PlatForm.firstMenus = [];
PlatForm.secondMenus = [];
PlatForm.threeMenus = [];
PlatForm.user;
PlatForm.userInfo;
var organizationParentShortName = '';//by wangcl 定义一个全局变量，用作中文显示用。
var current_menu_href;//记录当前conBody包含页面url

$.addMenu = function(settings){
	var options = jQuery.extend({
		target:'#conBody',
		precall:false,
		callback :false,
		isAutoLoad:true//是否自动load
	}, settings);
	var prefn = options.precall||function(){};
	var fn = options.callback||function(){};
	var isAutoLoad = options.isAutoLoad && true;
	$.getJSON($('#initPath').val()+'/UserMenuController.do?method=loadAllMenu',function(json){
										  var menu = json.user.menus;
										  PlatForm.user=json.user;	
										  
										  var firstMenus =[],secondMenus = [],threeMenus = [];
										  
										  $.each(menu,function(i,j){
															   switch(j.level){
																   case "1":
																	   firstMenus.push(this);
																	   break;
																   case "2":
																	   secondMenus.push(this);
																	   break;
																   default:
																	   threeMenus.push(this);
																	   break;
																   }
															   })
	
										 var topHtml='';
										  
										 $.each(firstMenus,function(k,n){	
											 //有二级菜单才显示
											 if( JSON.stringify(secondMenus).indexOf(n.objId) >= 0 )										 
											 	topHtml+='<li><a href="#" id="'+ n.objId + '" target="'+n.resource.url+'" ><span>'	+ n.name+ '</span></a></li>';
										 });
										
										 $("#navMain ul").html(topHtml).find('a').click(function(){
											 if($(this).parent().hasClass('selected')) return false;
											//显示左边菜单
											fnShowSub();
											
											//对三级的隐藏
											$('#menuList').addClass('hidden');$('#conBody').attr('class','grid16_16');
											
											var firstTarget = this.target
											if(this.target!='1')$("#conBody").loadPage(this.target); //加载第一级菜单的内容
											 
											$('#conTitle .navCurrent').addClass("hidden").empty();
											
											$("#navMain ul li").removeClass('selected');
											
											$("#navMain ul li").find("ul").remove();
											
											$(this).parent().addClass('selected');
														var currId = this.id;
														var text = $(this).text();
														var ul = document.createElement('ul');
														//增加二级
														$.each(secondMenus, function(i, sec) {
															if (sec.parent.objId == currId){
																
																var secLi = document.createElement('li');//二级
																var secA = document.createElement("a");
																	secA.href = "javascript:void(0);";
																	secA.innerHTML = '<span><img src="'+$("#initPath").val()+"/"+sec.icon+'"/></span>'+sec.name;
																	secA.id= currId +'_'+sec.objId;
																secLi.appendChild(secA);
																var secLiUl = document.createElement('ul');
																secLiUl.className = 'subnav';
																
																secLi.appendChild(secLiUl);
																ul.appendChild(secLi);
																	
																//二级点击事件
																$(secA).click(function(){
																	//样式切换
																	$(this).parent().parent().find('li').removeClass('selected');
																	$(this).parent().addClass('selected');
																	
																	//清空
																	$('#menuList ul').html('');
																	
																	//增加三级
																	$.each(threeMenus, function(j, thr) {
																				if (thr.parent.objId == sec.objId) {
																							var thrLi = document.createElement('li');//三级
																							var thrA = document.createElement("a");
																							thrA.href = thr.resource.url;
																							thrA.innerHTML = '<span><img src="view/resource/skin/thems/default/img/icons/dzcghome.png"></span>'+thr.name
																							thrA.target=options.target;
																							thrA.id=currId +'_'+sec.objId+'_'+thr.objId;
																							thrLi.appendChild(thrA);
																							$('#menuList ul').append(thrLi);
																							
																							//三级菜单点击事件//样式切换
																							$(thrA).click(function(){
																								$(this).parent().parent().find('li').removeClass('selected');
																								$(this).parent().addClass('selected');
																							})
																				}					  
																	})
																	
																	//展现三级菜单
																	if($('#menuList ul').html()){
																		$('#menuList').removeClass('hidden');$('#conBody').attr('class','grid16_13 omega');
																		
																		$('#menuList li:first').addClass('selected');//选中当前第一个
																		$("#conBody").loadPage( $('#menuList li:first').find('a').attr('href') );//跳转到当前第一个 
																		
																	} else {
																		$('#menuList').addClass('hidden');$('#conBody').attr('class','grid16_16');
																		
																		$("#conBody").loadPage( sec.resource.url ); 
																	}
																})
															}
														})
														
														$(this).parent().append(ul);
														
														//如果一级的targt==1则点击二级第一个
														if(firstTarget == '1' && isAutoLoad){
															$("#navMain").find("li[class=selected]:first").find("li a:first").trigger('click');
														}
														return false;
														});
										 	if(!prefn()) {return;}
										 	$("#navMain ul a:first").trigger('click');
										 	fn();
										  })
}
