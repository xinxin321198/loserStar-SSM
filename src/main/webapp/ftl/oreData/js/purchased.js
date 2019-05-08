var fileWindow1;

$(document).ready(function () {

});


function initGrid(dataList){
    var source =
    {
        localdata: dataList,
        datatype: "array",
        updaterow: function (rowid, rowdata, commit) {
            commit(true);
        },
        datafields:
        [
            { name: 'id', type: 'string' },
            { name: 'date', type: 'string' },
            { name: 'unit', type: 'string' },
            { name: 'wet_count', type: 'number' },
            { name: 'water_count', type: 'number' },
            { name: 'fe', type: 'number' },
            { name: 's', type: 'number' },
            { name: 'p', type: 'number' },
            { name: 'pb', type: 'number' },
            { name: 'as_', type: 'number' },
            { name: 'sio2', type: 'number' },
            { name: 'tio2', type: 'number' },
            { name: 'zn', type: 'number' },
            { name: 'cu', type: 'number' },
            { name: 'sn', type: 'number' },
            { name: 'k20', type: 'number' },
            { name: 'na20', type: 'number' },
            { name: 'price', type: 'number' },
            { name: 'remarks', type: 'string' }
        ],
        id:"id"
    };

    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#grid").jqxGrid(
        {
            width: '98%',
            height: (document.documentElement.clientHeight-40),
            source: dataAdapter,
            editable: true,
            enabletooltips: true,
            selectionmode: 'checkbox',
            columns: [
              { text: '日期', columntype: 'textbox', datafield: 'date', width: 120 },
              { text: '单位', columntype: 'textbox', datafield: 'unit', width: 120 },
              { text: '湿量', columntype: 'textbox', datafield: 'wet_count', width: 120 },
              { text: '水量', columntype: 'textbox', datafield: 'water_count', width: 120 },
              { text: 'fe', columntype: 'textbox', datafield: 'fe', width: 120 },
              { text: 's', columntype: 'textbox', datafield: 's', width: 120 },
              { text: 'p', columntype: 'textbox', datafield: 'p', width: 120 },
              { text: 'pb', columntype: 'textbox', datafield: 'pb', width: 120 },
              { text: 'as', columntype: 'textbox', datafield: 'as_', width: 120 },
              { text: 'sio2', columntype: 'textbox', datafield: 'sio2', width: 120 },
              { text: 'tio2', columntype: 'textbox', datafield: 'tio2', width: 120 },
              { text: 'zn', columntype: 'textbox', datafield: 'zn', width: 120 },
              { text: 'cu', columntype: 'textbox', datafield: 'cu', width: 120 },
              { text: 'sn', columntype: 'textbox', datafield: 'sn', width: 120 },
              { text: 'k20', columntype: 'textbox', datafield: 'k20', width: 120 },
              { text: 'na20', columntype: 'textbox', datafield: 'na20', width: 120 },
              { text: 'price', columntype: 'textbox', datafield: 'price', width: 120 },
              { text: '备注', columntype: 'textbox', datafield: 'remarks', width: 120 }
            ]
        });
}