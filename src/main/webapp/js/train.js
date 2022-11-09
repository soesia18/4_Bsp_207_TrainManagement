function loadAllTrains() {
    fetch('./api/trains')
        .then(result => {
            result.json().then(data => {
                console.log(data);
                let div = '';
                data.forEach(train => {
                    div += '<hr><span style="margin-right: 100px">' + train.id + '</span>' + '<span>' + train.type + '</span>';
                })
                document.getElementById('allTrains').innerHTML = div;
            })
        })
}

function loadListOfStation() {
    let id = document.getElementById('listStationsID').value;
    if (id === '') {
        return;
    }
    fetch('./api/trains/' + id)
        .then(result => {
            if (result.status !== 200) {
                alert(result.statusText);
                return;
            }
            result.json().then(data => {
                console.log(data);
                let div = '';
                data.stations.forEach(station => {
                    div += '<hr><span>' + station + '</span>';
                })
                document.getElementById('stations').innerHTML = div;
            })
        })
}

function addTrain() {
    let data = {
        id: document.getElementById("addTrainID").value,
        type: document.getElementById("addTrainType").value
    };

    console.log(data);

    fetch('./api/trains', {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
        .then(result => {
            if (result.status === 201){
                alert(result.headers.get("Location"));
                return;
            }
            alert(result.statusText);
        })
}

function addStation () {
    let id = document.getElementById("addStationID").value;

    fetch('./api/trains/' + id, {
        method: "PUT",
        headers: {"Content-Type":"application/json"},
        body: document.getElementById("addStationType").value
    })
        .then(result => {
            alert(result.statusText);
        })
}