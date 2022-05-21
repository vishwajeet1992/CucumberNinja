package Service.Impls;

import Beans.Orders;
import Pages.DashboardPage;
import Pages.Impls.DashboardPageImpl;
import Pages.Impls.LoginPageImpl;
import Pages.Impls.OrderDetailsImpl;
import Pages.LoginPage;
import Pages.OrderDetails;
import Service.NinjaService;

public class NinjaUiServiceImpl implements NinjaService {

    LoginPage loginPage = new LoginPageImpl();
    DashboardPage dashboardPage = new DashboardPageImpl();
    OrderDetails orderDetails = new OrderDetailsImpl();

    @Override
    public boolean isRegisteredUser() {
        throw new UnsupportedOperationException("API case only");
    }

    @Override
    public String isAuthenticated() {
        throw new UnsupportedOperationException("Only API method");
    }

    @Override
    public boolean login() {
        return loginPage.login();
    }

    @Override
    public String navigatesToOrderDetails() {
        return dashboardPage.navigatesToOrderDetails();
    }

    @Override
    public String printAirwayBill() {
        return orderDetails.printAirwayBill();
    }

    @Override
    public boolean createOrder(String orderDetails) {
        throw new UnsupportedOperationException("Only API cases");
    }
}
