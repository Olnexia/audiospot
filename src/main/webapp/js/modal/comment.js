
function showModal() {
    document.getElementById("comment").style.display = "block";
}

function closeModal() {
    document.getElementById("comment").style.display = "none";
}

window.onclick = function(event) {
    if (event.target === document.getElementById("comment")) {
        document.getElementById("comment").style.display = "none";
    }
};