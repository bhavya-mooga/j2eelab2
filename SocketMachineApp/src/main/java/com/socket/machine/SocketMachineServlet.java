package com.socket.machine;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SocketMachineServlet
 */
@WebServlet("/SocketMachineServlet")
public class SocketMachineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SocketMachineServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String socketType = request.getParameter("socketType");
        String customer_name = request.getParameter("customer_name");
        String customer_email = request.getParameter("customer_email");
        String quantityStr = request.getParameter("quantity");

        // Validate input
        if (socketType == null || customer_name == null || customer_email == null || quantityStr == null || socketType.isEmpty() || customer_name.isEmpty() || customer_email.isEmpty() || quantityStr.isEmpty()) {
            response.getWriter().println("Error: All fields are required.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            response.getWriter().println("Error: Quantity must be a valid number.");
            return;
        }

        if (quantity <= 0) {
            response.getWriter().println("Error: Quantity must be greater than zero.");
            return;
        }

        // Business logic to calculate price
        double pricePerUnit = 0.0;
        switch (socketType) {
            case "TYPE_A":
                pricePerUnit = 5.0;
                break;
            case "TYPE_B":
                pricePerUnit = 7.0;
                break;
            case "TYPE_C":
                pricePerUnit = 10.0;
                break;
            default:
                response.getWriter().println("Error: Invalid socket type.");
                return;
        }

        double totalPrice = pricePerUnit * quantity;

        // Generate response
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Socket Machine Quote</title></head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>Socket Machine Quote</h1>");
        response.getWriter().println("<p>Socket Type: " + socketType + "</p>");
        response.getWriter().println("<p>Quantity: " + quantity + "</p>");
        response.getWriter().println("<p>Customer Name: " + customer_name + "</p>");
        response.getWriter().println("<p>Customer Email: " + customer_email + "</p>");
        response.getWriter().println("<p>Total Price: $" + totalPrice + "</p>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
