# CatApiValidation
API Dataset Validation

Currently two of the tests will fail.

Data Validation Tests

1. isFactsAllValid
  Test: validates is the cat facts returened via "/facts" all match to the facts in the supplied dataset.
  Result: This will fail as the 5 random cat facts returned by the API are unlikely to match the existing dataset.

2. isAllFactsIdValid
  Test: Validates that all the facts in the dataset exist using "/facts/{_Id}"
  Result: This will fail as some facts in the dataset no longer exist in the online database.
  
 
