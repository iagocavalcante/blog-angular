package com.estacio.pos.blog.web.rest;

import com.estacio.pos.blog.BlogCmsApp;

import com.estacio.pos.blog.domain.Info;
import com.estacio.pos.blog.repository.InfoRepository;
import com.estacio.pos.blog.service.InfoService;
import com.estacio.pos.blog.service.dto.InfoDTO;
import com.estacio.pos.blog.service.mapper.InfoMapper;
import com.estacio.pos.blog.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the InfoResource REST controller.
 *
 * @see InfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BlogCmsApp.class)
public class InfoResourceIntTest {

    private static final String DEFAULT_SOBRE = "AAAAAAAAAA";
    private static final String UPDATED_SOBRE = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final String DEFAULT_FACEBOOK = "AAAAAAAAAA";
    private static final String UPDATED_FACEBOOK = "BBBBBBBBBB";

    private static final String DEFAULT_TWITTER = "AAAAAAAAAA";
    private static final String UPDATED_TWITTER = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR = "BBBBBBBBBB";

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private InfoMapper infoMapper;

    @Autowired
    private InfoService infoService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInfoMockMvc;

    private Info info;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InfoResource infoResource = new InfoResource(infoService);
        this.restInfoMockMvc = MockMvcBuilders.standaloneSetup(infoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Info createEntity(EntityManager em) {
        Info info = new Info()
            .sobre(DEFAULT_SOBRE)
            .linkedin(DEFAULT_LINKEDIN)
            .facebook(DEFAULT_FACEBOOK)
            .twitter(DEFAULT_TWITTER)
            .avatar(DEFAULT_AVATAR);
        return info;
    }

    @Before
    public void initTest() {
        info = createEntity(em);
    }

    @Test
    @Transactional
    public void createInfo() throws Exception {
        int databaseSizeBeforeCreate = infoRepository.findAll().size();

        // Create the Info
        InfoDTO infoDTO = infoMapper.infoToInfoDTO(info);
        restInfoMockMvc.perform(post("/api/infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(infoDTO)))
            .andExpect(status().isCreated());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeCreate + 1);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getSobre()).isEqualTo(DEFAULT_SOBRE);
        assertThat(testInfo.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testInfo.getFacebook()).isEqualTo(DEFAULT_FACEBOOK);
        assertThat(testInfo.getTwitter()).isEqualTo(DEFAULT_TWITTER);
        assertThat(testInfo.getAvatar()).isEqualTo(DEFAULT_AVATAR);
    }

    @Test
    @Transactional
    public void createInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = infoRepository.findAll().size();

        // Create the Info with an existing ID
        info.setId(1L);
        InfoDTO infoDTO = infoMapper.infoToInfoDTO(info);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInfoMockMvc.perform(post("/api/infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(infoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInfos() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

        // Get all the infoList
        restInfoMockMvc.perform(get("/api/infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(info.getId().intValue())))
            .andExpect(jsonPath("$.[*].sobre").value(hasItem(DEFAULT_SOBRE.toString())))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN.toString())))
            .andExpect(jsonPath("$.[*].facebook").value(hasItem(DEFAULT_FACEBOOK.toString())))
            .andExpect(jsonPath("$.[*].twitter").value(hasItem(DEFAULT_TWITTER.toString())))
            .andExpect(jsonPath("$.[*].avatar").value(hasItem(DEFAULT_AVATAR.toString())));
    }

    @Test
    @Transactional
    public void getInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

        // Get the info
        restInfoMockMvc.perform(get("/api/infos/{id}", info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(info.getId().intValue()))
            .andExpect(jsonPath("$.sobre").value(DEFAULT_SOBRE.toString()))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN.toString()))
            .andExpect(jsonPath("$.facebook").value(DEFAULT_FACEBOOK.toString()))
            .andExpect(jsonPath("$.twitter").value(DEFAULT_TWITTER.toString()))
            .andExpect(jsonPath("$.avatar").value(DEFAULT_AVATAR.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInfo() throws Exception {
        // Get the info
        restInfoMockMvc.perform(get("/api/infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);
        int databaseSizeBeforeUpdate = infoRepository.findAll().size();

        // Update the info
        Info updatedInfo = infoRepository.findOne(info.getId());
        updatedInfo
            .sobre(UPDATED_SOBRE)
            .linkedin(UPDATED_LINKEDIN)
            .facebook(UPDATED_FACEBOOK)
            .twitter(UPDATED_TWITTER)
            .avatar(UPDATED_AVATAR);
        InfoDTO infoDTO = infoMapper.infoToInfoDTO(updatedInfo);

        restInfoMockMvc.perform(put("/api/infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(infoDTO)))
            .andExpect(status().isOk());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate);
        Info testInfo = infoList.get(infoList.size() - 1);
        assertThat(testInfo.getSobre()).isEqualTo(UPDATED_SOBRE);
        assertThat(testInfo.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testInfo.getFacebook()).isEqualTo(UPDATED_FACEBOOK);
        assertThat(testInfo.getTwitter()).isEqualTo(UPDATED_TWITTER);
        assertThat(testInfo.getAvatar()).isEqualTo(UPDATED_AVATAR);
    }

    @Test
    @Transactional
    public void updateNonExistingInfo() throws Exception {
        int databaseSizeBeforeUpdate = infoRepository.findAll().size();

        // Create the Info
        InfoDTO infoDTO = infoMapper.infoToInfoDTO(info);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInfoMockMvc.perform(put("/api/infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(infoDTO)))
            .andExpect(status().isCreated());

        // Validate the Info in the database
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);
        int databaseSizeBeforeDelete = infoRepository.findAll().size();

        // Get the info
        restInfoMockMvc.perform(delete("/api/infos/{id}", info.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Info> infoList = infoRepository.findAll();
        assertThat(infoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Info.class);
    }
}
