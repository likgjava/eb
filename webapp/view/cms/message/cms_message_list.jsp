<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/cms/resbase/default/css/index_frame_out_css.css"/>
<script type="text/javascript">
var page = 1;
// 获得留言列表
function getMessageList(){
$('#messageList').empty();
$.getJSON($('#initPath').val()+'/CmsMessage.do?method=list&rp=5&page='+page,{},function(json){
	var pageFlip = "";
	pageFlip += '<span class="next paginate_button" id="firstMessagePage" page="1" title="首页">首页</span>'
	for(var curPage = page,i=-3;i<0;i++){
	if(!(page + i <1))
		pageFlip += '<span class="next paginate_button" page="'+(curPage+i)+'" title="'+(curPage+i)+'">'+(curPage+i)+'</span> ';
	}
		pageFlip += '<span class="next " page="'+page+'" title="'+page+'">'+(page)+'</span> ';
	for(var curPage = page,i=1;i<4;i++){
		if(!(page + i >json.totalPageNum))
		pageFlip += '<span class="next paginate_button" page="'+(curPage+i)+'" title="'+(curPage+i)+'">'+(curPage+i)+'</span> ';
	}
	pageFlip += '<span class="last paginate_button" id="lastMessagePage" page="'+json.totalPageNum+'" title="尾页">尾页</span>';
	pageFlip += '<span class="totalNum">当前第'+json.page+'页'+'  共'+json.totalPageNum+'页 </span>';
	$('#messagePaginate').empty().append(pageFlip).find('span').click(function(){
		if(!$(this).attr('page')){return false;}
		page = parseInt($(this).attr('page'));
		getMessageList();
	});
	$('#currentPage').html();
	$.each(json.rows,function(i,n){
		if(json.curUserId)
		var _tr = $('#messageTemp').find('tr:eq(0)').clone(true);
		var _name = n.userName;
		if(!n.userName){
			_name = "IP："+n.userIp;
		}
		$(_tr).attr('mid',n.objId);
		$(_tr).find('[name=name]').html(_name);
		$(_tr).find('[name=time]').html(n.createTime.substring(0,16));
		$(_tr).find('[name=content]').html(n.content);
		$(_tr).find('[name=reMessageBtn]').attr('id',n.objId);

		// 如果该留言是登录用户发表，并且是当前用户的话，则可以删除该留言
		if(json.curUserId && n.user && n.user.objId == json.curUserId){
			$(_tr).find('[name^=deleteMessageBtn]').attr('id',n.objId);
		}else{
			$(_tr).find('[name^=deleteMessageBtn]').remove();
		}
		if(undefined != n.user && n.user.avatar){
			$(_tr).find('img').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+n.user.avatar);
		}
		$.each(n.reMessage,function(j,s){
			var _rtr = $('#messageTemp').find('tr:eq(1)').clone(true);
			var _rname = n.userName;
			if(!s.userName){
				_rname = "IP："+s.userIp;
			}
			$(_rtr).attr('mid',s.objId);
			$(_rtr).attr('pid',n.objId);
			
			// 如果该回复是登录用户发表，并且是当前用户的话，则可以删除该回复
			if(json.curUserId && s.user && s.user.objId == json.curUserId){
				$(_rtr).find('[name^=deleteMessageBtn]').attr('id',s.objId);
			}else{
				$(_rtr).find('[name^=deleteMessageBtn]').remove();
			}
			$(_rtr).find('[name=name]').html(_rname);
			$(_rtr).find('[name=time]').html(s.createTime.substring(0,16));
			$(_rtr).find('[name=content]').html(s.content);
			if(undefined != s.user && s.user.avatar){
				$(_rtr).find('img').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+s.user.avatar)
			}
			$(_tr).find('[name=reMessage]').append(_rtr);
		})
		
		$('#messageList').append(_tr);
	})
});
}
$(document).ready(function(){

	// 获得留言列表
	getMessageList()
	// 点击回复
	$('[name=reMessageBtn]').click(function(){
		if($(this).hasClass("cntClick"))return false;
		var mid = $(this).attr('id');
		var _box = $('#messageTemp').find('tr:eq(2)').clone(true);
		$(_box).find('[name$=ReMessageBtn]').attr('pid',mid);
		$('#messageList').find('[mid='+mid+']').find('[name=reMessage]').append(_box);
		$(this).addClass("cntClick");
		return false;
	})
	
	// 点击提交回复
	$('[name=saveReMessageBtn]').click(function(){
		var pid = $(this).attr('pid');
		// 按钮的当前行
		var curRow = $(this).parent().parent().parent();
		// 当前留言行
		$('#messageList').find('[mid='+pid+']').find('[name=reMessageBtn]').removeClass('cntClick');

		var message = {};
		message.content = $(curRow).find('textarea').text();
		message.parent = {};
		message.parent.objId = pid;
		$.getJSON($('#initPath').val()+'/CmsMessage.do?method=saveMessage',{'messageJson':JSON.stringify(message)},function(json){
			if(json.success){
				var obj = json.message;
				var _rme = $('#messageTemp').find('tr:eq(1)').clone(true);
				var _rname = obj.userName;
				if(!obj.userName){
					_rname = ""+obj.userIp;
				}
				$(_rme).attr('mid',obj.objId);
				$(_rme).attr('pid',obj.parent.objId);
				$(_rme).find('[name=name]').html(_rname);
				$(_rme).find('[name=time]').html(obj.createTime);
				$(_rme).find('[name=content]').html(obj.content);
				if(json.avatarId){
					$(_rme).find('img').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+json.avatarId);
				}
				$(curRow).before(_rme);
				$(curRow).remove();
			}
		})
		return false;
	})
	
	// 点击取消回复
	$('[name=cancleReMessageBtn]').click(function(){
		var pid = $(this).attr('pid');
		// 按钮的当前行
		var curRow = $(this).parent().parent().parent();
		// 当前留言行
		$('#messageList').find('[mid='+pid+']').find('[name=reMessageBtn]').removeClass('cntClick');

		$(curRow).remove();
		return false;
	})
	
	// 点击删除留言或回复(先移除)
	$('[name^=deleteMessageBtn]').click(function(){
		if(window.confirm("确定要删除该条"+($(this).attr('name').replace('deleteMessageBtn_','')=='r'?'回复':'留言')+"吗？")){
			$.getJSON($('#initPath').val()+'/CmsMessage.do?method=remove',{'objId':$(this).attr('id')},function(json){
			})
			$('#messageList').find('[mid='+$(this).attr('id')+']').remove();
		}
		return false;
	})

	$('#addMessageBtn').toggle(function(){
		$('#addMessageBtn').html("关闭留言框")
		$('#messageForm').show();
	},function(){
		$('#addMessageBtn').html("我要留言");
		$('#messageForm').hide();
	}); 

	// 提交留言
	$('[name=saveMessageBtn]').click(function(){
		var message = {};
		message.content = $(this).parent().parent().find('textarea').text();
		$(this).parent().parent().find('textarea').text("");
		$.getJSON($('#initPath').val()+'/CmsMessage.do?method=saveMessage',{'messageJson':JSON.stringify(message)},function(json){
			if(json.success){
				var obj = json.message;
				var _me = $('#messageTemp').find('tr:eq(0)').clone(true);
				var _name = obj.userName;
				if(!obj.userName){
					_name = ""+obj.userIp;
				}
				$(_me).attr('mid',obj.objId);
				$(_me).find('[name=name]').html(_name);
				$(_me).find('[name=time]').html(obj.createTime);
				$(_me).find('[name=content]').html(obj.content);
				$(_me).find('[name=reMessageBtn]').attr('id',obj.objId);
				$(_me).find('[name^=deleteMessageBtn]').attr('id',obj.objId);
				if(json.avatarId){
					$(_me).find('img').attr('src',$('#initPath').val()+'/AttachmentController.do?method=showImg&objId='+json.avatarId);
				}
				$('#messageList').find('tr:eq(0)').before(_me);
				$('#addMessageBtn').click();
			}
		})
		return false;
	});
	
	// 取消留言
	$('[name=cancleMessageBtn]').click(function(){
		$('#addMessageBtn').click();
	});

})
</script>
<div class="formLayout">
<table  id="messageTemp" class="hidden">
<tr>
<td style="width:100px;vertical-align:top"><img src="cms/fileico/defautAvator.png" width="100px"/><br/></td>
<td style="vertical-align:top"><div><span name="name">郭永荣</span>&nbsp;&nbsp;<span name="time">2010-11-30 09:30:50</span><span style="float:right"><a href="javascript:void(0)" name="deleteMessageBtn_m">删除</a>&nbsp;<a href="javascript:void(0)" name="reMessageBtn">回复</a></span></div><hr/><div name="content">发表内容</div><table name="reMessage"></table></td>
</tr>
<tr>
<td style="width:50px;vertical-align:top"><img src="cms/fileico/defautAvator.png" width="50px"/><br/></td>
<td style="vertical-align:top"><div><span name="name">郭永荣</span>&nbsp;&nbsp;<span name="time">2010-11-30 09:30:50</span></div><span style="float:right"><a href="javascript:void(0)" name="deleteMessageBtn_r">删除</a></span><hr/><div name="content">回复内容</div></td>
</tr>

<tr>
<td colSpan="2">
<textarea style="width:80%;height:50px"></textarea>
<div class="conOperation">
	<button type="button" name="saveReMessageBtn"><span>提交</span></button>
	<button type="button" name="cancleReMessageBtn" tabindex="19"><span>取消</span></button>
</div>
</td>
</tr>
</table>

<div style="float:right" ><a href="javascript:void(0)" id="addMessageBtn">我要留言</a></div>
<br/>
<br/>
<div id ="messageForm" class="hidden">
	<textarea style="width:80%;height:80px"></textarea>
	<div class="conOperation">
		<button type="button" name="saveMessageBtn"><span>提交</span></button>
		<button type="button" name="cancleMessageBtn" tabindex="19"><span>取消</span></button>
	</div>
</div>
<table id="messageList">
</table>
<div class="dataTables_paginate paging_full_numbers" id="messagePaginate">
</div>
</div>
