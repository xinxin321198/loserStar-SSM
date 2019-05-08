<#setting classic_compatible=true>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>选择一个编号查询统计数据</title>
        <link rel="stylesheet" href="../css/bootstrap.min.css">
        <script src="../js/jquery-3.3.1.min.js"></script>
        <script src="../js/loserStarJsUtils.js"></script>
        <script src="../js/ajaxUtils.js" type="text/javascript"></script>
        <script src="../ftl/oreData/js/statistics.js" type="text/javascript"></script>
    </head>

    <body>
        编号：
       <table class="table table-condensed table-striped table-bordered">
           <tr>
               <td>编号</td>
               <td>备注</td>
               <td>类型</td>
               <td>操作</td>
           </tr>
        <#list list as l>
        <tr>
            <td>${l.no}</td>
            <td>${l.remarks}</td>
            <td><#if l.type=="1">已购<#else>未购</#if></td>
            <td><button onclick="window.open('statistics.od?no=${l.no}')">统计</button></td>
        </tr>
        </#list>
       </table>
    </body>

    </html>