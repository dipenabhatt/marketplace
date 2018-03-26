package com.marketplace.bids.v1;

import com.marketplace.bids.v1.data.ProjectBid;
import com.marketplace.bids.v1.repository.BidRepository;
import com.marketplace.project.v1.Entity.ProjectEntity;
import com.marketplace.project.v1.repository.ProjectRepository;
import common.ex.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by dipen.bhatt on 3/25/18.
 */
public class BidServiceImplTest extends TestCase {

    BidRepository bidRepository;

    ProjectRepository projectRepository;

    BidService bidService;

    @Override
    protected void setUp(){

        bidRepository = mock(BidRepository.class);
        projectRepository = mock(ProjectRepository.class);
        bidService = new BidServiceImpl(projectRepository,bidRepository);

        ProjectBid bid = new ProjectBid();
        bid.setBidAmount("1000");
        bid.setBuyerId("1006");
    }

    @Test
    public void testCreateBidForProjectNotFound() throws Exception {
        when(projectRepository.findById(1L)).thenReturn(Optional.<ProjectEntity>empty());
        ProjectBid bid = new ProjectBid();
        bid.setBidAmount("1000");
        bid.setBuyerId("1006");
        try {
            bidService.createBid("1", bid);
        }catch (Exception e){
            assertEquals(true,e instanceof ValidationException);
        }
    }

    @Test
    public void testCreateBidGreaterThenMaxBudget() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity("test","desc",1000D, LocalDateTime.now());
        Optional<ProjectEntity> projectEntity1 = Optional.of(projectEntity);
        when(projectRepository.findById(1L)).thenReturn(projectEntity1);
        ProjectBid bid = new ProjectBid();
        bid.setBidAmount("10000");
        bid.setBuyerId("1006");
        try {
            bidService.createBid("1", bid);
        }catch (Exception e){
            assertEquals(true,e instanceof ValidationException);
        }
    }


    @Test
    public void testCreateBidAfterLastAcceptingTime() throws Exception {
        LocalDateTime biddingTime = LocalDateTime.of(2018,03,20,15,00);
        ProjectEntity projectEntity = new ProjectEntity("test","desc",1000D, biddingTime);
        Optional<ProjectEntity> projectEntity1 = Optional.of(projectEntity);
        when(projectRepository.findById(1L)).thenReturn(projectEntity1);
        ProjectBid bid = new ProjectBid();
        bid.setBidAmount("999");
        bid.setBuyerId("1006");
        try {
            bidService.createBid("1", bid);
        }catch (Exception e){
            assertEquals(true,e instanceof ValidationException);
        }
    }


}