/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function ocultarPreloader() {
    $("#log_std").hide();
    $("#reg_std").hide();
    $("#log_t").hide();
    $("#reg_teach").hide();
}

function showStudent() {
    $("#icon_teacher").css('background-color', 'transparent');
    $("#icon_student").css('background-color', '#ff8c00');
    $("#teacher").hide();
    $("#reg_student").hide();
    $("#student").show(1000);
}

function showTeacher() {
    $("#icon_student").css('background-color', 'transparent');
    $("#icon_teacher").css('background-color', '#ff8c00');
    $("#student").hide();
    $("#reg_teacher").hide();
    $("#teacher").show(1000);
}

function showRegStudent() {
    $("#icon_teacher").css('background-color', 'transparent');
    $("#icon_student").css('background-color', '#ff8c00');
    $("#student").hide();
    $("#reg_student").show(1000);
}

function showRegTeacher() {
    $("#icon_student").css('background-color', 'transparent');
    $("#icon_teacher").css('background-color', '#ff8c00');
    $("#teacher").hide();
    $("#reg_teacher").show(1000);
}

function loginStd() {
    $("#btn_login_std").hide();
    $("#btn_a_std").hide();
    $("#log_std").show();
    var user = $("#user_std").val();
    var psw = $("#psw_std").val();
    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "Login?user=" + user + "&psw=" + psw, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.responseText !== "error") {
            window.location.href = 'inicio_alumno.html';
            //document.getElementById("rpta").innerHTML = "<p style='color: green'>Login correcto</p>";
        } else if (this.responseText === "error") {
            alert("Usuario no registrado o datos incorrectos");
            window.location.href = 'index.html';
            
            return false;
        }
    };
}

function loginTeacher() {
    $("#btn_login_teach").hide();
    $("#btn_a_teach").hide();
    $("#log_t").show();
    var user = $("#user_prof").val();
    var psw = $("#psw_prof").val();

    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "LoginTeacher?user=" + user + "&psw=" + psw, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.responseText !== "error") {
            //document.getElementById("rpta").innerHTML = "<p style='color: green'>Login correcto</p>";
            sessionStorage.setItem('data', JSON.stringify(this.responseText));
            console.log(sessionStorage.getItem("data"));
            window.location.href = 'profesor.html';
        } else if (this.responseText === "error") {
            
            alert("Usuario no registrado o datos incorrectos");
            window.location.href = 'index.html';            
            return false;
        }
    };
}

function registrarStudent() {
    $("#btn_reg_std").hide();
    $("#btn_a_reg_std").hide();
    $("#reg_std").show();
    var user = $("#reg_user_std").val();
    var psw = $("#reg_psw_std").val();
    var nombre = $("#reg_name_std").val();

    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "Registrar?user=" + user + "&psw=" + psw + "&nombre=" + nombre, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("rpta").innerHTML = "<p style='color: green'>Registro correcto</p>";
            $("#user").val("");
            $("#psw").val("");
            $("#name").val("");
        }
    };
}

function registrarTeacher() {
    $("#btn_reg_teacher").hide();
    $("#btn_a_reg_teach").hide();
    $("#reg_teach").show();
    var user = $("#reg_user_teacher").val();
    var psw = $("#reg_psw_teacher").val();
    var nombre = $("#reg_name_teacher").val();

    var xhttp = new XMLHttpRequest();

    xhttp.open("POST", "RegistrarTeacher?user=" + user + "&psw=" + psw + "&nombre=" + nombre, true);
    xhttp.send();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("rpta").innerHTML = "<p style='color: green'>Registro correcto</p>";
            $("#user").val("");
            $("#psw").val("");
            $("#name").val("");
        }
    };
}