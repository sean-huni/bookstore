package xyz.lib.bookstore.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import xyz.lib.bookstore.dto.BookDTO;
import xyz.lib.bookstore.enums.CategoryEnum;
import xyz.lib.bookstore.enums.FormatEnum;
import xyz.lib.bookstore.util.AuthenticationUtil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.controller
 * USER      : sean
 * DATE      : 31-Mon-Dec-2018
 * TIME      : 09:55
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BookIntegrationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookIntegrationTest.class);
    private static Long testID = 0L;

     /*
        ...INTEGRATION TESTS...
     */

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void pretest() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void aGivenNoValidCategoryEntryInNewBookDTO_whenSavingNewBook_thenFailCategoryValidationWithClientError() throws Exception {

        final Gson gson = new Gson();
        final BookDTO bookDTO = new BookDTO();

        bookDTO.setTitle("Demo Book");

        final String jsonString = gson.toJson(bookDTO);

        LOGGER.debug("JSON String HTTP.POST: \n{}", jsonString);

        MvcResult mvcResult = mockMvc.perform(post("/v1/books")
                .content(jsonString)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        assertFalse("Must NOT contain 'Title' validation error.", mvcResult.getResolvedException().getMessage().contains("Title of the book is required"));
        assertTrue("Must  contain 'Category' validation error.", mvcResult.getResolvedException().getMessage().contains("Invalid option for the category field"));
    }

    @Test
    public void bGivenNoTitleEntryInNewBookDTO_whenSavingNewBook_thenFailTitleValidationWithClientError() throws Exception {

        final Gson gson = new Gson();
        final BookDTO bookDTO = new BookDTO();

        bookDTO.setCategory(CategoryEnum.PROGRAMMING.toString());

        final String jsonString = gson.toJson(bookDTO);

        LOGGER.debug("JSON String HTTP.POST: \n{}", jsonString);

        MvcResult mvcResult = mockMvc.perform(post("/v1/books")
                .content(jsonString)
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().is4xxClientError())
                .andDo(print())
                .andReturn();

        assertTrue("Must contain 'Title' validation error.", mvcResult.getResolvedException().getMessage().contains("Title of the book is required"));
        assertFalse("Must NOT contain 'Category' validation error.", mvcResult.getResolvedException().getMessage().contains("Invalid option for the category field"));
    }

    @Test
    public void cGivenValidBook_whenCreatingNewBook_thenSuccess() throws Exception {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        final BookDTO bookDTO = new BookDTO();

        newTestBook(bookDTO);

        final String jsonString = gson.toJson(bookDTO);

        LOGGER.debug("JSON String HTTP.POST: \n{}", jsonString);

        MvcResult mvcResult = mockMvc.perform(post("/v1/books")
                .content(jsonString)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn();

        final String resp = mvcResult.getResponse().getContentAsString();
        final BookDTO bookDTOResp = gson.fromJson(resp, BookDTO.class);

        assertNotNull("Must return the id of the book", bookDTOResp.getId());
        assertTrue("ID of the book must be a positive Long.", bookDTOResp.getId() > 0);

        testID = bookDTOResp.getId();
        LOGGER.info("Resp CREATE-TEST-ID: " + testID);
    }

    @Test
    public void eGivenBookId_whenUpdatingBook_thenSuccess() throws Exception {
        final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        BookDTO bookDTO = new BookDTO();
        newTestBook(bookDTO);

        bookDTO.setActive(false);
        bookDTO.setQuantity(0);

        final String jsonString = gson.toJson(bookDTO);

        LOGGER.debug("JSON String HTTP.PUT: \n{}", jsonString);

        LOGGER.info("Req UPDATE-TEST-ID: " + testID);

        mockMvc.perform(put("/v1/books/" + testID)
                .content(jsonString)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.active").value(false))
                .andExpect(jsonPath("$.quantity").value(0));
    }

    @Test
    public void dGivenBookTitle_whenSearchingBookTitleByParam_thenSuccess() throws Exception {
//
        try {
            AuthenticationUtil.configureAuthentication("ANONYMOUS");
            mockMvc.perform(get("/v1/books?all-by-title=OOP")
                    .contentType("application/json;charset=UTF-8"))
                    .andDo(print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$[0]").isNotEmpty())
                    .andExpect(jsonPath("$[0].id").isNumber())
                    .andExpect(jsonPath("$[0].id").value(testID))
                    .andExpect(jsonPath("$[0].title").value("OOP Java"));

        } finally {
            AuthenticationUtil.clearAuthentication();
        }


    }

    @Test
    public void fGivenBookIdAndImgFile_whenUploadingImg_thenSuccess() throws Exception {
        // Mock Request
        File file = new File("/home/sean/Pictures/a5.png");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        final byte[] bytes;
        try (inputStream) {
            bytes = inputStream.readAllBytes();
        }

        MockMultipartFile jsonFile = new MockMultipartFile("image", bytes);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/books/" + testID + "/images")
                .file("multipart", jsonFile.getBytes())
                .characterEncoding("UTF-8"))
                .andExpect(status().isCreated());
    }

    @Test
    public void gGivenBookId_whenDeleteBook_thenSuccess() throws Exception {
        mockMvc.perform(delete("/v1/books/" + testID)
                .contentType("application/json;charset=UTF-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    private void newTestBook(BookDTO bookDTO) {
        bookDTO.setTitle("OOP Java");
        bookDTO.setAuthor("Sean Huni");
        bookDTO.setPublisher("S.A Publisher");
        bookDTO.setDatePublished(new Date());
        bookDTO.setCategory(CategoryEnum.ENGINEERING.name());
        bookDTO.setPages(10);
        bookDTO.setFormat(FormatEnum.PAPERBACK.name());
        bookDTO.setIsbn("987-5-2456-4561-3");
        bookDTO.setWeight(0.2);
        bookDTO.setListPrice(new BigDecimal(23.23));
        bookDTO.setOurPrice(new BigDecimal(20.23));
        bookDTO.setActive(true);
        bookDTO.setDescription("This is a demo book for testing.");
        bookDTO.setQuantity(12);
    }


}