// JavaScript source code
//General Script to show parking data
var data = [];


function obtainData(garage, floor) {
    //window.localStorage.setItem("disabled", "disabled-yes");
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

                typeMap = {"DIS":"disabled","Motorcycle":"motorcycle","Carpool":"carpool"};
                

                if (val.garage == garage && val.floor == floor && val.isAvailable == "true") {    
                    if(val.spaceType=="REG"){
               
                        dict = { "spaceNumber": val.spaceNumber, "spaceType": val.spaceType };
                        data.push(dict);
                    }
                    else if (window.localStorage.getItem(typeMap[val.spaceType]).includes("yes")) {
                        document.writeln(val.spaceType + "<br>");
                        dict = { "spaceNumber": val.spaceNumber, "spaceType": val.spaceType,"available:":val.isAvailable };
                        data.push(dict);
                    }
                 

                }
            });
            resolve(data);
        });

    });
    


}


function getArray(fireData) {
    return data;
}

