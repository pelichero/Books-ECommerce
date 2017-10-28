package br.edu.caelum.controller;

import br.edu.caelum.config.AppWebConfiguration;
import br.edu.caelum.config.DataSourceConfigurationTest;
import br.edu.caelum.config.JPAConfiguration;
import br.edu.caelum.config.SecurityConfiguration;
import br.edu.caelum.dao.ProductDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        AppWebConfiguration.class,
        JPAConfiguration.class,
        SecurityConfiguration.class,
        DataSourceConfigurationTest.class
})
@ActiveProfiles("test")
public class ProductsControllerTest {

    @Autowired
    private ProductDAO dao;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Transactional
    public void shouldListAllBooksInTheHome() throws Exception{
        this.mockMvc.perform(
                get("/products"))
                .andExpect(MockMvcResultMatchers
                        .forwardedUrl("/WEB-INF/views/products/list.jsp"));
    }

    @Test
    public void onlyAdminShouldAccessProductsForm() throws Exception{
        RequestPostProcessor processor = SecurityMockMvcRequestPostProcessors
                .user("comprador@gmail.com").password("123456").roles("COMPRADOR");
        this.mockMvc.perform(get("/products/form").with(processor))
                .andExpect(MockMvcResultMatchers.status().is(403));
    }

    /*
    public ResultMatcher attributeExists(final String... names){
        return new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                ModelAndView mav = getModelAndView(result);
                for(String name : names){
                    assetTrue("Model attribute '"+name+"' does not exists. ",
                    mav.getModel().get(name) != null;
                }
            }
        };
    }
    */
}
