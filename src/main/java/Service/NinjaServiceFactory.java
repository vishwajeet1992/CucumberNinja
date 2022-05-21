package Service;

import Service.Impls.NinjaApiServiceImpl;
import Service.Impls.NinjaUiServiceImpl;

public class NinjaServiceFactory {

    public static NinjaService getNinjaServiceByType(String automationFlow) throws Exception {
        switch (automationFlow) {
            case "UI":
                return new NinjaUiServiceImpl();
            case "API":
                return new NinjaApiServiceImpl();
            default:
                throw new Exception("Unidentified type");
        }
    }
}
