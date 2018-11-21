<%@page pageEncoding="UTF-8"%>

	<!DOCTYPE html>
	<HTML>
		<HEAD>
			<TITLE> ZTREE DEMO </TITLE>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8">

			<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
			<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
			<script src="${APP_PATH }/script/docs.min.js"></script>
			<script type="text/javascript" src="${APP_PATH }/ztree/jquery.ztree.all-3.5.min.js"></script>

			<link rel="stylesheet" href="${APP_PATH }/ztree/zTreeStyle.css" type="text/css">

			<script LANGUAGE="JavaScript">
				var zTreeObj;
				// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
				var setting = {};
				// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）

			//	异步加载数据

var zNodes =[
		{ name:"父节点1 - 展开11111", open:true,
			children: [
			{ name:"父节点11 - 折叠",
				children: [
				{ name:"叶子节点111"},
				{ name:"叶子节点112"},
				{ name:"叶子节点113"},
				{ name:"叶子节点114"}
				]},
		{ name:"父节点12 - 折叠",
		children: [
		{ name:"叶子节点121"},
		{ name:"叶子节点122"},
		{ name:"叶子节点123"},
		{ name:"叶子节点124"}
		]},
		{ name:"父节点13 - 没有子节点", isParent:true}
		]},
		{ name:"父节点2 - 折叠",
		children: [
		{ name:"父节点21 - 展开", open:true,
		children: [
		{ name:"叶子节点211"},
		{ name:"叶子节点212"},
		{ name:"叶子节点213"},
		{ name:"叶子节点214"}
		]},
		{ name:"父节点22 - 折叠",
		children: [
		{ name:"叶子节点221"},
		{ name:"叶子节点222"},
		{ name:"叶子节点223"},
		{ name:"叶子节点224"}
		]},
		{ name:"父节点23 - 折叠",
		children: [
		{ name:"叶子节点231"},
		{ name:"叶子节点232"},
		{ name:"叶子节点233"},
		{ name:"叶子节点234"}
		]}
		]},
		{ name:"父节点3 - 没有子节点", isParent:true}

	];

		$(function () {
			zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
			</script>
		</HEAD>
		<BODY>
			<div>
				<ul id="treeDemo" class="ztree"></ul>
			</div>
		</BODY>
	</HTML>


