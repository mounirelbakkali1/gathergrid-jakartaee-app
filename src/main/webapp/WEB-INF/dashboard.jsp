<%@ page import="ma.youcode.gathergrid.domain.Event" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Dashboard</title>
    <jsp:include page="../components/head.jsp"/>
    <jsp:include page="../components/table.jsp"/>
    <jsp:include page="../components/my-events.jsp"/>
</head>
<body>

    <h1>you are in dashboard</h1>
    <h4>List of your organization</h4>
    <div class="container">
        <div class="organization">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Action</th>
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
                                <h2>Manage <b>Employees</b></h2>
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
    <!-- Edit Modal HTML -->
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
                            <label>Available Places:</label>
                            <input class="form-control available-place" type="number" min="10" value="10" required name="maxTickets">
                        </div>
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
                        <input name="id" class="id" />
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
                        <input class="id" name="id" />
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

    <div>
        <div class="d-flex justify-content-between">
            <h4>List of your events</h4>
            <a href="event">Create Event</a>
        </div>
        <div class="container">
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Location</th>
                    <th>Organization</th>
                    <th>Date</th>
                    <th>Hour</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${events}" var="events">
                    <tr>
                        <td data-label="first-name">${events.name}</td>
                        <td data-label="last-name">${events.description}</td>
                        <td data-label="last-name">${events.category.name}</td>
                        <td data-label="first-name">${events.location}</td>
                        <td data-label="last-name">${events.organization.name}</td>
                        <td data-label="first-name">${events.date}</td>
                        <td data-label="last-name">${events.hour}</td>
                        <td data-label="last-name">
                            <form action="event" method="post">
                                <input type="text" hidden="hidden" name="action" readonly value="edit">
                                <input type="text" hidden="hidden" name="id" readonly value="${events.id}">
                                <button class="btn btn-warning" >Edit</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
<jsp:include page="../components/js-scripts.jsp"></jsp:include>
<script>
    function deleteOrganization(id) {
        $.ajax({
            url: "http://localhost:8080/GatherGrid-1.0-SNAPSHOT/api/organizations/" + id,
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
</script>
</html>
