package Steps;

import Service.NinjaServiceFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;

import java.io.BufferedInputStream;
import java.net.URL;

public class AirwayBillSteps  {

    public String trackingID = "";
    public String sUrl = "";

    @Given("^Given Shipper opened the Ninja Dashboard login page$")
    public void navigateToLogin() throws Exception {
        Assert.assertTrue(NinjaServiceFactory.getNinjaServiceByType("UI").login());

    }

    @Then("^Shipper navigates to order details of first order$")
    public void navigateToOrderDetails() throws Exception {
        trackingID = NinjaServiceFactory.getNinjaServiceByType("UI").navigatesToOrderDetails();
    }

    @When("^User print airway bill from Orders page$")
    public void printAirwayBill() throws Exception {
        sUrl = NinjaServiceFactory.getNinjaServiceByType("UI").printAirwayBill();
    }

    @Then("^User verify tracking Id in pdf$")
    public void verifyIdInPdf() {


        Assert.assertTrue(verifyPDFContent(sUrl, trackingID));
    }


    public boolean verifyPDFContent(String strURL, String text) {



        String output = "";
        boolean flag = false;
        try {
            URL url = new URL(strURL);
            BufferedInputStream file = new BufferedInputStream(url.openStream());
            PDDocument document = null;
            try {
                document = PDDocument.load(file);
                output = new PDFTextStripper().getText(document);
                System.out.println(output);
            } finally {
                if (document != null) {
                    document.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (output.contains(text)) {
            flag = true;
        }
        return flag;


    }


}
