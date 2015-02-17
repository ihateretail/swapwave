package com.ootey.swapwave.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.ootey.swapwave.domain.Vendor;
import com.ootey.swapwave.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * REST controller for managing Vendor.
 */
@RestController
@RequestMapping("/api")
public class VendorResource {

    private final Logger log = LoggerFactory.getLogger(VendorResource.class);

    @Inject
    private VendorRepository vendorRepository;

    /**
     * POST  /vendors -> Create a new vendor.
     */
    @RequestMapping(value = "/vendors",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void create(@RequestBody Vendor vendor) {
        log.debug("REST request to save Vendor : {}", vendor);
        vendorRepository.save(vendor);
    }

    /**
     * GET  /vendors -> get all the vendors.
     */
    @RequestMapping(value = "/vendors",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Vendor> getAll() {
        log.debug("REST request to get all Vendors");
        return vendorRepository.findAll();
    }

    /**
     * GET  /vendors/:id -> get the "id" vendor.
     */
    @RequestMapping(value = "/vendors/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Vendor> get(@PathVariable Long id, HttpServletResponse response) {
        log.debug("REST request to get Vendor : {}", id);
        Vendor vendor = vendorRepository.findOne(id);
        if (vendor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vendor, HttpStatus.OK);
    }

    /**
     * DELETE  /vendors/:id -> delete the "id" vendor.
     */
    @RequestMapping(value = "/vendors/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public void delete(@PathVariable Long id) {
        log.debug("REST request to delete Vendor : {}", id);
        vendorRepository.delete(id);
    }
}
