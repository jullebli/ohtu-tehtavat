Feature: User can log in with valid username/password-combination

    Scenario: user can login with correct password
       Given command login is selected
       When  username "pekka" and password "akkeptest8" are entered
       Then  system will respond with "logged in"

    Scenario: user can not login with incorrect password
        Given command login is selected
        When  username "pekka" and password "wrong" are entered
        Then  system will respond with "wrong username or password"

    Scenario: nonexistent user can not login to 
        Given command login is selected
        When  username "nonexisting" and password "anything" are entered
        Then  system will respond with "wrong username or password"
