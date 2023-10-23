<!---favicon-->
<link rel="icon" href="./files/favicon.png" sizes="16x16">


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600;800&display=swap');

    *{
        font-family: 'Poppins', sans-serif;
    }
    .event .table-responsive {
        margin: 30px 0;
    }
    .event .table-wrapper {
        background: #fff;
        color: #0e1c36;
        padding: 20px 25px;
        border-radius: 3px;
        min-width: 1000px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
    .event .table-title {
        padding-bottom: 15px;
        padding: 16px 30px;
        min-width: 100%;
        margin: -20px -25px 10px;
        border-radius: 3px 3px 0 0;
    }
    .event .table-title h2 {
        margin: 5px 0 0;
        font-size: 24px;
    }
    .event .table-title .btn-group {
        float: right;
    }
    .event .table-title .btn {
        color: #fff;
        float: right;
        font-size: 13px;
        border: none;
        min-width: 50px;
        border-radius: 2px;
        border: none;
        outline: none !important;
        margin-left: 10px;
    }
    .event .table-title .btn i {
        float: left;
        font-size: 21px;
        margin-right: 5px;
    }
    .event .table-title .btn span {
        float: left;
        margin-top: 2px;
    }
    .event table.table tr th, table.table tr td {
        border-color: #e9e9e9;
        padding: 12px 15px;
        vertical-align: middle;
    }
    .event table.table tr th:first-child {
        width: 60px;
    }
    .event table.table tr th:last-child {
        width: 100px;
    }
    .event table.table-striped tbody tr:nth-of-type(odd) {
        background-color: #fcfcfc;
    }
    .event table.table-striped.table-hover tbody tr:hover {
        background: #f5f5f5;
    }
    .event table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }
    .event table.table td:last-child i {
        opacity: 0.9;
        font-size: 22px;
        margin: 0 5px;
    }
    .event table.table td a {
        font-weight: bold;
        color: #566787;
        display: inline-block;
        text-decoration: none;
        outline: none !important;
    }
    .event table.table td a:hover {
        color: #2196F3;
    }
    .event table.table td a.edit {
        color: #FFC107;
    }
    .event table.table td a.delete {
        color: #F44336;
    }
    .event table.table td i {
        font-size: 19px;
    }
    .event table.table .avatar {
        border-radius: 50%;
        vertical-align: middle;
        margin-right: 10px;
    }
    .event .pagination {
        float: right;
        margin: 0 0 5px;
    }
    .event .pagination li a {
        border: none;
        font-size: 13px;
        min-width: 30px;
        min-height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 2px !important;
        text-align: center;
        padding: 0 6px;
    }
    .event .pagination li a:hover {
        color: #666;
    }
    .event .pagination li.active a, .pagination li.active a.page-link {
        background: #03A9F4;
    }
    .event .pagination li.active a:hover {
        background: #0397d6;
    }
    .event .pagination li.disabled i {
        color: #ccc;
    }
    .event .pagination li i {
        font-size: 16px;
        padding-top: 6px
    }
    .event .hint-text {
        float: left;
        margin-top: 10px;
        font-size: 13px;
    }

    /* Modal styles */
    .modal .modal-dialog {
        max-width: 400px;
    }
    .modal .modal-header, .modal .modal-body, .modal .modal-footer {
        padding: 20px 30px;
    }
    .modal .modal-content {
        border-radius: 3px;
        font-size: 14px;
    }
    .modal .modal-footer {
        background: #ecf0f1;
        border-radius: 0 0 3px 3px;
    }
    .modal .modal-title {
        display: inline-block;
    }
    .modal .form-control {
        border-radius: 2px;
        box-shadow: none;
        border-color: #dddddd;
    }
    .modal textarea.form-control {
        resize: vertical;
    }
    .modal .btn {
        border-radius: 2px;
        min-width: 100px;
    }
    .modal form label {
        font-weight: normal;
    }

    .be-comment-block {
        margin-bottom: 10px !important;
        border: 1px solid #edeff2;
        border-radius: 2px;
        padding: 10px 20px;
        border:1px solid #ffffff;
    }

    .comments-title {
        font-size: 16px;
        color: #262626;
        margin-bottom: 15px;
        font-family: 'Conv_helveticaneuecyr-bold';
    }

    .be-img-comment {
        width: 45px;
        height: 45px;
        float: left;
        margin-bottom: 15px;
    }

    .be-ava-comment {
        width: 45px;
        height: 45px;
        border-radius: 50%;
    }

    .be-comment-content {
        margin-left: 80px;
    }

    .be-comment-content span {
        display: inline-block;
        width: 49%;
        margin-bottom: 15px;
    }

    .be-comment-name {
        font-size: 13px;
        font-family: 'Conv_helveticaneuecyr-bold';
    }

    .be-comment-content a {
        color: #383b43;
    }

    .be-comment-content span {
        display: inline-block;
        width: 49%;
        margin-bottom: 15px;
    }

    .be-comment-time {
        text-align: right;
    }

    .be-comment-time {
        font-size: 11px;
        color: #b4b7c1;
    }

    .be-comment-text {
        font-size: 13px;
        line-height: 18px;
        color: #7a8192;
        display: block;
        background: #f6f6f7;
        border: 1px solid #edeff2;
        padding: 5px 20px 20px 10px;
    }
</style>