// JavaScript source code
//General Script to show parking data

function obtainData(garage,floor) {
    var config = {
        apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
        databaseURL: "https://smartpark-aa8eb.firebaseio.com",
    };
    firebase.initializeApp(config);

    var database = firebase.database(); //get a database reference
    link = '/parking/' + garage + '/' + floor + '/';
    begin = 0;
    end = 0;
    if (garage=='NorthGarage') {

        if (floor=='Floor1') {
            begin = 2001;
            end = 2475;
        }
        else if(floor=='Floor2'){
            begin=2117;
            end = 2228;
        }
        else if(floor=='Floor3'){
            begin= 2229;
            end = 2454;
        }
        else{
            begin = 2336;
            end = 2453;
        }
    }
    else{
        if (floor == 'Floor1') {
            begin = 126;
            end = 297;
        }
        else if (floor == 'Floor2') {
            begin = 298;
            end = 457;
        }
        else if (floor == 'Floor3') {
            begin = 460;
            end = 627;
        }
        else if (floor == 'Floor4') {
            begin= 628;
            end= 791;
        }
        else {
            begin= 792;
            end = 907;
        }
       
    }

    for (i = begin; i <= end; i++) {
        firebase.database().ref(link + i).once('value').then(function (snapshot) {

            var spaceNumber = snapshot.val().spaceNumber;
            var spaceType = snapshot.val().spaceType;
            var avail = snapshot.val().isAvailable;
            document.write("Number: " + spaceNumber + ", ");
            document.write("Type: " + spaceType + ",  ");
            document.write("Is available: " + avail + ",\n ");

        });
    }
}