function showAlbumTrackModal() {
    document.getElementById("addTrackToAlbum").style.display = "block";
}

function closeAlbumTrackModal() {
    document.getElementById("addTrackToAlbum").style.display = "none";
}

window.onclick = function (event) {
    if (event.target === document.getElementById("addTrackToAlbum")) {
        document.getElementById("addTrackToAlbum").style.display = "none";
    }
};