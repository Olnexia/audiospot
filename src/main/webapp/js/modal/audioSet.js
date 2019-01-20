
function showAudioSetModal() {
    document.getElementById("editAudioSet").style.display = "block";
}

function closeAudioSetModal() {
    document.getElementById("editAudioSet").style.display = "none";
}

window.onclick = function(event) {
    if (event.target === document.getElementById("editAudioSet")) {
        document.getElementById("editAudioSet").style.display = "none";
    }
};