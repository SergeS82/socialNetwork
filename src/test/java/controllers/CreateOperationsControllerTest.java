package controllers;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.repository.AuthorRepository;
import com.example.springexample.repository.SubscriptionRepository;
import com.example.springexample.test.TestEntity;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@ActiveProfiles("test")
public class CreateOperationsControllerTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    private void init() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

//    @Test
//    @DisplayName("Загрузка справочника Авторов.")
//    void createSomeAuthors() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        TestEntity<Author, AuthorDto, AuthorRepository> testEntity = new TestEntity<>("/test_data/data.json", Author.class, AuthorDto.class, authorRepository);
//        testEntity.clearMapping();
//        for (Map.Entry<String,List<AuthorDto>> authorDtos : testEntity.getData().entrySet()) {
//            for (AuthorDto authorDto : authorDtos.getValue()) {
//                String oldId = testEntity.getOldId(authorDto);
//                String inContent = testEntity.makeInContent(authorDto);
//                ResultActions resultActions = null;
//                try {
//                    resultActions = mockMvc.perform(MockMvcRequestBuilders
//                            .post("/author")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(inContent));
//                    String outContent = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
//                    String newId = testEntity.getNewId(outContent);
//                    testEntity.addMapping(oldId, newId);
//                } catch (ServletException ex) {
//                } finally {
//                    System.out.println(inContent);
//                    Assertions.assertEquals(authorDtos.getKey(), (resultActions != null)?String.valueOf(resultActions.andReturn().getResponse().getStatus()):"000");
//                }
//            }
//        }
//        testEntity.writeMapperToFile();
//    }
//    @Test
//    @DisplayName("Загрузка справочника Подписок.")
//    void createSomeSubscriptions() throws JsonProcessingException {
//        common.subscriptionRepository.deleteAll();
//        for (Map.Entry<String,List<SubscriptionDto>> dtos : common.authorList.getSubscriptions().entrySet()) {
//            for (SubscriptionDto dto: dtos.getValue()) {
//                JsonNode oldJson = common.dtoMapper.convertValue(dto, JsonNode.class);
//                SubscriptionDto newDto = new SubscriptionDto();
//                newDto.setAuthor(common.authorMapping.get(oldJson.get("author").asText()));
//                newDto.setSubscription(common.authorMapping.get(oldJson.get("subscription").asText()));
//                String inContent = common.dtoMapper.writeValueAsString(newDto);
//                ResultActions resultActions = null;
//                try {
//                    resultActions = common.mockMvc.perform(MockMvcRequestBuilders
//                            .post("/subscription")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(inContent));
//                } catch (Exception e) {
//                    //e.printStackTrace();
//                } finally {
//                    System.out.println(inContent);
//                    Assertions.assertEquals(dtos.getKey(), (resultActions != null)?String.valueOf(resultActions.andReturn().getResponse().getStatus()):"000");
//                }
//
//            }
//        }
//    }

}