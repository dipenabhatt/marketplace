package com.marketplace.project.v1.service;

import com.marketplace.bids.v1.BidService;
import com.marketplace.bids.v1.BidServiceImpl;
import com.marketplace.bids.v1.data.ProjectBid;
import com.marketplace.bids.v1.repository.BidRepository;
import com.marketplace.project.v1.Entity.ProjectEntity;
import com.marketplace.project.v1.data.Project;
import com.marketplace.project.v1.repository.ProjectRepository;
import common.ex.NotFoundException;
import common.ex.ValidationException;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

/**
 * Created by dipen.bhatt on 3/25/18.
 */

public class ProjectServiceImplTest extends TestCase {


    private ProjectRepository projectRepository;

    private BidRepository bidRepository;

    private BidService bidService;
    private ProjectService projectService;


    private void prepareFindProjectByIds(){

        bidRepository = mock(BidRepository.class);
        projectRepository = mock(ProjectRepository.class);
        bidService = new BidServiceImpl(projectRepository,bidRepository);
        projectService  = new ProjectServiceImpl(projectRepository,bidService);
    }

    private void prepareForCreateProject(){
        bidRepository = mock(BidRepository.class);
        projectRepository = mock(ProjectRepository.class);
        bidService = new BidServiceImpl(projectRepository,bidRepository);
        projectService  = new ProjectServiceImpl(projectRepository,bidService);
    }



    @Override
    protected  void setUp(){
        prepareFindProjectByIds();
        prepareForCreateProject();
    }


    @Test
    public void testFindProjectById() throws Exception {

        ProjectBid projectBid = new ProjectBid();
        projectBid.setBidAmount("100");
        projectBid.setBuyerId("1006");
        when(bidRepository.findMinBuyerBidByProjectId(1L)).thenReturn(projectBid);
        ProjectEntity projectEntity = new ProjectEntity("test","desc",1000D, LocalDateTime.now());
        projectEntity.setId(1L);
        Optional<ProjectEntity> projectEntity1 = Optional.of(projectEntity);
        when(projectRepository.findById(1L)).thenReturn(projectEntity1);

        Project project = projectService.findProjectById(1L);
        assertNotNull(project);
        boolean result = "test".equals(project.getName()) && "desc".equals(project.getDescription())
                            && new Double(1000D).equals(project.getMaxBudgetAmount())
                            && new String("100").equals(project.getMinBid().getBidAmount());
        assertEquals(true, result);
    }

    @Test
    public void testFindProjectByIdWithException() throws Exception {
        when(bidRepository.findMinBidByProjectId(1L)).thenReturn(100D);
        ProjectEntity projectEntity = new ProjectEntity("test","desc",1000D, LocalDateTime.now());
        Optional<ProjectEntity> projectEntity1 = Optional.of(projectEntity);
        when(projectRepository.findById(1L)).thenReturn(Optional.<ProjectEntity>empty());

        try {
            Project project = projectService.findProjectById(2L);
        }catch (Exception e){
            assertEquals(true,e instanceof NotFoundException);
        }
    }


    @Test
    public void testCreateProjectNullProject() throws Exception {
        try {
            projectService.createProject(null);
        }catch (Exception e){
            assertEquals(true,e instanceof ValidationException);
        }
    }

    @Test
    public void testCreateProjectNullProjectName() throws Exception {
        try {
            Project project = new Project(null,"desc",1000D,LocalDateTime.now(),null);
            Long aLong = projectService.createProject(project);
        }catch (Exception e){
            assertEquals(true,e instanceof ValidationException);
        }
    }
}