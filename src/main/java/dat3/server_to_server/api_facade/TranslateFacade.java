package dat3.server_to_server.api_facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


//ADD DTO CLASSES HERE

public class TranslateFacade {

  static final String URI = "https://api.openai.com/v1/completions";
  static final String API_KEY = "YOUR API KEY"

  RestTemplate restTemplate = new RestTemplate();

  public String translateText(String text,int maxTokens) throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("Authorization", "Bearer " + API_KEY);

    Map<String, Object> requestBody = new HashMap<>();
    requestBody.put("model", "text-davinci-003");
    requestBody.put("prompt", "translate this into danish: " + text);
    requestBody.put("temperature", 0.3);
    requestBody.put("max_tokens", maxTokens);
    requestBody.put("top_p", 1.0);
    requestBody.put("frequency_penalty", 0.0);
    requestBody.put("presence_penalty", 0.0);

    HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
    ResponseEntity<String> response = restTemplate.exchange("https://api.openai.com/v1/completions", HttpMethod.POST, entity, String.class);

    String responseBody = response.getBody();
    System.out.println(responseBody);

//    ObjectMapper om = new ObjectMapper();
//    TranslateDTO root = om.readValue(responseBody, TranslateDTO.class);
//    return root.choices.get(0).text;
     return "YOU SHOULD RETURN THE TRANSLATED TEXT";

  }

  public static void main(String[] args) throws JsonProcessingException {
    TranslateFacade facade = new TranslateFacade();
    String res = facade.translateText("Hello, how are you?", 20);
    System.out.println(res);
  }

}
