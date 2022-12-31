package flat.io.myrh.application.offer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OfferRequest {

    @NotNull(message = "Offer title can not be empty!")
    @NotBlank(message = "Offer title can not be empty!")
    public String title;

    @NotBlank(message = "Description can not be empty!")
    @NotNull(message = "Description can not be empty!")
    public String description;

    @NotBlank(message = "City can not be empty!")
    @NotNull(message = "City can not be empty!")
    public String city;

    @Min(value = 0,message = "Salary can not be less then 0")
    public Double salary;

    @NotNull(message = "Please select education!")
    public Long educationId;

    @NotNull(message = "Please select a job title!")
    public Long jobTitleId;
}
