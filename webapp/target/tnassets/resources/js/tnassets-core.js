var TabManager = {};

TabManager.config = function(cfg) {
	TabManager.setResizingTab(cfg.tabId);
	TabManager.setTabUrls(cfg.tabUrls);
};

TabManager.setResizingTab = function(tabId) {
	TabManager.tabId = tabId;
	
	$(window).resize(function(){
		TabManager.resize();
	});
	
	TabManager.resize();
};

TabManager.setTabUrls = function(urls) {
	TabManager.tabUrls = urls;
	
	TabManager.setIframeSrc(0);
    $(TabManager.tabId).tabs({
    	onSelect: function(title, index) {
    		TabManager.setIframeSrc(index); 
    	}
    });	    
	
}

TabManager.resize = function() {
    $(TabManager.tabId).tabs('resize', {width: $(window).width() - 20, height: $(window).height() - 80})
}

TabManager.setIframeSrc = function(index) {
//	alert(document.getElementById("if" + index).src.length);
	if (document.getElementById("if" + index).src == null || document.getElementById("if" + index).src.length == 0) {
		document.getElementById("if" + index).src = 'http://' + window.location.hostname + ':5601' + urls[index];
	}
}

