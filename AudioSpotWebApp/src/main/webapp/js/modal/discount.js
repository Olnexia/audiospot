function showDiscountModal() {
    document.getElementById("discount").style.display = "block";
}

function closeDiscountModal() {
    document.getElementById("discount").style.display = "none";
}

window.onclick = function (event) {
    if (event.target === document.getElementById("discount")) {
        document.getElementById("discount").style.display = "none";
    }
};