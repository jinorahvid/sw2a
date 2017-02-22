/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function search() {

    // Declare variables 
    var input, filter, table, tr, td, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("listaTesis");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function listarTesisA() {
    var xhttp = new XMLHttpRequest();
    
    xhttp.open("POST", "ListarTesisAsesor", true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            $("#listaTesisA").html("");
            $("#listaTesisA").html(this.response);
        }
    };
}

function listarTesisP() {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "ListarTesisProfesor", true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            $("#listaTesisP").html("");
            $("#listaTesisP").html(this.response);
        }
    };
}

function aceptarTesis(id) {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "AceptarTesis?idT=" + id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            listarTesisP();
        }
    };
}

function rechazarTesis(id) {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "RechazarTesis?idT=" + id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            listarTesisP();
        }
    };
}

function aceptarSolicitud(id) {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "AceptarSolicitud?idT=" + id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            listarTesisA();
        }
    };
}

function rechazarSolicitud(id) {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "RechazarSolicitud?idT=" + id, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            listarTesisA();
        }
    };
}

function logOut() {

    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "LogOut");
    xhttp.send();
    xhttp.onreadystatechange = function () {
        window.location.href = 'index.html';
    };
}

