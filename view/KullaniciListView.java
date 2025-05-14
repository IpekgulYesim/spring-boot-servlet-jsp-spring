package jrout.tutorial.springbootservletjsp.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jrout.tutorial.springbootservletjsp.model.Kullanici;

@WebServlet(name = "KullaniciListView", urlPatterns = {"/kullaniciListView"})
public class KullaniciListView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        List<Kullanici> KullaniciList = (List<Kullanici>)request.getAttribute("KullaniciList");

        String top = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "  font-family: arial, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #dddddd;\n" +
                "  text-align: left;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even) {\n" +
                "  background-color: #dddddd;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>HTML Table</h2>\n" +
                "\n" +
                "<table>\n" +
                "  <tr>\n" +
                "    <th>KULLANICI ID</th>\n" +
                "    <th>ADI</th>\n" +
                "    <th>SOYADI</th>\n" +
                "    <th>KULLANICI ADI</th>\n" +
                "    <th>EMAIL</th>\n" +
                "  </tr>\n";

        for (Kullanici Kullanici : KullaniciList){
            String loop = "  <tr>\n" +
                    "    <td>"+Kullanici.getidKULLANICI()+"...</td>\n" +
                    "    <td>"+Kullanici.getADI()+"</td>\n" +
                    "    <td>"+Kullanici.getSOYADI()+"</td>\n" +
                    "    <td>"+Kullanici.getKULLANICI_ADI()+"</td>\n" +
                    "    <td>"+Kullanici.getEMAIL()+"</td>\n" +
                    "  </tr>\n";
            top += loop;
        }


        String end=
                "</table>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>\n";
        String completeHtml = top + end;

        writer.println(completeHtml);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
