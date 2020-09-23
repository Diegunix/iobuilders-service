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
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
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

import com.diegunix.iobuilders.bean.PaymentBean;
import com.diegunix.iobuilders.bean.UserBean;
import com.diegunix.iobuilders.controller.dto.request.CreatePaymentRequestDto;
import com.diegunix.iobuilders.service.PaymentService;
import com.google.gson.Gson;

@RunWith(PowerMockRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureRestDocs
public class PaymentControllerRestDocsTest {

  private PaymentService paymentService;

  PaymentController controller;

  @Autowired
  private MockMvc mockMvc;

  @Rule
  public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

  public static FieldDescriptor[] paymentModel() {
    return new FieldDescriptor[] { fieldWithPath("id").description("The transaction id"),
        fieldWithPath("transaction").description("The amount of the transaction"),
        fieldWithPath("createdDate").description("The created date of the transaction"),
        subsectionWithPath("user").description("The user of the transaction") };
  }

  @Before
  public void before() {
    paymentService = mock(PaymentService.class);
    controller = new PaymentController(paymentService);
    mockMvc = MockMvcBuilders.standaloneSetup(controller)
        .alwaysDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", Preprocessors.preprocessRequest()))
        .apply(MockMvcRestDocumentation.documentationConfiguration(this.restDocumentation).uris().withScheme("http")
            .withHost("api.iobuilders.diegunix.com").withPort(9090).and().snippets().withDefaults(
                CliDocumentation.curlRequest(), HttpDocumentation.httpRequest(), HttpDocumentation.httpResponse()))
        .build();
  }

  @Test
  public void getAllTransactionByUser() throws Exception {
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    
    PaymentBean payment = PaymentBean.builder().id(2L).createdDate(LocalDateTime.now()).transaction(200.0).user(user).build(); 
    List<PaymentBean> listPayments = new ArrayList<>();
    listPayments.add(payment);

    when(paymentService.getAllTransactionByUser(anyLong())).thenReturn(listPayments);

    mockMvc
        .perform(RestDocumentationRequestBuilders.get("/transaction/user/{id}", 2).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            responseFields(fieldWithPath("[]").description("Array of Users")).andWithPrefix("[]", paymentModel()),
            pathParameters(parameterWithName("id").description("Id of the user"))));

  }

  @Test
  public void getAmountByUser() throws Exception {
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    
    PaymentBean payment = PaymentBean.builder().id(2L).createdDate(LocalDateTime.now()).transaction(200.0).user(user).build();
    
    when(paymentService.getAmountByUser(anyLong())).thenReturn(payment);

    mockMvc
        .perform(
            RestDocumentationRequestBuilders.get("/transaction/user/{id}/amount", 2).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()), responseFields(paymentModel()),
            pathParameters(parameterWithName("id").description("Id of the user"))));
  }

  @Test
  public void createTransaction() throws Exception {
    UserBean user = new UserBean();
    user.setId(2L);
    user.setLogin("Test");
    user.setCreatedDate(LocalDateTime.now());
    user.setFirstName("test");
    user.setLastName("test");
    user.setMail("test@test.com");
    
    PaymentBean payment = PaymentBean.builder().id(2L).createdDate(LocalDateTime.now()).transaction(200.0).user(user).build();
    
    when(paymentService.createTransaction(any(PaymentBean.class))).thenReturn(payment);
    CreatePaymentRequestDto input = CreatePaymentRequestDto.builder().transaction(200.0).build();
    String content = new Gson().toJson(input);
    mockMvc
        .perform(RestDocumentationRequestBuilders.post("/transaction/user/{id}", 2).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(content))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcRestDocumentation.document("{class-name}/{method-name}", preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()), responseFields(paymentModel()),
            pathParameters(parameterWithName("id").description("Id of the user"))));
  }

}