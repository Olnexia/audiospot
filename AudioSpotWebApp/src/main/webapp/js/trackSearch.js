let timeout = null;

function searchTracks() {
    clearTimeout(timeout);
    timeout = setTimeout(sendRequestForTracks, 500);
}

function sendRequestForTracks() {
    let searchRequest = document.getElementById('audioSearch').value;

    $.post( "controller",
        {
            command:"showTracks",
            request:searchRequest
        },
        function(data) {
            let parser = new DOMParser();
            let xmlDoc = parser.parseFromString(data, "text/html");
            let table = xmlDoc.getElementById("tracks");
            if (table == null) {
                document.getElementById("trackMessage").innerHTML
                    = '<h2>There are no tracks matching to search request</h2>';
                document.getElementById("tracks").innerHTML = '';
            } else {
                document.getElementById("tracks").innerHTML = table.innerHTML;
                document.getElementById("trackMessage").innerHTML = xmlDoc.getElementById("trackMessage").innerHTML;
            }
    })
}