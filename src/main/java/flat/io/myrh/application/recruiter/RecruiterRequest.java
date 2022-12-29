package flat.io.myrh.application.recruiter;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RecruiterRequest {

    @Email(message = "Please enter a valid email")
    @NotNull
    @NotBlank
    private String email;

    private String phone;

    @NotNull
    @Size(min = 6,message = "Password should be longer than 6!")
    private String password;
    private String image;

    @NotNull(message = "Role is required!")
    private Long roleId;

    @NotNull(message = "Company name is required!")
    @NotBlank(message = "Company name can not be empty!")
    private String companyName;

}
