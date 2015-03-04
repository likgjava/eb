// JavaScript Document


/**
 * 版权信息： 政采软件版权所有，未经珠海市政采软件技术有限公司的书面同意，不得拷贝，传播本文件及其相关信息的任何内容。 项目： 政府采购超源业务平台
 * JDK版本： 1.5 版本： 3.0 说明： 本文件包含***的代码。 修订历史： 序号： 1 日期： Jan 27, 2010 修改人： liujf
 * 修改说明：（为什么修改，修改了什么）
 */


var purCategoryTreeForm={};
purCategoryTreeForm.tree;
purCategoryTreeForm.isSort=0;

/*****处理树 开始*****/
function treeTab(){
    treeHe();
    $(window).wresize(treeHe);
    var h =$('#contentThis').height();
    $('.treeOutside').jqResize('.treeResize',function(w){if(parseInt(w)<240){$('.treeOutside').width(240);} $('.treeOutside').height(h)});
    function treeHe(){$('.treeOutside,.treeRight,.treeResize').height($('#contentThis').height());}
}

/*初始化树*/
purCategoryTreeForm.initGoodsClassTree=function(){
    //dhtmlXTreeObject(htmlObject, width, height, rootId)//rootId 虚拟根节点，在界面上不会显示，一般取值0
    purCategoryTreeForm.tree=new dhtmlXTreeObject('purCategoryTreeForm',"100%","100%",0);
    purCategoryTreeForm.tree.setImagePath($('#initPath').val()+'/view/resource/plug-in/dhtmlxTree/imgs/');
    //purCategoryTreeForm.tree.enableCheckBoxes(1);
    purCategoryTreeForm.tree.enableDragAndDrop(1);
    purCategoryTreeForm.tree.enableThreeStateCheckboxes(true);
    purCategoryTreeForm.tree.setOnClickHandler(purCategoryTreeForm.nodeClick);
    purCategoryTreeForm.tree.setXMLAutoLoading($('#initPath').val()+'/GoodsClassController.do?method=getTree&action=listById');//点 + 号触发展开事件
    purCategoryTreeForm.tree.loadXML($('#initPath').val()+'/GoodsClassController.do?method=getTree&action=listTop&isOpen=0',function(){
        //初始化树之后的回调函数
    });
}

/*点击节点事件*/
purCategoryTreeForm.nodeClick=function(id){
    var val=purCategoryTreeForm.tree.getItemText(id);
    //写入列表,过滤已经添加的品目
    $('#purCategoryNameSelect').not(":contains("+val+")").append("<option value='"+id+"'>"+val+"</option>");
}

/*初始化下拉控件的选项*/
purCategoryTreeForm.initValueOfSelectOpinion=function(){
    $("#goodsTypeItem option").each(function(){
		$("#purCategoryNameSelect").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
	});
}
/*****处理树 结束*****/

/*****业务操作处理 开始*****/

/*返回选中的采购品目列表*/
purCategoryTreeForm.returnPurCategorysList=function(){
    $("#goodsTypeItem option").remove();
    $("#purCategoryNameSelect option").each(function(){
        $('#goodsTypeItem').not(":contains("+$(this).text()+")").append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
    });
    $('#epsDialogClose').click();
}

/*删除选中的条目。*/
purCategoryTreeForm.removeOptions=function(){
	var namesSelect=document.getElementById("purCategoryNameSelect");
	for(var i=0;i<namesSelect.options.length;i++){
	  if(namesSelect.options[i].selected){
	   namesSelect.remove(i);
	   i=i-1;
	  }
	}
}

/*****业务操作处理 结束*****/

/**/
$(document).ready(function(){
    $('#purCategoryForm').validate();//表单验证
    purCategoryTreeForm.initGoodsClassTree();//加载树

    $("#returnPurCategoryBtn").click(function(){
        purCategoryTreeForm.returnPurCategorysList();
    });

    /*移除单或多个选项*/
    $("#removeFromList").click(function(){
    	purCategoryTreeForm.removeOptions();
    });

    /*关闭品目列表*/
    $('#cancelItemBtn').click(function(){
        $('#epsDialogClose').click();       
    });

    /*清空列表*/
    $("#removeAllFromList").click(function(){                              
        if(confirm('是否清空当前商品分类列表？')){
            $("#purCategoryNameSelect option").remove();
        }
    });

    purCategoryTreeForm.initValueOfSelectOpinion();
}
)

