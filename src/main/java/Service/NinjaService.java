package Service;

import Beans.Orders;

public interface NinjaService {

    boolean isRegisteredUser();

    String isAuthenticated();

    boolean login();

    String navigatesToOrderDetails();

    String printAirwayBill();

    boolean  createOrder(String orderDetails);

}
