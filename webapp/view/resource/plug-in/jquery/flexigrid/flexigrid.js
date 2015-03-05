/*
 * Flexigrid for jQuery - New Wave Grid
 *
 * Copyright (c) 2008 Paulo P. Marinas (webplicity.net/flexigrid)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 *
 * $Date: 2008-07-14 00:09:43 +0800 (Tue, 14 Jul 2008) $
 * Modify by:anson,chents@gpcsoft.com
 * $Date: 2009-12-4
* Depends:
 * 
 *  jquery.js v1.3 + 
 * 
 */

(function($){
		  
	$.addFlex = function(t,p)
	{
		
		if (t.grid) return false; //return if already exist	
		
		// apply default properties
		p = $.extend({
			 minGridHeight:0,//grid最低高度
			 height: 'auto', //default height
			 width: 'auto', //auto width
			 striped: true, //隔行不同样式
			 novstripe: false,
			 minwidth: 30,  //列的最小宽度
			 minheight: 80, //列的最小高度
			 resizable: false, //是否可伸缩
			 url: false, //ajax方式对应的url地址
			 params:{},//[{pp:'adsfs'}]此处为数组格式
			 method: 'POST', // 数据发送方式
			 dataType: 'json', // 数据加载的类型
			 errormsg: '错误请求',//错误提升信息
			 usepager: true, //是否分页
			 nowrap: true, //是否不换行
			 page: 1, //默认当前页
			 total: 1, //总页面数
			 useRp: true, //是否可以动态设置每页显示的结果数
			 singleSelect:false,//是否单选
			 checkbox:false,//是否要多选框
			 searchZone:false,//搜索表单域id
			 rowId:'objId',//绑定行id
			 rows:[],
			 queryColumns:'',//搜索的指定列
			 rp: 15, // 每页默认的结果数
			 rpOptions: [5,10,15,20,25,40],//可选择设定的每页结果数
			 title: false,//是否包含标题
			 buttons:false,//是否显示操作按钮
			 pagestat: '当前显示 {from}-{to} 共 {total} 记录',//显示当前页和总页面的样式
			 procmsg: '处理中, 请等待 ...',//正在处理的提示信息
			 query: '',//搜索查询的条件
			 qtype: '',//搜索查询的类别
			 nomsg: '没有找到相关信息',//无结果的提示信息
			 colTitle:'显示/隐藏列',
			 sortname:'', //排序列名
			 sortorder:'',//排序方法,asc/desc
			 minColToggle: 1, //minimum allowed column to be hidden
			 showTableToggleBtn:false,//可收缩标题按钮
			 showToggleBtn: true, //show or hide column toggle popup
			 hideOnSubmit: true,//隐藏提交
			 autoload: true,//自动加载
			 blockOpacity: 0.5,//透明度设置
			 exportExcelFn:false,//导出excel
			 printerFn:false,//打印
			 tree:false,//tree显示
			 isOpen:true,//是否展开树
			 treeParentId:'parent.objId',
			 exportExcel:false,//导出execl
			 printer:false,//打印
			 onToggleCol: false,//当在行之间转换时
			 onChangeSort: false,//当改变排序时
			 onSuccess: false,//成功后执行
			 onKeyRight:false,//右键事件
			 onSubmit: false // 调用自定义的计算函数
		  }, p);
		  		

		$(t)
		.show() //show if hidden
		.attr({cellPadding: 0, cellSpacing: 0, border: 0})  //remove padding and spacing
		.removeAttr('width') //remove width properties	
		;
		
		//create grid class
		var g = {
			hset : {},
			rePosDrag: function () {

			var cdleft = 0 - this.hDiv.scrollLeft;
			if (this.hDiv.scrollLeft>0) cdleft -= Math.floor(p.cgwidth/2);
			$(g.cDrag).css({top:g.hDiv.offsetTop+1});
			var cdpad = this.cdpad;
			
			$('div',g.cDrag).hide();
			
			$('thead tr:first th:visible',this.hDiv).each
				(
			 	function ()
					{
					var n = $('thead tr:first th:visible',g.hDiv).index(this);

					var cdpos = parseInt($('div',this).width());
					var ppos = cdpos;
					if (cdleft==0) 
							cdleft -= Math.floor(p.cgwidth/2); 

					cdpos = cdpos + cdleft + cdpad;
					
					
					if (p.checkbox) {
						//add checkbox width
						$('div:eq('+n+')',g.cDrag).css({'left':cdpos+14+'px'}).show();
					}
					else{
						$('div:eq('+n+')',g.cDrag).css({'left':cdpos+'px'}).show();
						}

					cdleft = cdpos;
					}
				);
				
			},
			fixHeight: function (newH) {
					newH = false;
					if (!newH) newH = $(g.bDiv).height();
					var hdHeight = $(this.hDiv).height();
					$('div',this.cDrag).each(
						function ()
							{
								$(this).height(newH+hdHeight);
							}
					);
					
					var nd = parseInt($(g.nDiv).height());
					
					if (nd>newH)
						$(g.nDiv).height(newH).width(200);
					else
						$(g.nDiv).height('auto').width('auto');
					
					$(g.block).css({height:newH,marginBottom:(newH * -1)});
					
					var hrH = g.bDiv.offsetTop + newH;
					if (p.height != 'auto' && p.resizable) hrH = g.vDiv.offsetTop;
					$(g.rDiv).css({height: hrH});
				
			},
			dragStart: function (dragtype,e,obj) { //default drag function start
				
				if (dragtype=='colresize') //column resize
					{
						$(g.nDiv).hide();$(g.nBtn).hide();
						var n = $('div',this.cDrag).index(obj);
						var ow = $('th:visible div:eq('+n+')',this.hDiv).width();
						$(obj).addClass('dragging').siblings().hide();
						$(obj).prev().addClass('dragging').show();
						
						this.colresize = {startX: e.pageX, ol: parseInt(obj.style.left), ow: ow, n : n };
						$('body').css('cursor','col-resize');
					}
				else if (dragtype=='vresize') //table resize
					{
						var hgo = false;
						$('body').css('cursor','row-resize');
						if (obj) 
							{
							hgo = true;
							$('body').css('cursor','col-resize');
							}
						this.vresize = {h: p.height, sy: e.pageY, w: p.width, sx: e.pageX, hgo: hgo};
						
					}

				else if (dragtype=='colMove') //column header drag
					{
						
						$(g.nDiv).hide();$(g.nBtn).hide();
						this.hset = $(this.hDiv).offset();
						this.hset.right = this.hset.left + $('table',this.hDiv).width();
						this.hset.bottom = this.hset.top + $('table',this.hDiv).height();
						this.dcol = obj;
						this.dcoln = $('th',this.hDiv).index(obj);
						
						//tree By : Anson
						if(p.tree){if($(obj).attr('axis')=='col0') return false;}
						this.colCopy = document.createElement("div");
						this.colCopy.className = "colCopy";
						this.colCopy.innerHTML = obj.innerHTML;
						if ($.browser.msie)
						{
						this.colCopy.className = "colCopy ie";
						}
						
						
						$(this.colCopy).css({position:'absolute',float:'left',display:'none', textAlign: obj.align});
						$('body').append(this.colCopy);
						$(this.cDrag).hide();
						
					}
														
				$('body').noSelect();
			
			},
			dragMove: function (e) {
			
				if (this.colresize) //column resize
					{
						var n = this.colresize.n;
						var diff = e.pageX-this.colresize.startX;
						var nleft = this.colresize.ol + diff;
						var nw = this.colresize.ow + diff;
						if (nw > p.minwidth)
							{
								$('div:eq('+n+')',this.cDrag).css('left',nleft);
								this.colresize.nw = nw;
							}
					}
				else if (this.vresize) //table resize
					{
						var v = this.vresize;
						var y = e.pageY;
						var diff = y-v.sy;
						
						if (!p.defwidth) p.defwidth = p.width;
						
						if (p.width != 'auto' && !p.nohresize && v.hgo)
						{
							var x = e.pageX;
							var xdiff = x - v.sx;
							var newW = v.w + xdiff;
							if (newW > p.defwidth)
								{
									this.gDiv.style.width = newW + 'px';
									p.width = newW;
								}
						}
						
						var newH = v.h + diff;
						if ((newH > p.minheight || p.height < p.minheight) && !v.hgo)
							{
								this.bDiv.style.height = newH + 'px';
								p.height = newH;
								this.fixHeight(newH);
							}
						v = null;
					}
				else if (this.colCopy) {
					$(this.dcol).addClass('thMove').removeClass('thOver'); 
					if (e.pageX > this.hset.right || e.pageX < this.hset.left || e.pageY > this.hset.bottom || e.pageY < this.hset.top)
					{
						//this.dragEnd();
						$('body').css('cursor','move');
					}
					else 
					$('body').css('cursor','pointer');
					$(this.colCopy).css({top:e.pageY + 10,left:e.pageX + 20, display: 'block'});
				}													
			
			},
			dragEnd: function () {

				if (this.colresize)
					{
						var n = this.colresize.n;
						var nw = this.colresize.nw;
						
						//alert(this.hDiv)

								$('th:visible div:eq('+n+')',this.hDiv).css('width',nw);
								$('tr',this.bDiv).each (
									function ()
										{
										//$('td:visible div:eq('+n+')',this).css('width',nw);
										$('td:visible:eq('+n+') div:first',this).css('width',nw);
										}
								);
								this.hDiv.scrollLeft = this.bDiv.scrollLeft;


						$('div:eq('+n+')',this.cDrag).siblings().show();
						$('.dragging',this.cDrag).removeClass('dragging');
						
						this.rePosDrag();
						this.fixHeight();
						this.colresize = false;
					}
				else if (this.vresize)
					{
						this.vresize = false;
					}
				else if (this.colCopy)
					{
						$(this.colCopy).remove();
						if (this.dcolt != null)
							{
							
							
							if((p.tree&& this.dcolt>0) || (!p.tree)) {						
								//tree no drog
								if (this.dcoln>this.dcolt)
									
									$('th:eq('+this.dcolt+')',this.hDiv).before(this.dcol);
								else
									$('th:eq('+this.dcolt+')',this.hDiv).after(this.dcol);
								
								this.switchCol(this.dcoln,this.dcolt);
								}
								
							$(this.cdropleft).remove();
							$(this.cdropright).remove();
							this.rePosDrag();
							
																			
							}
						
						this.dcol = null;
						this.hset = null;
						this.dcoln = null;
						this.dcolt = null;
						this.colCopy = null;
						
						$('.thMove',this.hDiv).removeClass('thMove');
						$(this.cDrag).show();
					}										
				$('body').css('cursor','default');
				$('body').noSelect(false);
			},
			toggleCol: function(cid,visible) {
				
				var ncol = $("th[axis='col"+cid+"']",this.hDiv)[0];
				var n = $('thead th',g.hDiv).index(ncol);
				var cb = $('input[value='+cid+']',g.nDiv)[0];
				
				
				if (visible==null)
					{
						visible = ncol.hide;
					}
				
				
				
				if ($('input:checked',g.nDiv).length<p.minColToggle&&!visible) return false;
				
				if (visible)
					{
						ncol.hide = false;
						$(ncol).show();
						cb.checked = true;
					}
				else
					{
						ncol.hide = true;
						$(ncol).hide();
						cb.checked = false;
					}
					
						$('tbody tr',t).each
							(
								function ()
									{
										if (visible)
											$('td:eq('+n+')',this).show();
										else
											$('td:eq('+n+')',this).hide();
									}
							);							
				
				this.rePosDrag();
				
				if (p.onToggleCol) p.onToggleCol(cid,visible);
				
				return visible;
			},
			switchCol: function(cdrag,cdrop) { //switch columns
				
				$('tbody tr',t).each
					(
						function ()
							{
								if (cdrag>cdrop)
									$('td:eq('+cdrop+')',this).before($('td:eq('+cdrag+')',this));
								else
									$('td:eq('+cdrop+')',this).after($('td:eq('+cdrag+')',this));
							}
					);
					
					//switch order in nDiv
					if (cdrag>cdrop)
						$('tr:eq('+cdrop+')',this.nDiv).before($('tr:eq('+cdrag+')',this.nDiv));
					else
						$('tr:eq('+cdrop+')',this.nDiv).after($('tr:eq('+cdrag+')',this.nDiv));
						
					if ($.browser.msie&&$.browser.version<7.0) $('tr:eq('+cdrop+') input',this.nDiv)[0].checked = true;	
					
					this.hDiv.scrollLeft = this.bDiv.scrollLeft;
			},			
			scroll: function() {
				
					this.hDiv.scrollLeft = this.bDiv.scrollLeft;
					//$('.copybDiv').css('top',this.bDiv.scrollTop+'px');
					// $(".copybDiv").animate({left : this.bDiv.scrollLeft },{ duration:0 , queue:false });
					this.rePosDrag();
			},
			addData: function (data) { //parse data
				
				if (p.preProcess)
					data = p.preProcess(data);
				
				$('.pReload',this.pDiv).removeClass('loading');
				this.loading = false;

				if (!data) 
					{
					$('.pPageStat',this.pDiv).html(p.errormsg);	
					return false;
					}

				if (p.dataType=='xml')
					p.total = +$('rows total',data).text();
				else
					p.total = data.total;
					
				if (p.total==0)
					{
					$('tr, a, td, div',t).unbind();
					$(t).empty();
					p.pages = 1;
					p.page = 1;
					this.buildpager();
					$('.pPageStat',this.pDiv).html(p.nomsg);
					return false;
					}
				
				p.pages = Math.ceil(p.total/p.rp);
				
				if (p.dataType=='xml')
					p.page = +$('rows page',data).text();
				else
					p.page = data.page;
				
				this.buildpager();

				//build new body
				var tbody = document.createElement('tbody');
				
				if (p.dataType=='json')
				{
					//tree by: Anson
					if(p.tree){
						$(tbody).addClass('gridTree');
						function initnode(node,levarr){
                        var ht=[];
                        var arrlen=levarr.length;
                        var isLeaf = node.isLeaf;

                        ht.push("<span class='treegrid_folder' hidden='",!(p.isOpen),"' name='",levarr,"'>");
                        for(var i=0;i<arrlen-1;i++){
                            if(levarr[i]==1){
								ht.push("<div class='elbowLine treeGridIcon'></div>");
                            }
                            else{
								ht.push("<div class='space treeGridIcon'></div>");
                            }
                        }
                        if(arrlen>0){
                            if(levarr[arrlen-1]==1){
                                if(p.isOpen){
                                    if(isLeaf=='false'){
									    ht.push("<div class='treegrid_folder EMinus treeGridIcon'></div>");
                                    }
                                    else{
                                        ht.push("<div class='elbow treeGridIcon'></div>");
                                    }
                                }
                                else{
                                    if(isLeaf=='false'){
										ht.push("<div class='treegrid_folder EPlus treeGridIcon'></div>");
                                    }
                                    else{
										ht.push("<div class='elbow treeGridIcon'></div>");
                                    }
                                }
                            }
                            else{
                                if(p.isOpen){
                                    if(isLeaf=='false'){
                                        ht.push("<div class='treegrid_folder EEndMinus treeGridIcon'></div>");
                                    }
                                    else{
                                        ht.push("<div class='elbowEnd treeGridIcon'></div>");
                                    }
                                }
                                else{
                                    if(isLeaf=='false'){
										ht.push("<div class='treegrid_folder EEndPlus treeGridIcon'></div>");
                                    }
                                    else{
                                        ht.push("<div class='elbowEnd treeGridIcon'></div>");
                                    }
                                }
                            }
                        }
                        if(isLeaf=='false'){
                            if(p.isOpen){
								 ht.push("<div class='folderisopen folderOpen treeGridIcon'></div>");
                            }
                            else{
								 ht.push("<div class='folderisopen folder treeGridIcon'></div>");
                            }
                        }else{
                             ht.push("<div class='leaf treeGridIcon'></div>");
                        }
                        ht.push("</span>");
                        return ht.join("");
                    } //end initnode
						function buildNode(node,odata,levarr){
							
							var tr = document.createElement('tr');
							//if (i % 2 && p.striped) tr.className = 'erow';
							var tdVal = [];
							var nid = node.objId;
							tr.setAttribute('trName',levarr);
							//给每行添加id
							if (p.rowId){
									$.each( node, function(x,y){if(p.rowId==x) tr.id =y ;})
								}else{
									if (nid) tr.id = nid;
									}
									
							if (p.colModel){
								
								for (j=0;j<p.colModel.length;j++){
								
												var cm = p.colModel[j];
												//取列名
												var seleceName = (cm.alias==''?cm.name:cm.alias);
												
												$.each(node,function(x,y) { 
												 //	json bug
												if(typeof(node[seleceName])=='undefined'){node[seleceName]=' '}
													//过滤key
													if(seleceName==x){tdVal.push(y);}; 
																			   
												  });
												
												}
							}
							//add cell
							$('thead tr:first th',g.hDiv).each
									(
										function (i)
											{
												
												var td = document.createElement('td');
												var idx = $(this).attr('axis').substr(3);
												td.align = this.align;
												td.innerHTML = (i==0) ? initnode(node,levarr) +tdVal[idx] : tdVal[idx];
												$(tr).append(td);
												td = null;
											}
									); 
							//add autoWidth
							$(tr).append('<td style="width:9999px; border-right-width:0px"  class="gridAutoWidth"></td>');
							//add checkbox for rows
							if (p.checkbox) {
	
								var cth = $('<th/>');
								
								var cthch = $('<input type="checkbox" value="' + $(tr).attr('id') +'"/>');
								
								var objTr = $(tr);
								
								cthch.addClass("noborder").click(function(){
																		  
																		  if(this.checked){
	
																			objTr.addClass('trSelected');
																			  }
																		  else{
																			  objTr.removeClass('trSelected'); 
																			  }
																		  })
								
								cth.addClass("cth").attr({ width: "22"}).append(cthch);
	
								$(tr).prepend(cth);
	
								
								
							}
							$(tbody).append(tr);

							var arr=[];
							var newdataxml=[];
							$.each(odata,function(i,j){
								var parentid = j[p.treeParentId];
								if(parentid==nid){
									arr.push(j);
								}else{
									newdataxml.push(j);
								}
							});
							if(arr.length>0){
								level++;//有子节点 层++
							}
							else if(arr.length==0 &&levpos[level]==0){//无子节点，并且为本层最后一个节点 层-- 数组pop
								while(arr.length==0 &&levpos[level]==0){
									level--;
									levpos.pop();
								}
							}
							var arrindex=0;
							$(arr).each(function(i,j){
								arrindex++;
								if(arr.length==arrindex){
									levpos[level]=0;
									buildNode(j,newdataxml,levpos);
								}//最后一个
								else{
									levpos[level]=1;
									buildNode(j,newdataxml,levpos);
								}//非最后一个
	
							});

						
						}//end buildNode
							
						var level=0,levpos=[];
						var arr=[];
						var odata=[];
						
						$.each(data.rows,function(i,row){
												 
							var parentid = row[p.treeParentId];
							var nid = row.id;
							if(parentid=='0' || parentid==""){
								arr.push(row);
								}else{
									odata.push(row);
									}
						 });
						
						var arrindex=0;
						$(arr).each(function(i,j){
				
							arrindex++;
							if(arr.length==arrindex){
								levpos[level]=0;
								buildNode(j,odata,levpos);
							}//最后一个
							else{
								levpos[level]=1;
								buildNode(j,odata,levpos);
							}//非最后一个
						});
						
						
	
					}else{
						
					$.each
					(
					 data.rows,
					 function(i,row) 
					 	{
							
							//add row
							var tr = document.createElement('tr');
							if (i % 2 && p.striped) tr.className = 'erow';
							
							var tdVal = [];
							var tdFomart = [];//td的类型样式
							
							//add RowId
							if (p.rowId){
								
								$.each( data.rows[i], function(x,y){
										   
									if(p.rowId==x){tr.setAttribute('id',y);
									}

									})
								}
								
							if (p.colModel){
								
								for (j=0;j<p.colModel.length;j++){
													var cm = p.colModel[j];
													//取列名 edit by wangcl 判断别名未定义
													var seleceName = (!cm.alias||cm.alias==''?cm.name:cm.alias);
													tdFomart.push(cm.format);//给列加上样式名
													//修复json Bug
													if(typeof(data.rows[i][seleceName])=='undefined'){data.rows[i][seleceName]=''}
													//过滤key
													$.each( data.rows[i], function(x,y){
			if(seleceName==x){  //by wangsw
				if(seleceName.indexOf('.') > 0 && typeof(data.rows[i])!='string' ){//by wangsw 20100127
					var value='';
					try{
						value=eval('data.rows[i].'+seleceName);
					}catch(e){
						tdVal.push(y); 
					}
					if(value!=''){
						var emun=eval('data.rows[i].'+seleceName+'_value');
						if(!value) value='';
						tdVal.push(!emun?value:emun); 
					}
				}else
					tdVal.push(y); 
			}
	})
													
																}
										}	

								//add cell
							$('thead tr:first th',g.hDiv).each(function(hh,h){
																		
										var td = document.createElement('td');
										var idx = $(this).attr('axis').substr(3);
										td.align = this.align;
										td.innerHTML = tdVal[idx];
										$(td).addClass(tdFomart[hh]);
										$(tr).append(td);
										td = null;


								})
							//add autoWidth
							$(tr).append('<td style="width:9999px; border-right-width:0px" class="gridAutoWidth"></td>');
							//add checkbox
							if (p.checkbox) {

								var cth = $('<th/>');
								
								var cthch = $('<input type="checkbox" value="' + $(tr).attr('id') +'"/>');
								
								var objTr = $(tr);
								
								cthch.addClass("noborder").click(function(){
																		  
																		  if(this.checked){

																			objTr.addClass('trSelected');
																			  }
																		  else{
																			  objTr.removeClass('trSelected'); 
																			  }
																		  })
								
								cth.addClass("cth").attr({ width: "22"}).append(cthch);
								
								$(tr).prepend(cth);

								
							}											
			
							
							$(tbody).append(tr);

							tr = null;

						}
					);
					
					}
					
				} 
				
				else if (p.dataType=='xml') {

				i = 1;

				$("rows row",data).each
				(
				 
				 	function ()
						{
							
							i++;
							
							var tr = document.createElement('tr');
							if (i % 2 && p.striped) tr.className = 'erow';

							var nid =$(this).attr('id');
							if (nid) tr.id = 'row' + nid;
							
							nid = null;
							
							var robj = this;

							
							
							$('thead tr:first th',g.hDiv).each
							(
							 	function ()
									{
										
										var td = document.createElement('td');
										var idx = $(this).attr('axis').substr(3);
										td.align = this.align;
										td.innerHTML = $("cell:eq("+ idx +")",robj).text();
										$(tr).append(td);
										td = null;
									}
							);
							
							
							if ($('thead',this.gDiv).length<1) //handle if grid has no headers
							{
								$('cell',this).each
								(
								 	function ()
										{
										var td = document.createElement('td');
										td.innerHTML = $(this).text();
										$(tr).append(td);
										td = null;
										}
								);
							}
							
							$(tbody).append(tr);
							tr = null;
							robj = null;
						}
				);
				
				}

				$('tr',t).unbind();
				$(t).empty();
				
				$(t).append(tbody);
				this.addCellProp();
				this.addRowProp();
				
				//this.fixHeight($(this.bDiv).height());
				
				this.rePosDrag();
				
				tbody = null; data = null; i = null; 
				
				//if (p.onSuccess) p.onSuccess();
				if (p.hideOnSubmit) $(g.block).remove();//$(t).show();
				
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				
				if ($.browser.opera) $(t).css('visibility','visible');
				
			},
			changeSort: function(th) { //change sortorder
			
				if (this.loading) return true;
				
				$(g.nDiv).hide();$(g.nBtn).hide();
				
				if (p.sortname == $(th).attr('abbr'))
					{
						if (p.sortorder=='asc') p.sortorder = 'desc'; 
						else p.sortorder = 'asc';						
					}
				
				$(th).addClass('sorted').siblings().removeClass('sorted');
				$('.sdesc',this.hDiv).removeClass('sdesc');
				$('.sasc',this.hDiv).removeClass('sasc');
				$('div',th).addClass('s'+p.sortorder);
				p.sortname= $(th).attr('abbr');
				
				if (p.onChangeSort)
					p.onChangeSort(p.sortname,p.sortorder);
				else
					this.populate();				
			
			},
			buildpager: function(){ //rebuild pager based on new properties
			
			$('.pcontrol input',this.pDiv).val(p.page);
			$('.pcontrol span',this.pDiv).html(p.pages);
			
			var r1 = (p.page-1) * p.rp + 1; 
			var r2 = r1 + p.rp - 1; 
			
			if (p.total<r2) r2 = p.total;
			
			var stat = p.pagestat;
			
			stat = stat.replace(/{from}/,r1);
			stat = stat.replace(/{to}/,r2);
			stat = stat.replace(/{total}/,p.total);
			
			$('.pPageStat',this.pDiv).html(stat);
			
			},
			populate: function () { //get latest data

				if (this.loading) return true;

				if (p.onSubmit)
					{
						var gh = p.onSubmit();
						if (!gh) return false;
					}

				this.loading = true;
				if (!p.url) return false;
				
				$('.pPageStat',this.pDiv).html(p.procmsg);
				
				$('.pReload',this.pDiv).addClass('loading');
				
				$(g.block).css({top:g.bDiv.offsetTop});
				
				if (p.hideOnSubmit) $(this.gDiv).prepend(g.block); //$(t).hide();
				
				if ($.browser.opera) $(t).css('visibility','hidden');
				
				if (!p.newp) p.newp = 1;
				
				if (p.page>p.pages) p.page = p.pages;
				
				// by wangsw
				if(p.queryColumns.lastIndexOf(',')==p.queryColumns.length-1)
					p.queryColumns=p.queryColumns.substring(0,p.queryColumns.length-1);
				
				p.queryColumns+=',objId';//by wangsw
				p.queryColumns=$.unique(p.queryColumns.split(',')).toString();
				
				var param = [
					 { name : 'page', value : p.newp }
					,{ name : 'rp', value : p.rp }
					,{ name : 'sortname', value : p.sortname}
					,{ name : 'sortorder', value : p.sortorder }
					,{ name : 'queryColumns', value : p.queryColumns}
					,{ name : 'isAJAX', value : 'true' }//同步/异步判断
					,{ name : 'qtype', value : p.qtype}
				];				
				
				$.each(p.query,function(m,n){//by wangsw
					param.push(n);
				});
				
				var alias=[];//别名
				if (p.colModel){
					$.each(p.queryColumns.split(','),function(qqq,qq){
						alias.push(qq);
					});
					for(var u=0; u<alias.length; u++){
						$.each(p.colModel,function(qqq,qq){
							if(qq.name==alias[u]){
								alias[u]=(qq.alias==''?alias[u]:qq.alias);
								return false;
							}
						});
					}
				}
				param.push({name:'alias', value:alias.toString()});
				
				//add other param
				for(var key in p.params){//by wangsw
					var object={name:key, value:p.params[key]};
					param.push(object);
				}
				$.ajax({
				   type: p.method,
				   url: p.url,
				   data: param,
				   dataType: p.dataType,
				   success: function(data){
					  if(data.result)
					  {alert(data.result);}
					  if(data.failure) return false; 
					   g.addData(data);
					   p.rows = data.rows;
					   if(p.height=='auto'){
					   	 $(g.bDiv).css({height:"auto"});//reload时，重置自动高度
					   	 if(p.rows.length<5&&p.minGridHeight>0)$(g.bDiv).css({height:p.minGridHeight});//设置最低高度wangcl
					   }
					   if (p.onSuccess) p.onSuccess();
						/*设置样式格式化金额*/
						$('.money div', '#'+p.id).each(function(i,n){
							if($('input',this).length>0)
								$(this).val(formatAmount($(this).val(),2));
							else if($('a',this).length>0)
								$('a',this).html(formatAmount($(this).html(),2));
							else 
								$(this).html(formatAmount($(this).html(),2));
						});
						/*设置样式格式化数量*/
						$('.amount div', '#'+p.id).not('input').each(function(i,n){
							if($('input',this).length>0)
								$(this).val(formatAmount($(this).val()));
							else if($('a',this).length>0)
								$('a',this).html(formatAmount($(this).html()));
							else 
								$(this).html(formatAmount($(this).html()));
						});
						/*设置date格式化*/
						$('.date div', '#'+p.id).not('input').each(function(i,n){
							if($('input',this).length==0)$(this).html($(this).html().substring(0,10));    
						});
						/*设置datetime格式化*/   
						$('.datetime div', '#'+p.id).not('input').each(function(i,n){
							if($('input',this).length==0)$(this).html($(this).html().substring(0,19));    
						});
				   },
				   error: function(data) { try { if (p.onError) p.onError(data); } catch (e) {} }
				 });
			},
			getLatestParams: function () { //get params
				
				if (!p.newp) p.newp = 1;
				if (p.page>p.pages) p.page = p.pages;
				var param = [//by wangsw
					 { name : 'page', value : p.newp }
					,{ name : 'rp', value : p.rp }
					,{ name : 'sortname', value : p.sortname}
					,{ name : 'sortorder', value : p.sortorder }
					,{ name : 'isAJAX', value : true }//by wangsw
					,{ name : 'queryColumns', value : p.queryColumns }
					,{ name : 'qtype', value : p.qtype}
				];		
				
				for(var key in p.params){//by wangsw
					var object={name:key, value:p.params[key]};
					param.push(object);
				}
			return param;
			},
			doSearch: function () {
				//p.query = $('input[name=q]',g.sDiv).val();
				//p.qtype = $('select[name=qtype]',g.sDiv).val();
				p.query = g.dt;
				p.qtype = -1;
				p.newp = 1;

				this.populate();				
			},
			changePage: function (ctype){ //change page
			
				if (this.loading) return true;
			
				switch(ctype)
				{
					case 'first': p.newp = 1; break;
					case 'prev': if (p.page>1) p.newp = parseInt(p.page) - 1; break;
					case 'next': if (p.page<p.pages) p.newp = parseInt(p.page) + 1; break;
					case 'last': p.newp = p.pages; break;
					case 'input': 
							var nv = parseInt($('.pcontrol input',this.pDiv).val());
							if (isNaN(nv)) nv = 1;
							if (nv<1) nv = 1;
							else if (nv > p.pages) nv = p.pages;
							$('.pcontrol input',this.pDiv).val(nv);
							p.newp =nv;
							break;
				}
			
				if (p.newp==p.page) return false;
				
				if (p.onChangePage) 
					p.onChangePage(p.newp);
				else				
					this.populate();
			
			},
			addCellProp: function ()
			{
				
					$('tbody tr td',g.bDiv).each
					(
						function ()
							{
									var tdDiv = document.createElement('div');
									var n = $('td',$(this).parent()).index(this);
									var pth = $('th:eq('+n+')',g.hDiv).get(0);
			
									if (pth!=null)
									{
										var sortName = p.sortname.split(',');
										for(var x=0;x<sortName.length;x++){
									if (sortName[x]==$(pth).attr('abbr')&&sortName[x]) 
										{
											$(this).addClass('sorted')
										}
										}
									 $(tdDiv).css({textAlign:pth.align,width: $('div:first',pth)[0].style.width});
									 
									 if (pth.hide) $(this).css('display','none');
									 
									 }
									 
									 if (p.nowrap==false) $(tdDiv).css('white-space','normal');
									 
									 if (this.innerHTML=='') this.innerHTML = '&nbsp;';
									 
									 //tdDiv.value = this.innerHTML; //store preprocess value
									 tdDiv.innerHTML = this.innerHTML;
									 
									 var prnt = $(this).parent()[0];
									 var pid = false;
									 if (prnt.id) pid = prnt.id.substr(3);
									 
									 if (pth!=null)
									 {
									 if (pth.process) pth.process(tdDiv,pid);
									 }
									 
									$(this).empty().append(tdDiv).removeAttr('width'); //wrap content

									//add editable event here 'dblclick'

							}
					);
					
			},
			getCellDim: function (obj) // get cell prop for editable event
			{
				var ht = parseInt($(obj).height());
				var pht = parseInt($(obj).parent().height());
				var wt = parseInt(obj.style.width);
				var pwt = parseInt($(obj).parent().width());
				var top = obj.offsetParent.offsetTop;
				var left = obj.offsetParent.offsetLeft;
				var pdl = parseInt($(obj).css('paddingLeft'));
				var pdt = parseInt($(obj).css('paddingTop'));
				return {ht:ht,wt:wt,top:top,left:left,pdl:pdl, pdt:pdt, pht:pht, pwt: pwt};
			},
			addRowProp: function()
			{
					$('tbody tr',g.bDiv).each
					(
						function ()
							{
							$(this)
							.click(
								function (e) 
									{ 
										var obj = (e.target || e.srcElement); if (obj.href || obj.type) return true;
										$(this).toggleClass('trSelected');
										//add checkbox
										if(p.checkbox){
											if($(this).hasClass('trSelected')){
												$(this).find('input')[0].checked=true;
											}
											else{
												$(this).find('input')[0].checked=false
												}
										}
										if (p.singleSelect) $(this).siblings().removeClass('trSelected');
									}
							)
							.mousedown(
								function (e)
									{
										if (e.shiftKey)
										{
										$(this).toggleClass('trSelected'); 
										g.multisel = true; 
										this.focus();
										$(g.gDiv).noSelect();
										}
									}
							)
							.mouseup(
								function ()
									{
										if (g.multisel)
										{
										g.multisel = false;
										$(g.gDiv).noSelect(false);
										}
									}
							)
							.hover(
								function (e) 
									{ 
									if (g.multisel) 
										{
										$(this).toggleClass('trSelected'); 
										}
									},
								function () {}						
							)
							.bind('contextmenu', function(e) {
														  $(this).addClass('trOver');
														  var id=this.id;
														  var json = $(t).flexGetRowJsonById(id);
														  if (p.onKeyRight) p.onKeyRight(json,e);
														  $(this).removeClass('trOver');
														  //右键事件;
														  return false;
														  })
							;
							
							if ($.browser.msie&&$.browser.version<7.0)
								{
									$(this)
									.hover(
										function () { $(this).addClass('trOver'); },
										function () { $(this).removeClass('trOver'); }
									)
									;
								}
							}
					);
					//tree by: Anson
				if(p.tree){
				 var imgname, hidden;
                $("span.treegrid_folder",t).each(
                    function(i){
                        imgname=$(this).attr('name');
                        hidden=$(this).attr('hidden');
						var tTr = $(this).findParentTag('tr');
                        if(imgname.length!=1&&!p.isOpen) tTr.css('display',"none");
						
                        tTr.attr({
								 "trname":imgname,
								 "isOpen":p.isOpen
								 });

                        $("div.treegrid_folder",this).click(function(){
	
                            folderAction($(this).findParentTag('tr'),this);
							return false;
                        })
                    });
				 function folderAction(tr,handle){
						
					
					var isOpen=eval($(tr).attr("isOpen"));
					if(isOpen){
						
						if($(handle).hasClass('EMinus')) $(handle).removeClass("EMinus").addClass("EPlus");
						if($(handle).hasClass('EEndMinus')) $(handle).removeClass("EEndMinus").addClass("EEndPlus");
						
						$(tr).attr("isOpen",false);
						$("div.folderisopen",tr).removeClass("folderOpen").addClass("folder");
	
						var name=$(tr).attr("trname");
						var nameLen=name.length;
						var index = $(tr).parent().find('tr').index(tr);
						$(tr).parent().find('tr:gt('+index+')').each(function(){
																
																var theName=$(this).attr("trname");	
																var theLen=theName.length;
																if (nameLen==theLen) return false;
																if(name==theName.substr(0,nameLen)&&theLen>nameLen){
																	$(this).hide();
																}
															
																
						})
				   
					}
					else{
						
						if($(handle).hasClass('EPlus')) $(handle).removeClass("EPlus").addClass("EMinus");
						if($(handle).hasClass('EEndPlus')) $(handle).removeClass("EEndPlus").addClass("EEndMinus");
						$(tr).attr("isOpen",true);
						$("div.folderisopen",tr).removeClass("folder").addClass("folderOpen");
						function showNode(tr){
							var name=$(tr).attr("trname");
							var nameLen=name.length;
							var index = $(tr).parent().find('tr').index(tr);
							$(tr).parent().find('tr:gt('+index+')').each(function(){
															var theName=$(this).attr("trname");	
															var theLen=theName.length;
															if (nameLen==theLen) return false;
															if(name == theName.substr(0,nameLen) && theLen==(nameLen+2)){
																$(this).show();
																if(eval($(this).attr("isOpen"))) showNode(this);
																
															}
															
							})
	
						};
						showNode(tr);
			 }
			 	
				}
			}
					
			},
			pager: 0
			};		
		
		//create model if any
		if (p.colModel)
		{
			thead = document.createElement('thead');
			tr = document.createElement('tr');
			p.queryColumns+=',';	//
			
			for (i=0;i<p.colModel.length;i++)
				{
					var cm = p.colModel[i];
					var th = document.createElement('th');

					th.innerHTML = cm.display;
					
					if (cm.name&&cm.sortable)
						$(th).attr('abbr',cm.name);
						
					
					//th.idx = i;
					$(th).attr('axis','col'+i);
					
					if (cm.align)
						th.align = cm.align;
						
					if (cm.width) 
						$(th).attr('width',cm.width);

					if (cm.hide)
						{
						th.hide = true;
						}
						
					if (cm.process)
						{
							th.process = cm.process;
						}
						
					//add lock
					if(cm.lock)
						$(th).attr('lock',cm.name);
					if(cm.queryable)//by wangsw 2010.2.2
						p.queryColumns+=cm.name+',';//by wangsw
				//	p.queryColumns.push(cm.name);
					
					$(tr).append(th);
				}
			$(thead).append(tr);
			$(t).prepend(thead);
		} // end if p.colmodel	

		//init divs
		g.gDiv = document.createElement('div'); //create global container
		g.mDiv = document.createElement('div'); //create title container
		g.hDiv = document.createElement('div'); //create header container
		g.bDiv = document.createElement('div'); //create body container
		g.vDiv = document.createElement('div'); //create grip
		g.rDiv = document.createElement('div'); //create horizontal resizer
		g.cDrag = document.createElement('div'); //create column drag
		g.block = document.createElement('div'); //creat blocker
		g.nDiv = document.createElement('div'); //create column show/hide popup
		g.nBtn = document.createElement('div'); //create column show/hide button
		g.iDiv = document.createElement('div'); //create editable layer
		g.tDiv = document.createElement('div'); //create toolbar
		g.sDiv = document.createElement('div');
		
		if (p.usepager) g.pDiv = document.createElement('div'); //create pager container
		else p.rp = 100;   //如果不分页，默认条数为100 by liangxj
		g.hTable = document.createElement('table');

		//set gDiv
		g.gDiv.className = 'flexigrid';
		g.gDiv.setAttribute('eps','epsFlexigrid');
		if (p.width!='auto') g.gDiv.style.width = p.width + 'px';

		//add conditional classes
		if ($.browser.msie)
			$(g.gDiv).addClass('ie');
		
		if (p.novstripe)
			$(g.gDiv).addClass('novstripe');

		$(t).before(g.gDiv);
		$(g.gDiv)
		.append(t)
		;

		//set toolbar
		if (p.buttons || p.title) 
		{
			g.tDiv.className = 'tDiv';
			var tDiv2 = document.createElement('div');
			tDiv2.className = 'tDiv2';
			
			for (i=0;i<p.buttons.length;i++)
				{
					var btn = p.buttons[i];
					if (!btn.separator)
					{
						var btnDiv = document.createElement('div');
						btnDiv.className = 'fbutton';
						btnDiv.innerHTML = "<div><span>"+btn.name+"</span></div>";
						if (btn.bclass) 
							$('span',btnDiv)
							.addClass(btn.bclass)
							.css({paddingLeft:20})
							;
						//btnDiv.onpress = btn.onpress;//onpress
						btnDiv.onpress = (typeof(btn.onpress)=='string'?eval(btn.onpress):btn.onpress);//by wangsw
						btnDiv.name = btn.name;
						if (btn.onpress)
						{
							$(btnDiv).click
							(	
								function () 
								{
								this.onpress(this.name,g.gDiv);
								}
							);
						}
						$(tDiv2).append(btnDiv);
						if ($.browser.msie&&$.browser.version<7.0)
						{
							$(btnDiv).hover(function(){$(this).addClass('fbOver');},function(){$(this).removeClass('fbOver');});
						}
						
					} else {
						$(tDiv2).append("<div class='btnseparator'></div>");
					}
				}
				$(g.tDiv).append(tDiv2);
				$(g.tDiv).append("<div style='clear:both'></div>");
				$(g.gDiv).prepend(g.tDiv);
		}
		
		//set hDiv
		g.hDiv.className = 'hDiv';

		$(t).before(g.hDiv);

		//set hTable
			g.hTable.cellPadding = 0;
			g.hTable.cellSpacing = 0;
			$(g.hDiv).append('<div class="hDivBox"></div>');
			$('div',g.hDiv).append(g.hTable);
			var thead = $("thead:first",t).get(0);
			if (thead) $(g.hTable).append(thead);
			thead = null;
		
		if (!p.colmodel) var ci = 0;

		//setup thead			
			$('thead tr:first th',g.hDiv).each
			(
			 	function ()
					{
						var thdiv = document.createElement('div');
						
						
					
						if ($(this).attr('abbr'))
							{
							$(this).click(
								function (e) 
									{
										
										if (!$(this).hasClass('thOver')) return false;
										var obj = (e.target || e.srcElement);
										if (obj.href || obj.type) return true; 
										g.changeSort(this);
									}
							)
							;
							var sortName = p.sortname.split(',');
							var sortOrder = p.sortorder.split(',');
							for(var x=0;x<sortName.length;x++){
							if ($(this).attr('abbr')==sortName[x])
								{
								this.className = 'sorted';
								thdiv.className = 's'+sortOrder[x];
								}
							}
							}
							if (this.hide) $(this).hide();
							
							if (!p.colmodel)
							{
								$(this).attr('axis','col' + ci++);
							}
							
							if(isNaN(this.width)){
								$(thdiv).css({textAlign:this.align, width: this.width});
								}
							else{
						 $(thdiv).css({textAlign:"center", width: this.width + 'px'});
							}
						 thdiv.innerHTML = this.innerHTML;
						 
						$(this).empty().append(thdiv).removeAttr('width')
						.mousedown(function (e) 
							{
								//add lock
								if (!$(this).attr('lock'))
									g.dragStart('colMove',e,this);
							})
						.hover(
							function(){
								
								if (!g.colresize&&!$(this).hasClass('thMove')&&!g.colCopy) $(this).addClass('thOver');
								
								if ($(this).attr('abbr')!=p.sortname&&!g.colCopy&&!g.colresize&&$(this).attr('abbr')) $('div',this).addClass('s'+p.sortorder);
								else if ($(this).attr('abbr')==p.sortname&&!g.colCopy&&!g.colresize&&$(this).attr('abbr'))
									{
										var no = '';
										if (p.sortorder=='asc') no = 'desc';
										else no = 'asc';
										$('div',this).removeClass('s'+p.sortorder).addClass('s'+no);
									}
								
								if (g.colCopy) 
									{
									var n = $('th',g.hDiv).index(this);
									
									if (n==g.dcoln) return false;
									
									
									
									if (n<g.dcoln) $(this).append(g.cdropleft);
									else $(this).append(g.cdropright);
									
									g.dcolt = n;
									
									} else if (!g.colresize) {
										
									var nv = $('th:visible',g.hDiv).index(this);
									var onl = parseInt($('div:eq('+nv+')',g.cDrag).css('left'));
									var nw = parseInt($(g.nBtn).width()) + parseInt($(g.nBtn).css('borderLeftWidth'));
									nl = onl - nw + Math.floor(p.cgwidth/2);
									
									$(g.nDiv).hide();$(g.nBtn).hide();
									if(isNaN(nl))nl=0;									
									$(g.nBtn).css({'left':nl,top:g.hDiv.offsetTop}).show();
									
									var ndw = parseInt($(g.nDiv).width());
									
									$(g.nDiv).css({top:g.bDiv.offsetTop});
									
									if ((nl+ndw)>$(g.gDiv).width())
										$(g.nDiv).css('left',onl-ndw+1);
									else
										$(g.nDiv).css('left',nl);
										
									if ($(this).hasClass('sorted')) 
										$(g.nBtn).addClass('srtd');
									else
										$(g.nBtn).removeClass('srtd');
										
									}
									
							},
							function(){
								$(this).removeClass('thOver');
								if ($(this).attr('abbr')!=p.sortname) $('div',this).removeClass('s'+p.sortorder);
								else if ($(this).attr('abbr')==p.sortname)
									{
										var no = '';
										if (p.sortorder=='asc') no = 'desc';
										else no = 'asc';
										
										$('div',this).addClass('s'+p.sortorder).removeClass('s'+no);
									}
								if (g.colCopy) 
									{								
									$(g.cdropleft).remove();
									$(g.cdropright).remove();
									g.dcolt = null;
									}
							})
						; //wrap content
					}
			);

		//set bDiv
		g.bDiv.className = 'bDiv';
		$(t).before(g.bDiv);
		$(g.bDiv)
		.css({ height: (p.height=='auto') ? 'auto' : p.height+"px"})
		.scroll(function (e) {g.scroll()})
		.append(t)
		;
		
		if (p.height == 'auto') 
			{
			$('table',g.bDiv).addClass('autoht');
			}


		//add td properties
		g.addCellProp();
		
		//add row properties
		g.addRowProp();
		
		//set cDrag
		
		var cdcol = $('thead tr:first th:first',g.hDiv).get(0);
		
		if (cdcol != null)
		{		
		g.cDrag.className = 'cDrag';
		g.cdpad = 0;
		
		g.cdpad += (isNaN(parseInt($('div',cdcol).css('borderLeftWidth'))) ? 0 : parseInt($('div',cdcol).css('borderLeftWidth'))); 
		g.cdpad += (isNaN(parseInt($('div',cdcol).css('borderRightWidth'))) ? 0 : parseInt($('div',cdcol).css('borderRightWidth'))); 
		g.cdpad += (isNaN(parseInt($('div',cdcol).css('paddingLeft'))) ? 0 : parseInt($('div',cdcol).css('paddingLeft'))); 
		g.cdpad += (isNaN(parseInt($('div',cdcol).css('paddingRight'))) ? 0 : parseInt($('div',cdcol).css('paddingRight'))); 
		g.cdpad += (isNaN(parseInt($(cdcol).css('borderLeftWidth'))) ? 0 : parseInt($(cdcol).css('borderLeftWidth'))); 
		g.cdpad += (isNaN(parseInt($(cdcol).css('borderRightWidth'))) ? 0 : parseInt($(cdcol).css('borderRightWidth'))); 
		g.cdpad += (isNaN(parseInt($(cdcol).css('paddingLeft'))) ? 0 : parseInt($(cdcol).css('paddingLeft'))); 
		g.cdpad += (isNaN(parseInt($(cdcol).css('paddingRight'))) ? 0 : parseInt($(cdcol).css('paddingRight'))); 

		$(g.bDiv).before(g.cDrag);
		
		var cdheight = $(g.bDiv).height();
		var hdheight = $(g.hDiv).height();
		
		$(g.cDrag).css({top: -hdheight + 'px'});
		
		$('thead tr:first th',g.hDiv).each
			(
			 	function ()

					{
						var cgDiv = document.createElement('div');
						$(g.cDrag).append(cgDiv);
						if (!p.cgwidth) p.cgwidth = $(cgDiv).width();
						$(cgDiv).css({height: cdheight + hdheight})
						.mousedown(function(e){g.dragStart('colresize',e,this);})
						;
						if ($.browser.msie&&$.browser.version<7.0)
						{
							g.fixHeight($(g.gDiv).height());
							$(cgDiv).hover(
								function () 
								{
								g.fixHeight();
								$(this).addClass('dragging') 
								},
								function () { if (!g.colresize) $(this).removeClass('dragging') }
							);
						}
					}
			);
		
		//g.rePosDrag();
							
		}
		

		//add strip		
		if (p.striped) 
			$('tbody tr:odd',g.bDiv).addClass('erow');
			
			
		if (p.resizable && p.height !='auto') 
		{
		g.vDiv.className = 'vGrip';
		$(g.vDiv)
		.mousedown(function (e) { g.dragStart('vresize',e)})
		.html('<span></span>');
		$(g.bDiv).after(g.vDiv);
		}
		
		if (p.resizable && p.width !='auto' && !p.nohresize) 
		{
		g.rDiv.className = 'hGrip';
		$(g.rDiv)
		.mousedown(function (e) {g.dragStart('vresize',e,true);})
		.html('<span></span>')
		.css('height',$(g.gDiv).height())
		;
		if ($.browser.msie&&$.browser.version<7.0)
		{
			$(g.rDiv).hover(function(){$(this).addClass('hgOver');},function(){$(this).removeClass('hgOver');});
		}
		$(g.gDiv).append(g.rDiv);
		}
		
		// add pager
		if (p.usepager)
		{
		g.pDiv.className = 'pDiv';
		g.pDiv.innerHTML = '<div class="pDiv2"></div>';
		$(g.bDiv).after(g.pDiv);
		var html = ' <div class="pGroup"> <div class="pFirst pButton"><span></span></div><div class="pPrev pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">第 <input type="text" size="4" value="1" />页 共<span> 1 </span>页</span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton"><span></span></div><div class="pLast pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
		$('div',g.pDiv).html(html);
		
		$('.pReload',g.pDiv).click(function(){g.populate()});
		$('.pFirst',g.pDiv).click(function(){g.changePage('first')});
		$('.pPrev',g.pDiv).click(function(){g.changePage('prev')});
		$('.pNext',g.pDiv).click(function(){g.changePage('next')});
		$('.pLast',g.pDiv).click(function(){g.changePage('last')});
		$('.pcontrol input',g.pDiv).keydown(function(e){if(e.keyCode==13) g.changePage('input')});
		if ($.browser.msie&&$.browser.version<7) $('.pButton',g.pDiv).hover(function(){$(this).addClass('pBtnOver');},function(){$(this).removeClass('pBtnOver');});
			
			if (p.useRp)
			{
			var opt = "";
			for (var nx=0;nx<p.rpOptions.length;nx++)
			{
				if (p.rp == p.rpOptions[nx]) sel = 'selected="selected"'; else sel = '';
				 opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >" + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
			};
			$('.pDiv2',g.pDiv).prepend("<div class='pGroup'><select name='rp'>"+opt+"</select></div> <div class='btnseparator'></div>");
			$('select',g.pDiv).change(
					function ()
					{
						if (p.onRpChange) 
							p.onRpChange(+this.value);
						else
							{
							p.newp = 1;
							p.rp = +this.value;
							g.populate();
							}
					}
				);
			}
		
/*		//add search button
		if (p.searchitems)
			{
				$('.pDiv2',g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
				$('.pSearch',g.pDiv).click(function(){$(g.sDiv).slideToggle('fast',function(){$('.sDiv:visible input:first',g.gDiv).trigger('focus');});});				
				//add search box
				g.sDiv.className = 'sDiv';
				
				
				sitems = p.searchitems;
				
				var sopt = "";
				for (var s = 0; s < sitems.length; s++)
				{
					if (p.qtype=='' && sitems[s].isdefault==true)
					{
					p.qtype = sitems[s].name;
					sel = 'selected="selected"';
					} else sel = '';
					sopt += "<option value='" + sitems[s].name + "' " + sel + " >" + sitems[s].display + "&nbsp;&nbsp;</option>";						
				}
				
				if (p.qtype=='') p.qtype = sitems[0].name;
				
				$(g.sDiv).append("<div class='sDiv2'>Quick Search <input type='text' size='30' name='q' class='qsbox' /> <select name='qtype'>"+sopt+"</select> <input type='button' value='Clear' /></div>");

				$('input[name=q],select[name=qtype]',g.sDiv).keydown(function(e){if(e.keyCode==13) g.doSearch()});
				
				$('input[value=Clear]',g.sDiv).click(function(){$('input[name=q]',g.sDiv).val(''); p.query = ''; g.doSearch(); });
				$(g.bDiv).after(g.sDiv);				
				
			}*/
		
		//add search zone
		if(p.searchZone){
				
/*				$('.pDiv2',g.pDiv).prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
				$('.pSearch',g.pDiv).click(function(){$(g.sDiv).slideToggle('fast',function(){$('.sDiv:visible input:first',g.gDiv).trigger('focus');});});				
				//add search box
				g.sDiv.className = 'sDiv';
				
				var $zone = $('#'+p.searchZone);
				
				$(g.sDiv).append("<div class='sDiv2'></div>")
				.find('.sDiv2').empty().append($zone)
				.find('#'+p.searchZone)
				.submit(function(){
								// g.dt = $(this).serialize();
								 g.dt = $(this).serializeArray();
								 g.doSearch();
								 return false;
				});
				
				$(g.bDiv).after(g.sDiv);*/
				//解决提交没反应的问题
				$('#'+p.searchZone+' button').click(function(){
								// g.dt = $(this).serialize();
								 g.dt = $('#'+p.searchZone).serializeArray();
								 g.doSearch();
								 return false;
				});
			}
		}
		else{
			if(p.searchZone){
				$('#'+p.searchZone)
				.submit(function(){
								// g.dt = $(this).serialize();
								 g.dt = $(this).serializeArray();
								 g.doSearch();
								 return false;
				});
			}
		}
		$(g.pDiv,g.sDiv).append("<div style='clear:both'></div>");
		//add exportExcel
		if(p.exportExcel){
			$('.pDiv2',g.pDiv).append("<div class='btnseparator'></div> <div class='pGroup'> <div class='exportExcel pButton'><span></span></div></div>")
			.find('.exportExcel').click(function(){
												 var gh = p.exportExcel(p.url,g.getLatestParams());
												 if (!gh) return false;
												 
												 });
			

			}
		if(p.printer){
			$('.pDiv2',g.pDiv).append("<div class='btnseparator'></div> <div class='pGroup'> <div class='printer pButton'><span></span></div></div>")
			
			.find('.printer').click(function(){
											 var gh = p.printer(p.url,g.getLatestParams());
											 if (!gh) return false;
											 });
			}
		// add title
		if (p.title)
		{
			if(p.title.indexOf('#')>-1){
				p.title = $(p.title).val();//支持隐藏域传值add by wangcl
			}
			g.mDiv.className = 'mDiv';
			g.mDiv.innerHTML = '<div class="ftitle">'+p.title+'</div>';
			$(g.gDiv).prepend(g.mDiv);
			if (p.showTableToggleBtn)
				{
					$(g.mDiv).append('<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
					$('div.ptogtitle',g.mDiv).click
					(
					 	function ()
							{
								$(g.gDiv).toggleClass('hideBody');
								$(this).toggleClass('vsble');
							}
					);
				}
			//g.rePosDrag();
		}

		//setup cdrops
		g.cdropleft = document.createElement('span');
		g.cdropleft.className = 'cdropleft';
		g.cdropright = document.createElement('span');
		g.cdropright.className = 'cdropright';

		//add block
		g.block.className = 'gBlock';
		var gh = $(g.bDiv).height();
		var gtop = g.bDiv.offsetTop;
		$(g.block).css(
		{
			width: g.bDiv.style.width,
			height: gh,
			background: 'white',
			position: 'relative',
			marginBottom: (gh * -1),
			zIndex: 1,
			top: gtop,
			left: '0px'
		}
		);
		$(g.block).fadeTo(0,p.blockOpacity);
		
		
		
		// add column control
		if ($('th',g.hDiv).length)
		{
			
			g.nDiv.className = 'nDiv';
			g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
			$(g.nDiv).css(
			{
				marginBottom: (gh * -1),
				display: 'none',
				top: gtop
			}
			).noSelect()
			;
			
			var cn = 0;
			

			$('th div',g.hDiv).each
			(
			 	function ()
					{
						var kcol = $("th[axis='col" + cn + "']",g.hDiv)[0];
						var chk = 'checked="checked"';
						if (kcol.style.display=='none') chk = '';
						
						$('tbody',g.nDiv).append('<tr><td class="ndcol1"><input type="checkbox" '+ chk +' class="togCol" value="'+ cn +'" /></td><td class="ndcol2">'+this.innerHTML+'</td></tr>');
						cn++;
					}
			);
			
			//add checkbox
			if (p.checkbox) {
				$('tr',g.hDiv).each(
					function(){
						

						var cth = $('<td/>');
						
						var cthch = $('<input type="checkbox"/>');
						
						cthch.click(function(){
																  if(this.checked){
																	  $('tbody tr',g.bDiv).each(function(){
																										 $(this).addClass('trSelected').find('input')[0].checked=true;
																										 })
																	  }
																  else{
																	  
																	   $('tbody tr',g.bDiv).each(function(){
																										 $(this).removeClass('trSelected').find('input')[0].checked=false;
																										 })
																	  }
																  })
						
						cth.addClass("cth").attr({ width: "22" }).append(cthch);
						
						$(this).prepend(cth);
							
							})
					};
			//add autoWidth
			$('thead tr:first',g.hDiv).append('<td style="width:9999px"></td>');
			
			if ($.browser.msie&&$.browser.version<7.0)
				$('tr',g.nDiv).hover
				(
				 	function () {$(this).addClass('ndcolover');},
					function () {$(this).removeClass('ndcolover');}
				);
			
			$('td.ndcol2',g.nDiv).click
			(
			 	function ()
					{
						if ($('input:checked',g.nDiv).length<=p.minColToggle&&$(this).prev().find('input')[0].checked) return false;
						return g.toggleCol($(this).prev().find('input').val());
					}
			);
			
			$('input.togCol',g.nDiv).click
			(
			 	function ()
					{
						
						if ($('input:checked',g.nDiv).length<p.minColToggle&&this.checked==false) return false;
						$(this).parent().next().trigger('click');
						//return false;
					}
			);


			$(g.gDiv).prepend(g.nDiv);
			
			$(g.nBtn).addClass('nBtn')
			.html('<div></div>')
			.attr('title',p.colTitle)
			.click
			(
			 	function ()
				{
			 	$(g.nDiv).toggle(); return true;
				}
			);
			
			if (p.showToggleBtn) $(g.gDiv).prepend(g.nBtn);
			
		}
		
		
/*		$(g.hDiv)
		.clone(true)
		.css({'position':'absolute','top':'80px'})
		.addClass('copyhDiv')
		.find('.hDivBox').css('paddingRight','0').end()
		.find('th').each(function(){

						if($(this).attr('lock')){
									$(this).show();
									}else{
										$(this).hide();
										}

								  }).end().insertBefore(g.sDiv);
		//$(g.cDrag).clone(true).insertBefore($(g.sDiv));
		//$(g.bDiv).clone(true).insertBefore($(g.sDiv));*/
		
		
		//alert($('thead tr:first th',g.hDiv).length);
		//var top = $(g.hDiv).offset().top;
/*		$('<div class="hDiv copyhDiv" style="position:absolute;top:51px"><div class="hDivBox" style="padding-right:0"><table cellpadding="0" cellspacing="0"><thead><tr id="copyTr"><td width="22"><input type="checkbox"/></td></tr></thead></table></div></div>').insertBefore(g.sDiv);
		$('<div class="copybDiv" style="position:absolute; top:0px;"><table cellpadding="0" cellspacing="0" border="0"><tbody></tbody></table></div>').appendTo(g.bDiv);
		
		$('thead tr:first th',g.hDiv).each(function(){
													if($(this).attr('lock'))
													{
													$('#copyTr').append($(this).clone(true));
													var index = $('thead tr:first th',g.hDiv).index($(this));
												//	alert(index)
													}
													})*/
		//alert($('tbody tr th',g.hDiv).length)
/*		$('th:visible div',g.hDiv).resize(function(){
														var i = $('th div',g.hDiv).index($(this));
														var obj =  $('.copyhDiv').find('th').eq(i).find('div');
														var w = $(this).width();
														
														obj.css('width',w);
														
															 })*/
		// add date edit layer
		$(g.iDiv)
		.addClass('iDiv')
		.css({display:'none'})
		;
		$(g.bDiv).append(g.iDiv);
		
		// add flexigrid events
		$(g.bDiv)
		.hover(function(){$(g.nDiv).hide();$(g.nBtn).hide();},function(){if (g.multisel) g.multisel = false;})
		;
		$(g.gDiv)
		.hover(function(){},function(){$(g.nDiv).hide();$(g.nBtn).hide();})
		;
		
		//add document events
		$(document)
		.mousemove(function(e){g.dragMove(e)})
		.mouseup(function(e){g.dragEnd()})
		.hover(function(){},function (){g.dragEnd()})
		;
		
		//browser adjustments
		if ($.browser.msie&&$.browser.version<7.0)
		{
			$('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv',g.gDiv)
			.css({width: '100%'});
			$(g.gDiv).addClass('ie6');
			if (p.width!='auto') $(g.gDiv).addClass('ie6fullwidthbug');			
		} 
		
		g.rePosDrag();
		g.fixHeight();
		
		//make grid functions accessible
		t.p = p;
		t.grid = g;
		
		// load data
		if (p.url&&p.autoload) 
			{
			g.populate();
			}
		
		return t;		
			
	};

	var docloaded = false;

	$(document).ready(function () {docloaded = true} );

	$.fn.flexigrid = function(p) {

		return this.each( function() {
				if (!docloaded)
				{
					$(this).hide();
					var t = this;
					$(document).ready
					(
						function ()
						{
						$.addFlex(t,p);
						}
					);
				} else {
					$.addFlex(this,p);
				}
			});

	}; //end flexigrid

	$.fn.flexReload = function(p) { // function to reload grid

		return this.each( function() {
				if (this.grid&&this.p.url) this.grid.populate();
			});

	}; //end flexReload

	$.fn.flexOptions = function(p) { //function to update general options

		return this.each( function() {
				if (this.grid) $.extend(this.p,p);
			});

	}; //end flexOptions

	$.fn.flexToggleCol = function(cid,visible) { // function to reload grid

		return this.each( function() {
				if (this.grid) this.grid.toggleCol(cid,visible);
			});

	}; //end flexToggleCol

	$.fn.flexAddData = function(data) { // function to add data to grid

		return this.each( function() {
				if (this.grid) this.grid.addData(data);
			});

	};

	$.fn.noSelect = function(p) { //no select plugin by me :-)

		if (p == null) 
			prevent = true;
		else
			prevent = p;

		if (prevent) {
		
		return this.each(function ()
			{
				if ($.browser.msie||$.browser.safari) $(this).bind('selectstart',function(){return false;});
				else if ($.browser.mozilla) 
					{
						$(this).css('MozUserSelect','none');
						$('body').trigger('focus');
					}
				else if ($.browser.opera) $(this).bind('mousedown',function(){return false;});
				else $(this).attr('unselectable','on');
			});
			
		} else {

		
		return this.each(function ()
			{
				if ($.browser.msie||$.browser.safari) $(this).unbind('selectstart');
				else if ($.browser.mozilla) $(this).css('MozUserSelect','inherit');
				else if ($.browser.opera) $(this).unbind('mousedown');
				else $(this).removeAttr('unselectable','on');
			});
		
		}

	}; //end noSelect
	//get rowId
	$.fn.flexGetid = function(){
		var rowId=[];
		this.find('.trSelected').each(function(){rowId.push(this.id)})
		return rowId.toString();
	}; //end flexGetId
		
	//选中行,objId为数组
	$.fn.selectedRows = function(objId){
		var id = objId;
		var self = this;
		this.each( function() {
							if (this.grid){
								if(self.find('tr').length<1) return false;
								$.each(id,function(i,j){$('#'+j,self).click(); })
							}
		})
		
		
		return this;
	};
		
	//flexGetRowsJson 得到所有当前row的json
	$.fn.flexGetRowsJson = function(p){
		var json=[];
		this.each( function() {
				if (this.grid&&this.p.rows)
				json = this.p.rows;
			});
				
		return json;
	}//end flexGetRowsJson
	
	//flexGetRowJsonById 通过id得到行json
	$.fn.flexGetRowJsonById = function(id,p){
		var json=[],returnVal=[];
		json = this.flexGetRowsJson();			
		$.each(json,function(i,n){
							 if(n.objId===id)
							 returnVal =n;	
							 })
		return returnVal;
	}//end flexGetRowJsonById 
	
	//flexGetColIndexByName 通过name取得索引值
	$.fn.flexGetColIndexByName = function(name){
		var index;
		this.each(function(){
				   var self = this;
				   if(self.grid){
					   $(self.grid.hDiv).find('th').each(function(){
																  if($(this).attr('abbr')==name) index = $('tr th',self.grid.hDiv).index($(this)[0]);
																  });
					   }
				   })
		return index;
	}//end flexGetColIndexByName
		
	//flexGetColByName 通过name取得列,并回调	
	$.fn.flexGetColByName = function(str){
		var self = this;
		$.each(str,function(name,fn){
				var index = self.flexGetColIndexByName(name);
				fn = fn||function () {};
				self.each(function(){
							$(this.grid.bDiv).find('tr').each(function(i){
										  fn(this.id,$(this).find('td').eq(index).find('div')[0]);//返回行id及列dom
									})
				})
		
		
		
		 })
		return this;
	}//end flexGetColByName
	
	//flexAddOptionStr 增加操作字符串
	$.fn.flexAddOptionStr = function (str){

		this.each(function(){
							if(this.grid)
								$(this.grid.bDiv).find('.gridAutoWidth').each(function(){
																					   
										var id = $(this).parent('tr')[0].id;
										var div = $(this).find('div');
										$.each(str,function(i,fn){
											fn($(i),id,div);
//											$(i).unbind('click').bind('click',function(e) {fn(id);}).appendTo(div);
										})
										
								})
		})
		return this;
		}
		
	//end flexAddOptionStr
	
	//by wangsw 判断选中的是否唯一       是唯一则返回true
	$.fn.isSelectEmpty=function(){
		var objId=$(this).getSelects().split(',');
		if(objId!='')
			return false;
		return true;
	}
	//by wangsw 判断选中的是一行记录       是唯一则返回true
	$.fn.isSelectOne=function(){
		var objId=$(this).getSelects().split(',');
		if(objId.length>1)
			return false;
		return true;
	}
	//by wangsw 获取选中的一个对象的ID  例如  11
	$.fn.getSelect=function(){
		return $(this).getSelects().split(',')[0];
	}
	//by wangsw	获取选中的ID  例如 11,22,33 格式
	$.fn.getSelects=function(){
		var rowId=[];
		this.find('.trSelected').each(function(){rowId.push(this.id)})
		return rowId.toString();
	}
	//by liangxj	获取选中的ID数组  例如 11,22,33 格式
	$.fn.getSelectArray=function(){
		var rowId=[];
		this.find('.trSelected').each(function(){rowId.push(this.id)})
		return rowId;
	}
	//刷新grid
	$.fn.reload=function(){
		$(this).parent().find('table:last').flexReload();
	}
	
	//根据ID获取一行
	$.fn.getRowById=function(id){
		return $(this).parent().find('table:last').flexGetRowJsonById(id);//wangsw 2010.02.02
	}
	
	//查找上级节点 by:Anson
	$.fn.findParentTag = function(tag){
	   var self = this;
	   while(self.attr('tagName')!=tag.toUpperCase())
		 {
		 self = self.parent();
		 
		 }
	   return self;
   }
	
	//画button by liangxj
	$.fn.flexReDrawButtons = function(buttons){
		//set toolbar
		var g = $(this).attr("grid");
		if (buttons) 
		{
			g.tDiv.className = 'tDiv';
			var tDiv2 = document.createElement('div');
			tDiv2.className = 'tDiv2';
			
			for (i=0;i<buttons.length;i++)
				{
					var btn = buttons[i];
					if (!btn.separator)
					{
						var btnDiv = document.createElement('div');
						btnDiv.className = 'fbutton';
						btnDiv.innerHTML = "<div><span>"+btn.name+"</span></div>";
						if (btn.bclass) 
							$('span',btnDiv)
							.addClass(btn.bclass)
							.css({paddingLeft:20})
							;
						//btnDiv.onpress = btn.onpress;//onpress
						btnDiv.onpress = (typeof(btn.onpress)=='string'?eval(btn.onpress):btn.onpress);//by wangsw
						btnDiv.name = btn.name;
						if (btn.onpress)
						{
							$(btnDiv).click
							(	
								function () 
								{
								this.onpress(this.name,g.gDiv);
								}
							);
						}
						$(tDiv2).append(btnDiv);
						if ($.browser.msie&&$.browser.version<7.0)
						{
							$(btnDiv).hover(function(){$(this).addClass('fbOver');},function(){$(this).removeClass('fbOver');});
						}
						
					} else {
						$(tDiv2).append("<div class='btnseparator'></div>");
					}
				}
				$(g.tDiv).empty().append(tDiv2);
		}
	}
})(jQuery);