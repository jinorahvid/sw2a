
import dto.Dto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lenovo
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Dto d = new Dto();
        //System.out.println(d.getAsesor(1));
        //System.out.println(d.listarTesis());
        //System.out.println(d.listarTesisProfesor("902"));
        //System.out.println(d.listarTesisAsesor(1));
        //d.enviarSolicitud(1, 1);
        System.out.println(d.getSeccion("1"));
        //System.out.println(d.listarTesisSinAsesor("901"));
        //System.out.println(d.getLastTesisId());
        //System.out.println(d.loginTeacher("hquintana", "hquintana"));
    }

}
