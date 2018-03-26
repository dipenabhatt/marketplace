# How to Install And Run
    1. download the code from following project
    2. goto root directory(marketplace) of the project.
    3. Compile the project run following command
        - ./gradlew build
    4. Run the Project with Following Command
        - ./gradlew bootRun
    5. To Run directly as an Jar follow this command
        5.1 Run following command to build a Jar file
            - ./gradlew jar
        5.2 Run Following Command to Run the In built Server.
            - java -jar build/libs/marketplace-0.0.1-SNAPSHOT.jar

# API and End Points

    1. Create Project
        - Method: POST
        - Url : http://localhost:8080/v1/projects
        - Header: ContentType: application/json
        - Request Json :{
                	"name":"test project",
                	"description": "test description",
                	"maxBudgetAmount": "1000",
                	"bidingEndTime": "2018-03-26T15:00:00"
                }
        - ResponseCode: 201
        - ResponseBody: <projectId>
    2. List Project
        - Method: GET
        - Url : http://localhost:8080/v1/projects/{projectId}
        - Response Code: 200
        - Response Json:{
                             "name": "test project",
                             "description": "test description",
                             "maxBudgetAmount": 1000,
                             "bidingEndTime": "2018-03-26T15:00:00",
                             "minBid": {
                                     "bidAmount": "99.99",
                                     "buyerId": "1009"
                             }
                         }
    3. Create Bid
        - Method: POST
        - Url : http://localhost:8080/v1/projects/{projectId}/bids
        - Header: ContentType: application/json
        - Request Json: {
                        	"bidAmount":"99.99",
                        	"buyerId":"1009"
                        }
        - Response Code: 201

# Choice of technologies

    1. Jdk 1.8
    2. Spring Boot with JPA
        - Spring boot is light weight, ideal candidate to build quick Rest API and ready to use for production.
    3. H2 embedded DB to store the project data.
    4. In Memory ConcurrentHashMap Data Structure to Store Bids for faster minimum Bid calculation using MinHeap data Structure.