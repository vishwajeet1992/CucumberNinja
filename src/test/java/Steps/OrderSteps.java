package Steps;

import Beans.*;
import Service.NinjaServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dto.ContactDetailsDTO;
import dto.ParcelDTO;
import org.junit.Assert;

import java.util.List;

public class OrderSteps {
    private Orders orders;

    @Given("^The shipper is registered user in Ninja Van$")
    public void isRegisteredUser() throws Exception {
        Assert.assertTrue(NinjaServiceFactory.getNinjaServiceByType("API").isRegisteredUser());
    }

    @When("^Shipper verifies authentication$")
    public void isAuthenticated() throws Exception {
        Assert.assertTrue(NinjaServiceFactory.getNinjaServiceByType("API").isAuthenticated());

    }

    @And("^Shipper gets access token for creating order$")
    public void getAccessToken() throws Exception {
        NinjaServiceFactory.getNinjaServiceByType("API").getAccessToken();
    }


    @When("^Shipper selects (.*) service for (.*) with following details$")
    public void setFromDetails(String serviceLevel, String serviceType, DataTable dataTable) {
        List<ContactDetailsDTO> detailsDTO = dataTable.asList(ContactDetailsDTO.class);

        ContactDetailsDTO fromAddress = detailsDTO.get(0);
        orders = Orders.builder().serviceLevel(serviceLevel).serviceType(serviceType).build();
        ContactDetails contactDetails = ContactDetails.builder()
                .email(fromAddress.getEmail())
                .name(fromAddress.getName())
                .phoneNumber(fromAddress.getPhoneNumber())
                .address(Address.builder()
                        .address1(fromAddress.getAddress1())
                        .address2(fromAddress.getAddress2())
                        .country(fromAddress.getCountry())
                        .postcode(fromAddress.getPostcode())
                        .build()).build();
        orders.setFrom(contactDetails);
    }

    @And("^Shipper ships to following address$")
    public void setToDetails(DataTable dataTable) {
        List<ContactDetailsDTO> contactDetailsDTO = dataTable.asList(ContactDetailsDTO.class);
        ContactDetailsDTO toAddress = contactDetailsDTO.get(0);
        ContactDetails contactDetails = ContactDetails.builder()
                .email(toAddress.getEmail())
                .name(toAddress.getName())
                .phoneNumber(toAddress.getPhoneNumber())
                .address(Address.builder()
                        .address1(toAddress.getAddress1())
                        .address2(toAddress.getAddress2())
                        .country(toAddress.getCountry())
                        .postcode(toAddress.getPostcode())
                        .build()).build();
        orders.setTo(contactDetails);
    }

    @Then("^Shipper places orders with following details on (.*)$")
    public void createOrder(String pickupDate, DataTable dataTable) throws Exception {
        List<ParcelDTO> parcelDTOS = dataTable.asList(ParcelDTO.class);
        ParcelDTO parcelDTO = parcelDTOS.get(0);
        Parcel parcel = Parcel.builder()
                .pickupDate(pickupDate)
                .deliveryTimeSlot(TimeSlot.builder()
                        .startTime(parcelDTO.getDeliveryStartTime())
                        .endTime(parcelDTO.getDeliveryEndTime())
                        .timezone(parcelDTO.getDeliveryTimeZone()).build())
                .pickupTimeSlot(TimeSlot.builder()
                        .startTime(parcelDTO.getPickupStartTime())
                        .endTime(parcelDTO.getPickupEndTime())
                        .timezone(parcelDTO.getPickupTimeZone()).build())
                .pickupAddress(Address.builder()
                        .address1(parcelDTO.getAddress1())
                        .address2(parcelDTO.getAddress2())
                        .country(parcelDTO.getCountry())
                        .postcode(parcelDTO.getPostcode())
                        .build()).build();

        orders.setParcelJob(parcel);

        ObjectMapper objectMapper = new ObjectMapper();

        NinjaServiceFactory.getNinjaServiceByType("API").createOrder(objectMapper.writeValueAsString(orders));
    }

}
