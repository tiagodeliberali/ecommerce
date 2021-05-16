package br.com.tiagodeliberali.ecommerce.application.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.Assertions.assertThat;

public class ResponseBodyMatchers {
    private ObjectMapper objectMapper = new ObjectMapper();

    public ResultMatcher containsError(
            String type,
            String title,
            String details) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();

            assertThat(json)
                    .contains("https://tiagodeliberali.com.br/ecommerce/store/" + type)
                    .contains(title)
                    .contains(details);
        };
    }

    public <T> ResultMatcher containsObjectAsJson(
            Object expectedObject) {
        return mvcResult -> {
            String json = mvcResult.getResponse().getContentAsString();
            assertThat(json).isEqualToIgnoringWhitespace(
                    objectMapper.writeValueAsString(expectedObject));
        };
    }

    static ResponseBodyMatchers responseBody() {
        return new ResponseBodyMatchers();
    }
}
