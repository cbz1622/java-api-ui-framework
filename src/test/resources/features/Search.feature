Feature: Search functionality

  Scenario Outline: Search the item and verify product list page for matching results
    Given User access the Home page
    When User enters query string as <query_string>
    Then the product list page should include the appropriate result <result>

    Examples:
      | query_string             | result          |
      | "iphone"                 | "Apple iPhone"  | # Valid input
      | "dafgjkadgkmrhayzklzyrh" | "No results for"| # invalid input