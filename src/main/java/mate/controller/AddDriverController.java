package mate.controller;

import mate.lib.Injector;
import mate.model.Driver;
import mate.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/drivers/add")
public class AddDriverController extends HttpServlet {
    Injector injector = Injector.getInstance("mate");
    DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/addDriver.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String licenseNumber = req.getParameter("license_number");
        if (!name.isEmpty() && !licenseNumber.isEmpty()) {
            Driver driver = new Driver();
            driver.setName(name);
            driver.setLicenseNumber(licenseNumber);
            driverService.create(driver);
            resp.sendRedirect(req.getContextPath() + "/drivers");
        } else {
            resp.sendRedirect(req.getContextPath() + "/drivers/add");
        }
    }
}
