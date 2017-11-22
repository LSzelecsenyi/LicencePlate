package com.lszelecsenyi.licenceplates;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.nio.charset.Charset;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@EnableWebMvc
public class LicenceplatesApplicationTests {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void successfulMessageReceiving() throws Exception {
//		JSONObject message = new JSONObject();
//
//		message.put("id", new Integer(7655482));
//		message.put("username", "EggDice");
//		message.put("text", "How you doin'?");
//		message.put("timestamp", Long.valueOf("1322018752992"));
//
//		JSONObject client = new JSONObject();
//
//		client.put("id", "EggDice");
//
//		JSONObject wrapper = new JSONObject();
//		wrapper.put("message", message);
//		wrapper.put("client", client);
//
//		mockMvc.perform(post("/api/message/receive")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(wrapper.toString()))
//				.andExpect(status().isOk());
//	}

//	@Test
//    public void testGet() throws Exception {
//        JSONObject response = new JSONObject();
//
//        response.put("result", "ok");
//        response.put("data", new Iterable<>() {
//        })
//
//        JSONObject licenceplate = new JSONObject();
//
//        licenceplate.put("plate", "UWO-352");
//        licenceplate.put("carBrand", "Kia");
//        licenceplate.put("carModel", "Sedona");
//        licenceplate.put("color", "Pink");
//        licenceplate.put("year", "2010");
//
//    }

    @Test
    public void testLPBrand() throws Exception {
        this.mockMvc.perform(get("api/search")
                .param("brand", "kia")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.response.result").value("ok"))
                .andExpect(jsonPath("$.response.data.plate").value("UWO-352"))
                .andExpect(jsonPath("$.response.data.carBrand").value("Kia"))
                .andExpect(jsonPath("$.response.data.carModel").value("Sedona"))
                .andExpect(jsonPath("$.response.data.color").value("Pink"))
                .andExpect(jsonPath("$.response.data.year").value("2010"));
    }

    @Test
    public void grootTestWithMessage() throws Exception {
        mockMvc.perform(get("/api/search?brand=kia"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.result", is("ok")))
                .andExpect(jsonPath("$.data.plate", is("UWO-352")))
                .andExpect(jsonPath("$.data.carBrand", is("Kia")))
                .andExpect(jsonPath("$.data.carModel", is("Sedona")))
                .andExpect(jsonPath("$.data.color", is("Pink")))
                .andExpect(jsonPath("$.data.year", is("2010")));
    }

}

