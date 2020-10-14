# Search Volume Estimation Service

The services uses the basics of `Binary Search` algorithm to find the occurrences of keyword while searching with 
various substrings of the keyword. The keyword splitting algorithm is based on binary search logic of finding mid 
and the decision to move mid forward or backward will depend on the availability of keyword in the search results of the substring(0 to mid).

The estimation is performed using the below formulae:

    (totalCountOfMatchingSearchResults /
    (totalNoOfLettersInKeyword * noOfSearchResultsFromAutoCompletionAPI) ) * 100
    
    Where 
        totalCountMatchingSearchResults        : It is the cumulative sum of each search  result strings 
                                                 which contains the keyword.
        noOfSearchResultsFromAutoCompletionAPI : No of maximum search result from single call to 
                                                AutoCompletion endpoint.      

AutoCompletionAPI Used is https://completion.amazon.com/search/complete

The algorithm runs in **O(log n)** time complexity and constant space
    
## API Details

    URL: http://localhost:8080/search-volume/estimate?keyword=bat
    Response: {
              "keyword": "bat",
              "score": 40
              }
    HTTP Status: 200         
    HTTP METHOD: GET

### Swagger UI http://localhost:8080/swagger-ui.html#!/search-volume-estimation-controller/estimateUsingGET

### How to Run
 - Execute the below command from the project base location in your terminal
   
        ./mvnw spring-boot:run