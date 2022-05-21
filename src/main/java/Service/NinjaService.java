package Service;

import Beans.Orders;

public interface NinjaService {

    boolean isRegisteredUser();

    boolean isAuthenticated();

    String getAccessToken();

    boolean login();

    String navigatesToOrderDetails();

    String printAirwayBill();

    boolean createOrder(String orderDetails);

}
