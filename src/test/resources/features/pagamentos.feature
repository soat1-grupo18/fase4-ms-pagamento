Feature: Pagamentos

  Scenario: returns all payments that have status pendente
    Given there are pagamentos with status PENDENTE
    When the client requests the endpoint with status PENDENTE
    Then a list of PENDENTE pagamentos is returned to the client
