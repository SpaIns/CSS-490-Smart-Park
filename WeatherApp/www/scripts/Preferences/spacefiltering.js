//Javascript here works to enable/disable showing certain spots (carpool, motorcycle, disabled etc)
$(document).ready(function () {
    $(".space-filter").click(function () {
        var id = new String($(this).attr('id'));
        var storage = window.localStorage;
        //This below part is kinda clumsy, there's likely a better way to do this
        if (id.startsWith("disabled")) {
            storage.setItem("disabled", id); //will be set "disabled-yes" or "disabled-no"
            $('#textbox')[0].value = id;
        }
        else if (id.startsWith("carpool")) {
            storage.setItem("carpool", id); //will be set "carpool-yes" or "carpool-no"
            $('#textbox')[0].value = id;
        }
        else {
            alert("Preference setting not supported - go tell the devs to fix");
        }
    })
});