Feature: Gets payments by status

Scenario: Returns all payments that have status PENDENTE
  Given: there are payments with status PENDENTE
  When: client requests \\/pagamentos\\?status=PENDENTE
  Then: a list of payments is returned
