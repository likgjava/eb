var PlatForm = {}
PlatForm.user;
var organizationParentShortName = '';//by wangcl 定义一个全局变量，用作中文显示用。
var current_menu_href;//记录当前conBody包含页面url
//默认展开第一个二级菜单
function openSecTopMenu() {
	$('#contentSub>ul>li>a:first').trigger('click');
}
function diableA() {
	event.returnValue = false;
}
$.addMenu = function(settings) {
	var options = jQuery.extend({
				target : '#conBody',
				callback : false
			}, settings);
	var fn = options.callback || function() {
	};
	$.getJSON($('#initPath').val() + '/UserMenuController.do?method=loadMenu',function(json) {
				var topMenus = json.topMenus;
				PlatForm.user = json.user;
				var username = PlatForm.user.usName;
				var empName ='';
				if (PlatForm.user.emp)
					empName = PlatForm.user.emp.name;
				$('#currentUser').html('<span>您好，</span>' + empName);
				
				var topHtml = '';

				$.each(topMenus, function(k, n) {
							topHtml += '<li><a target="' + n.resource.url
									+ '"  href="#" id="' + n.objId + '"><span>'
									+ n.name + '</span></a></li>';
						});

				$("#navMain ul").html(topHtml);
				//点击顶级菜单
				$("#navMain ul").find('a').click(function() {
					if ($(this).parent().hasClass('selected'))
						return false;
						
					var target_url = this.target;
					$('#conTitle .navCurrent').empty();
					$("#conBody").loadPage(target_url,null,function(){
						current_menu_href = target_url;
					});
					
					$(this).parent('li').siblings().removeClass('selected')
							.end().addClass('selected');
					var currId = this.id;
					
					//second
					$.getJSON($('#initPath').val() + '/UserMenuController.do?method=loadChildMenu',{id:currId},function(secondMenus){
						var ul = document.createElement('ul');
						$.each(secondMenus.childMenus, function(i, sec){
							var secLi = document.createElement('li');//二级
							var secA = document.createElement("a");
							secA.href = "#";
							secA.innerHTML = '<span></span>' + sec.name;
							//secA.id = currId + '_' + sec.objId;
							secA.id = sec.objId;
							secA.className = 'icon1';
							secLi.appendChild(secA);

							var secLiUl = document.createElement('ul');//here
							secLiUl.className = 'subnav';
							
							secLi.appendChild(secLiUl);
							ul.appendChild(secLi);
						});
						$('#contentSub').empty().append(ul);//添加二级菜单
						
						$('#contentSub>ul>li>a').click(function() {
							if ($(this).parent().hasClass('selected'))
								return false;
								
							$(this).parent().siblings('li').removeClass('selected').end()
									.addClass('selected');
							var ch = $('#contentSub').height()
									- $('#contentSub>ul').height()
									+ $(this).next('ul').height();
							$(this).next('ul').css({
										'overflow' : 'auto',
										'height' : ch + 'px'
									});
							
							//third	
							var _this = $(this);
							if(_this.next('ul').find('li').length==0)
							$.getJSON($('#initPath').val() + '/UserMenuController.do?method=loadChildMenu',{id:_this.attr('id')},function(thirdMenus){
								$.each(thirdMenus.childMenus, function(i, thr){
									var thrLi = document.createElement('li');//三级
									var thrA = document.createElement("a");
									thrA.href = $('#initPath').val() + '/'
											+ thr.resource.url;
									thrA.onclick = 'diableA()';
									thrA.innerHTML = thr.name;
									thrA.target = options.target;
									thrA.id = thr.objId;
									thrLi.appendChild(thrA);
									_this.parent().find('ul')[0].appendChild(thrLi);
								})
							})	
						})
					})
					setTimeout('openSecTopMenu()', 300);
					return false;
				});
				$("#navMain ul a:first").trigger('click');
				fn();
			})
}
