/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Sorts;
import dao.Conexion;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Lenovo
 */
public class Dto {

    private Conexion c = new Conexion();

    public String getLastStudentId() {
        Conexion c = new Conexion();
        MongoCollection<Document> col = c.getConnection("usuarios");

        Document doc = col.find().sort(Sorts.orderBy(Sorts.descending("_id"))).first();

        if (doc == null) {
            return "0";
        } else {
            return (doc.getString("_id"));
        }
    }

    public String getLastTeacherId() {
        Conexion c = new Conexion();
        MongoCollection<Document> col = c.getConnection("profesores");

        Document doc = col.find().sort(Sorts.orderBy(Sorts.descending("_id"))).first();

        if (doc == null) {
            return "0";
        } else {
            return (doc.getString("_id"));
        }
    }

    public String getLastTesisId() {
        Conexion c = new Conexion();
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");

        Document doc = col.find().sort(Sorts.orderBy(Sorts.descending("_id"))).first();

        if (doc == null) {
            return "0";
        } else {
            System.out.println(doc.getString("_id"));
            return (doc.getString("_id"));

        }
    }    
    

    public void registrarTema(String tema, String usuario) {
        c = new Conexion();
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        //int idUsuario = 0;
        Document doc = new Document();
        doc.append("_id", String.valueOf((Integer.parseInt(getLastTesisId())+1)));
        doc.append("titulo", tema);
        doc.append("idAsesor", "0");
        doc.append("estadoP", "pendiente");
        doc.append("estadoA", "");
        doc.append("idAlumno", usuario);
        doc.append("seccion", getSeccion(usuario));
        col.insertOne(doc);
    }

    public String getSeccion(String id) {
        String sec = "";
        MongoCollection<Document> col = c.getConnection("alumnos");
        Document doc = col.find(eq("_id", id)).first();
        try {
            System.out.println(doc);
            sec = String.valueOf(doc.getString("seccion"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return sec;
    }

    public boolean existe(String user, String psw) {
        Conexion c = new Conexion();
        MongoCollection<Document> col = c.getConnection("usuarioxactividad");
        long count = col.count(and(eq("usuario", user), eq("contraseña", psw)));
        System.out.println(c);
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }

//    public void registrarStudent(String nombre, String usuario, String psw) {
//        c = new Conexion();
//        MongoCollection<Document> col = c.getConnection("alumnos");
//        //int idUsuario = 0;
//        Document doc = new Document();
//        doc.append("_id", (getLastStudentId() + 1));
//        doc.append("nombre", nombre);
//        doc.append("usuario", usuario);
//        doc.append("psw", psw);
//        col.insertOne(doc);
//    }
//
//    public void registrarTeacher(String nombre, String usuario, String psw) {
//        c = new Conexion();
//        MongoCollection<Document> col = c.getConnection("profesores");
//        //int idUsuario = 0;
//        Document doc = new Document();
//        doc.append("_id", (getLastTeacherId() + 1));
//        doc.append("nombre", nombre);
//        doc.append("usuario", usuario);
//        doc.append("psw", psw);
//        col.insertOne(doc);
//    }
    public String loginStudent(String u, String p) {
        String id;
        JSONObject o = new JSONObject();
        MongoCollection<Document> col = c.getConnection("alumnos");
        Document doc = col.find(and(eq("usuario", u), eq("psw", p))).first();
        try {
            o.put("nombre", doc.getString("nombre"));
            o.put("id", doc.getString("_id"));
            o.put("seccion", doc.getString("seccion"));
        } catch (NullPointerException e) {
            System.out.println(e);
            o.put("nombre", "error");
            o.put("id", "error");
            o.put("seccion", "error");
        }
        return o.toString();
    }

    public String loginTeacher(String u, String p) {
        String id;
        JSONObject o = new JSONObject();
        MongoCollection<Document> col = c.getConnection("profesores");
        Document doc = col.find(and(eq("usuario", u), eq("psw", p))).first();
        try {
            o.put("nombre", doc.getString("nombre"));
            o.put("id", doc.getString("_id"));
            o.put("seccion", doc.getString("seccion"));
        } catch (NullPointerException e) {
            System.out.println(e);
            o.put("nombre", "error");
            o.put("id", "error");
            o.put("seccion", "error");
        }
        return o.toString();
    }

    public String listarTesis() {
        String cadena = "";
        MongoCollection<Document> col = c.getConnection("universo_tesis");
        MongoCursor<Document> cursor = col.find().sort(Sorts.orderBy(Sorts.descending("año"))).iterator();
        Document doc;
        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                cadena += "<tr>"
                        + "<td width='20%'>" + doc.getString("titulo").toUpperCase().trim() + "</td>"
                        + "<td width='20%'>" + doc.getString("año") + "</td>"
                        + "<td width='20%'>" + doc.getString("estado").toUpperCase().trim() + "</td>"
                        + "</tr>";
            }
        } catch (Exception e) {
            System.out.println("listarTesis universo: " + e);
        } finally {
            cursor.close();
        }
        return cadena;
    }

    public String getAlumno(String id) {
        MongoCollection<Document> col = c.getConnection("alumnos");
        JSONObject o = new JSONObject();
        try {
            Document doc = col.find(eq("_id", id)).first();
            o.put("nombre", doc.getString("nombre"));
            o.put("id", doc.getString("_id"));
            o.put("seccion", doc.getString("seccion"));
        } catch (NullPointerException e) {
            o.clear();
        }
        return o.toString();
    }

    public String getAsesor(String id) {

        JSONObject o = new JSONObject();
        MongoCollection<Document> col = c.getConnection("profesores");
        try {
            Document doc = col.find(eq("_id", id)).first();
            o.put("nombre", doc.getString("nombre"));
            o.put("id", doc.getString("_id"));
            o.put("seccion", doc.getString("seccion"));
            //p = doc.getString("nombre");
        } catch (NullPointerException e) {
            o.clear();
        }
        return o.toString();
    }

    public String getNombreAlumno(String id) {
        String nombre = "";
        MongoCollection<Document> col = c.getConnection("alumnos");
        try {
            Document doc = col.find(eq("_id", id)).first();
            nombre = doc.getString("nombre");
            //p = doc.getString("nombre");
        } catch (NullPointerException e) {
            nombre = "";
        }
        return nombre;
    }

    public String listarTesisAsesor(String id) {
        String cadena = "";
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        MongoCursor<Document> cursor = col.find(and(eq("estadoA", "pendiente"), eq("idAsesor", id))).iterator();
        Document doc;
        try {

            while (cursor.hasNext()) {
                System.out.println("while asessor");
                doc = cursor.next();
                cadena += "<tr>"
                        + "<td width='20%'>" + doc.getString("titulo").toUpperCase().trim() + "</td>"
                        + "<td width='20%'>" + getNombreAlumno(doc.getString("idAlumno")) + "</td>";
                if (doc.getString("estadoA").equalsIgnoreCase("pendiente")) {
                    cadena += "<td width='20%'><button onclick='aceptarSolicitud(" + doc.getString("_id") + ")'>OK</button>"
                            + "<button onclick='rechazarSolicitud(" + doc.getString("_id") + ")'>X</button></td><tr>";
                } else {
                    cadena += "<td width='20%'></td><tr>";
                }
                //System.out.println(doc);               
            }
            //p = doc.getString("nombre");
        } catch (NullPointerException e) {
            System.out.println("tesisAsesor: " + e);
        } finally {
            cursor.close();
        }
        return cadena;
    }

    public String listarTesisProfesor(String seccion) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        MongoCursor<Document> cursor = col.find(eq("seccion", seccion)).iterator();
        Document doc;
        String cadena = "";
        try {

            while (cursor.hasNext()) {
                doc = cursor.next();
                cadena += "<tr>"
                        + "<td width='20%'>" + doc.getString("titulo").toUpperCase().trim() + "</td>"
                        + "<td width='20%'>" + getNombreAlumno(doc.getString("idAlumno")) + "</td>";
                if (doc.getString("estadoP").equalsIgnoreCase("pendiente")) {
                    cadena += "<td width='20%'><button onclick='aceptarTesis(" + doc.getString("_id") + ")'>OK</button>"
                            + "<button onclick='rechazarTesis(" + doc.getString("_id") + ")'>X</button></td><tr>";
                } else {
                    cadena += "<td width='20%'></td><tr>";
                }
                //System.out.println(doc);               
            }
            //p = doc.getString("nombre");
        } catch (NullPointerException e) {
            System.out.println("listar tesis: " + e);
        } finally {
            cursor.close();
        }
        return cadena;
    }

    public void enviarSolicitud(String idTesis, String idA) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        Document doc = col.find(eq("_id", idTesis)).first();
        col.updateOne(doc, new Document("$set", new Document("estadoA", "pendiente")));

        MongoCollection<Document> col1 = c.getConnection("tesis_alumno_asesor");
        Document doc1 = col1.find(eq("_id", idTesis)).first();
        col.updateOne(doc1, new Document("$set", new Document("idAsesor", idA)));
    }

    public String listarTesisSinAsesor(String seccion) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        MongoCursor<Document> cursor = col.find(and(eq("seccion", (seccion)), eq("idAsesor", 0))).iterator();
        Document doc;
        String cadena = "";
        try {

            while (cursor.hasNext()) {
                doc = cursor.next();
                cadena += "<tr>"
                        + "<td width='20%'>" + doc.getString("titulo").toUpperCase().trim() + "</td>"
                        + "<td width='20%'><select id='sel' style='display:inline'><option>*** Seleccione asesor ***</option></select></td>"
                        + "<td><button onclick='enviarSolicitud(" + doc.getString("_id") + ")'>Enviar</button></td></tr>";
                //System.out.println(doc);               
            }
            //p = doc.getString("nombre");
        } catch (NullPointerException e) {
            System.out.println("listar sin asesor: " + e);
        } finally {
            cursor.close();
        }
        return cadena;
    }

    public String listarAsesores() {
        String cadena = "";
        MongoCollection<Document> col = c.getConnection("profesores");
        MongoCursor<Document> cursor = col.find().iterator();
        Document doc;
        try {
            while (cursor.hasNext()) {
                doc = cursor.next();
                cadena += "<option value='" + doc.getString("_id") + "'>" + doc.getString("nombre") + "</option>";
            }
        } catch (Exception e) {
            System.out.println("listarTesisAsesores: " + e);
        } finally {
            cursor.close();
        }
        return cadena;
    }

    public void aceptarTesisProfesor(String idTesis) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        Document doc = col.find(eq("_id", idTesis)).first();

        col.updateOne(doc, new Document("$set", new Document("estadoP", "aceptado")));
    }

    public void rechazarTesisProfesor(String idTesis) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        Document doc = col.find(eq("_id", idTesis)).first();

        col.updateOne(doc, new Document("$set", new Document("estadoP", "rechazado")));
    }

    public void aceptarSolicitudAsesor(String idTesis, String idAsesor) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        Document doc = col.find(eq("_id", idTesis)).first();

        col.updateOne(doc, new Document("$set", new Document("estadoA", "aceptado")));

        MongoCollection<Document> col1 = c.getConnection("tesis_alumno_asesor");
        Document doc2 = col1.find(eq("_id", idTesis)).first();

        col.updateOne(doc2, new Document("$set", new Document("idAsesor", idAsesor)));
    }

    public void rechazarSolicitudAsesor(String idTesis) {
        MongoCollection<Document> col = c.getConnection("tesis_alumno_asesor");
        Document doc = col.find(eq("_id", idTesis)).first();
        col.updateOne(doc, new Document("$set", new Document("estadoA", "rechazado")));

        MongoCollection<Document> col1 = c.getConnection("tesis_alumno_asesor");
        Document doc2 = col1.find(eq("_id", idTesis)).first();
        col.updateOne(doc2, new Document("$set", new Document("idAsesor", 0)));
    }
}
