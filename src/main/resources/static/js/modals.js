$(document).ready(function () {

    let roleList = ["ROLE_ADMIN", "ROLE_USER"];

    getAllUsers();

    function getAllUsers() {

        $.getJSON("http://localhost:8080/api/users", function (data) {

            let rows = '';

            $.each(data, function (key, user) {
                rows += createRows(user);
            });
            $('#tData').append(rows);

        });
    }

    $(document).on('click', '.edit-btn', function () {
        const user_id = $(this).attr('data-id');
        $.ajax({
            url: '/api/users/' + user_id,
            method: 'GET',
            dataType: 'json',
            success: function (user) {
                $('#idEdit').val(user.id);
                $('#usernameEdit').val(user.username);
                $('#passwordEdit').val(user.password);
                $('#rolesEdit').empty();
                roleList.map(role => {
                    $('#rolesEdit').append('<option name="' + role + '" >' +
                        role + '</option>')
                })
            }
        });
    });

    $('#editRef').on('click', (e) => {
        e.preventDefault();
        let userEditId = $('#idEdit').val();
        let editUser = {
            id: $("input[name='id']").val(),
            username: $("input[name='username']").val(),
            password: $("input[name='password']").val(),
            roles: $('select#rolesEdit').val()
        }

        $.ajax({
            url: '/api/users/' + userEditId,
            method: 'PUT',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(editUser),
            success: (data) => {
                let newRow = createRows(data);
                $('#tData').find('#' + userEditId).replaceWith(newRow);
                $('#editModal').modal('hide');
                $('#admin-tab').tab('show');
            },
        });
    });

    $(document).on('click', '.del-btn', function () {
        let user_id = $(this).attr('data-id');

        $.ajax({
            url: '/api/users/' + user_id,
            method: 'GET',
            dataType: 'json',
            success: function (user) {
                $('#idDelete').empty().val(user.id);
                $('#usernameDelete').empty().val(user.username);
                $('#rolesDelete').empty();
                roleList.map(role => {
                    $('#rolesDelete').append('<option>' + role + '</option>');
                })
            }
        });
    });

    $('#delRef').on('click', (e) => {
        e.preventDefault();
        let userId = $('#idDelete').val();

        $.ajax({
            url: '/api/users/' + userId,
            method: 'DELETE',
            success: function () {
                $('#' + userId).remove();
                $('#deleteModal').modal('hide');
                $('#admin-tab').tab('show');
            }
        });
    });

    function createRows(user) {
        let temp = "";
        temp += '<tr id=' + user.id + '>';
        temp += "<td>" + user.id + "</td>";
        temp += "<td>" + user.username + "</td>";
        temp += "<td>" + user.roles.map(role => role.role) + "</td>";
        temp +=
            '<td>' +
            '<input id="eBtn" ' +
            'value="Edit" ' +
            'type="button" ' +
            'class="btn-primary btn edit-btn" ' +
            'data-toggle="modal" ' +
            'data-target="#editModal" ' +
            'data-id="' + user.id + '">' +
            '</td>';
        temp += '<td>' +
            '<input id="dBtn" ' +
            'value="Delete" ' +
            'type="button" ' +
            'class="btn btn-danger del-btn" ' +
            'data-toggle="modal" ' +
            'data-target="#deleteModal" ' +
            'data-id=" ' + user.id + ' ">' +
            '</td>';

        temp += '</tr>';

        return temp;
    }

    $('.newUser').on('click', () => {

        $('#username').empty().val('')
        $('#password').empty().val('')
        $('#roles').empty().val('')
        roleList.map(role => {
            $('#roles').append('<option role="' + role + '">' +
                role + '</option>')
        });

    })

    $("#addNewUserButton").on('click', () => {
        let newUser = {
            username: $('#username').val(),
            password: $('#password').val(),
            roles: $('select#roles').val()
        }

        $.ajax({
            url: '/api/users',
            method: 'POST',
            dataType: 'json',
            data: JSON.stringify(newUser),
            contentType: 'application/json; charset=utf-8',

            success: function () {

                $('#tData').empty();
                getAllUsers();
                $('#admin-tab').tab('show');
            }
        });

    })
})