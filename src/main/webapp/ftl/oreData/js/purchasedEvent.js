var purchasedEvent = {}

purchasedEvent.openUploadFileWindow = function(){
    var url = "readerExcel.do";
    //初始化文件上传窗口
    fileWindow1  = FileUploadWindow_WebUploader.initFileUploadWindow("uploadWindow","#thelist","#picker","#ctlBtn",url,
    function(data){
        alert("上传成功!")
        initGrid(data.Sheet1);
    },
    function(){
        alert("上传失败!");
    });
    
    fileWindow1.open();
}

//保存数据
purchasedEvent.save = function(){
    var rowindexes = $('#grid').jqxGrid('getselectedrowindexes');
    var list = new Array();
    if(rowindexes.length<=0){
        alert("请至少选择一行数据");
        return;
    }
    for(var i=0;i<rowindexes.length;i++){
        var row =  $('#grid').jqxGrid('getrowdata', rowindexes[i]);
        delete row.boundindex;
        delete row.visibleindex;
        delete row.uniqueid;
        delete row.uid;
        list.push(row);
    }
    
    var saveUrl = "save.do";
    var saveData = {};
    saveData.no = $("#no").val();
    saveData.type = $("#type").val();
    saveData.remarks = $("#remarks").val();
    saveData.list = list;
    if(saveData.no==undefined||saveData.no==null||saveData.no==""){
    	alert("没有填入编号no");
    	return ;
    }
    postJson(saveUrl,saveData,"json",function(data){
    	alert(data.msg);
    });
}