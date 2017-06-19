Scenario: mehrere Tickets werden gekauft
Given ein Ticket befindet sich im Warenkorb
When ein zweites Ticket hinzugefügt wird
Then sollte nur ein Eintrag für beide Tickets erscheinen mit der Anzahl 2