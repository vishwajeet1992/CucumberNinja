package Steps;

import Beans.*;
import Service.NinjaServiceFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.ContactDetailsDTO;
import dto.ParcelDTO;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class OrderSteps {
    private Orders orders;

    @Given("^The shipper is registered user in Ninja Van$")
    public void isRegisteredUser() throws Exception {
        Assert.assertTrue(NinjaServiceFactory.getNinjaServiceByType("API").isRegisteredUser());
    }

    @When("^Shipper authenticate to NinjaVan system$")
    public void isAuthenticated() throws Exception {
        NinjaServiceFactory.getNinjaServiceByType("API").isAuthenticated();

    }

    @When("^Shipper selects (.*) service for '(.*) with following details$")
    public void setFromDetails(String serviceLevel, String serviceType, DataTable dataTable) {

        List<ContactDetailsDTO> fromOrdersList = dataTable.asList(ContactDetailsDTO.class);
        ContactDetailsDTO fromAddress = fromOrdersList.get(0);
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
    public void setToDetails(List<ContactDetailsDTO> contactDetailsDTO) {
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
    public void createOrder(String pickupDate, List<ParcelDTO> parcelDTOS) throws JsonProcessingException {
        ParcelDTO parcelDTO = parcelDTOS.get(0);
        Parcel parcel = Parcel.builder()
                .pickupDate(pickupDate)
                .deliveryTimeSlot(TimeSlot.builder()
                        .startTime(parcelDTO.getDeliveryStartTime())
                        .endTime(parcelDTO.getDeliveryEndTime())
                        .timeZone(parcelDTO.getDeliveryTimeZone()).build())
                .pickupTimeSlot(TimeSlot.builder()
                        .startTime(parcelDTO.getPickupStartTime())
                        .endTime(parcelDTO.getPickupEndTime())
                        .timeZone(parcelDTO.getPickupTimeZone()).build())
                .pickupAddress(Address.builder()
                        .address1(parcelDTO.getAddress1())
                        .address2(parcelDTO.getAddress2())
                        .country(parcelDTO.getCountry())
                        .postcode(parcelDTO.getPostcode())
                        .build()).build();

        orders.setParcelJob(parcel);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString(orders);

    }

}
