<#setting classic_compatible=true>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>导入数据</title>
        <link rel="stylesheet" href="../js/jqwidgets5/styles/jqx.base.css" />
        <link rel="stylesheet" href="../js/jqwidgets5/styles/jqx.bootstrap.css">
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/jquery-3.3.1.min.js"></script>
        <script src="../js/loserStarJsUtils.js"></script>
        <script src="../js/jqwidgets5/jqx-all.js"></script>
        <script src="../js/ajaxUtils.js" type="text/javascript"></script>
        <!-- 上传组件begin -->
        <link rel="stylesheet" href="../js/webuploader-0.1.5/webuploader.css">
        <script src="../js/webuploader-0.1.5/webuploader.html5only.min.js"></script>
        <script src="../js/FileUploadWindow_WebUploader.js" type="text/javascript"></script>
          <!-- 上传组件end -->
        
        <script src="../ftl/oreData/js/purchased.js" type="text/javascript"></script>
        <script src="../ftl/oreData/js/purchasedEvent.js" type="text/javascript"></script>
    </head>

    <body>

     编号：<input type="text" id="no" name="no" value="111">
     类型：
    <select id="type">
        <option value="1">已购</option>
        <option value="2">未购</option>
    </select>
    备注：
    <textarea name="remarks" id="remarks" cols="30" rows="1"></textarea><br/>
    <button onclick="purchasedEvent.openUploadFileWindow()" class="btn btn-primary">读取excel</button>
    <button onclick="purchasedEvent.save()" class="btn btn-success">保存</button>
    <button onclick="window.open('statisticsList.do')" class="btn btn-success">编号列表</button>
     <!-- 上传文件 -->
     <div id="uploadWindow" style="display:none">
 <!--             <div><span>上传附件</span></div>
             <div>
                 <div id="jqxFileUpload" style="margin-top:5px;"></div>
             </div> -->
             <div>
             	<span>上传附件</span>
             </div>
             <div id="uploader" class="wu-example">
                 <div>待上传文件列表</div>
 			    <div id="thelist" class="uploader-list"></div>
 			    <div class="btns">
 			        <div id="picker">选择文件</div>
 			        <button id="ctlBtn" class="btn btn-success">开始上传</button>
 			    </div>
 			</div>
     </div>
     
     <div id="grid"></div>
    </body>

    </html>