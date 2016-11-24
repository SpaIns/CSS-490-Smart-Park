// JavaScript source code
//General Script to show parking data
var data = [];


function obtainData(garage, floor) {
    var config = {
        apiKey: "AIzaSyDIkSj_PO9zfpbqYcoeDfton9NLPkQUdrI",
        databaseURL: "https://smartpark-aa8eb.firebaseio.com",
    };
    firebase.initializeApp(config);

    var database = firebase.database(); //get a database reference
    link = "/spaces";
    return new Promise(function (resolve, reject) {
        firebase.database().ref(link).once('value').then(function (snap) {
            snap.forEach(function (cSnap) {
                val = cSnap.val();
                if (val.garage == garage && val.floor == floor && val.isAvailable == "true") {
                    //document.writeln(val.spaceNumber +"<br>");
                    dict = { "spaceNumber": val.spaceNumber, "spaceType": val.spaceType };
                    data.push(dict);
                    //showArray();


                }
            });
            resolve(data);
        });

    });
    


}


function getArray(fireData) {
    return data;
}

function showArray() {
    document.writeln(data.length);
    //for (i = 0; i < data.length; i++) {
      //  document.writeln(data[i]["spaceNumber"]);
    //}

}

