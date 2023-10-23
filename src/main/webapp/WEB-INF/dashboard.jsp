<%@ page import="ma.youcode.gathergrid.domain.Event" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<!doctype html>
<html lang="en">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500&display=swap" rel="stylesheet">

    <title>Dashboard</title>

    <jsp:include page="../components/head.jsp"/>
    <jsp:include page="../components/my-events.jsp"/>
    <style>
        body {
            color: #566787;
            background: #f5f5f5;
            font-family: 'Varela Round', sans-serif;
            font-size: 13px;
        }
    </style>
</head>
<body>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Clicker+Script&family=Poppins&family=Roboto:ital,wght@0,100;0,300;0,500;0,900;1,300&display=swap');
    *{
        font-family: 'Poppins';
    }

    p {
        color: #b3b3b3;
        font-weight: 300;
    }

    h1, h2, h3, h4, h5, h6,
    .h1, .h2, .h3, .h4, .h5, .h6 {
        font-family: "Roboto", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
    }

    a {
        transition: 0.3s all ease;
    }
    a, a:hover {
        text-decoration: none !important;
    }

    h2 {
        font-size: 20px;
    }

    body {
        position: relative;
    }
    body:before {
        position: absolute;
        content: "";
        z-index: 1;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: rgba(0, 0, 0, 0.5);
        opacity: 0;
        visibility: hidden;
        transition: 0.3s all ease-in-out;
    }
    body.show-sidebar:before {
        opacity: 1;
        visibility: visible;
        height: 106rem;

    }

    .site-section {
        padding: 7rem 0;
    }

    aside, main {
        height: 100vh;
        min-height: 580px;
    }

    aside {
        width: 300px;
        left: 0;
        z-index: 1001;
        position: fixed;
        transform: translateX(-100%);
        background-color: #fff;
        transition: 1s transform cubic-bezier(0.23, 1, 0.32, 1);
    }
    .show-sidebar aside {
        transform: translateX(0%);
    }
    aside .toggle {
        padding-left: 30px;
        padding-top: 12px;
        position: absolute;
        right: 0;
        transform: translateX(100%);
    }
    .show-sidebar aside .toggle .burger:before, .show-sidebar aside .toggle .burger span, .show-sidebar aside .toggle .burger:after {
        background: #fff;
    }
    .show-sidebar aside {
        box-shadow: 10px 0 30px 0 rgba(0, 0, 0, 0.1);
    }
    aside .side-inner {
        padding: 30px 0;
        height: 100vh;
        overflow-y: scroll;
        -webkit-overflow-scrolling: touch;
    }
    aside .side-inner .profile {
        margin-left: auto;
        margin-right: auto;
        text-align: center;
        margin-bottom: 30px;
        padding-bottom: 30px;
        border-bottom: 1px solid #efefef;
    }
    aside .side-inner .profile img {
        width: 80px;
        margin: 0 auto 20px auto;
        border-radius: 50%;
    }
    aside .side-inner .profile .name {
        font-size: 18px;
        margin-bottom: 0;
    }
    aside .side-inner .profile .country {
        font-size: 14px;
        color: #cfcfcf;
    }
    aside .side-inner .counter {
        margin-bottom: 30px;
        padding-bottom: 30px;
        border-bottom: 1px solid #efefef;
        text-align: center;
    }
    aside .side-inner .counter div .number {
        display: block;
        font-size: 20px;
        color: #000;
    }
    aside .side-inner .counter div .number-label {
        color: #cfcfcf;
    }
    aside .side-inner .nav-menu ul, aside .side-inner .nav-menu ul li {
        padding: 0;
        margin: 0px;
        list-style: none;
    }
    aside .side-inner .nav-menu ul li a {
        display: block;
        padding-left: 30px;
        padding-right: 30px;
        padding-top: 10px;
        padding-bottom: 10px;
        color: #8b8b8b;
        position: relative;
        transition: 0.3s padding-left ease;
    }
    aside .side-inner .nav-menu ul li a:before {
        content: "";
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
        width: 0px;
        background-color: #ff7315;
        opacity: 0;
        visibility: hidden;
        transition: 0.3s opacity ease, 0.3s visibility ease, 0.3s width ease;
    }
    aside .side-inner .nav-menu ul li a:active, aside .side-inner .nav-menu ul li a:focus, aside .side-inner .nav-menu ul li a:hover {
        outline: none;
    }
    aside .side-inner .nav-menu ul li a:hover {
        background: #fcfcfc;
        color: #000;
    }
    aside .side-inner .nav-menu ul li a:hover:before {
        width: 4px;
        opacity: 1;
        visibility: visible;
    }
    aside .side-inner .nav-menu ul li.active a {
        background: #fcfcfc;
        color: #000;
    }
    aside .side-inner .nav-menu ul li.active a:before {
        opacity: 1;
        visibility: visible;
        width: 4px;
    }

    main {
        width: 100%;
    }
    main .post-entry {
        margin-bottom: 30px;
    }
    main .post-entry .custom-thumbnail {
        flex: 0 0 80px;
        margin-right: 30px;
    }
    main .post-content h3 {
        font-size: 18px;
    }
    main .post-content .post-meta {
        font-size: 15px;
        color: #ccc;
    }

    /* Burger */
    .burger {
        width: 28px;
        height: 32px;
        cursor: pointer;
        position: relative;
        z-index: 99;
        float: right;
    }

    .burger:before, .burger span, .burger:after {
        width: 100%;
        height: 2px;
        display: block;
        background: #000;
        border-radius: 2px;
        position: absolute;
        opacity: 1;
    }

    .burger:before, .burger:after {
        transition: top 0.35s cubic-bezier(0.23, 1, 0.32, 1), transform 0.35s cubic-bezier(0.23, 1, 0.32, 1), opacity 0.35s cubic-bezier(0.23, 1, 0.32, 1), background-color 1.15s cubic-bezier(0.86, 0, 0.07, 1);
        -webkit-transition: top 0.35s cubic-bezier(0.23, 1, 0.32, 1), -webkit-transform 0.35s cubic-bezier(0.23, 1, 0.32, 1), opacity 0.35s cubic-bezier(0.23, 1, 0.32, 1), background-color 1.15s cubic-bezier(0.86, 0, 0.07, 1);
        content: "";
    }

    .burger:before {
        top: 4px;
    }

    .burger span {
        top: 15px;
    }

    .burger:after {
        top: 26px;
    }

    /* Hover */
    .burger:hover:before {
        top: 7px;
    }

    .burger:hover:after {
        top: 23px;
    }

    /* Click */
    .burger.active span {
        opacity: 0;
    }

    .burger.active:before, .burger.active:after {
        top: 40%;
    }

    .burger.active:before {
        -webkit-transform: rotate(45deg);
        -moz-transform: rotate(45deg);
        filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=5); /*for IE*/
    }

    .burger.active:after {
        -webkit-transform: rotate(-45deg);
        -moz-transform: rotate(-45deg);
        filter: progid:DXImageTransform.Microsoft.BasicImage(rotation=-5); /*for IE*/
    }

    .burger:focus {
        outline: none;
    }/*# sourceMappingURL=style.css.map */
</style>


<aside class="sidebar">
    <div class="toggle">
        <a href="#" class="burger js-menu-toggle" data-toggle="collapse" data-target="#main-navbar">
            <span></span>
        </a>
    </div>
    <div class="side-inner">

        <div class="profile">
            <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Image" class="img-fluid">
            <h3 class="name">Debby Williams</h3>
            <span class="country">New York, USA</span>
        </div>

        <div class="counter d-flex justify-content-center">
            <!-- <div class="row justify-content-center"> -->
            <div class="col">
                <strong class="number">892</strong>
                <span class="number-label">Posts</span>
            </div>
            <div class="col">
                <strong class="number">22.5k</strong>
                <span class="number-label">Followers</span>
            </div>
            <div class="col">
                <strong class="number">150</strong>
                <span class="number-label">Following</span>
            </div>
            <!-- </div> -->
        </div>

        <div class="nav-menu">
            <ul>
                <li class="active"><a href="#"><span class="icon-home mr-3"></span>Feed</a></li>
                <li><a href="#"><span class="icon-search2 mr-3"></span>Explore</a></li>
                <li><a href="#"><span class="icon-notifications mr-3"></span>Notifications</a></li>
                <li><a href="#"><span class="icon-location-arrow mr-3"></span>Direct</a></li>
                <li><a href="#"><span class="icon-pie-chart mr-3"></span>Stats</a></li>
                <li><a href="/logout"><span class="icon-sign-out mr-3"></span>Sign out</a></li>
            </ul>
        </div>
    </div>

</aside>
<main>


    <jsp:include page="../components/navbar.jsp"/>

    <h3 class="text-center" style="background: white; padding: 22px;margin: 21px 0;color: #0e1c36;">Dashboard</h3>
    <div class="container">

    <h4>List of your organization</h4>
        <div class="organization">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Description</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${organizations}" var="organization">
                    <tr>
                        <td data-label="first-name">${organization.name}</td>
                        <td data-label="last-name">${organization.description}</td>
                        <td data-label="last-name">
                            <button id="deleteOrganization" class="btn btn-danger" onclick="deleteOrganization(${organization.id})">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="container-xl">
        <div class="event">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-6">
                                <h2>Manage <b>Events</b></h2>
                            </div>
                            <div class="col-sm-6">
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
                                <a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>
                            </div>
                        </div>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Location</th>
                            <th>Organization</th>
                            <th>Date</th>
                            <th>Hour</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${events}" var="event">
                                <tr>
                                    <td >${event.name}</td>
                                    <td >${event.description}</td>
                                    <td >${event.category.name}</td>
                                    <td >${event.location}</td>
                                    <td >${event.organization.name}</td>
                                    <td >${event.date}</td>
                                    <td >${event.hour}</td>
                                    <td>
                                        <a href="#editEmployeeModal"
                                           onclick="prepareForModal(${event.id},'${event.name}','${event.description}','${event.category.id}','${event.location}','${event.organization.id}','${event.date}','${event.hour}')"
                                           class="edit" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="#deleteEmployeeModal" onclick="perpareToDelete(${event.id})" class="delete" data-toggle="modal"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="clearfix">
                        <div class="hint-text">Showing <b>5</b> out of <b>25</b> entries</div>
                        <ul class="pagination">
                            <li class="page-item disabled"><a href="#">Previous</a></li>
                            <li class="page-item"><a href="#" class="page-link">1</a></li>
                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                            <li class="page-item active"><a href="#" class="page-link">3</a></li>
                            <li class="page-item"><a href="#" class="page-link">4</a></li>
                            <li class="page-item"><a href="#" class="page-link">5</a></li>
                            <li class="page-item"><a href="#" class="page-link">Next</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Add Modal HTML -->
    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="add-form" action="event" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Add Employee</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <input hidden="hidden" name="action" value="post" />
                        <div class="form-group">
                            <label >Name:</label>
                            <input  type="text" class="form-control name"  name="name" placeholder="Event Name" required>
                        </div>
                        <div class="form-group">
                            <label >Description:</label>
                            <input type="text" class="form-control description"  name="description"  placeholder="Event Description"  required>
                        </div>
                        <div class="form-group">
                            <label >Location:</label>
                            <input type="text" class="form-control location"  name="location" placeholder="Event Location" required>
                        </div>
                        <div class="form-group">
                            <label >Time:</label>
                            <input  type="time" class="form-control time" name="time" required>
                        </div>
                        <div class="form-group">
                            <label >Date:</label>
                            <input  type="date" class="form-control date" name="date" required>
                        </div>
                        <div class="form-group">
                            <label>Category:</label>
                            <select class="form-control category" name="category">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Your Beloved Organizations:</label>
                            <select class="form-control organization" name="organization">
                                <c:forEach items="${organizations}" var="organization">
                                    <option value="${organization.id}" >${organization.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Ticket Packs:</label>
                            <c:forEach items="${ticketTypes}" var="type">
                                <div class="form-group d-flex justify-content-between align-items-center gap-2 ticket-packs">
                                    <input id="${type}-checkbox" onclick="ticketCheckBoxClicked(this,'${type}-price','${type}-available-places')" type="checkbox">
                                    <input id="${type}-available-places" placeholder="${type} Available Place" name="${fn:toLowerCase(type)}-available-places" class="form-control" type="number" min="0" disabled>
                                    <input id="${type}-price" placeholder="${type} Ticket Price" name="${fn:toLowerCase(type)}-ticket-price" class="form-control" type="number" min="0" disabled>
                                </div>
                            </c:forEach>
                        <div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Edit Modal HTML -->
    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="edit-form" action="event" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Edit Employee</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <input hidden="hidden" name="action" value="put" />
                        <input hidden="hidden" name="id" class="id" />
                        <div class="form-group">
                            <label >Name:</label>
                            <input  type="text" class="form-control name"  name="name" placeholder="Event Name" required>
                        </div>
                        <div class="form-group">
                            <label >Description:</label>
                            <input type="text" class="form-control description"  name="description"  placeholder="Event Description"  required>
                        </div>
                        <div class="form-group">
                            <label >Location:</label>
                            <input type="text" class="form-control location"  name="location" placeholder="Event Location" required>
                        </div>
                        <div class="form-group">
                            <label >Time:</label>
                            <input  type="time" class="form-control time" value="15:00"   name="time" required>
                        </div>
                        <div class="form-group">
                            <label >Date:</label>
                            <input  type="date" class="form-control date"  name="date" required>
                        </div>
                        <div class="form-group">
                            <label>Category:</label>
                            <select class="form-control category" name="category">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Your Beloved Organizations:</label>
                            <select class="form-control organization" name="organization">
                                <c:forEach items="${organizations}" var="organization">
                                    <option value="${organization.id}" >${organization.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Available Places:</label>
                            <input class="form-control available-place" type="number" min="10" value="10" required name="maxTickets">
                        </div>
                        <div class="form-group">
                            <label>Ticket Packs:</label>
                            <c:forEach items="${ticketTypes}" var="type">
                            <div class="form-group d-flex justify-content-between align-items-center gap-2 ticket-packs">
                                <input id="${type}-checkbox" onclick="ticketCheckBoxClicked(this,'${type}-price')" type="checkbox">
                                <input id="${type}-price" placeholder="${type} Ticket Price" name="${fn:toLowerCase(type)}-ticket-price" class="form-control" type="number" min="0" disabled>
                            </div>
                            </c:forEach>
                            <div>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Delete Modal HTML -->
    <div id="deleteEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form class="delete-form" action="event" method="post">
                    <div class="modal-header">
                        <h4 class="modal-title">Delete Employee</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">
                        <input hidden="hidden" name="action" value="delete" />
                        <input hidden="hidden" class="id" name="id" />
                        <p>Are you sure you want to delete these Records ?</p>
                        <p class="text-warning"><small>This action cannot be undone.</small></p>
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-danger" value="Delete">
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>
    $(function() {

        'use strict';

        $('.js-menu-toggle').click(function(e) {

            var $this = $(this);



            if ( $('body').hasClass('show-sidebar') ) {
                $('body').removeClass('show-sidebar');
                $this.removeClass('active');
            } else {
                $('body').addClass('show-sidebar');
                $this.addClass('active');
            }

            e.preventDefault();

        });

// click outisde offcanvas
        $(document).mouseup(function(e) {
            var container = $(".sidebar");
            if (!container.is(e.target) && container.has(e.target).length === 0) {
                if ( $('body').hasClass('show-sidebar') ) {
                    $('body').removeClass('show-sidebar');
                    $('body').find('.js-menu-toggle').removeClass('active');
                }
            }
        });



    });
</script>


<jsp:include page="../components/js-scripts.jsp"></jsp:include>
<script>
    function deleteOrganization(id) {
        $.ajax({
            url: "${pageContext.request.contextPath}/api/organizations/" + id,
            type: 'DELETE',
            success: function(data,status) {
                console.log(status)
                if(status==="success") location.reload();
            }
        });
    }
    function prepareForModal(id,name,description,categoryId,location,organizationId,date,hour){
        $('.edit-form .id').val(id)
        $('.edit-form .name').val(name)
        $('.edit-form .description').val(description)
        $('.edit-form .location').val(location)
        $('.edit-form .date').val(date)
        document.querySelector(".edit-form .time").value=hour.substring(0,5)
        $('.edit-form .category option').prop('selected', false);
        $('.edit-form .category  option[value="' + categoryId + '"]').prop('selected', true);
        $('.edit-form .organization option').prop('selected', false);
        $('.edit-form .organization  option[value="' + organizationId + '"]').prop('selected', true);
    }
    function perpareToDelete(eventId){
        $('.delete-form .id').val(eventId)
    }
    function ticketCheckBoxClicked(checkbox,priceInputId,availablePlacesInputId){
        let checkedCheckboxes = $(' .ticket-packs input[type="checkbox"]:checked');
        if(checkedCheckboxes.length !== 0){
            if ($(checkbox).is(':checked')) {
                $('#' + priceInputId).prop('disabled', false);
                $('#' + availablePlacesInputId).prop('disabled', false);
            } else {
                $('#' + priceInputId).prop('disabled', true).val('');
                $('#' + availablePlacesInputId).prop('disabled', true).val('');
            }
        }else{
            alert("There must be at least one ticket type: ");
            checkbox.setAttribute('checked')
        }
    }
</script>

</body>
</html>

<!-- <h1><%= "Hello world" %></h1> -->
<!-- <br/> -->
<!-- <a href="login">login</a>
<a href="register">register</a>
<a href="secure">secure</a> -->
</body>
</html>
