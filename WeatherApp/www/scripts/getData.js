// JavaScript source code
//General Script to show parking data

/*
function obtainData(garage,floor) {
    var config = {
        apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
        databaseURL: "https://smartpark-aa8eb.firebaseio.com",
    };
    firebase.initializeApp(config);

    var database = firebase.database(); //get a database reference
    link = '/spaces/';
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


        var spaceNumer = null;
        var spaceType = null;
        var avail = null;
        
        firebase.database().ref(link + i).once('value').then(function (snapshot) {
            setTimeout(function () {
                spaceNumber = snapshot.val().spaceNumber;
                spaceType = snapshot.val().spaceType;
                avail = snapshot.val().isAvailable;
                var databox = document.getElementById("databox");
                databox.value = databox.value + ("Space: " + spaceNumber + ", ");
                databox.value = databox.value + ("Type: " + spaceType + ",  ");
                databox.value = databox.value + ("Open: " + avail + "<br>" + "<br>");
                databox.value = databox.value + ('\n');
            },4000);
            

       });
    } 
}
*/
function obtainData(garage, floor) {
    var config = {
        apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
        databaseURL: "https://smartpark-aa8eb.firebaseio.com",
    };
    firebase.initializeApp(config);

    var database = firebase.database(); //get a database reference
    link = "/parking/" + garage +"/" +floor;
    x = [];
    
      firebase.database().ref(link).once('value').then(function (snap) {
         setTimeout(function () {
           
             snap.forEach(function (cSnap) {
                 setTimeout(function () {
                     val=cSnap.val()
                     if(val.isAvailable==true){
                         document.write("Space: " + val.spaceNumber + " Type: " + val.spaceType+"<br>");
                     }
                     //Next three lines are for printing all spaces instead of available ones
                     //document.write("Space: "+val.spaceNumber);
                     //document.write(" Type: "+val.spaceType);
                     //document.write(" Open: "+val.isAvailable+"<br>");

                 },100);
             });

          }, 4000);

      });

    
}

