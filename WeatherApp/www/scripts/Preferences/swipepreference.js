//This script is used to control the setting of the up/down or left/right swipe options
//in preferences

$(document).ready(function () {

    $('#updownset').click(function () {
        changeToUpDown();
        $('#textbox')[0].value = "up/down"; //use [0] as you want the raw DOM object, not the jQuery object
    });
    $('#leftrightset').click(function () {
        changeToLeftRight();
        $('#textbox')[0].value = "left/right";
    });
});

function changeToUpDown() {
    var storage = window.localStorage;
    //var value = storage.getItem(key); // Pass a key name to get its value.
    storage.setItem("swipes", "updown") // Pass a key name and its value to add or update that key.
    //updown = "true";
    //storage.removeItem(key) // Pass a key name to remove that key from storage.
    //document.write("test updown");
    //document.write(storage.getItem("swipes"));
    //document.getElementById('textbox').value = storage.getItem("swipes");
    alert("did updown");
}
function changeToLeftRight() {
    var storage = window.localStorage;
    storage.setItem("swipes", "leftright");
    //updown = "false";
    // document.write("test leftright");
    //document.write(storage.getItem("swipes"));
    //document.getElementById('textbox').value = storage.getItem("swipes");
    alert("did leftright");
}