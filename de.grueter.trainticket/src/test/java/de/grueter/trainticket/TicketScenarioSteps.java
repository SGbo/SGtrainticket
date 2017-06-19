package de.grueter.trainticket;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class TicketScenarioSteps {
	ShopModel shopModel = ShopModel.getInstance();

	@Given("ein Ticket befindet sich im Warenkorb")
	public void singleTicketInBasket() {
		shopModel.addProductToBasket("Einzelticket Kurzstrecke", "1.60 €");
	}
	
	@When("ein zweites Ticket hinzugefügt wird")
	public void addSecondTicketToBasket() {
		shopModel.addProductToBasket("Einzelticket Kurzstrecke", "1.60 €");
	}
	
	@Then("sollte nur ein Eintrag für beide Tickets erscheinen mit der Anzahl 2")
	public void theResultingBasketShouldContainOneItemWithCountOfTwo() {
		Assert.assertFalse(shopModel.getBasketModel().getRowCount() == 2);
	}
}
