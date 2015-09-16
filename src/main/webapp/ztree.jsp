<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
	<head>
		<!-- css -->
		<link rel="stylesheet" href="/plugin/resources/plugins/zTree_v3/css/zTreeStyle/zTreeStyle.css">
		<!-- js -->
		<script src="/plugin/resources/plugins/zTree_v3/js/jquery.ztree.core-3.5.min.js"></script>
	</head>
	<body>
		<div class="fluid-container">
			<div class="row">
				<div class="col-md-12">
					<h1>ztree</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<ul class="ztree" id="tree"></ul>
				</div>
			</div>
		</div>
		<!--
	      属性的排列顺序
	      
	      class
	      id, name
	      data-*
	      src, for, type, href
	      title, alt
	      aria-*, role
	    -->
		<script>
			$(function() {
				var setting = {
					data:{
						key:{
							name:"name", 
							title:"name"
						},
						simpleData:{
							enable:true,
							idKey:"id",
							pIdKey:"parentId",
							rootPId:0
						}
					},
					async: {
						enable: true,
						url:"/treeController/tree",
						autoParam:["id"]
					},
					 callback: {
						    onAsyncSuccess: onAsyncSuccess
					 }
				};
				 function onAsyncSuccess(event, treeId, treeNode, msg) {
					 console.log(event);
					 treeNode.isParent = true;
				}
				$.fn.zTree.init($("#tree"), setting);
			});
		</script>
	</body>
</html>