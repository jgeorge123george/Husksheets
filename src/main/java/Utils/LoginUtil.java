package Utils;

import Model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class LoginUtil {


  public Person login(String url, String userName, String password) {
    Person loginRequest = new Person();
    loginRequest.setUsername(userName);
    loginRequest.setPassword(password);

    response =

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    } else {
      // Handle non-2xx responses appropriately
      return null;
    }
  }
}
