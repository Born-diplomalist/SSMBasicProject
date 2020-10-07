<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
    <!-- Begin of toolbar -->
    <div id="wu-toolbar">
        <div class="wu-toolbar-button">
            <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="openAdd()" plain="true">添加</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEdit()" plain="true">修改</a>
            <a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="remove()" plain="true">删除</a>
        </div>
        <div class="wu-toolbar-search">
            <label>菜单名称：</label><input class="wu-text" style="width:100px">
            <a href="#" class="easyui-linkbutton" iconCls="icon-search">开始检索</a>
        </div>
    </div>
    <!-- End of toolbar -->
    <table id="data-datagrid" class="easyui-datagrid" toolbar="#wu-toolbar-2"></table>
</div>
<!-- Begin of easyui-dialog -->
<div id="add-dialog" class="easyui-dialog" data-options="closed:true,iconCls:'icon-save'" style="width:400px; padding:10px;">
	<form id="add-form" method="post">
        <table>
            <tr>
                <td width="60" align="right">菜单名称:</td>
                <!--class上加easyui-validatebox，设置属性data-option="required:true,missingMessage:'请输入菜单名称' 可让该项变为必选项-->
                <!-- <td><input type="text" name="name" class="wu-text easyui-validatebox"  data-option="required:true,missingMessage:'请输入菜单名称'"/></td>-->
                <td><input type="text" name="name" class="wu-text easyui-validatebox" data-options="required:true, missingMessage:'请填写菜单名称'" /></td>
            </tr>
            <tr>
                <td align="right">上级菜单:</td>
                <td>
             	    <select name="parentId" class="easyui-combobox" panelHeight="auto" style="width:268px">
		                <option value="0">选择用户组</option>
		                <option value="1">黄钻</option>
		                <option value="2">红钻</option>
		                <option value="3">蓝钻</option>
		            </select>
                </td>
            </tr>
            <tr>
                <td align="right">菜单URL:</td>
                <td><input type="text" name="url" class="wu-text" /></td>
            </tr>
            <tr>
                <td valign="top" align="right">菜单图标:</td>
                <td><input type="text" name="icon" class="wu-text  easyui-validatebox" data-options="required:true,missingMessage:'请输入菜单图标'"/></td>
            </tr>
        </table>
    </form>
</div>
<!-- End of easyui-dialog -->

<script type="text/javascript">
	/**
	* Name 添加记录
	*/
	function add(){
		//对表单进行验证
		var validate =$('#add-form').form("validate");
		if(!validate){
			$.message.alert("消息提醒","请检查你输入的数据！","warning");
			return;
		}
		//将表单中的输入框按照name=value的格式拼接起来
		//var data=$("#add-form").serialize();
		var name=$("input[name='name']").val();
		var parentId=$("input[name='parentId']").val();
		var url=$("input[name='url']").val();
		var icon=$("input[name='icon']").val();
		//提交表单
			//data:data,
		$.ajax({
			url:'../admin/menu/add',
			dataType:'json',
			data:{name:name,parentId:parentId,url:url,icon:icon},
			type:'post',
			success:function(data){
				if(data.type='success'){
					$.messager.alert('信息提示','提交成功！','info');
					$('#add-dialog').dialog('close');
				}
				else{
					$.messager.alert('信息提示',data.msg,'warning');
				}
			}
		});
	}
	
	/**
	* Name 修改记录
	*/
	function edit(){
		$('#add-form').form('submit', {
			url:'',
			success:function(data){
				if(data){
					$.messager.alert('信息提示','提交成功！','info');
					$('#add-dialog').dialog('close');
				}
				else
				{
					$.messager.alert('信息提示','提交失败！','info');
				}
			}
		});
	}
	
	/**
	* Name 删除记录
	*/
	function remove(){
		$.messager.confirm('信息提示','确定要删除该记录？', function(result){
			if(result){
				var items = $('#data-datagrid').datagrid('getSelections');
				var ids = [];
				$(items).each(function(){
					ids.push(this.productid);	
				});
				//alert(ids);return;
				$.ajax({
					url:'',
					data:'',
					success:function(data){
						if(data){
							$.messager.alert('信息提示','删除成功！','info');		
						}
						else
						{
							$.messager.alert('信息提示','删除失败！','info');		
						}
					}	
				});
			}	
		});
	}
	
	/**
	* Name 打开添加窗口
	*/
	function openAdd(){
		$('#add-form').form('clear');
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "添加信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: add
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}
	
	/**
	* Name 打开修改窗口
	*/
	function openEdit(){
		$('#add-form').form('clear');
		var item = $('#data-datagrid').datagrid('getSelected');
		//alert(item.productid);return;
		$.ajax({
			url:'',
			data:'',
			success:function(data){
				if(data){
					$('#add-dialog').dialog('close');	
				}
				else{
					//绑定值
					$('#add-form').form('load', data)
				}
			}	
		});
		$('#add-dialog').dialog({
			closed: false,
			modal:true,
            title: "修改信息",
            buttons: [{
                text: '确定',
                iconCls: 'icon-ok',
                handler: edit
            }, {
                text: '取消',
                iconCls: 'icon-cancel',
                handler: function () {
                    $('#add-dialog').dialog('close');                    
                }
            }]
        });
	}	
	
	/**
	* Name 分页过滤器
	*/
	function pagerFilter(data){            
		if (typeof data.length == 'number' && typeof data.splice == 'function'){// is array                
			data = {                   
				total: data.length,                   
				rows: data               
			}            
		}        
		var dg = $(this);         
		var opts = dg.datagrid('options');          
		var pager = dg.datagrid('getPager');          
		pager.pagination({                
			onSelectPage:function(pageNum, pageSize){                 
				opts.pageNumber = pageNum;                   
				opts.pageSize = pageSize;                
				pager.pagination('refresh',{pageNumber:pageNum,pageSize:pageSize});                  
				dg.datagrid('loadData',data);                
			}          
		});           
		if (!data.originalRows){               
			data.originalRows = (data.rows);       
		}         
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);          
		var end = start + parseInt(opts.pageSize);        
		data.rows = (data.originalRows.slice(start, end));         
		return data;       
	}
	
	/**
	* 载入数据
	*/
	$('#data-datagrid').datagrid({
		url:'../admin/menu/list',
		loadFilter:pagerFilter,
		//是否显示行号
		rownumbers:true,
		//只能单选
		singleSelect:true,
		pageSize:20,           
		pagination:true,
		multiSort:true,
		//列是否要填充
		fitColumns:true,
		fit:true,
		columns:[[
			{ checkbox:true},
			{ field:'name',title:'菜单名称',width:100,sortable:true},
			{ field:'url',title:'菜单URL',width:180,sortable:true},
			{ field:'icon',title:'图标icon',width:100}
		]]
	});
</script>