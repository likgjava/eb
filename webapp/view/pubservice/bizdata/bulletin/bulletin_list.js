var page = 1;
var count = 20;
var totalPage = 2;
function loadBulletinList(){
	
	
	$("#provinceNoticeTable tbody").empty();
	$.getJSON($('#initPath').val()+'/BulletinController.do?method=list&bullType='+$("#bullType").val()+'&auditStatus=02',{"queryColumns":"objId,bullTitle,relDate,createTime","page":page,"rp":count},function(json){
		totalPage = parseInt(json.totalPageNum);
		page = parseInt(json.page);
		$.each(json.rows,function(i,n){
			if(i == 0) {
				$("#bullTypeCN").html(n.bullTitleCN)
			}
			var row = $("#provinceNoticeTable tfoot tr").clone(true).appendTo("#provinceNoticeTable tbody");
			$(row).find("td").eq(0).empty().append('<a href="javascript:void(0);" class="" id="'+n.objId+'" title="'+n.bullTitle+'" >'+n.bullTitle+"</a>").end().find("a").click(function() {
				$("#contentMain").loadPage($('#initPath').val()+"/BulletinController.do?method=toShowView&objId="+$(this).attr("id"));
			});
			var dateString = n.relDate;
			if(undefined != dateString && "" != dateString) {
				dateString = dateString.substring(0,10)
			}
			$(row).find("td:last").html(dateString);
		})
		
		if(json.rows.length == 0) {
			$("#provinceNoticeTable tbody").append("<tr><td class='center' colSpan='2'>没有检索到公告信息!</td></tr>");
		}
		var flipPage = "<div class='dataTables_paginate paging_full_numbers'>";
		if(page > 1 ) {
			flipPage += '<span class="next paginate_button"  name="flipPage_1" count="'+count+'" title="首页">首页</span>'; 
		}
		
		if(page > 1 && page - 1 > 0) {
			flipPage += '<span class="previous paginate_button"  name="flipPage_' + (page -1 ) + '" count="'+count+'" title="上一页">上一页</span>'; 
		}
		
		if(page >= 1 && page + 1 <= totalPage) {
			flipPage += '<span class="next paginate_button" name="flipPage_' + (page + 1) + '" count="'+count+'" title="下一页">下一页</span>'; 
		}
		if(totalPage > 1 && page < totalPage){
			flipPage += '<span class="last paginate_button" name="flipPage_' + totalPage + '" count="'+count+'" title="尾页">尾页</span>';
		}
		
		flipPage += '<span class="totalNum">页次：'+page+'/'+totalPage+'';
		flipPage += '每页'+count+'条</span>'; 
		flipPage += '</div>'
		$("div[id=flipPage]").empty().append(flipPage).find("[name^=flipPage_]").click(function() {
			page = $(this).attr("name").replace("flipPage_","");
			page = parseInt(page);
			loadBulletinList();
		});
	})
}
$(document).ready(function(){
	
	
	// 加载采购公告
	loadBulletinList();
	
	// 为左边栏设定选中样式
	$('.subnav>li').removeClass('selected');
	$('.subnav>li[id='+$('#bullType').val()+']').addClass('selected');
	
	$("#startTime").epsDatepicker();
    $("#endTime").epsDatepicker();
    
    // 搜索
    $('#searchNewsContentMainBtn').click(function() {
    	$("#conBody").loadPage($('#initPath').val()+'/BulletinController.do?method=searchBulletin&searchKey='+strIgnore($('#searchKeyContentMain').val())+'&pageSize=20&bullType='+$('#bullType').val()+'&startTime='+$('#startTime').val()+'&endTime='+$('#endTime').val()+'&bulletinSrc='+$('#bulletinSrc').val()+'&searchResultForm=search_bulletin_body_result.ftl');
    });
});

