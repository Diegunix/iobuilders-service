package com.diegunix.iobuilders.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.cli.CliDocumentation;
import org.springframework.restdocs.http.HttpDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.controller.dto.request.CreateUserRequestDto;
import com.diegunix.iobuilders.service.UserService;
import com.google.gson.Gson;

@RunWith(PowerMockRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class UserControllerRestDocsTest {

  private UserService userService;

  UserController controller;

  @Autowired
  private MockMvc mockMvc;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

  public static FieldDescriptor[] userModel() {
    return new FieldDescriptor[] { fieldWithPath("id").description("The user id"),
        fieldWithPath("login").description("The login of the user"),
        fieldWithPath("createdDate").description("The created date of the user"),
        fieldWithPath("updatedDate").description("The updated date of the user"),
        fieldWithPath("active").description("Indicates if the user is active"),
        fieldWithPath("mail").description("The email of the user"),
        fieldWithPath("firstName").description("The name of the user"),
        fieldWithPath("lastName").description("The surname of the user") };
  }

  @Before
  public void before() {
    userService = mock(UserService.class);
    controller = new UserController(userService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .alwaysDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", Preprocessors.preprocessRequest()))
        .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation).uris().withScheme("http")
            .withHost("api.iobuilders.diegunix.com").withPort(9090).and().snippets().withDefaults(
                CliDocumentation.curlRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse()))
        .build();
  }

  @Test
  public void getAllUsers() throws Exception {
    List<UserBean> listUsers = new ArrayList<>();
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    user.setActive(true);
    user.setUpdatedDate(LocalDateTime.now());
    listUsers.add(user);

    when(userService.getAllUser()).thenReturn(listUsers);

    mockMvc.perform(RestDocumentationRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            responseFields(fieldWithPath("[]").description("Array of Users")).andWithPrefix("[]", userModel())));

  }

  @Test
  public void getUser() throws Exception {
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    user.setActive(true);
    user.setUpdatedDate(LocalDateTime.now());
    when(userService.getUser(anyLong())).thenReturn(user);

    mockMvc.perform(RestDocumentationRequestBuilders.get("/user/{id}", 2).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()), responseFields(userModel()),
            pathParameters(parameterWithName("id").description("Id of the user"))));
  }

  @Test
  public void deleteUser() throws Exception {
    mockMvc.perform(RestDocumentationRequestBuilders.delete("/user/{id}", 2).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent()).andDo(MockMvcRestDocumentation.document(
            "{class-name}/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
  }

  @Test
  public void updateUser() throws Exception {
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    user.setActive(true);
    user.setUpdatedDate(LocalDateTime.now());
    when(userService.updateUser(any(UserBean.class))).thenReturn(user);
    CreateUserRequestDto input = CreateUserRequestDto.builder().firstName("test").lastName("test").mail("test@test.com")
        .build();
    String content = new Gson().toJson(input);
    mockMvc
        .perform(RestDocumentationRequestBuilders.put("/user/{id}", 2).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(content))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()), responseFields(userModel()),
            pathParameters(parameterWithName("id").description("Id of the user"))));
  }

  @Test
  public void createUser() throws Exception {
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    user.setActive(true);
    user.setUpdatedDate(LocalDateTime.now());
    when(userService.createUser(any(UserBean.class))).thenReturn(user);
    CreateUserRequestDto input = CreateUserRequestDto.builder().login("test").firstName("test").lastName("test")
        .mail("test@test.com").build();
    String content = new Gson().toJson(input);
    mockMvc
        .perform(RestDocumentationRequestBuilders.post("/user").accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(content))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()), responseFields(userModel())));
  }

}