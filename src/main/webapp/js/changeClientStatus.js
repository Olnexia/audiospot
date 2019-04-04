function changeClientStatus(id){
    $.post("controller",
        {
           command:"changeClientStatus",
           userId:id
        },function (data) {
            let parser = new DOMParser();
            let xmlDoc = parser.parseFromString(data, "text/html");
            let table = xmlDoc.getElementById("clients");
                document.getElementById("clients").innerHTML = table.innerHTML;
        });
}