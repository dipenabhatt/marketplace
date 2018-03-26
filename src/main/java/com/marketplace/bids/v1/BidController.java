package com.marketplace.bids.v1;

import com.marketplace.bids.v1.data.ProjectBid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dipen.bhatt on 3/25/18.
 */

@RestController
public class BidController {


    private BidService bidService;

    @Autowired
    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @RequestMapping(path = "/v1/projects/{projectId}/bids", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBid(@PathVariable("projectId") String projectId,@RequestBody ProjectBid bid){
        bidService.createBid(projectId,bid);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
