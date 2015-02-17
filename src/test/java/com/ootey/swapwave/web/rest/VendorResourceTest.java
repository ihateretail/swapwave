package com.ootey.swapwave.web.rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.List;

import com.ootey.swapwave.Application;
import com.ootey.swapwave.domain.Vendor;
import com.ootey.swapwave.repository.VendorRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the VendorResource REST controller.
 *
 * @see VendorResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class VendorResourceTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final String DEFAULT_NAME = "SAMPLE_TEXT";
    private static final String UPDATED_NAME = "UPDATED_TEXT";
    private static final String DEFAULT_DBA = "SAMPLE_TEXT";
    private static final String UPDATED_DBA = "UPDATED_TEXT";

    private static final Long DEFAULT_FEDERAL_ID = 0L;
    private static final Long UPDATED_FEDERAL_ID = 1L;

    private static final DateTime DEFAULT_YEAR_ESTABLISHED = new DateTime(0L, DateTimeZone.UTC);
    private static final DateTime UPDATED_YEAR_ESTABLISHED = new DateTime(DateTimeZone.UTC).withMillisOfSecond(0);
    private static final String DEFAULT_YEAR_ESTABLISHED_STR = dateTimeFormatter.print(DEFAULT_YEAR_ESTABLISHED);
    private static final String DEFAULT_OWNERSHIP_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_OWNERSHIP_TYPE = "UPDATED_TEXT";
    private static final String DEFAULT_OWNERSHIP_STATES = "SAMPLE_TEXT";
    private static final String UPDATED_OWNERSHIP_STATES = "UPDATED_TEXT";
    private static final String DEFAULT_BUSINESS_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_BUSINESS_TYPE = "UPDATED_TEXT";
    private static final String DEFAULT_LOCATION = "SAMPLE_TEXT";
    private static final String UPDATED_LOCATION = "UPDATED_TEXT";

    private static final Integer DEFAULT_NUMBEROF_LOCATIONS = 0;
    private static final Integer UPDATED_NUMBEROF_LOCATIONS = 1;

    private static final Integer DEFAULT_YEARS_IN_PRESENT_LOC = 0;
    private static final Integer UPDATED_YEARS_IN_PRESENT_LOC = 1;

    private static final Long DEFAULT_JBT_ID = 0L;
    private static final Long UPDATED_JBT_ID = 1L;

    private static final Integer DEFAULT_JBT_RATING = 0;
    private static final Integer UPDATED_JBT_RATING = 1;

    private static final Integer DEFAULT_BBBRATING = 0;
    private static final Integer UPDATED_BBBRATING = 1;

    @Inject
    private VendorRepository vendorRepository;

    private MockMvc restVendorMockMvc;

    private Vendor vendor;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        VendorResource vendorResource = new VendorResource();
        ReflectionTestUtils.setField(vendorResource, "vendorRepository", vendorRepository);
        this.restVendorMockMvc = MockMvcBuilders.standaloneSetup(vendorResource).build();
    }

    @Before
    public void initTest() {
        vendor = new Vendor();
        vendor.setName(DEFAULT_NAME);
        vendor.setDba(DEFAULT_DBA);
        vendor.setFederalId(DEFAULT_FEDERAL_ID);
        vendor.setYearEstablished(DEFAULT_YEAR_ESTABLISHED);
        vendor.setOwnershipType(DEFAULT_OWNERSHIP_TYPE);
        vendor.setOwnershipStates(DEFAULT_OWNERSHIP_STATES);
        vendor.setBusinessType(DEFAULT_BUSINESS_TYPE);
        vendor.setLocation(DEFAULT_LOCATION);
        vendor.setNumberofLocations(DEFAULT_NUMBEROF_LOCATIONS);
        vendor.setYearsInPresentLoc(DEFAULT_YEARS_IN_PRESENT_LOC);
        vendor.setJbtId(DEFAULT_JBT_ID);
        vendor.setJbtRating(DEFAULT_JBT_RATING);
        vendor.setBbbrating(DEFAULT_BBBRATING);
    }

    @Test
    @Transactional
    public void createVendor() throws Exception {
        // Validate the database is empty
        assertThat(vendorRepository.findAll()).hasSize(0);

        // Create the Vendor
        restVendorMockMvc.perform(post("/api/vendors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(vendor)))
                .andExpect(status().isOk());

        // Validate the Vendor in the database
        List<Vendor> vendors = vendorRepository.findAll();
        assertThat(vendors).hasSize(1);
        Vendor testVendor = vendors.iterator().next();
        assertThat(testVendor.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testVendor.getDba()).isEqualTo(DEFAULT_DBA);
        assertThat(testVendor.getFederalId()).isEqualTo(DEFAULT_FEDERAL_ID);
        assertThat(testVendor.getYearEstablished().toDateTime(DateTimeZone.UTC)).isEqualTo(DEFAULT_YEAR_ESTABLISHED);
        assertThat(testVendor.getOwnershipType()).isEqualTo(DEFAULT_OWNERSHIP_TYPE);
        assertThat(testVendor.getOwnershipStates()).isEqualTo(DEFAULT_OWNERSHIP_STATES);
        assertThat(testVendor.getBusinessType()).isEqualTo(DEFAULT_BUSINESS_TYPE);
        assertThat(testVendor.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testVendor.getNumberofLocations()).isEqualTo(DEFAULT_NUMBEROF_LOCATIONS);
        assertThat(testVendor.getYearsInPresentLoc()).isEqualTo(DEFAULT_YEARS_IN_PRESENT_LOC);
        assertThat(testVendor.getJbtId()).isEqualTo(DEFAULT_JBT_ID);
        assertThat(testVendor.getJbtRating()).isEqualTo(DEFAULT_JBT_RATING);
        assertThat(testVendor.getBbbrating()).isEqualTo(DEFAULT_BBBRATING);
    }

    @Test
    @Transactional
    public void getAllVendors() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get all the vendors
        restVendorMockMvc.perform(get("/api/vendors"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id").value(vendor.getId().intValue()))
                .andExpect(jsonPath("$.[0].name").value(DEFAULT_NAME.toString()))
                .andExpect(jsonPath("$.[0].dba").value(DEFAULT_DBA.toString()))
                .andExpect(jsonPath("$.[0].federalId").value(DEFAULT_FEDERAL_ID.intValue()))
                .andExpect(jsonPath("$.[0].yearEstablished").value(DEFAULT_YEAR_ESTABLISHED_STR))
                .andExpect(jsonPath("$.[0].ownershipType").value(DEFAULT_OWNERSHIP_TYPE.toString()))
                .andExpect(jsonPath("$.[0].ownershipStates").value(DEFAULT_OWNERSHIP_STATES.toString()))
                .andExpect(jsonPath("$.[0].businessType").value(DEFAULT_BUSINESS_TYPE.toString()))
                .andExpect(jsonPath("$.[0].location").value(DEFAULT_LOCATION.toString()))
                .andExpect(jsonPath("$.[0].numberofLocations").value(DEFAULT_NUMBEROF_LOCATIONS))
                .andExpect(jsonPath("$.[0].yearsInPresentLoc").value(DEFAULT_YEARS_IN_PRESENT_LOC))
                .andExpect(jsonPath("$.[0].jbtId").value(DEFAULT_JBT_ID.intValue()))
                .andExpect(jsonPath("$.[0].jbtRating").value(DEFAULT_JBT_RATING))
                .andExpect(jsonPath("$.[0].bbbrating").value(DEFAULT_BBBRATING));
    }

    @Test
    @Transactional
    public void getVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", vendor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(vendor.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.dba").value(DEFAULT_DBA.toString()))
            .andExpect(jsonPath("$.federalId").value(DEFAULT_FEDERAL_ID.intValue()))
            .andExpect(jsonPath("$.yearEstablished").value(DEFAULT_YEAR_ESTABLISHED_STR))
            .andExpect(jsonPath("$.ownershipType").value(DEFAULT_OWNERSHIP_TYPE.toString()))
            .andExpect(jsonPath("$.ownershipStates").value(DEFAULT_OWNERSHIP_STATES.toString()))
            .andExpect(jsonPath("$.businessType").value(DEFAULT_BUSINESS_TYPE.toString()))
            .andExpect(jsonPath("$.location").value(DEFAULT_LOCATION.toString()))
            .andExpect(jsonPath("$.numberofLocations").value(DEFAULT_NUMBEROF_LOCATIONS))
            .andExpect(jsonPath("$.yearsInPresentLoc").value(DEFAULT_YEARS_IN_PRESENT_LOC))
            .andExpect(jsonPath("$.jbtId").value(DEFAULT_JBT_ID.intValue()))
            .andExpect(jsonPath("$.jbtRating").value(DEFAULT_JBT_RATING))
            .andExpect(jsonPath("$.bbbrating").value(DEFAULT_BBBRATING));
    }

    @Test
    @Transactional
    public void getNonExistingVendor() throws Exception {
        // Get the vendor
        restVendorMockMvc.perform(get("/api/vendors/{id}", 1L))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Update the vendor
        vendor.setName(UPDATED_NAME);
        vendor.setDba(UPDATED_DBA);
        vendor.setFederalId(UPDATED_FEDERAL_ID);
        vendor.setYearEstablished(UPDATED_YEAR_ESTABLISHED);
        vendor.setOwnershipType(UPDATED_OWNERSHIP_TYPE);
        vendor.setOwnershipStates(UPDATED_OWNERSHIP_STATES);
        vendor.setBusinessType(UPDATED_BUSINESS_TYPE);
        vendor.setLocation(UPDATED_LOCATION);
        vendor.setNumberofLocations(UPDATED_NUMBEROF_LOCATIONS);
        vendor.setYearsInPresentLoc(UPDATED_YEARS_IN_PRESENT_LOC);
        vendor.setJbtId(UPDATED_JBT_ID);
        vendor.setJbtRating(UPDATED_JBT_RATING);
        vendor.setBbbrating(UPDATED_BBBRATING);
        restVendorMockMvc.perform(post("/api/vendors")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(vendor)))
                .andExpect(status().isOk());

        // Validate the Vendor in the database
        List<Vendor> vendors = vendorRepository.findAll();
        assertThat(vendors).hasSize(1);
        Vendor testVendor = vendors.iterator().next();
        assertThat(testVendor.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testVendor.getDba()).isEqualTo(UPDATED_DBA);
        assertThat(testVendor.getFederalId()).isEqualTo(UPDATED_FEDERAL_ID);
        assertThat(testVendor.getYearEstablished().toDateTime(DateTimeZone.UTC)).isEqualTo(UPDATED_YEAR_ESTABLISHED);
        assertThat(testVendor.getOwnershipType()).isEqualTo(UPDATED_OWNERSHIP_TYPE);
        assertThat(testVendor.getOwnershipStates()).isEqualTo(UPDATED_OWNERSHIP_STATES);
        assertThat(testVendor.getBusinessType()).isEqualTo(UPDATED_BUSINESS_TYPE);
        assertThat(testVendor.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testVendor.getNumberofLocations()).isEqualTo(UPDATED_NUMBEROF_LOCATIONS);
        assertThat(testVendor.getYearsInPresentLoc()).isEqualTo(UPDATED_YEARS_IN_PRESENT_LOC);
        assertThat(testVendor.getJbtId()).isEqualTo(UPDATED_JBT_ID);
        assertThat(testVendor.getJbtRating()).isEqualTo(UPDATED_JBT_RATING);
        assertThat(testVendor.getBbbrating()).isEqualTo(UPDATED_BBBRATING);
    }

    @Test
    @Transactional
    public void deleteVendor() throws Exception {
        // Initialize the database
        vendorRepository.saveAndFlush(vendor);

        // Get the vendor
        restVendorMockMvc.perform(delete("/api/vendors/{id}", vendor.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Vendor> vendors = vendorRepository.findAll();
        assertThat(vendors).hasSize(0);
    }
}
