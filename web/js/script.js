/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//$(document).ready(function () {
//    $('#example').DataTable();
//});

$(document).ready(function () {
    $('#example').DataTable({
        "language": {
            "zeroRecords": "Không tìm thấy",
            "info": "Trang _PAGE_ trong _PAGES_",
            "infoEmpty":      "Hiển thị 0 đến 0 của 0 dòng",
            "infoFiltered": "",
            "paginate": {
                "first": "First",
                "last": "Last",
                "next": "Tiếp",
                "previous": "Lùi"
            },
            "lengthMenu": "Hiển thị _MENU_ dòng",
            "search":         "Tìm kiếm: ",
        }
    });
});
