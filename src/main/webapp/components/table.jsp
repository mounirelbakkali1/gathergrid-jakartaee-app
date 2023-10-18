body {
    background: #f8f8f8;
}
/* ============================================================ Responsive Table via Data Label ============================================================ */
table {
    border: 0;
    width: 100%;
    margin: 0;
    padding: 0;
    border-collapse: collapse;
    border-spacing: 0;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .3);
}
table thead {
    background: #f0f0f0;
    height: 60px !important;
}
table thead tr th:first-child {
    padding-left: 45px;
}
table thead tr th {
    text-transform: uppercase;
    line-height: 60px !important;
    text-align: left;
    font-size: 11px;
    padding-top: 0px !important;
    padding-bottom: 0px !important;
}
table tbody {
    background: #fff;
}
table tbody tr {
    border-top: 1px solid #e5e5e5;
    height: 60px;
}
table tbody tr td:first-child {
    padding-left: 45px;
}
table tbody tr td {
    height: 60px;
    line-height: 60px !important;
    text-align: left;
    padding: 0 10px;
    font-size: 14px;
}
table tbody tr td i {
    margin-right: 8px;
}
@media screen and (max-width: 800px) {
    table {
        border: 1px solid transparent;
        box-shadow: none;
    }
    table thead {
        display: none;
    }
    table tbody tr {
        border-bottom: 45px solid #f8f8f8;
    }
    table tbody tr td:first-child {
        padding-left: 10px;
    }
    table tbody tr td:before {
        content: attr(data-label);
        float: left;
        font-size: 10px;
        text-transform: uppercase;
        font-weight: bold;
    }
    table tbody tr td {
        display: block;
        text-align: right;
        font-size: 14px;
        padding: 0px 10px !important;
        box-shadow: 0 1px 1px rgba(0, 0, 0, .3);
    }
}
