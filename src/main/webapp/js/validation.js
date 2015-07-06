$(document).ready(function () {
    $('#submitForm').validate({
        rules: {
            startingDate: {
                required: true,
            },
            sendImages: {
                required: true
            }
        },
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element);
            } else {
                error.insertAfter(element);
            }
        }
    });

});
$("#submitForm").submit(function () {
    if ($(this).valid()) {
        alert("Thank you!");
    }
});
$("#submitForm").each(function () {
    this.reset();
});