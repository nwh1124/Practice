function viewer_detail() {
	
	var viewerEl = document.querySelector('.article-detail__body');
	
	if ( viewerEl == null ) {
		return;
	}
	
	var body = viewerEl.innerHTML;
	
	var viewer = new toastui.Editor.factory({
		el : viewerEl,
		initialValue : body,
		viewer : true,
	    plugins: [toastui.Editor.plugin.codeSyntaxHighlight]
	});
}

viewer_detail();

function viewer_index() {
	
	var viewerEl = document.querySelector('.main .content');
	
	if ( viewerEl == null ) {
		return;
	}
	
	var body = viewerEl.innerHTML;
	
	var viewer = new toastui.Editor.factory({
		el : viewerEl,
		initialValue : body,
		viewer : true,
	    plugins: [toastui.Editor.plugin.codeSyntaxHighlight]
	});
}

function viewer_index_2() {
	
	var viewerEl = document.querySelector('.main .content:nth-child(2)');
	
	if ( viewerEl == null ) {
		return;
	}
	
	var body = viewerEl.innerHTML;
	
	var viewer = new toastui.Editor.factory({
		el : viewerEl,
		initialValue : body,
		viewer : true,
	    plugins: [toastui.Editor.plugin.codeSyntaxHighlight]
	});
}

viewer_index();

viewer_index_2.normalize();
viewer_index_2();
