<div align="center" style="width:'100%'; ">
	<div id="tabPanel" class="easyui-tabs" style="width:700px;height:250px" data-options="tabWidth:280, tabHeight: 35">
		<div title="<img align='center' src='resources/images/connection.png'/>&nbsp;&nbsp;Production Instance Cost" style="padding:5px">
			<iframe id="if0" height="99%" width="99%"></iframe>
		</div>
		<div title="<img align='center' src='resources/images/connection.png'/>&nbsp;&nbsp;Dev Instance Cost" style="padding:5px">
			<iframe id="if1" height="99%" width="99%"></iframe>
		</div>
		<div title="<img align='center' src='resources/images/connection.png'/>&nbsp;&nbsp;Production State" style="padding:5px">
			<iframe id="if2" height="99%" width="99%"></iframe>
		</div>
		<div title="<img align='center' src='resources/images/connection.png'/>&nbsp;&nbsp;Dev Instance State" style="padding:5px">
			<iframe id="if3" height="99%" width="99%"></iframe>
		</div>
	</div>
</div>

  
<script type="text/javascript">
	var urls = [
		"/#/dashboard/Instance-Production?embed&_g=(refreshInterval:(display:'60%20seconds',pause:!f,section:1,value:60000),time:(from:now-30d,mode:quick,to:now))",
		"/#/dashboard/Instance-Dev?embed&_g=(refreshInterval:(display:'60%20seconds',pause:!f,section:1,value:60000),time:(from:now-30d,mode:quick,to:now))",
		"/#/dashboard/Instance-Production-State?embed&_g=(refreshInterval:(display:'60%20seconds',pause:!f,section:1,value:60000),time:(from:now-30d,mode:quick,to:now))",
		"/#/dashboard/Instance-Dev-State?embed&_g=(refreshInterval:(display:'60%20seconds',pause:!f,section:1,value:60000),time:(from:now-30d,mode:quick,to:now))"
	]; 
	 
	$(function() {
		TabManager.config({
			tabId : '#tabPanel',
			tabUrls : urls
		});
	});
</script>
