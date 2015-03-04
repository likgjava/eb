//下拉树，在input下出现一个下拉层，url为树的xml,model为是否单选
//by Anson
//chents@gpcsoft.com
jQuery.fn.listTree = function(url,model,callback){
	var self = this;　
	var sele = (model)? (model=='checkbox' ? 'checkbox' : 'radio') :'radio';
	var cln = self.attr('class');
	var left = self.offset().left;
	var top = self.offset().top;
	var height = self.height();
	var width = self.width();
	var cssProp = { position: 'absolute'};
	callback = callback || function() {};
	cssProp.top =  top + height + 4;
	cssProp.left = left;
	cssProp.float = 'left';
	cssProp.width = width;
	cssProp.border = '1px solid #cccccc';
	cssProp.zIndex = '999999';
	cssProp.background='#FFFFFF';
	if($('#selectDivList_tree')){$('#selectDivList_tree').remove();}
	
	var confirmButton = '';
	if(sele=='checkbox'){
		confirmButton = '<button id="closeBtn_tree">确定</button>'
	}
	$('body').append('<div id="selectDivList_tree" >'+
//			  '<button id="openAllItem_tree" class="btn" title="打开所有子节点">打开节点</button>'+
//			  '<button id="closeAllItem_tree" class="btn" title="关闭所有子节点">关闭节点</button>'+
 			  '<div id="editTree_tree" class="xTree"></div>'+
 			  confirmButton +
			  '</div>');
	$('#selectDivList_tree').css(cssProp).hide();
	
	cln=new dhtmlXTreeObject("editTree_tree","100%","85%",0);
	//皮肤路径
	cln.setImagePath($("#initPath").val()+"/view/resource/plug-in/dhtmlxTree/imgs/");
	//是否有多选
	if(sele=='checkbox'){
		cln.enableCheckBoxes(1);
		//是否一并选择子节点
		cln.enableThreeStateCheckboxes(true);
		
		$('#closeBtn_tree').click(function(){
									 var check = cln.getAllChecked().split(',');
									 var checkVal =  $.map(check,function(i){return cln.getItemText(i);})
									 self.val(checkVal);
									 $('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});
									 callback();
		})
		
	}
	//不允许拖动
	cln.enableDragAndDrop(0);
	
	if(sele=='radio'){
		//单击触发
		cln.setOnClickHandler(function(id){
								var treeVal = cln.getItemText(id);
								self.val(treeVal);
								callback(id,treeVal);
								$('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});
		});
		//双击触发
		cln.setOnDblClickHandler(function(id){
								var treeVal = cln.getItemText(id);
								self.val(treeVal); 
								callback(id,treeVal);
								$('#selectDivList_tree').slideUp('fast',function(){$('#selectDivList_tree').remove();});
		});
	}else{
		$('#closeBtn_tree').click(function(){
			var selectedId = cln.getSelectedItemId();
			var val = cln.getItemText(selectedId); 
			$('#selectDivList_tree').slideUp('fast',function(){
				$('#selectDivList_tree').remove();
			});
			callback(selectedId,val);
		})
	}
	
	cln.loadXML(url);
	cln.setXMLAutoLoading(url);
	
//	$('#openAllItem_tree').click(function(){cln.openAllItems(0);})
//	$('#closeAllItem_tree').click(function(){cln.closeAllItems(0);})
	
	$('#selectDivList_tree').slideDown('fast');
	
	}
	
	