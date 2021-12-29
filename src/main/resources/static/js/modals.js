$(document).ready(function () {


    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');

            $.get(href, function (user, status) {
                $('.myForm #idEdit').val(user.id);
                $('.myForm #usernameEdit').val(user.username);
                $('.myForm #passwordEdit').val();
                $('.myForm #rolesEdit').val(user.roles);
            });
            $('.myForm #editModal').modal();

    });

    $('.table .dBtn').on('click', function (event) {
        event.preventDefault();
        let href = $(this).attr('href');
        $.get(href, function (user, status) {
            $('.myDeleteForm #idDelete').val(user.id);
            $('.myDeleteForm #usernameDelete').val(user.username);
            $('.myDeleteForm #rolesDelete').val(user.roles);
        });
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });
});