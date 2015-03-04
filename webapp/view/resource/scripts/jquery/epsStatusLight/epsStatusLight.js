/*
 * epsStatusLight
 * 状态灯
 * 
 * @url 请求状态数据的url
 * @data 请求url附加参数
 * @setting 状态值与显示样式对照
 * 
 * by:wangcl
 *
 * Depends:
 * 
 */

(function($) {

$.fn.epsStatusLight = function(url,data,setting) {
		var _this = $(this);
		setting = $.extend({
			ebuyMethodCN:'',
			'00':'notstarted',
			'01':'onGoing',
			'02':'completed',
			head_class:'pFirst',
			root_class:'pEnd'
		}, setting);
		$.getJSON(url,data,function(json){
			var result = json.result;
			if(result.length==0)return;
			var ul = document.createElement('ul');
			var titleLi = document.createElement('li');
			titleLi.className='navtitle';
			ul.appendChild(titleLi);
			//first
			$.each(result,function(i,n){
				var li = document.createElement('li');
				//if(n.status&&setting[n.status])li.className= setting[n.status];
				//li.innerHTML ='<span>'+n.name+'</span>';
				li.innerHTML ='<lable>'+n.name+'</lable>';
				li.id=n.objId;
				if(!n.children||n.children.length==0){
					//li.id=n.url;
				}
				else{
					var ul2 = document.createElement('ul');
					//second
					$.each(n.children,function(j,m){
						var li2 = document.createElement('li');
						if(m.status&&setting[m.status])li2.className= setting[m.status];
						li2.innerHTML ='<span>'+m.name+'</span>';
						li2.id=m.objId;
						li2.parentTaskId = m.parent.objId;// 记录上级任务ID
						if(!m.children||m.children.length==0){
							//li2.id=m.url;
						}
						else{
							var ul3 = document.createElement('ul');
							//third
							$.each(m.children,function(k,o){
								var li3 = document.createElement('li');
								if(o.status&&setting[o.status])li3.className= setting[o.status];
								li3.innerHTML ='<span>'+o.name+'</span>';
								//if(!o.children||o.children.length==0){
										li3.id=o.url;
								//}
								ul3.appendChild(li3);
							})
							li2.appendChild(ul3);
						}
							
						ul2.appendChild(li2);
					})
					li.appendChild(ul2);
				}
					
				ul.appendChild(li);
				if(i==0)li.className= setting.head_class;
				if(i==(result.length-1))li.className= setting.root_class;
			})
			_this.prepend(ul);
			
			$('li','#projectNav').click(function(id){
				if($(this).attr('id') !='' && $(this).attr('parentTaskId') != ''){
					// 点击工作计划
					//planTemplateTask.clickMethod($(this).parent().attr('id')+"");
					ProjectInfo.selectedProjectPlan($(this).attr('id'),$(this).attr('parentTaskId'));
				}
			})
			//$('li:has(ul)',_this).hover(
			$('li:has(ul)',_this).hover(
				function(){
				  var ul = $('ul:first',this);
				  ul.show();
				  }
				  ,
				  function(){
				  var ul = $('ul:first',this);
				  ul.hide();  
			  });
			  //判断
			  var first = $('#projectNav>ul>li');
			$.each($('#projectNav>ul>li'),function(i,n){
				
//				'00':'pC',notstarted
//				'01':'pGoingC',onGoing
//				'02':'pCompletedC',completed
//				head_class:'pFirst',
//				root_class:'pEnd'
				
				if(i==0)return;
				//alert($(this).find('li.pCompletedC,li.pGoingC').length)
				$(this).removeClass('pFirst pEnd');										// 删除第一个节点与最后一个节点样式，后面会添加样式，避免样式冲突
				if($(this).find('li.notstarted,li.onGoing').length==0){					// 没有进行中与为开始的节点，则 当前节点为已完成
					if(i==1){$(this).addClass('pCompletedF');}							// 头
					else if(i==(first.length-1)){$(this).addClass('pCompletedE');}		// 尾
					else{$(this).addClass('pCompletedC');}								// 中间
				}
				else if($(this).find('li.pCompletedC, li.onGoing').length>0){			// 进行中
					if(i==1){$(this).addClass('pGoingF');}
					else if(i==(first.length-1)){$(this).addClass('pGoingE');}
					else{$(this).addClass('pGoingC');}
				}
				else {																	// 起始\结束节点 未开始
					if(i==1){$(this).addClass('pFirst');}
					else if(i==(first.length-1)){$(this).addClass('pEnd');}
					else{$(this).addClass('pC');}
				}
			})
			 
		});
}

/*以查看方式显示状态灯
 * Created at 2011-6-28 16:49 by zhouzhanghe
 * */
$.fn.epsStatusLightOnlyView = function(url,data,setting) {
	var _this = $(this);
	setting = $.extend({
		ebuyMethodCN:'',
		'00':'notstarted',
		'01':'onGoing',
		'02':'completed',
		head_class:'pFirst',
		root_class:'pEnd'
	}, setting);
	$.getJSON(url,data,function(json){
		var result = json.result;
		if(result.length==0)return;
		var ul = document.createElement('ul');
		var titleLi = document.createElement('li');
		titleLi.className='navtitle';
		titleLi.innerHTML ='<p><strong>'+setting.ebuyMethodCN+'</strong></p>';
		ul.appendChild(titleLi);
		//first
		$.each(result,function(i,n){
			var li = document.createElement('li');
			li.innerHTML = n.name;
			li.id=n.objId;
			if(!n.children||n.children.length==0){
				//li.id=n.url;
			}
			else{
				var ul2 = document.createElement('ul');
				//second
				$.each(n.children,function(j,m){
					var li2 = document.createElement('li');
					if(m.status&&setting[m.status])li2.className= setting[m.status];
					li2.innerHTML ='<span>'+m.name+'</span>';
					li2.id=m.objId;
					li2.parentTaskId = m.parent.objId;// 记录上级任务ID
					if(!m.children||m.children.length==0){
						//li2.id=m.url;
					}
					else{
						var ul3 = document.createElement('ul');
						//third
						$.each(m.children,function(k,o){
							var li3 = document.createElement('li');
							if(o.status&&setting[o.status])li3.className= setting[o.status];
							li3.innerHTML ='<span>'+o.name+'</span>';
							//if(!o.children||o.children.length==0){
									li3.id=o.url;
							//}
							ul3.appendChild(li3);
						})
						li2.appendChild(ul3);
					}
					$(li2).css("cursor","default");//设置为默认指针类型Modified at 2011-6-28 17:46 by zhouzhanghe 	
					ul2.appendChild(li2);
				})
				li.appendChild(ul2);
			}
				
			ul.appendChild(li);
			if(i==0)li.className= setting.head_class;
			if(i==(result.length-1))li.className= setting.root_class;
		})
		_this.prepend(ul);
		
		//$('li:has(ul)',_this).hover(
		$('li:has(ul)',_this).hover(
			function(){
			  var ul = $('ul:first',this);
			  ul.show();
			  }
			  ,
			  function(){
			  var ul = $('ul:first',this);
			  ul.hide();  
		  });
		  //判断
		  var first = $('#projectNav>ul>li');
		$.each($('#projectNav>ul>li'),function(i,n){
			
//			'00':'pC',notstarted
//			'01':'pGoingC',onGoing
//			'02':'pCompletedC',completed
//			head_class:'pFirst',
//			root_class:'pEnd'
			
			if(i==0)return;
			//alert($(this).find('li.pCompletedC,li.pGoingC').length)
			$(this).removeClass('pFirst pEnd');										// 删除第一个节点与最后一个节点样式，后面会添加样式，避免样式冲突
			if($(this).find('li.notstarted,li.onGoing').length==0){					// 没有进行中与为开始的节点，则 当前节点为已完成
				if(i==1){$(this).addClass('pCompletedF');}							// 头
				else if(i==(first.length-1)){$(this).addClass('pCompletedE');}		// 尾
				else{$(this).addClass('pCompletedC');}								// 中间
			}
			else if($(this).find('li.pCompletedC, li.onGoing').length>0){			// 进行中
				if(i==1){$(this).addClass('pGoingF');}
				else if(i==(first.length-1)){$(this).addClass('pGoingE');}
				else{$(this).addClass('pGoingC');}
			}
			else {																	// 起始\结束节点 未开始
				if(i==1){$(this).addClass('pFirst');}
				else if(i==(first.length-1)){$(this).addClass('pEnd');}
				else{$(this).addClass('pC');}
			}
			$(this).css("cursor","default"); //设置为默认指针类型Modified at 2011-6-28 17:46 by zhouzhanghe
		})
		 
	});
}
})(jQuery);
 
