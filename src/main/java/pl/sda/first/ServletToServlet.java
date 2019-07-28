package pl.sda.first;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

@WebServlet(urlPatterns = {"/servletToServlet", "/servletsCommunication"})
public class ServletToServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(ServletToServlet.class.getSimpleName());
    private static final String SERVER_ADDRESS
            = "http://localhost:8080/servlets/randomJson";
    private static final String PARAMETER = "giveRandomNumber";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String giveRandomNumber = request.getParameter(PARAMETER);

        if(giveRandomNumber.contains("true")){
//            JSONObject jsonObject = new JSONObject(getRandomNumber());
            writer.println("<h1>" + getRandomNumber() + "</h1>");
        } else {
            writer.println("<h2>Done!</h2>");
        }
    }

    private String getRandomNumber() throws IOException {
//        String query = String.format("param1=%s&param2=%s",
//                URLEncoder.encode("param1Value", "UTF-8"),
//                URLEncoder.encode("param1Value", "UTF-8"));

        URL url = new URL(SERVER_ADDRESS);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        connection.getContent();
        logger.info("Response code: " + connection.getResponseCode());
        return connection.getResponseMessage();
    }


}