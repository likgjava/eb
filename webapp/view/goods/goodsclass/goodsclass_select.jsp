<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/view/srplatform/common/init.jsp"%>
<script>
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
    var purcategoryName=purCategoryTreeForm.tree.getItemText(id);
    $("#goodsClassId").val(id);
    $("#goodsClassName").val(purcategoryName);
    $('#epsDialogClose').click();    
}
/*****处理树 结束*****/

/**/
$(document).ready(function(){
    purCategoryTreeForm.initGoodsClassTree();//加载树
    /*关闭品目列表*/
    $('#closeBtn').click(function(){
        $('#epsDialogClose').click();       
    });
    // 清空
    $('#cleanBtn').click(function(){
    	$("#goodsClassId").val("");
    	$("#goodsClassName").val("");
        $('#epsDialogClose').click();       
    });
}
)
</script>
<div>
	 <button class="largeBtn" id="cleanBtn" type="button"><span>清空</span></button> 
	 <button class="largeBtn" id="closeBtn" type="button"><span>关闭</span></button> 
    <div>
         <div id="purCategoryTreeForm" class="treeContentDiv"></div>
    </div>
</div>
