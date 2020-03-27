package edu.escuelaing.arep;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import spark.Request;
import spark.Response;
import java.net.URL;
import spark.Route;
import static spark.Spark.*;


public class LabSquare 
{
    public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; 
    }
    public static Integer square(Integer i){
        return i*i;
    }
    
     private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                +"<head>"
                +"<link href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" rel=\"stylesheet\"" 
                +"integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">" 
                +"</head>"
                + "<body>"
                + "<h1>Calculo del cuadrado de un numero</h1>"
                + "<form action=\"/results\">"
                + "  Numero:<br>"
                + "  <br>"
                + "  <textarea class=\"form-control\" name=\"data\" placeholder=\"5\">5</textarea>"
                + "  <br>"
                + "  <button class= \"btn btn-outline-primary\" type=\"submit\">Enviar</button>"
                + "</form>"
                +" <br>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
    //                + "<p><i>If you click the \"Calculate\" button, the form-data will be sent to a page called \"/results\".</i></p>"
    private static String resultsPage(Request req, Response res) throws Exception {
        String number=(req.queryParams("data"));
         URL pagina=new URL("https://6enoovvxvg.execute-api.us-east-1.amazonaws.com/Beta?value="+number);
        String contenido="";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(pagina.openStream()))) {
			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				contenido+=inputLine;
			}
		} catch (IOException io) {
			System.err.println(io);

		}
        return contenido;
    }
}
