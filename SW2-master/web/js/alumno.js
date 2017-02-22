$(document).ready(function () {
    $('#sel').material_select();
    listarAsesores();
});
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

function searchEstado() {

    // Declare variables 
    var input, filter, table, tr, td, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("listaTesis");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function registrarTema() {
    var tema = $("#tema").val();
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "RegistrarTema?tema=" + tema, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
//            document.getElementById("rpta").innerHTML = "<p style='color: green'>Registro correcto</p>";
            $("#tema").val("");
        }
    };
}

function searchAño() {

    // Declare variables 
    var input, filter, table, tr, td, i;
    input = document.getElementById("search");
    filter = input.value.toUpperCase();
    table = document.getElementById("listaTesis");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
function listarTesis() {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "ListarTesis", true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            $("#listaTesis").html("");
            $("#listaTesis").html(this.response);

        }
    };
}

function listarAsesores() {
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "ListarAsesores", true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            //$("#asesor").html("");
            $("#sel").html(this.response);
        }
    };
}

function listarTesisSinAsesores() {

    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "ListarTesisSinAsesor", true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            $("#tsa").html("");
            $("#tsa").html(this.response);

        }
    };
    listarAsesores();

}

function logOut() {

    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "LogOut");
    xhttp.send();
    xhttp.onreadystatechange = function () {
        window.location.href = 'index.html';
    };
}

function enviarSolicitud(idT){
    var xhttp = new XMLHttpRequest();
    var idA=$("#sel").val();
    xhttp.open("POST", "RegistrarAsesor?idT="+idT+"&idA="+idA);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            listarTesisSinAsesores();
        }
    };
}
