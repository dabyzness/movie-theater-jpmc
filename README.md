# Introduction

This is a poorly written application, and we're expecting the candidate to greatly improve this code base.

## Instructions

- **Consider this to be your project! Feel free to make any changes**
- There are several deliberate design, code quality and test issues in the current code, they should be identified and resolved
- Implement the "New Requirements" below
- Keep it mind that code quality is very important
- Focus on testing, and feel free to bring in any testing strategies/frameworks you'd like to implement
- You're welcome to spend as much time as you like, however, we're expecting that this should take no more than 2 hours

## `movie-theater`

### Current Features

- Customer can make a reservation for the movie
  - And, system can calculate the ticket fee for customer's reservation
- Theater have a following discount rules
  - 20% discount for the special movie
  - $3 discount for the movie showing 1st of the day
  - $2 discount for the movie showing 2nd of the day
- System can display movie schedule with simple text format

## New Requirements

- [x] New discount rules; In addition to current rules
  - [x] Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
  - [x] Any movies showing on 7th, you'll get 1$ discount
  - [x] The discount amount applied only one if met multiple rules; biggest amount one
- [x] We want to print the movie schedule with simple text & json format

## Additional Features Implemented

- Commented code
- Formatted code with Prettier
- Added tests for classes
- Altered original printSchedule to make it more human readable

## Notes

- Was debating on creating a JSON object and then printing that, but I wasn't sure if we could add dependencies, so I just left it alone.
- I wanted to test the two print statements for theater, but because I couldn't make fewer showings to make it easier for me, I decided against it.
- The JSON was up to my discretion, so I chose values that would be most useful
  - Example: For runtime/duration, I made it an integer of minutes, instead of (1 hour 30 mins) like in the original printSchedule
- [Post-Submission] I didn't feel right leaving customer id as type String, so I changed it to int. It might make sense to use long as int can only go up to ~2.2 billion and I'm sure movie theaters like AMC have sold more tickets than that.
